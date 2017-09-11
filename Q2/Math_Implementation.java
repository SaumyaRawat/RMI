import java.rmi.*;
import java.rmi.server.*;
public class Math_Implementation extends UnicastRemoteObject implements Math_Interface
{
    public Math_Implementation() throws Exception
    {
    }

    public int check_primality(int no) throws RemoteException
    {
        int i,cnt=0;
        for(i=2;i<=no/2;i++)
            if(no%i==0)
            {
                cnt++;
                break;
            }       
            return (cnt);
    }

    public int check_palindrome(String original) throws RemoteException
    {
      String reverse = "";
 
      System.out.println("Enter a string to check if it is a palindrome");
 
      int length = original.length();
 
      for ( int i = length - 1; i >= 0; i-- )
         reverse = reverse + original.charAt(i);
 
      if (original.equals(reverse))
        return(1);
      else
        return(0);
 
    }

    public int get_fibonacci(int n) throws RemoteException
    {
        int loop,num = 0,num2 = 1,fibonacci;
        for (loop = 0; loop < n; loop ++)
        {
            fibonacci = num + num2;
            num = num2;
            num2 = fibonacci;
        }
        return(num);
    }

    public String convert_case(String s) throws RemoteException
    {
        int i;
        String output = "";
        for(i=0;i<s.length();i++)
        {
            int ch=s.charAt(i);
            if(ch>64&&ch<91)
            {
                ch=ch+32;
                output+=(char)ch;
                //System.out.print( (char) ch);
            }
            else if(ch>96&&ch<123)
            {
                ch=ch-32;
                output+=(char)ch;
                //System.out.print( (char) ch);
            }
            if(ch==32)
                output+=" ";
                //System.out.print(" ");
        }
        return(output);
    }
}