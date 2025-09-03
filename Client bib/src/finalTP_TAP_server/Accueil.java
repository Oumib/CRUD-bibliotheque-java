package finalTP_TAP_server;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame{
    private LivreController livreController;

	public Accueil() {
        this.livreController = new LivreController();
        setTitle("Application Livres");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\PC\\OneDrive\\Images\\images des animaux\\logohome.jpg");
        setIconImage(icon);
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\PC\\OneDrive\\Images\\images des animaux\\books.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu accueilMenu = new JMenu("Accueil");
        menuBar.add(accueilMenu);

        JMenu livresMenu = new JMenu("Livres");
        menuBar.add(livresMenu);
        JMenuItem listeLivresItem = new JMenuItem("Liste des Livres");
        livresMenu.add(listeLivresItem);
        listeLivresItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ListeLivres listeLivresWindow = new ListeLivres(livreController);
                listeLivresWindow.setVisible(true);
            }
        });

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
    }
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Accueil clientGUI = new Accueil();
            clientGUI.setVisible(true);
        });
    }

}
