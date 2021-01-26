package Algos;
import java.util.*;
public class FractionalKnapSack {
    static class ItemValue {
        Double cost;
        double wt, val, ind;

        public ItemValue(int wt, int val, int ind) {
            this.wt = wt;
            this.val = val;
            this.ind = ind;
            cost = (double) val / (double) wt;
        }
    }

    static class Sort implements Comparator<ItemValue> {
        public int compare(ItemValue o1, ItemValue o2) {
            return o2.cost.compareTo(o1.cost);
        }
    }

    public static double getMaxValue(int[] wt, int[] val, int capacity)
    {
        ItemValue[] iVal = new ItemValue[wt.length];

        for (int i = 0; i < wt.length; i++)
        {
            iVal[i] = new ItemValue(wt[i], val[i], i);
        }

        Arrays.sort(iVal, new Sort());

        double totalValue = 0d;

        for (int i=0;i<wt.length;i++) {

            int cwt = (int)iVal[i].wt;
            int cval = (int)iVal[i].val;

            if (capacity - cwt >= 0)
            {
                capacity = capacity - cwt;
                totalValue = totalValue+ cval;
            }
            else
            {
                double fraction = ((double)capacity / (double)cwt);
                totalValue = totalValue + (cval * fraction);
                capacity = (int)(capacity - (cwt * fraction));
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args)
    {
        int[] wt = { 10, 40, 20, 30 };
        int[] val = { 60, 40, 100, 120 };
        int capacity = 50;

        double maxValue = getMaxValue(wt, val, capacity);

        System.out.println("Maximum value we can obtain = "+ maxValue);
    }
}
