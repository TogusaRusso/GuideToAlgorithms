/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unionfind;

/**
 *
 * @author togusa
 */
public class UnionFind {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //8-1 5-1 2-9 0-9 5-7 6-2 
        
        QuickFindUF uf = new QuickFindUF(10);
        uf.union(8, 1);
        uf.union(5, 1);
        uf.union(2, 9);
        uf.union(0, 9);
        uf.union(5, 7);
        uf.union(6, 2);
        
        
        System.out.println(uf.toString());
    }
    
}
