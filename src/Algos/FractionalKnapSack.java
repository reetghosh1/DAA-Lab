package Algos;
import java.util.*;
public class FractionalKnapSack {
    static class ItemValue {
        Double cost;
        double wt, ind;
        Integer val;

        public ItemValue(int wt, int val, int ind) {
            this.wt = wt;
            this.val = val;
            this.ind = ind;
            //cost = (double) val / (double) wt;
        }
    }

    static class Sort implements Comparator<ItemValue> {
        public int compare(ItemValue o1, ItemValue o2) {
            return o2.val.compareTo(o1.val);
        }
    }

    public static double getMaxValue(int[] wt, int[] val, double[] profit, int capacity)
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
            int cprof = (int)profit[i];

            if (capacity - cwt >= 0)
            {
                capacity = capacity - cwt;
                totalValue = totalValue+ cprof;
            }
            else
            {
                double fraction = ((double)capacity / (double)cwt);
                totalValue = totalValue + (cprof * fraction);
                capacity = (int)(capacity - (cwt * fraction));
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args)
    {
        int[] wt = {100, 100, 100, 100};
        String[] company = {"GRASIM", "TATASTEEL", "LT", "MARUTI", "TCS", "TITAN"};
        double[] opening_price = {1025.00, 651.00, 1370.00, 8117.00, 3308.95, 1505.05};
        double[] closing_price = {1114.65, 731.50, 1396.40, 8329.00, 3339.80, 1621.35};
        int capacity = 100000;

        int[] val = new int[6];
        double[] profit = new double[6];

        for(int i=0;i<6;i++)
        {
            val[i] = (int)(((closing_price[i] - opening_price[i])*100)/opening_price[i]);
            profit[i] = closing_price[i] - opening_price[i];

        }

        double maxValue = getMaxValue(wt, val, profit, capacity);

        System.out.println("Maximum value we can obtain = "+ maxValue);
    }
}
