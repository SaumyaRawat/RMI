import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.Date;
import java.text.*;

public class ATM_Implementation extends UnicastRemoteObject implements ATM_Interface
{
    public static class Transaction implements java.io.Serializable
    {
        private Long acc_no;
        private Long trans_id;
        private int time;
        private Date date;
        private int amount;
        
        //private Hashtable<Integer, Integer, Integer, Integer> tran_hist;
        public Transaction(Long t, Date d, int tm, int am)
        {
            this.trans_id=t;
            this.date=d;
            this.time=tm;
            this.amount=am;
        }
        public String get_trans()
        {
            String transac_line;
            transac_line = new String(trans_id.toString() + "\t" + date.toString() + "\t" +  amount +"\n");
            return(transac_line);
        }
        public Date get_date()
        {
            return(date);
        }
    }


    public static class Account_holder implements java.io.Serializable
    {
       
        public Hashtable<Long,Transaction> t_history;
        private String name,type;
        private int balance;
        private Long account_no;
        public Account_holder(Long a,String n, String t, int b, Hashtable<Long,Transaction> h)
        {
            this.name=n;
            this.type=t;
            this.balance=b;
            this.account_no=a;
            this.t_history = h;
        }
        public String get_name()
        {
            return(name);
        }
        public String get_type()
        {
            return(type);
        }
        public int get_balance()
        {
            return(balance);
        }
        public Long get_accno()
        {
            return(account_no);
        }
        public void set_balance(int x)
        {
            this.balance=x;
        }
        public Hashtable<Long,Transaction> get_history()
        {
            return(t_history);
        }
    }

    public Hashtable<Long,Account_holder> database;
    public Long tids;
    

    public ATM_Implementation() throws RemoteException   
    {
        tids = new Long(1000);
        database = load_data("ATM_Data.txt");
    }

    public Hashtable<Long,Account_holder> load_data(String file_path) throws RemoteException
    {
        Hashtable<Long, Account_holder>  tmp = new  Hashtable<Long, Account_holder>();
        System.out.println("Populating DB");

            // Account 1 : 1234567890
            Hashtable<Long,Transaction> acc_history1 = new Hashtable<Long,Transaction>();
            
            // Transaction 1
            this.tids+=1;
            String inputString = "11-11-2012";
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date inputDate = new Date();
        try{
            inputDate = dateFormat.parse(inputString);
        }catch(Exception e){}
            Transaction tr1 = new Transaction(this.tids,inputDate,1400,100);
            acc_history1.put(this.tids,tr1);

            // Transaction 2
            this.tids+=1;
            inputString = "12-1-2013";
            try{
                inputDate = dateFormat.parse(inputString);
            }catch(Exception e){}
            Transaction tr2 = new Transaction(this.tids,inputDate,1230,200);
            acc_history1.put(this.tids,tr2);            

            // Transaction 3
            this.tids+=1;
            inputString = "25-1-2016";
            try{
                inputDate = dateFormat.parse(inputString);
            }catch(Exception e){}
            Transaction tr3 = new Transaction(this.tids,inputDate,1600,12000);
            acc_history1.put(this.tids,tr3);            
 
            Account_holder acc1 = new Account_holder(new Long(1234567890),"WillyWiddershins","Premium",12000,acc_history1);
            tmp.put(new Long(1234567890),acc1);



            // Account 2 : 1098765432
            Hashtable<Long,Transaction> acc_history2 = new Hashtable<Long,Transaction>();
            
            // Transaction 1
            this.tids+=1;
            inputString = "25-1-2012";
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try{
                inputDate = dateFormat.parse(inputString);
            }catch(Exception e){}
            Transaction tr4 = new Transaction(this.tids,inputDate,1430,1000);
            acc_history2.put(this.tids,tr4);

            // Transaction 2
            this.tids+=1;
            inputString = "18-10-2013";
            try{
                inputDate = dateFormat.parse(inputString);
            }catch(Exception e){}
            Transaction tr5 = new Transaction(this.tids,inputDate,1215,100);
            acc_history2.put(this.tids,tr5);            
 
            Account_holder acc2 = new Account_holder(new Long(1098765432),"JonUmber","Basic",200,acc_history2);
            tmp.put(new Long(1098765432),acc2);



            // Account 3 : 1987654320
            Hashtable<Long,Transaction> acc_history3 = new Hashtable<Long,Transaction>();
            
            // Transaction 1
            this.tids+=1;
            inputString = "5-1-2015";
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try{
                inputDate = dateFormat.parse(inputString);
            }catch(Exception e){}
            Transaction tr6 = new Transaction(this.tids,inputDate,1130,100);
            acc_history3.put(this.tids,tr6);

            Account_holder acc3 = new Account_holder(new Long(1987654320),"RayHolt","Basic",1000, acc_history3);
            tmp.put(new Long(1987654320),acc3);



            // Account 4 : 1098765432
            Hashtable<Long,Transaction> acc_history4 = new Hashtable<Long,Transaction>();
            
            // Transaction 1
            this.tids+=1;
            inputString = "25-1-2017";
            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try{
                inputDate = dateFormat.parse(inputString);
            }catch(Exception e){}
            Transaction tr7 = new Transaction(this.tids,inputDate,930,1900);
            acc_history4.put(this.tids,tr7);

            Account_holder acc4 = new Account_holder(new Long(1876543209),"NealBennett","Premium",340000,acc_history4);
            tmp.put(new Long(1876543209),acc4);
            return(tmp);
            //i++;
            //}
        /*}
        catch(FileNotFoundException  e)
        {
            System.out.println("Error:"+e.toString());
        }
        catch(IOException  e)
        {
            System.out.println("Error:"+e.toString());
        }*/
    }

    public Long deposit(Long x,int y) throws RemoteException
    {
        // find object with specified account number x
        Account_holder person = this.database.get(x);
        if(person!=null)
        {   
            System.out.print("Account found!");
            Integer new_balance = person.get_balance()+y;
            person.set_balance(new_balance);
            this.database.put(new Long(x),person);
            //System.out.println(y+" rs. deposited to account.");

            // Update Transaction history
            this.tids+=1;
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date inputDate = new Date();
            try{
                inputDate = dateFormat.parse(inputDate.toString());
            }catch(Exception e){}
            Transaction tr1 = new Transaction(this.tids,inputDate,1400,y);
            person.t_history.put(this.tids,tr1);

            return(this.tids);

            //save_data();
        }
        else{
            System.out.println("Account does not not exist");
            return(new Long(0));
        }
    }

    public Long withdraw(Long x,int y) throws RemoteException
    {
        // find object with specified account number x
        Account_holder person = this.database.get(x);
        if(person!=null)
        {
            System.out.print("Account found!");
            Integer current_balance = person.get_balance();
            if(current_balance>=y)
            {
                Integer new_balance = person.get_balance()-y;
                person.set_balance(new_balance);
                this.database.put(new Long(x),person);

                // Update Transaction history
                this.tids+=1;
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date inputDate = new Date();
                try{
                    inputDate = dateFormat.parse(inputDate.toString());
                }catch(Exception e){}
                Transaction tr1 = new Transaction(this.tids,inputDate,1400,y);
                person.t_history.put(this.tids,tr1);


                //System.out.println(y+" rs. withdrawn from account."+
                //    "\n"+"New balance is: "+new_balance);
                //save_data();
                return(this.tids);
            }
            else
            {
                System.out.println("Insufficient balance!");
                return(new Long(-2));
            }   
        }
        else
        {
            System.out.println("Account does not not exist");
            return(new Long(-3));
        }
    }

    public int display_balance(Long x,int y) throws RemoteException
    {
        if(this.database==null)
        {
            System.out.print("DB is empty!"+"\n");
        }
        Account_holder person = this.database.get(x);
        if(person!=null)
        {
            Integer bal = person.get_balance();
            System.out.println(bal + " rs. left");
            return(bal);
        }
        else
        {
            return(-1);
        }
    }

    public String transaction_details1(Long x, int y) throws RemoteException
    {
        Account_holder per = this.database.get(x);
        StringBuilder data;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        data = new StringBuilder("Transaction_id\tDate\tTime\tAmount\n");
        Hashtable<Long,Transaction> tr = new Hashtable<Long,Transaction>();
        tr = per.t_history;
        if(y==1) // Display all transactions
        {
            Set<Long> keys = tr.keySet();
            for(Long key: keys){
                Transaction obj = tr.get(key);
                String line = obj.get_trans();
                data.append(line+"\n");
            }
        }
        return(data.toString());
    }

    public String transaction_details2(Long x, Date start_date, Date end_date) throws RemoteException
    {
        Account_holder per = this.database.get(x);
        StringBuilder data;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        data = new StringBuilder("Transaction_id\tDate\tTime\tAmount\n");
        Hashtable<Long,Transaction> tr = new Hashtable<Long,Transaction>();
        tr = per.t_history;
            // Take input for range
            //iterate through complete transaction history
            Set<Long> keys = tr.keySet();
            for(Long key: keys){
                Transaction obj = tr.get(key);
                Date tr_date = obj.get_date();
                //append only those that satisy range
                if (!tr_date.before (start_date) && !tr_date.after (end_date)) 
                {
                    String line = obj.get_trans();
                    data.append(line+"\n");
                }
            }
        return(data.toString());
    }

/*    public void save_data() throws RemoteException
    {
        String file_path="ATM_Data.txt";
        FileWriter fw = null;
        StringBuilder data;

        data = new StringBuilder("Account\tName\tType\tBalance\n");

        try {
            fw = new FileWriter(file_path);

            Integer key;
            Enumeration em = this.database.keys();

            while(em.hasMoreElements())
            {
                key = (Integer) em.nextElement();
                Account_holder ah = this.database.get(key);
                String p_name = ah.get_name();
                String p_type = ah.get_type();
                Integer p_balance = ah.get_balance();
                data.append(key.toString() + "\t" + p_name + "\t" + p_type + "\t" + p_balance.toString() +"\n");
            }
            fw.write(data.toString(),0,data.length());
            fw.flush();
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Err=" + e.toString());
        }
        catch(NoSuchElementException e)
        {
            System.out.println("Err="+e.toString());
        }
    } */
}
 