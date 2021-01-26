package Restaurant;

import java.util.*;
import java.io.*;
public class Item
{
    String name;
    int price;
    int orderfreq;
    String ing;

    Item(String name, int price, String ing)
    {
        this.name = name;
        this.price = price;
        orderfreq = 0;
        this.ing = ing;
    }
}
