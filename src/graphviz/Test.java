/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphviz;

import btree.BTree;
import btree.Noeud;
import java.io.File;

/**
 *
 * @author otmangx
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    Graphviz gv;
    Graph graph;
    Noeud search;
    public static void main(String[] args) {
        Test test = new Test();
        test.draw();
    }

    public Test() {
    gv = new Graphviz();                           //Graphviz Object.
    graph = new Graph("g1", GraphType.DIGRAPH);       //Create New Gpaph.
    graph.addAttribute(new Attribute("rankdir","TB")); 
    }
    
    
    
    private void ajouter (Noeud x, Node root) {
		assert(x == null);
//		System.out.print(x.feuille + " " + x.n + ":" );
                StringBuilder result = new StringBuilder();
		for ( int i = 0 ; i < x.getN() ; i++) {
			result.append(String.valueOf(" "+x.getCle()[i])+",");
		}
                result.deleteCharAt(result.length()-1);
                System.out.println(result);
                Node n1 = new Node("\""+result.toString()+"\"");
                graph.addNode(n1);
                if(root!=null) graph.addEdge(new Edge(root, n1));
		if (!x.isFeuille()) {
			for (int i = 0 ;i <  x.getN() + 1; i++) {
				ajouter(x.getEnfant()[i], n1);
			}
		}
	}
    
    private void draw() {
        BTree tree =new BTree(2);
        tree.ajouter(7);
        tree.ajouter(16);
        tree.ajouter(21);
        tree.ajouter(18);
        tree.ajouter(9);
        tree.ajouter(12);
        tree.ajouter(1);
        tree.ajouter(2);
        tree.ajouter(5);
        tree.ajouter(6);
        tree.ajouter(4);
        search = tree.chercher(tree.getRoot(), 12);
        ajouter(tree.getRoot(), null);
        
//        tree.ajouter();
//        tree.getNodes()
//        
//    Graphviz gv = new Graphviz();                           //Graphviz Object.
//    Graph graph = new Graph("g1", GraphType.DIGRAPH);       //Create New Gpaph.
//    graph.addAttribute(new Attribute("rankdir","TB"));      //Add some attribute.
//    
//    Node n1 = new Node("\"7,16\"");
////    n1.addAttribute(new Attribute("label","\" Node1 \""));
//    Node n2 = new Node("\"1,2,5,6\"");
//    Node n3 = new Node("\"9,12\"");
//    Node n4 = new Node("\"18,21\"");
//    graph.addNode(n1);
//    graph.addNode(n2);
//    graph.addNode(n3);
//    graph.addNode(n4);
//    
//    graph.addEdge(new Edge(n1, n2));
//    graph.addEdge(new Edge(n1, n3));
//    graph.addEdge(new Edge(n1, n4));
    
    
//    Node n1 = new Node("N1");                               //Create Node Object.
//    n1.addAttribute(new Attribute("label","\" Node1 \""));  //Add attribute
//    Node n2 = new Node("N2");
//    Node n3 = new Node("N3");
//
//    graph.addNode(n1);                                      //Add node to graph.
//    graph.addNode(n2);
//    graph.addNode(n3);
//    graph.addEdge(new Edge(n1, n2));                        //Add edge
//    graph.addEdge(new Edge(n2, n3));
//    graph.addEdge(new Edge(n3,n1));
    String type = "png";
    gv.getGraphByteArray(graph, type, "100");
}
    
}
