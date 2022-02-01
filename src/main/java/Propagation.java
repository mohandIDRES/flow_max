import org.graphstream.algorithm.Toolkit;
import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceEdge;

import java.io.IOException;

import static org.graphstream.algorithm.Toolkit.averageClusteringCoefficient;
import static org.graphstream.algorithm.Toolkit.averageDegree;

public class Propagation {


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
        Simulations simulations = new Simulations(g);

        System.out.println("La question numéro 1: ");
        double beta = 1. / 7;//probabilité de transmission dans une unité de temps
        double mu = 1. / 14;//le taux pour redevenir susceptibles
        System.out.println("Le taux de propagation du virus est : " + beta / mu);
        System.out.println("Le seuil épidémique du réseau : " + simulations.seuil(g.getNodeCount()));
        double degreMoyen = Toolkit.averageDegree(g);//Le degré moyen de ce graphe
        System.out.println("Le seuil théorique d'un réseau aléatoire du même degré moyen : " + 1 / (degreMoyen + 1));



      //  simulations.saveData("Scenario_1" ,   simulations.simulation1(g));
      //  simulations.saveData("Scenario_2" , simulations.simulation2(g));


        //simulations.saveData("Scenario_3" ,simulations.simulation3(g));

        System.out.println("Barabasi-Albert");
        Generator generate2 = new BarabasiAlbertGenerator(6);
        Graph graphBA = new SingleGraph("Barabasi-Albert");

        generate2.addSink(graphBA);
        generate2.begin();
        for(int i=0; i < g.getNodeCount(); i++)
            generate2.nextEvents();
        generate2.end();


        simulations.saveData("Scenario_BA" ,simulations.simulation3(graphBA));

        simulations.saveData("Scenario_BA_SC2" ,simulations.simulation2(graphBA));









    }








}