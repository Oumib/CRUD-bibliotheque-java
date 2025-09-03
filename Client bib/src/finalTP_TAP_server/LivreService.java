package finalTP_TAP_server;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
public interface LivreService extends Remote {
    void createLivre(Livre livre) throws RemoteException;
    List<Livre> readLivres() throws RemoteException;
    void updateLivre(Livre livre) throws RemoteException;
    void deleteLivre(String titre, String auteur) throws RemoteException;
}
