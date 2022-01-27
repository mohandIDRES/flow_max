import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.ArrayList;
import java.util.Random;

public class Simulations {
    public ArrayList simulation1(Graph g){
        String lst = " ";
        ArrayList<String> listFinale= new ArrayList<>();
        int nbJours = 10 ;
        Random rand = new Random();


        //Toute la population est en bonne santé
        for(Node node : g )
            node.setAttribute("health","healthy");

        //Choisir un individu au hasard pour l'infecter
        Node patientZero = Toolkit.randomNode(g);
        patientZero.setAttribute("health","infected");

        ArrayList<Node> infected = new ArrayList<>() ;
        infected.add(patientZero);

        ArrayList<Node> tmp = new ArrayList<>();

        for(int i=0;i<nbJours;i++){
            for(Node node:infected){//parcourir tous les individus infectes
                if(!tmp.contains(node))

                    tmp.add(node);

                //la probabilité de recevoir le mail infecté est 1/7
                if(rand.nextInt(7)+1==1) {
                    for(Edge e : node){
                        //Parcours des voisins de node
                        Node voisin = e.getOpposite(node);
                        if(!tmp.contains(voisin)){
                            voisin.setAttribute("health","infected");
                            tmp.add(voisin);
                        }
                    }
                }
            }
            //vider le tableau infected,prepare les individus infectés pour lendemain
            infected.clear();
            //mettre à jour
            for(Node n:tmp){
                if(rand.nextInt(14)+1==1) {
                    n.setAttribute("health", "healthy");
                }
                else infected.add(n);
            }
            lst += "Jour "+(i+1) +" ==> nb infecté(s) =  "+infected.size()+"\n";
        }

        System.out.println();
        listFinale.add(lst);

        System.out.println("Patient Zero : " +patientZero);

        System.out.println("liste des infectés : " +listFinale +"\n");
        return listFinale;

    }
}
