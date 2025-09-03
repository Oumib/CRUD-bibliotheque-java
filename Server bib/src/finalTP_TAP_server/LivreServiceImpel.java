package finalTP_TAP_server;
import java.rmi.RemoteException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.rmi.server.UnicastRemoteObject;
public class LivreServiceImpel extends UnicastRemoteObject implements LivreService {
	private static final String url = "jdbc:mysql://localhost:3306/Livre_DB";
    private static final String username = "root";
    private static final String password = "";
    public LivreServiceImpel() throws RemoteException {
        super();
    }
    @Override
    public void createLivre(Livre livre) throws RemoteException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO livre (Titre, Auteur, Commentaire) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, livre.getTitre());
                preparedStatement.setString(2, livre.getAuteur());
                preparedStatement.setString(3, livre.getCommentaire());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Erreur lors de la création du livre.", e);
        }
    }
    @Override
    public List<Livre> readLivres() throws RemoteException {
        List<Livre> livres = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT Livre_ID, Titre, Auteur, Commentaire FROM livre";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Livre livre = new Livre();
                    livre.setId(resultSet.getInt("Livre_ID"));
                    livre.setTitre(resultSet.getString("Titre"));
                    livre.setAuteur(resultSet.getString("Auteur"));
                    livre.setCommentaire(resultSet.getString("Commentaire"));
                    livres.add(livre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Erreur lors de la lecture des livres.", e);
        }
        return livres;
    }

    @Override
    public void updateLivre(Livre livre) throws RemoteException {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE livre SET titre=?, auteur=?, commentaire=? WHERE Livre_ID=?")) {

            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setString(3, livre.getCommentaire());
            preparedStatement.setInt(4, livre.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteLivre(String titre, String auteur) throws RemoteException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM livre WHERE Titre = ? AND Auteur = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, titre);
                preparedStatement.setString(2, auteur);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Erreur lors de la suppression du livre.", e);
        }
    }


}
