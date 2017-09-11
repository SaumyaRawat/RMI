import java.rmi.*;
import java.net.*;
public class Math_Server
{
    public static void main(String args[])
    {
        try
        {
        Math_Implementation Math1=new Math_Implementation();
        Math_Implementation Math2=new Math_Implementation();
        Math_Implementation Math3=new Math_Implementation();
        Math_Implementation Math4=new Math_Implementation();

        System.out.println("In Server ");
        Naming.rebind("rmi://localhost:5000/check_primality",Math1);
        Naming.rebind("rmi://localhost:5000/check_palindrome",Math2);
        Naming.rebind("rmi://localhost:5000/get_fibonacci",Math3);
        Naming.rebind("rmi://localhost:5000/convert_case",Math4);
        }
        catch(Exception ex){}
    }
}