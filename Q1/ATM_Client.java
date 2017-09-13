import java.io.*;
import java.rmi.*;
import java.net.*;
import java.util.Scanner;
import java.util.Date;
import java.text.*;

public class ATM_Client
{
    public static void main(String args[])
    {
        try
            {
                while(true)
                {
                    String url="rmi://localhost:5000/atm";
                    ATM_Interface intf=(ATM_Interface)Naming.lookup(url);
                    int amount;
                    System.out.println("Enter Account Number: ");
                    Scanner s=new Scanner(System.in);
                    Long acc_no=Long.parseLong(s.next());
                    
                    System.out.println("1. : Deposit Money"+"\n"+
                        "2. : Withdraw Money"+"\n"+
                        "3. : Check Balance"+"\n"+
                        "4. : Check Transaction History"+"\n"+
                        "5. : To Quit"+"\n");
                    s=new Scanner(System.in);
                    int no=Integer.parseInt(s.next());
                    Long res;
                    switch(no){
                        case 1:
                            System.out.println("Enter Amount to be deposited :");                            
                            BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
                            int num=Integer.parseInt(input.readLine());
                            res = intf.deposit(acc_no,num);
                            if(res>0)
                            {
                                System.out.println(num+" deposited. Transaction ID: "+res+"\n");
                                Integer bal_left = intf.display_balance(acc_no,0);
                                if(bal_left>-1)
                                {
                                    System.out.println("Balance left in account number "+acc_no+" is : "+bal_left+"\n");
                                }
                                else
                                {
                                    System.out.println("Account does not exist."+"\n");
                                }                                
                            }
                            else
                            {
                                System.out.println("Amount deposit failed. Please try again."+"\n");
                            }
                        break;

                        case 2:
                            System.out.println("Enter Amount to be withdrawn :");                            
                            BufferedReader get_amount=new BufferedReader(new InputStreamReader(System.in));
                            amount=Integer.parseInt(get_amount.readLine());
                            res = intf.withdraw(acc_no,amount);
                            if(res>new Long(-1))
                            {
                                System.out.println(amount+" succesfully withdrawn. Transaction ID: "+res+"\n");
                                Integer bal_left = intf.display_balance(acc_no,0);
                                if(bal_left>-1)
                                {
                                    System.out.println("Balance left in account number "+acc_no+" is : "+bal_left+"\n");
                                }
                                else
                                {
                                    System.out.println("Account does not exist."+"\n");
                                }
                            }
                            else if(res==new Long(-2))
                            {
                                System.out.println("Insufficient balance."+"\n");
                            }
                            else if(res==new Long(-3))
                            {
                                System.out.println("Account does not exist."+"\n");
                            }

                        break;
                        
                        case 3:
                            Integer bal_left = intf.display_balance(acc_no,0);
                            if(bal_left>-1)
                            {
                                System.out.println("Balance left in account number "+acc_no+" is : "+bal_left+"\n");
                            }
                            else
                            {
                                System.out.println("Account does not exist."+"\n");
                            }
                        break;

                        case 4:
                            System.out.println("Enter 1 to view all transactions and 2 to view transactions within a range :");                            
                            BufferedReader get_choice=new BufferedReader(new InputStreamReader(System.in));
                            int choice=Integer.parseInt(get_choice.readLine());
                            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                            String output ="";
                            if(choice == 1){
                                output = intf.transaction_details1(acc_no,choice);
                            }
                            else if(choice==2){
                                System.out.println("Enter Start Date (Eg. 25-1-2017): ");
                                s=new Scanner(System.in);
                                String input_date=s.next();
                                Date start_date = new Date();
                                try{
                                    start_date = dateFormat.parse(input_date);
                                }catch(Exception e){}

                                System.out.println("Enter End Date: ");
                                s=new Scanner(System.in);
                                input_date=s.next();
                                Date end_date = new Date();
                                try{
                                    end_date = dateFormat.parse(input_date);
                                }catch(Exception e){}
                                output = intf.transaction_details2(acc_no,start_date,end_date);    
                            }
                            System.out.println(output);
                            break;

                        case 5:
                            return;

                        default:
                            System.out.println("Entered option does not exist!");
                            break;
                    }
                }
            }
            catch(Exception ex)
            {
                System.out.println("Error: "+ex);
            }
    }
}