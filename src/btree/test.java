/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btree;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mac
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
                    BTree tree =new BTree(2);
        tree.ajouter(1);
         tree.ajouter(2);
          tree.ajouter(3);
           tree.ajouter(4);
            tree.ajouter(5);
             tree.ajouter(6);
              tree.ajouter(9);
               tree.ajouter(10);
                tree.ajouter(14);
                tree.ajouter(55);
                tree.ajouter(11);
                tree.ajouter(18);
                tree.ajouter(16);
               tree.afficher();
               System.out.println(tree.contenir(56));
//        try {
//            tree.serialiser("test.bt");
//        } catch (IOException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        try {
//        BTree tree = (BTree)BTree.deserialiser("test.bt");
//        tree.afficher();
//                } catch (IOException ex) {
//                    Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//                }

            }
    
}
