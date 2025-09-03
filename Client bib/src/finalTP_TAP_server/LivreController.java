package finalTP_TAP_server;
import java.rmi.RemoteException;

import java.rmi.Naming;
import java.util.List;

public class LivreController {
    private LivreService livreService;

    public LivreController() {
        try {
            livreService = (LivreService) Naming.lookup("rmi://localhost/LivreService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Livre> getListeLivres() {
        try {
            return livreService.readLivres();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void ajouterLivre(Livre livre) {
        try {
            livreService.createLivre(livre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifierLivre(Livre livre)  throws RemoteException {
        try {
            livreService.updateLivre(livre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void supprimerLivre(String titre, String auteur) {
        try {
            livreService.deleteLivre(titre, auteur);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
