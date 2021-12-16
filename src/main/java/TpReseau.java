import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSourceEdge;

import java.io.IOException;

import static org.graphstream.algorithm.Toolkit.averageClusteringCoefficient;
import static org.graphstream.algorithm.Toolkit.averageDegree;

public class TpReseau {
    public static void main(String [] args) {

        Graph g = new DefaultGraph("G") ;
        FileSourceEdge fs = new FileSourceEdge();

        fs.addSink(g);

        try {
            fs.readAll("/home/idres/Master/RI/data.txt");
        } catch( IOException e) {

        }
        System.out.println("Le nombre de noeud :"+g.getNodeCount());
        System.out.println("Le degré moyen :"+averageDegree(g));
        System.out.println("Le coefficient de clustering :"+averageClusteringCoefficient(g));
        System.out.println("Le coefficient de clustering pour un réseau aléatoire de la même taille et du même degré moyen :"+averageDegree(g)/g.getNodeCount());

        /*
Exo 5
La distance moyenne d'un reseau petit monde
<d> = ln N / ln<K>
6.7
*/
    }
}
