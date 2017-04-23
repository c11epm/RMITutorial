package client;

import resources.DollarToEuro;
import resources.PerfectCheck;
import resources.PrimeCheck;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by emil on 2017-04-23.
 */
public class Client {

    public static void main(String[] args) {
        Registry registry;
        int port = 1338;
        String host = "localhost";

        try {
            registry = LocateRegistry.getRegistry(host, port);
            String[] list = registry.list();
            System.out.println(list.length);
            for (String s : list) {
                System.out.println(s);
                if (s.equals("DollarToEuro")) {
                    DollarToEuro dollarStub = (DollarToEuro) registry.lookup(s);
                    System.out.println("Dollar: " + dollarStub.dollarToEuro(15.5));
                }

                if (s.equals("PerfectCheck")) {
                    PerfectCheck perfectStub = (PerfectCheck) registry.lookup(s);
                    System.out.println("Is perfect (28): " + perfectStub.isPerfect(28));
                }
                if (s.equals("PrimeCheck")) {
                    PrimeCheck primeStub = (PrimeCheck) registry.lookup(s);
                    System.out.println("Is prime (17): " + primeStub.isPrime(17));
                }
            }

        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
