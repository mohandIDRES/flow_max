import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.BreadthFirstIterator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static org.graphstream.algorithm.Toolkit.*;

public class BarabasiAlbert {
    public static void main(String [] args) {

        System.out.println("* Creation Reseau Barabasi-Albert");
        Graph graph = new SingleGraph("BAN");
        Generator gen = new BarabasiAlbertGenerator(6); // création d'un réseau dont chaque noeud à un degré entre 1 et 4

        gen.addSink(graph);
        gen.begin();
        while (graph.getNodeCount() < 31708 && gen.nextEvents());
        gen.end();
        //   System.setProperty("org.graphstream.ui", "swing");
        //  graph.display();

        System.out.println("Creation Reseau random avec la meme taille et degree");
        Graph graphRandom = new SingleGraph("Random");
        Generator randomGraph = new RandomGenerator(6);
        randomGraph.addSink(graphRandom);
        randomGraph.begin();
        while (graphRandom.getNodeCount() < 31708 && randomGraph.nextEvents());
        randomGraph.end() ;


        System.out.println("Nombre de noeuds d'un réseau Barabasi-Albert  : " + graph.getNodeCount());
        System.out.println("Degré moyen d'un réseau Barabasi-Albert " + averageDegree(graph));
        System.out.println("Le coefficient de clustering d'un réseau Barabasi-Albert : " + averageClusteringCoefficient(graph));
        System.out.println("Connexité du graphe Barabasi-Albert : " + isConnected(graph));
        System.out.println("************************************************************");

        System.out.println("Nombre de noeuds d'un réseau Aleatoire  : " + graphRandom.getNodeCount());
        System.out.println("Degré moyen d'un réseau aleatoire" + averageDegree(graphRandom));
        System.out.println("Le coefficient de clustering d'un réseau aleatoire :" + averageClusteringCoefficient(graphRandom));
        System.out.println("Connexité du graphe aléatoire : " + isConnected(graphRandom));

        writDataDest(graph , "destDEGBAL.dat");
        writeDataDistance(graph , "distancesBAL.dat");

        writDataDest(graphRandom , "destDEG_Alea.dat");


    }
    public static void writDataDest(Graph graph , String filename) {
        int[] destDeg = degreeDistribution(graph);
        try {
            String filepath = System.getProperty("user.dir") + File.separator + filename;
            FileWriter fw = new FileWriter(filepath);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < destDeg.length; i++) {
                String line = "";
                if (destDeg[i] != 0) {
                    bw.write(String.format(Locale.US, "%6d%20.8f%n", i, (double) destDeg[i] / graph.getNodeCount()));
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeDataDistance(Graph graph , String filename){
        List<Node> l = randomNodeSet(graph, 1000);
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
        System.out.println("La distance moyenne dans un réseau aléatoire avec les mêmes caractéristiques est :" + Math.log(graph.getNodeCount()) / Math.log(averageDegree(graph)));


        try {
            String filepath = System.getProperty("user.dir") + File.separator + filename;
            FileWriter fw = new FileWriter(filepath);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Integer name : distancesMap.keySet()) {
                bw.write(String.format(Locale.US, "%6d%20.8f%n", name, (double) distancesMap.get(name) / nb));

            }
            bw.close();
        } catch (IOException e) {
        }

    }
    }
