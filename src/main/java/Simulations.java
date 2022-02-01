import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulations {
    // 3 mois = 7 jours * 12 semaines  = 84
    int nbJours = 84 ;
    private Graph g;

    public Simulations(Graph g) throws IOException {
        this.g = g;
    }

    public Graph getG(){
        return this.g;
    }



    public String simulation1(Graph g){
        String lst = " ";
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
            System.out.println("j " + i);

            lst +=(i+1)+" "+infected.size()+"\n";

        }

        //listFinale.add(lst);

      //  System.out.println("Patient Zero : " +patientZero);

        return lst;

    }

    public String simulation2(Graph g){
        String lst = " ";
        Random rand = new Random();

        //Toute la population est en bonne santé
        for(Node node : g )
            node.setAttribute("health","healthy");

        List<Node> lst_immunise = Toolkit.randomNodeSet(g, g.getNodeCount()/2);
        for(Node node:lst_immunise) node.setAttribute("health","immunise");

        //on retire les noeuds immunisés
        for(Node node:lst_immunise){
            g.removeNode(node);}

        System.out.println("le seuil épidémique du réseau avec la stratégie 2 : "
                + seuil(g.getNodeCount()));

        // definition d'un patient zéro
        int k = rand.nextInt(g.getNodeCount());
        Node patientZero = g.getNode(k);
        patientZero.setAttribute("health","infected");

        ArrayList<Node> infected = new ArrayList<>();//pour stocker les individus infectés qui s'occupe de propager le virus chaque jour
        infected.add(patientZero);
        //un tableau pour stocker les individus de l'etat "healthy" en l'etat "infected" ou de l'etat "infected" en l'etat "healthy"
        ArrayList<Node> temp = new ArrayList<>();

        for(int i=0;i<nbJours;i++){
            for(Node node:infected){//parcourir tous les individus infectes
                if(!temp.contains(node)) temp.add(node);
                if(rand.nextInt(7)+1==1) {//la probabilité de recevoir le mail pour chaque voisin de l'individu infecté est 1/7
                    for(Edge e:node){
                        Node voisin = e.getOpposite(node);//obtenir tous les voisins de noeud node
                        if(!temp.contains(voisin) && !lst_immunise.contains(voisin)){
                            voisin.setAttribute("health","infected");
                            temp.add(voisin);
                        }
                    }
                }
            }

            //vider le tableau infected,prepare les individus infectés pour lendemain
            infected.clear();
            //mettre à jour
            for(Node node:temp){
                if(rand.nextInt(14)+1==1)
                    node.setAttribute("health", "healthy");
                else infected.add(node);
            }

            //System.out.println("j " + i);

            lst += (i+1)+" "+infected.size()+"\n";
        }

        return lst ;

    }

    public String simulation3(Graph g) throws IOException {
        String contenu = "";
        Random r = new Random();
        //trois mois,càd 90 jours

        for(Node node: g)//ajouter une attribut pour chaque noeud
            node.setAttribute("health","healthy");

        double someDegreGroupe0 = 0;
        double someDegreGroupe1 = 0;
        ArrayList<Node> immunise = new ArrayList<>();
        //convaincre 50 % des individus de convaincre un de leurs contacts de mettre à jour en permanence son anti-virus (immunisation sélective)
        List<Node> l = Toolkit.randomNodeSet(g,g.getNodeCount()/2);//l stocke les 50 % des individus

        //remplir le tableau immunise
        for(Node node:l){
            Node nodeImmunise = node.getEdge(r.nextInt(node.getDegree())).getOpposite(node);//choisir un des contacts pour les 50 % des individus
            nodeImmunise.setAttribute("health","immunise");
            immunise.add(nodeImmunise);
            someDegreGroupe0 += node.getDegree();
            someDegreGroupe1 += nodeImmunise.getDegree();
        }

        //question 3
        System.out.println("le degré moyen des groupes 0 est :"+someDegreGroupe0/l.size());
        System.out.println("le degré moyen des groupes 1 est :"+someDegreGroupe1/l.size());

        System.out.println("le seuil épidémique du réseau avec la stratégie 3 : "
                + seuil(g.getNodeCount()));

        int k = r.nextInt(immunise.size());//k est pour choisir un node comme le patient zero
        Node patientZero = immunise.get(k);//choisir le premier individu infecté
        patientZero.setAttribute("health","infected");

        ArrayList<Node> infected = new ArrayList<>();//pour stocker les individus infectés qui s'occupe de propager le virus chaque jour
        infected.add(patientZero);
        //un tableau pour stocker les individus de l'etat "healthy" en l'etat "infected" ou de l'etat "infected" en l'etat "healthy"
        ArrayList<Node> temp = new ArrayList<>();

        for(int i=0;i<nbJours;i++){
            for(Node node:infected){//parcourir tous les individus infectes
                if(!temp.contains(node)) temp.add(node);
                if(r.nextInt(7)+1==1) {//la probabilité de recevoir le mail pour chaque voisin de l'individu infecté est 1/7
                    for(Edge e:node){
                        Node voisin = e.getOpposite(node);//obtenir tous les voisins de noeud node
                        if(!temp.contains(voisin) && !l.contains(voisin)){
                            voisin.setAttribute("health","infected");
                            temp.add(voisin);
                        }
                    }
                }
            }
            //vider le tableau infected,prepare les individus infectés pour lendemain
            infected.clear();
            //mettre à jour
            for(Node node1:temp){
                if(r.nextInt(14)+1==1)
                    node1.setAttribute("health", "healthy");
                else infected.add(node1);
            }
         //   System.out.println("j " + i);

            contenu += (i+1)+" "+infected.size()+"\n";
        }
        return contenu;


    }


    public void saveData(String filename , String liste){
        try {
            FileWriter file = new FileWriter("Data/"+filename+".dat");
                file.write(liste);

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public double seuil(int nb) {
        int[] dd = Toolkit.degreeDistribution(this.getG());
        double degreMoyen = Toolkit.averageDegree(this.getG());
        double degreCarreMoyen = 0;
        for (int i = 0; i < dd.length; i++) {
            if (dd[i] != 0)
                degreCarreMoyen += Math.pow(i, 2) * ((double) dd[i] / nb);
        }
        return degreMoyen / degreCarreMoyen;
    }


}
