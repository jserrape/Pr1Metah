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
public class Grasp {
    
    public void mostrarMatriz(int x,int y,int mat[][]){
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                System.out.print(mat[i][j]+" ");
            }
            System.out.print("\n");
        }
    }
    
    
    public void graspSearch(int x,int y,int mat[][]) {
        System.out.println("\n\nComienzo la busqueda Grasp:");
        System.out.println("   x="+x);
        System.out.println("   y="+y);
        System.out.print("\n");
        
        int cont;
        for(int i=1;i<x;i++){
            cont=0;
            for(int j=1;j<y;j++){
                if(mat[i][j]==1)
                    ++cont;
            }
            mat[0][i]=cont;
        }
        mostrarMatriz(x,y,mat);
    }
    
    public void greedyRandomized(int x,int y,int mat[][]){
        
    }
}