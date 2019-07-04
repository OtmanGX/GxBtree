package gui;

import btree.Noeud;
import graphviz.Attribute;
import graphviz.Edge;
import graphviz.Graph;
import graphviz.GraphType;
import graphviz.Graphviz;
import graphviz.Node;



public class GraphRender {
    Graphviz gv;
    Graph graph;

    public GraphRender() {
        gv = new Graphviz();                           //Graphviz Object.
        
    }
    
    
    private void ajouter (Noeud x, Node root) {
            String name = x.toString();
            System.out.println(name);
            Node n1 = new Node(name);
            graph.addNode(n1);
            if(root!=null) graph.addEdge(new Edge(root, n1));
            if (!x.isFeuille()) {
                    for (int i = 0 ;i <  x.getN() + 1; i++) {
                            ajouter(x.getEnfant()[i], n1);
                    }
            }
    }
    
    public byte[] search(Noeud noeud) {
        for(Node node:graph.getNodeList()) {
            if(node.getId().equals(noeud.toString())) 
            {
                System.out.println("Found");
                node.addAttribute(new Attribute("color","blue"));
                return gv.getGraphByteArray(graph, "png", "100");
            }   
        }
        return null;
    }
    
    
    
    public byte[] getImage(Noeud noeud) {
        graph = new Graph("g1", GraphType.DIGRAPH);       //Create New Gpaph.
        graph.addAttribute(new Attribute("rankdir","TB")); 
        ajouter(noeud, null);
        return gv.getGraphByteArray(graph, "png", "100");
    }

}
