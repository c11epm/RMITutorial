package server;

import resources.DollarToEuro;
import resources.PerfectCheck;
import resources.PrimeCheck;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by emil on 2017-04-23.
 */
public class Server implements PrimeCheck, DollarToEuro, PerfectCheck {

    @Override
    public double dollarToEuro(double dollar) throws RemoteException {
        return dollar * 1.05; //Insert conversion rate
    }

    @Override
    public boolean isPerfect(int number) throws RemoteException {

        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }

    @Override
    public boolean isPrime(int number) throws RemoteException {
        if (number % 2 == 0) return false;
        //if not, then just check the odds
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0)
                return false;
        }
        return true;

    }

    public static void main(String[] args) {
        int port = 1338;
        String host = "localhost";
        Registry reg;
        Server server;
        try {
            server = new Server();
            DollarToEuro dollarStub = (DollarToEuro) UnicastRemoteObject.exportObject(server, 0);
            PerfectCheck perfectStub = server;
            PrimeCheck primeStub = server;

            reg = LocateRegistry.getRegistry(port);

            reg.rebind("DollarToEuro", dollarStub);
            reg.rebind("PerfectCheck", perfectStub);
            reg.rebind("PrimeCheck", primeStub);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not find/create the registry");
        }
    }
}
