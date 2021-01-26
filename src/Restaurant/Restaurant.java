package Restaurant;

import java.io.*;
import java.util.Scanner;

public class Restaurant extends Customer
{
    int bill=0;
    int onum=0;
    int capacity = 5;
    Item[] menu = new Item[3];
    String cname="";
    Restaurant()
    {
        menu[0] = new Item("Biryani", 200, "Rice");
        menu[1] = new Item("Roti", 100, "Flour");
        menu[2] = new Item("Paneer", 100, "Spices");
    }

    void PlaceOrder() throws IOException
    {
        bill=0;
        int qty=0;
        InputStreamReader read=new InputStreamReader(System.in);
        BufferedReader in=new BufferedReader(read);
        System.out.println("Items available: Biryani, Roti, Paneer");
        String x = "";
        while(!x.equals("0"))
        {
            System.out.println("Type item name to order, else type 0 to exit!");
            x = in.readLine();
            for(int i=0;i<3;i++)
            {
                if(x.equalsIgnoreCase(menu[i].name))
                {
                    System.out.println("Enter quantity:");
                    qty = Integer.parseInt(in.readLine());
                    bill = bill+(menu[i].price*qty);
                    menu[i].orderfreq = menu[i].orderfreq+qty;
                    break;
                }
                else
                {
                    continue;
                }
            }
        }

        File file=new File("ordernum.txt");
        Scanner input = null;
        try {
            String line="";
            input = new Scanner(file);
            while (input.hasNext()) {
                line = input.nextLine();
            }
            onum = Integer.parseInt(line)+1;
            FileWriter fw = new FileWriter("ordernum.txt");
            String rw = onum+"";
            for(int i=0;i<rw.length();i++)
            {
                fw.write(rw.charAt(i));
            }
            fw.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void GenBill() throws IOException
    {
        String[] words;
        System.out.println("The total bill is: "+bill);
        capacity++;
        if(FindCustomer(cname)==false)
        {
            AddCustomer(cname);
            FileWriter fw = new FileWriter("orders.txt", true);
            String input = cname+","+bill+","+onum+"\n";
            for(int i=0;i<input.length();i++)
            {
                fw.write(input.charAt(i));
            }
            fw.close();
        }
        else
        {
            System.out.println("here");
            try{
                FileWriter fw = new FileWriter("orders.txt", true);
                File file1 = new File("orders.txt");
                Scanner input = new Scanner(file1);
                while(input.hasNextLine())
                {
                    System.out.println("here");
                    String line = input.nextLine();
                    System.out.println(line);
                    if(line.contains(cname))
                    {
                        words = line.split(",");
                        System.out.println(words[0]);
                        int oldsum = Integer.parseInt(words[1]);
                        int newsum = oldsum+bill;
                        String newline = words[0]+","+newsum+"\n";
                        line = line.replace(line,newline);
                        fw.write(line);
                    }
                }
                fw.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void BookSeat() throws IOException
    {
        InputStreamReader read=new InputStreamReader(System.in);
        BufferedReader in=new BufferedReader(read);
        System.out.println("Enter Restaurant.Restaurant.Customer Name without space");
        cname=in.readLine();
        if(capacity>0)
        {
            System.out.println("Your seat has been booked!");
            capacity--;
            return;
        }
        else
        {
            System.out.println("Restaurant.Restaurant is full!");
            return;
        }
    }

    public void SearchItem() throws IOException
    {
        int flag = 0;
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(read);
        String x = in.readLine();
        for (int i = 0; i < 3; i++)
        {
            if (x.equalsIgnoreCase(menu[i].name))
            {
            	flag = 1;
                System.out.println("Restaurant.Restaurant.Item found!");
                System.out.println("Restaurant.Restaurant.Item Name = "+menu[i].name+", Restaurant.Restaurant.Item price = "+menu[i].price+", Restaurant.Restaurant.Item ingredients = "+menu[i].ing+", Restaurant.Restaurant.Item frequency = "+menu[i].orderfreq);
            }
        }
        if(flag == 0) 
        {
            System.out.println("Sorry, that item is not available!");
        }
    }

    public static void main(String args[]) throws IOException
    {
        InputStreamReader read=new InputStreamReader(System.in);
        BufferedReader in=new BufferedReader(read);
        Restaurant ob1 = new Restaurant();
        Customer ob2 = new Customer();

        System.out.println("Select a Choice:");
        int ch;
        do
        {
            System.out.println("1)Book Seat\n2)Place Order\n3)Search Restaurant.Restaurant.Item\n4)Generate Bill\n5)Find Restaurant.Restaurant.Customer\n6)Find Premium Restaurant.Restaurant.Customer\n7)Add Restaurant.Restaurant.Customer\n0)Exit\n");
            ch = Integer.parseInt(in.readLine());
            switch (ch)
            {
                case 1:
                    ob1.BookSeat();
                    break;
                case 2:
                    ob1.PlaceOrder();
                    break;
                case 3:
                    ob1.SearchItem();
                    break;
                case 4:
                    ob1.GenBill();
                    break;
                case 5:
                    System.out.println("Enter Restaurant.Restaurant.Customer name to be searched without space");
                    String y = in.readLine();
                    ob2.FindCustomer(y);
                    break;
                case 6:
                    ob2.FindPremiumCustomer();
                    break;
                case 7:
                    System.out.println("Enter name without space");
                    String z = in.readLine();
                    ob2.AddCustomer(z);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        }while(ch!=0);
    }
}
