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



    }
}
