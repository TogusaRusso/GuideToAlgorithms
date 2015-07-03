/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unionfind;

import java.util.Arrays;

/**
 *
 * @author togusa
 */
public class WeightedQuickUnionCompressedUF {
     private int[] id, sz;
    
    public WeightedQuickUnionCompressedUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        for(int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }
    
    private int root(int i)
    {
        while (i != id[i])
        {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    
    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }
    
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if(i == j) return;
        if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else               { id[j] = i; sz[i] += sz[j]; }
    }
    
    public String toString()
    {return Arrays.toString(id);}

    
}
