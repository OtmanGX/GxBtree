/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btree;

import java.io.Serializable;

/**
 *
 * @author otmangx
 */
public class Noeud implements Serializable{
    public static int nbrNoeuds = 0;
    int n;
    int Cle[];
    Noeud enfant[];
    boolean feuille = true;

    public Noeud(int ordre) {
         Cle = new int[2*ordre-1];
         enfant = new Noeud[2*ordre];
         nbrNoeuds++;
    }

    public int ordrerouver(int k){
            for (int i = 0 ; i < this.n ; i++) {
                    if (this.Cle[i] == k) {
                            return i;
                    }
            }
            return -1;
    };

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(" ");
            for ( int i = 0 ; i < n; i++) {
                    result.append(String.valueOf(" "+Cle[i])+",");
            } 
        result.deleteCharAt(result.length()-1);
        return "\""+result.toString().trim()+"\"";
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        nbrNoeuds--;
    }
    
    
    
    
                
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[] getCle() {
        return Cle;
    }

    public void setCle(int[] Cle) {
        this.Cle = Cle;
    }

    public Noeud[] getEnfant() {
        return enfant;
    }

    public void setEnfant(Noeud[] enfant) {
        this.enfant = enfant;
    }

    public boolean isFeuille() {
        return feuille;
    }

    public void setFeuille(boolean feuille) {
        this.feuille = feuille;
    }
	}
