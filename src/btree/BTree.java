/*
 * ordreo change this license header, choose License Headers in Project Properties.
 * ordreo change this template file, choose ordreools | ordreemplates
 * and open the template in the editor.
 */
package btree;

/**
 *
 * @author Mac
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

public class BTree implements Serializable{

	private int ordre;
        private Noeud root;

    public BTree() {
    }
        
        
        
    public BTree(int ordre) {
        this.ordre = ordre;
        root = new Noeud(ordre);
        root.n = 0;
        root.feuille = true;
}
    public void nettoyer() {
        root = new Noeud(ordre);
        root.n = 0;
        root.feuille = true;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public Noeud getRoot() {
        return root;
    }

    public void setRoot(Noeud root) {
        this.root = root;
    }
    
    

    public void serialiser(String nomFichier) throws FileNotFoundException, IOException{
    ObjectOutputStream oos = null;
    FileOutputStream fichierOut = new FileOutputStream(nomFichier);

       oos = new ObjectOutputStream(fichierOut);
       oos.writeObject(this);

       oos.flush();

      oos.close();

    }
    public static BTree deserialiser(String nomFichier) throws FileNotFoundException, IOException, ClassNotFoundException {
    ObjectInputStream ois = null;

    FileInputStream fichierIn = new FileInputStream(nomFichier);

   ois = new ObjectInputStream(fichierIn);

     Object btree = ois.readObject();
      ois.close();
      return (BTree)btree;

    }

    public Noeud[] getNodes(){

    return root.enfant;
    }

    public Noeud chercher (Noeud x,int Cle) {
            int i = 0;
            if (x == null) return x;
            for (i = 0 ; i < x.n ; i++) {
                    if (Cle < x.Cle[i]) {
                            break;
                    }
                    if (Cle == x.Cle[i]) {
                            return x;
                    }
            }
            if (x.feuille) {
                    return null;
            } else {
                    return chercher(x.enfant[i],Cle);
            }
    }

    private void diviser (Noeud x , int pos , Noeud y) {
            Noeud z = new Noeud(ordre);
            z.feuille = y.feuille;
            z.n = ordre - 1;
            for (int j = 0 ; j < ordre - 1 ; j++) {
                    z.Cle[j] = y.Cle[j+ordre];
            }
            if (! y.feuille) {
                    for (int j = 0 ; j < ordre ; j++) {
                            z.enfant[j] = y.enfant[j+ordre];
                    }
            }
            y.n = ordre-1;
            for (int j = x.n ; j >= pos+1 ; j--) {
                    x.enfant[j+1] = x.enfant[j];
            }
            x.enfant[pos+1] = z;

            for (int j = x.n-1 ; j >= pos ; j--) {
                    x.Cle[j+1] = x.Cle[j];
            }
            x.Cle[pos] = y.Cle[ordre-1];
            x.n = x.n + 1;
    }

    public void ajouter (final int Cle) {
            Noeud r = root;
            if (r.n == 2*ordre - 1 ) {
                    Noeud s = new Noeud(ordre);
                    root = s;
                    s.feuille = false;
                    s.enfant[0] = r;
                    diviser(s,0,r);
                    ajouter(s,Cle);
            } else {
                    ajouter(r,Cle);
            }
    }

    final private void ajouter (Noeud x , int k) {

            if (x.feuille) {
                    int i = 0;
                    for (i = x.n-1 ; i >= 0 && k < x.Cle[i] ; i--) {
                            x.Cle[i+1] = x.Cle[i];
                    }
                    x.Cle[i+1] = k;
                    x.n = x.n + 1; 
            } else {
                    int i = 0;
                    for (i = x.n-1 ; i >= 0 && k < x.Cle[i] ; i--){};
                    i++;
                    Noeud tmp = x.enfant[i];
                    if (tmp.n == 2*ordre -1) {
                            diviser(x,i,tmp);
                            if ( k > x.Cle[i]) {
                                    i++;
                            }
                    }
                    BTree.this.ajouter(x.enfant[i], k);
            }

    }

    public void afficher () {
            afficher(root);
    }

    private void afficher (Noeud x) {
            assert(x == null);
            System.out.print(x.feuille + " " + x.n + ":" );
            for ( int i = 0 ; i < x.n ; i++) {
                    System.out.print(x.Cle[i]+ " ");
            }
            System.out.println();
            if (!x.feuille) {
                    for (int i = 0 ;i <  x.n + 1; i++) {
                            afficher(x.enfant[i]);
                    }
            }
    }



    private void supprimer (Noeud x , int Cle) {
            int pos = x.ordrerouver(Cle);
            if (pos != -1) {
                    if (x.feuille) {
                            int i = 0 ;
                            for (i = 0 ; i < x.n && x.Cle[i] != Cle ; i++){};
                            for ( ; i < x.n ; i++) {
                                    if (i != 2*ordre - 2){
                                            x.Cle[i] = x.Cle[i+1];
                                    }
                            }
                            x.n--;
                            return;
                    }
                    if (!x.feuille){
                            //if (x.enfant[pos].n >= ordre){


                                    Noeud pred = x.enfant[pos];
                                    int predCle = 0;
                                    //System.out.println(pos);
                                    if (pred.n >= ordre) {

                                            for (;;) {
                                                    if (pred.feuille) {
                                                            System.out.println(pred.n);
                                                            predCle = pred.Cle[pred.n - 1];
                                                            break;
                                                    } else {
                                                            pred = pred.enfant[pred.n];
                                                    }
                                            }
                                            BTree.this.supprimer (pred, predCle);
                                            x.Cle[pos] = predCle;
                                            return;
                                    }


                                    Noeud nextNoeud = x.enfant[pos+1];
                                    if (nextNoeud.n >= ordre) {
                                            int nextCle = nextNoeud.Cle[0];
                                            if (!nextNoeud.feuille){
                                                    nextNoeud = nextNoeud.enfant[0];
                                                    for (;;) {
                                                            if (nextNoeud.feuille) {
                                                                    nextCle = nextNoeud.Cle[nextNoeud.n-1];
                                                                    break;
                                                            } else {
                                                                    nextNoeud = nextNoeud.enfant[nextNoeud.n];
                                                            }
                                                    }
                                            }
                                            BTree.this.supprimer(nextNoeud, nextCle);
                                            x.Cle[pos] = nextCle;
                                            return;
                                    }

                                    int temp = pred.n + 1;
                                    pred.Cle[pred.n++] = x.Cle[pos];
                                    for (int i = 0, j = pred.n ; i < nextNoeud.n ; i++) {
                                            pred.Cle[j++] = nextNoeud.Cle[i];
                                            pred.n++;
                                    }
                                    for (int i = 0 ; i < nextNoeud.n+1 ; i++){
                                            pred.enfant[temp++] = nextNoeud.enfant[i];
                                    }

                                    x.enfant[pos] = pred;

                                    for (int i = pos ; i < x.n ; i++) {
                                            if (i != 2*ordre - 2) {
                                                    x.Cle[i] = x.Cle[i+1];
                                            }
                                    }

                                    for (int i = pos+1 ; i < x.n+1 ; i++) {
                                            if (i != 2*ordre - 1) {
                                                    x.enfant[i] = x.enfant[i+1];
                                            }
                                    }
                                    x.n--;
                                    if (x.n == 0) {
                                            if (x == root) {
                                                    root = x.enfant[0];
                                            }
                                            x = x.enfant[0];
                                    }
                                    BTree.this.supprimer(pred,Cle);
                                    return;
                            //}
                    }
            } else {

                    for (pos = 0 ; pos < x.n ; pos++) {
                            if (x.Cle[pos] > Cle) {
                                    break;
                            }
                    }
//			System.out.println(pos);
//			Show(x);
                    Noeud tmp = x.enfant[pos];
                    if (tmp.n >= ordre) {
                            BTree.this.supprimer (tmp,Cle);
                            return;
                    }
//			System.out.println(pos + " " + ordre + " " + tmp.n);
                    if (true) {
                            Noeud nb = null;
                            int devider = -1;

                            if (pos != x.n && x.enfant[pos+1].n >= ordre) {
//					System.out.print("yahho");
                                    devider = x.Cle[pos];
                                    nb = x.enfant[pos+1];
                                    x.Cle[pos] = nb.Cle[0];
                                    tmp.Cle[tmp.n++] = devider;
                                    tmp.enfant[tmp.n] = nb.enfant[0];
                                    for (int i = 1 ; i < nb.n ; i++) {
                                            nb.Cle[i-1] = nb.Cle[i];
                                    }
                                    for (int i = 1 ; i <= nb.n ; i++) {
                                            nb.enfant[i-1] = nb.enfant[i];
                                    }
                                    nb.n--;
                                    BTree.this.supprimer(tmp,Cle);
//					x.enfant[x.n+1] = nb.enfant[];
                                    //
                                    return;
                            } else if (pos != 0 && x.enfant[pos-1].n >= ordre){

//					System.out.println("aa");
                                    devider = x.Cle[pos-1];
//					System.out.println("helllooooo " + devider);
                                    nb = x.enfant[pos-1];
                                    x.Cle[pos-1] = nb.Cle[nb.n-1];
                                    Noeud enfant = nb.enfant[nb.n];
                                    nb.n--;

//					System.out.println("ordrehisss - " + tmp.);
                                    for(int i = tmp.n ; i > 0 ; i--) {
                                            tmp.Cle[i] = tmp.Cle[i-1];
                                    }
                                    tmp.Cle[0] = devider;
                                    for(int i = tmp.n + 1 ; i > 0 ; i--) {
                                            tmp.enfant[i] = tmp.enfant[i-1];
                                    }
                                    tmp.enfant[0] = enfant;
                                    tmp.n++;
//					Show(root);
                                    BTree.this.supprimer(tmp,Cle);
                                    return;
                            } else {

                                    Noeud lt = null;
                                    Noeud rt = null;
                                    boolean last = false;
                                    //System.out.println(x.Cle[pos]);
                                    if (pos != x.n) {
                                            devider = x.Cle[pos];
                                            lt = x.enfant[pos]; 
                                            rt = x.enfant[pos+1];
                                    } else {
                                            devider = x.Cle[pos-1];
                                            rt = x.enfant[pos];
                                            lt = x.enfant[pos-1];
                                            last = true;
                                            pos--;
                                    }

                                    for (int i = pos; i < x.n-1  ; i++){
                                            x.Cle[i] = x.Cle[i+1];
                                    }

//					for(int i = x.n + 1 ; i > pos ; i--) {
//						x.enfant[i-1] = x.enfant[i];
//					}
                                    for(int i = pos+1 ; i < x.n ; i++) {
                                            x.enfant[i] = x.enfant[i+1];
                                    }
                                    x.n--;
                                    lt.Cle[lt.n++] = devider;
                                    int numenfant = 0;
                                    //lt.enfant[lt.n] = rt.enfant[numenfant++];
                                    //Show(root);
                                    for (int i = 0, j = lt.n; i < rt.n+1 ; i++,j++) {
                                            if (i < rt.n) {
                                                    lt.Cle[j] = rt.Cle[i];
                                            }
                                            lt.enfant[j] = rt.enfant[i];
                                    }
                                    lt.n += rt.n;
                                    if (x.n == 0) {
                                            if (x == root) {
                                                    root = x.enfant[0];
                                            }
                                            x = x.enfant[0];
                                    }
                                    //System.out.println("Heeee");

                                    BTree.this.supprimer(lt,Cle);

                                    return;
                            }				
                    }
            }
    }


    public void supprimer (int Cle) {
            Noeud x = chercher(root, Cle);
            if (x == null) {
//			System.out.println("No!!!");
                    return;
            }
            BTree.this.supprimer(root,Cle);
    }





    public boolean contenir(int k) {
            if (this.chercher(root, k) != null) {
                    return true;
            } else {
                    return false;
            }
    }
	
}