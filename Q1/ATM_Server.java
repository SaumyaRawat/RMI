import java.rmi.*;
import java.net.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ATM_Server
{
    public static void main(String args[])
    {
        try
        {
            ATM_Implementation Atm=new ATM_Implementation();
            Registry reg = LocateRegistry.createRegistry(5000);
            reg.bind("atm", new ATM_Implementation());
        }
        catch(Exception ex)
        {
            System.out.println ("ATM failed with error: " + ex);
        }
    }
}