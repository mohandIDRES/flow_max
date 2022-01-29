import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceEdge;

import java.io.IOException;

import static org.graphstream.algorithm.Toolkit.averageClusteringCoefficient;
import static org.graphstream.algorithm.Toolkit.averageDegree;

public class Propagation {
    private Graph g;

    public Propagation(Graph g) throws IOException {
        this.g = g;
    }

    public Graph getG(){
        return this.g;
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Propagation dans des réseaux :");
        Graph g = new DefaultGraph("g");
        FileSource fs = new FileSourceEdge();
        fs.addSink(g);
        try {
            fs.readAll("data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fs.removeSink(g);
        }
        Propagation p = new Propagation(g);
        System.out.println("La question numéro 1: ");
        double beta = 1. / 7;//probabilité de transmission dans une unité de temps
        double mu = 1. / 14;//le taux pour redevenir susceptibles
        System.out.println("Le taux de propagation du virus est : " + beta / mu);
        System.out.println("Le seuil épidémique du réseau : " + p.seuil(g.getNodeCount()));
        double degreMoyen = Toolkit.averageDegree(g);//Le degré moyen de ce graphe
        System.out.println("Le seuil théorique d'un réseau aléatoire du même degré moyen : " + 1 / (degreMoyen + 1));


        Simulations simulations = new Simulations();

        simulations.saveData("Scenario_1" ,   simulations.simulation1(g));

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