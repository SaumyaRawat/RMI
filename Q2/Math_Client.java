import java.io.*;
import java.rmi.*;
import java.net.*;
import java.util.Scanner;
public class Math_Client
{
    public static void main(String args[])
    {
        try
            {
                while(true)
                {
                    System.out.println("1. : Check Primality"+"\n"+
                        "2. : Palindrome​ ​Test"+"\n"+
                        "3. : Nth Fibonacci Number"+"\n"+
                        "4. : Convert Case"+"\n"+
                        "Type quit to exit"+"\n");
                    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                    if(br.readLine().equals("quit")){
                        System.out.println("Exiting..");
                        return;
                    }
                    
                    int no=Integer.parseInt(br.readLine());
                    
                    switch(no){
                        case 1:
                            System.out.println("Enter no. :");                            
                            BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
                            int num=Integer.parseInt(input.readLine());
                            String url="rmi://localhost:5000/check_primality";
                            Math_Interface intf=(Math_Interface)Naming.lookup(url);
                            int x=intf.check_primality(num);
                            if(x==0)
                            System.out.println(num+" is prime number"+"\n");
                            else
                            System.out.println(num+" is not prime number"+"\n");
                        break;
                        case 2:
                            System.out.println("Enter string :");                            
                            BufferedReader get_string=new BufferedReader(new InputStreamReader(System.in));
                            String original=get_string.readLine();
                            String url2="rmi://localhost:5000/check_palindrome";
                            Math_Interface intf2=(Math_Interface)Naming.lookup(url2);
                            int res=intf2.check_palindrome(original);
                            if(res==1)
                                System.out.println(original+" is a palindrome"+"\n");
                            else
                                System.out.println(original+" is not a palindrome"+"\n");
                        break;
                        case 3:
                            System.out.println("Enter N :");                            
                            BufferedReader get_n=new BufferedReader(new InputStreamReader(System.in));
                            int n=Integer.parseInt(get_n.readLine());
                            String url3="rmi://localhost:5000/get_fibonacci";
                            Math_Interface intf3=(Math_Interface)Naming.lookup(url3);
                            int res3=intf3.get_fibonacci(n);
                            System.out.println(res3+" is the Nth fibonacci number"+"\n");
                        break;

                        case 4:
                            System.out.println("Enter string to be converted :");                            
                            BufferedReader get_string2=new BufferedReader(new InputStreamReader(System.in));
                            String s=get_string2.readLine();
                            String url4="rmi://localhost:5000/convert_case";
                            Math_Interface intf4=(Math_Interface)Naming.lookup(url4);
                            String res4=intf4.convert_case(s);
                            System.out.println(res4+" is the converted string"+"\n");
                        break;                        

                        default:
                            System.out.println("Entered option does not exist!");
                            break;
                    }
                }
            }catch(Exception ex){}
    }
}