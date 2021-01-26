package Restaurant;

import java.util.*;
import java.io.*;
public class Customer
{
    String name="";
    long mnum=0;

    public void AddCustomer(String name) throws IOException
    {
        InputStreamReader read=new InputStreamReader(System.in);
        BufferedReader in=new BufferedReader(read);
        FileWriter fw = new FileWriter("customer.txt", true);
        System.out.println("Enter mobile number");
        mnum=Long.parseLong(in.readLine());
        String input = name+","+mnum+"\n";

        for(int i=0;i<input.length();i++)
        {
            fw.write(input.charAt(i));
        }

        System.out.println("Restaurant.Restaurant.Customer Added!");
        fw.close();
    }

    public boolean FindCustomer(String str) throws IOException
    {
        InputStreamReader read=new InputStreamReader(System.in);
        BufferedReader in=new BufferedReader(read);
        //System.out.println("Enter Restaurant.Restaurant.Customer Name to be searched without spaces:");
        //String str = in.readLine();

        File file=new File("customer.txt");
        Scanner input = null;
        try{
            input=new Scanner(file);
            while(input.hasNext())
            {
                String line = input.nextLine();
                if(line.contains(str))
                {
                    System.out.println(line);
                    return true;
                }
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public void FindPremiumCustomer() throws IOException {
        int i=0;
        String[] words1;
        String[] finwords = new String[10];
        File file1 = new File("orders.txt");
        Scanner input = new Scanner(file1);
        while (input.hasNextLine()) {
            //System.out.println("here");
            String line = input.nextLine();
            //System.out.println(line);
            words1 = line.split(",");
            if(Integer.parseInt(words1[1])>=1000)
            {
                finwords[i]=words1[0];
                i++;
            }
        }
        System.out.println("Premium Customers are:");
        int j=0;
        while(finwords[j]!=null)
        {
            System.out.println(finwords[j]);
            j++;
        }
        System.out.println();
        System.out.println();
    }
}
