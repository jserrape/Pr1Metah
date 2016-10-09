/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1metah;

/**
 *
 * @author Xenahort
 */
public class Tabu {
    public void tabuSearch(int x,int y,int mat[][],int greedySol[]) {
        System.out.println("\n\nComienzo la busqueda tabu:");
        System.out.println("   x="+x);
        System.out.println("   y="+y);
        System.out.print("   Solucion greedy: ");
        for(int i=1;i<x;i++){
            if(greedySol[i]==1){
                System.out.print(i + ":" + greedySol[i] + " ");
            }
        }
        System.out.print("\n");
    }
}