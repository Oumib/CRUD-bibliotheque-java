package finalTP_TAP_server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterLivreF extends JFrame {
    private LivreController livreController;

    private JTextField titreField;
    private JTextField auteurField;
    private JTextField commentaireField;

    public AjouterLivreF(LivreController livreController) {
        this.livreController = livreController;

        setTitle("Ajouter Livre");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setSize(700, 460);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 60));

        titreField = new JTextField();
        auteurField = new JTextField();
        commentaireField = new JTextField();
        JLabel titleLabel = new JLabel("Ajouter un livre");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel, BorderLayout.NORTH);

        formPanel.add(new JLabel("Titre:"));
        formPanel.add(titreField);
        formPanel.add(new JLabel("Auteur:"));
        formPanel.add(auteurField);
        formPanel.add(new JLabel("Commentaire:"));
        formPanel.add(commentaireField);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 60));
        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.setBackground(Color.GREEN);
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterLivre();
            }
        });

        JButton annulerButton = new JButton("Annuler");

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjouterLivreF.this.dispose();
            }
        });

        buttonsPanel.add(ajouterButton);
        buttonsPanel.add(annulerButton);
        add(buttonsPanel, BorderLayout.SOUTH);

    }

    private void ajouterLivre() {
        Livre nouveauLivre = new Livre(
                titreField.getText(),
                auteurField.getText(),
                commentaireField.getText());

        livreController.ajouterLivre(nouveauLivre);

        AjouterLivreF.this.dispose();
        refresh();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AjouterLivreF addBookForm = new AjouterLivreF(new LivreController());
            addBookForm.setVisible(true);

        });
    }
    private void refresh() {
        SwingUtilities.invokeLater(() -> {
            this.dispose();
            new ListeLivres(livreController).setVisible(true);
        });
    }
}
