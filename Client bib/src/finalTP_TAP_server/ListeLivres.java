package finalTP_TAP_server;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class ListeLivres extends JFrame {
    private LivreController livreController;
    private JTable livresTable;

    public ListeLivres(LivreController livreController) {
        this.livreController = livreController;

        setTitle("Liste des Livres");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setSize(1000, 600);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\PC\\OneDrive\\Images\\images des animaux\\logoA.jpg");
        setIconImage(icon);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        setLocationRelativeTo(null);
        setResizable(false);
        JMenu accueilMenu = new JMenu("Accueil");
        menuBar.add(accueilMenu);
        JMenuItem accueilItem = new JMenuItem("Accueil");
        accueilMenu.add(accueilItem);
        accueilItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Accueil accueil = new Accueil();
                accueil.setVisible(true);
            }
        });

        JMenu livresMenu = new JMenu("Livres");
        menuBar.add(livresMenu);

        JMenu quitterMenu = new JMenu("Quitter");
        menuBar.add(quitterMenu);
        JMenuItem quitterItem = new JMenuItem("Quitter l'application");
        quitterMenu.add(quitterItem);
        quitterItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Liste des Livres");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.setBackground(Color.GREEN);
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjouterLivreF ajouterF = new AjouterLivreF(livreController);
                ajouterF.setVisible(true);
                refresh();
            }
        });
        ajouterButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        titlePanel.add(ajouterButton, BorderLayout.EAST);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        List<Livre> livres = livreController.getListeLivres();
        Object[][] data = new Object[livres.size()][4]; 

        for (int i = 0; i < livres.size(); i++) {
            Livre livre = livres.get(i);
            data[i][0] = livre.getId(); 
            data[i][1] = livre.getTitre();
            data[i][2] = livre.getAuteur();
            data[i][3] = livre.getCommentaire();
        }

        DefaultTableModel model = new DefaultTableModel(data, new Object[]{"ID", "Titre", "Auteur", "Commentaire"});
        livresTable = new JTable(model);

        livresTable.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(livresTable);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.setBackground(Color.WHITE);
        JPanel buttonsPanel = new JPanel();
        JButton modifierButton = new JButton("Modifier");
        modifierButton.setBackground(Color.ORANGE);
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = livresTable.getSelectedRow();
                if (selectedRow != -1) {
                    Livre livre = livres.get(selectedRow);
                    ModifierLivreF modifier = new ModifierLivreF(livreController, livre);
                    modifier.setVisible(true);
                    modifier.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            refresh();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(ListeLivres.this, "Veuillez sélectionner un livre à modifier.", "Avertissement", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.add(modifierButton);

        JButton supprimerButton = new JButton("Supprimer");
        supprimerButton.setBackground(Color.RED);
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = livresTable.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) livresTable.getModel();

                    String titre = (String) model.getValueAt(selectedRow, 1);
                    String auteur = (String) model.getValueAt(selectedRow, 2);
                    String commentaire = (String) model.getValueAt(selectedRow, 3);

                    int option = JOptionPane.showConfirmDialog(
                            ListeLivres.this,
                            "Êtes-vous sûr de vouloir supprimer ce livre?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        Livre livreToDelete = new Livre();
                        livreToDelete.setTitre(titre);
                        livreToDelete.setAuteur(auteur);
                        livreController.supprimerLivre(livreToDelete.getTitre(), livreToDelete.getAuteur());
                        refresh();
                    }
                }
            }
        });

        buttonsPanel.add(supprimerButton);

        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        add(mainPanel);
    }

    private void refresh() {
        SwingUtilities.invokeLater(() -> {
            this.dispose();
            new ListeLivres(livreController).setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ListeLivres list = new ListeLivres(new LivreController());
            list.setVisible(true);
        });
    }
}