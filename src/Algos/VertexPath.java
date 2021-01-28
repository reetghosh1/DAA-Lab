package Algos;
import java.io.*;
import java.util.*;
public class VertexPath
{
    private int V;
    private LinkedList<Integer> adj[];

    //Constructor
    VertexPath(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }


    void addEdge(int v,int w)  {   adj[v].add(w);   }


    Boolean isReachable(int s, int d)
    {
        boolean visited[] = new boolean[V];

        LinkedList<Integer> queue = new LinkedList<Integer>();


        visited[s]=true;
        queue.add(s);


        Iterator<Integer> i;
        while (queue.size()!=0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.println(s);

            int n;
            i = adj[s].listIterator();

            while (i.hasNext())
            {
                n = i.next();

                if (n==d)
                    return true;

                // Else, continue to do BFS
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }

        return false;
    }


    public static void main(String args[])
    {

        VertexPath g = new VertexPath(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        int u = 1;
        int v = 3;
        if (g.isReachable(u, v))
            System.out.println("There is a path from " + u +" to " + v);
        else
            System.out.println("There is no path from " + u +" to " + v);

        u = 3;
        v = 1;
        if (g.isReachable(u, v))
            System.out.println("There is a path from " + u +" to " + v);
        else
            System.out.println("There is no path from " + u +" to " + v);
    }
}