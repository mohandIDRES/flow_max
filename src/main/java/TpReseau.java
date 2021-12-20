import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSourceEdge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import static org.graphstream.algorithm.Toolkit.*;

public class TpReseau {
    public static void main(String [] args) {

        Graph g = new DefaultGraph("G") ;
        FileSourceEdge fs = new FileSourceEdge();

        fs.addSink(g);

        try {
            fs.readAll("/home/idres/Master/RI/data.txt");
        } catch( IOException e) {
            System.out.println("fichier introuvable");

        }
        /*Pour afficher les données */
  //      System.setProperty("org.graphstream.ui", "swing");
//        g.display();
        System.out.println("Le nombre de noeud :"+g.getNodeCount());
        System.out.println("Le degré moyen :"+averageDegree(g));
        System.out.println("Le coefficient de clustering :"+averageClusteringCoefficient(g));
        System.out.println("Le coefficient de clustering pour un réseau aléatoire de la même taille et du même degré moyen :"+averageDegree(g)/g.getNodeCount());

        if(isConnected(g))
            System.out.println("le graphe est connex");
        else
            System.out.println("le graphe n'est pas connex");


        System.out.println("un réseau aleatoire de la même taille et degré moyen sera-t-il connex ? =>  NON");

        int[] destDeg = degreeDistribution(g);
        String filename = "destDEG.dat";
        try {
            String filepath=System.getProperty("user.dir")+ File.separator+filename;
            FileWriter fw = new FileWriter(filepath);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < destDeg.length; i++) {
                String line = "";
                if (destDeg[i] != 0) {
                    bw.write(String.format(Locale.US, "%6d%20.8f%n", i, (double)destDeg[i] / g.getNodeCount()));
                }
            }
            bw.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
