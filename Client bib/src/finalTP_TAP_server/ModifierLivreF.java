package finalTP_TAP_server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ModifierLivreF extends JFrame {
    private LivreController livreController;
    private Livre livre;

    private JTextField titreField;
    private JTextField auteurField;
    private JTextField commentaireField;
    private int livreId;

    public ModifierLivreF(LivreController livreController, Livre livre) {
        this.livreController = livreController;
        this.livre = livre;
        
        setTitle("Modifier les informations du livre");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 470);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu accueilMenu = new JMenu("Accueil");
        menuBar.add(accueilMenu);
        JMenuItem accueilItem = new JMenuItem("Accueil");
        accueilMenu.add(accueilItem);
        accueilItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Accueil accueil=new Accueil();
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
        JLabel titleLabel = new JLabel("Modifier les informations du livre");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 60));
        titreField = new JTextField(livre.getTitre());
        auteurField = new JTextField(livre.getAuteur());
        commentaireField = new JTextField(livre.getCommentaire());
        formPanel.setBackground(Color.WHITE);
        formPanel.add(new JLabel("Titre:"));
        formPanel.add(titreField);
        formPanel.add(new JLabel("Auteur:"));
        formPanel.add(auteurField);
        formPanel.add(new JLabel("Commentaire:"));
        formPanel.add(commentaireField);

        add(formPanel, BorderLayout.CENTER);

        JButton modifierButton = new JButton("Modifier");
        JButton annulerButton = new JButton("Annuler");

        modifierButton.setBackground(Color.ORANGE);
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	modifierLivre(livre);
            }
        });        
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifierLivreF.this.dispose();
            }
        });

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 60));
        buttonsPanel.add(modifierButton);
        buttonsPanel.add(annulerButton);
        add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.setBackground(Color.WHITE);
        this.livreId = livre.getId();
    }

    private void modifierLivre(Livre livre) {
        try {
            livre.setId(livreId);
            livre.setTitre(titreField.getText());
            livre.setAuteur(auteurField.getText());
            livre.setCommentaire(commentaireField.getText());

            livreController.modifierLivre(livre);

            JOptionPane.showMessageDialog(this, "Livre modifié avec succès.");
            refreshListeLivres();
            dispose();
        } catch (RemoteException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la modification du livre.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshListeLivres() {
        SwingUtilities.invokeLater(() -> {
            ListeLivres listeLivres = new ListeLivres(livreController);
            listeLivres.setVisible(true);
            this.dispose();
        });
    }

}

