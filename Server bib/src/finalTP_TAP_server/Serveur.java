package finalTP_TAP_server;

import java.rmi.Naming;

import java.rmi.registry.LocateRegistry;

public class Serveur {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); 
            LivreService livreService = new LivreServiceImpel();
            Naming.rebind("rmi://localhost/LivreService", livreService);
            System.out.println("Le Serveur RMI est en cours d'éxécution");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
