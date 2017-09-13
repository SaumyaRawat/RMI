import java.rmi.*;
import java.util.Date;
import java.text.*;

public interface ATM_Interface extends Remote
{
    public Long deposit(Long x,int y) throws RemoteException;
    public Long withdraw(Long x,int y) throws RemoteException;
    public int display_balance(Long x,int y) throws RemoteException;
    public String transaction_details1(Long x, int y) throws RemoteException;
    public String transaction_details2(Long x, Date start_date, Date end_date) throws RemoteException;
}