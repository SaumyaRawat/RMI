import java.rmi.*;
public interface Math_Interface extends Remote
{
    public int check_primality(int no) throws RemoteException;
    public int check_palindrome(String original) throws RemoteException;
    public int get_fibonacci(int n) throws RemoteException;
    public String convert_case(String s) throws RemoteException;

}