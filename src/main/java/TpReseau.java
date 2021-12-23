import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.BreadthFirstIterator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSourceEdge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static org.graphstream.algorithm.Toolkit.*;

public class TpReseau {
    public static void main(String [] args) {



        Graph g = new DefaultGraph("G");
        FileSourceEdge fs = new FileSourceEdge();

        fs.addSink(g);

        try {
            fs.readAll("/home/idres/Master/RI/data.txt");
        } catch (IOException e) {
            System.out.println("fichier introuvable");

        }
        /*Pour afficher les données */
        //      System.setProperty("org.graphstream.ui", "swing");
//        g.display();
       System.out.println("Le nombre de noeud :" + g.getNodeCount());
        System.out.println("Le degré moyen :" + averageDegree(g));
        System.out.println("Le coefficient de clustering :" + averageClusteringCoefficient(g));
        System.out.println("Le coefficient de clustering pour un réseau aléatoire de la même taille et du même degré moyen :" + averageDegree(g) / g.getNodeCount());

        if (isConnected(g))
            System.out.println("le graphe est connex");
        else
            System.out.println("le graphe n'est pas connex");


        System.out.println("un réseau aleatoire de la même taille et degré moyen sera-t-il connex ? =>  NON");



        int[] destDeg = degreeDistribution(g);
        String filename = "destDEG.dat";
        try {
            String filepath = System.getProperty("user.dir") + File.separator + filename;
            FileWriter fw = new FileWriter(filepath);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < destDeg.length; i++) {
                String line = "";
                if (destDeg[i] != 0) {
                    bw.write(String.format(Locale.US, "%6d%20.8f%n", i, (double) destDeg[i] / g.getNodeCount()));
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Node> l = randomNodeSet(g, 1000);
        HashMap<Integer, Integer> distancesMap = new HashMap<Integer, Integer>();
        double distance = 0;
        int nb = 0;
        for (int i = 0; i < 1000; i++) {
            BreadthFirstIterator bfi = new BreadthFirstIterator(l.get(i));
            while (bfi.hasNext()) {
                Node opNode = bfi.next();
                int key = bfi.getDepthOf(opNode);
                if (distancesMap.containsKey(key)) {
                    int t = distancesMap.get(key);
                    int value = 1 + t;
                    distancesMap.put(key, value);
                } else {
                    distancesMap.put(key, 1);
                }
                distance += bfi.getDepthOf(bfi.next());
                nb++;
            }
        }
        double distMoy = distance / (nb);
        System.out.println("La distance moyenne calculée avec 1000 noeuds au hasard :" + distMoy);
        System.out.println("La distance moyenne dans un réseau aléatoire avec les mêmes caractéristiques est :" + Math.log(g.getNodeCount()) / Math.log(averageDegree(g)));

        String filename1 = "distances.dat";

        try {
            String filepath = System.getProperty("user.dir") + File.separator + filename1;
            FileWriter fw = new FileWriter(filepath);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Integer name : distancesMap.keySet()) {
                bw.write(String.format(Locale.US, "%6d%20.8f%n", name, (double) distancesMap.get(name) / nb));

            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
