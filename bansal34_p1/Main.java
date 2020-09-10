import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class Main {

    private static String parseCommand(String line){
        if (line == null) return null;
        //return null;
        return line.split(" ")[0];
    }

    private static int parseOne(String line){
        if (line == null) return -1;
        return Integer.parseInt(line.split(" ")[1]);
    }

    private static int parseTwo(String line){
        if (line == null) return -1;
        return Integer.parseInt(line.split(" ")[2]);
    }

    private static double parseThree(String line){
        if (line == null) return -1;
        return Double.parseDouble(line.split(" ")[3]);
    }

    public static void main(String[] args) {
        /*read arguments from the command line into variables filename*/

        String filename = args[0];
        BufferedReader reader;
        Graph graph ;//= new Graph(6);
        UGraph ugraph;

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            String command = parseCommand(line);

            if (command.equals("dgraph")) {
                int size = parseOne(line); //size = number of vertices
                //TODO: construct a directed graph
                graph = new Graph(size);
                line = reader.readLine();
                while (line != null) {
                    command = parseCommand(line);
                    if (command.equals("add")){
                        int u = parseOne(line); //u is starting vertex
                        int v = parseTwo(line); //v is ending vertex
                        double weight = parseThree(line); //weight is weight
                        //TODO: add edge to graph
                        graph.addEgde(u, v, weight);
                    }
                    else if (command.equals("v")){
                        //TODO: get the number of vertices
                        int c=graph.verticesCount();
                        System.out.println("Number of vertices: "+c);
                    }
                    else if (command.equals("e")){
                        //TODO: get the number of edges
                        int e=graph.edgeCount();
                        System.out.println("Number of edges: "+e);
                    }
                    else if (command.equals("adj")) {
                        int v = parseOne(line); //v is the vertex you want to get the adjacency list for
                        //TODO: get the adjacency list of v
                        graph.adjacent(v);
                    }
                    else if (command.equals("weight")) {
                        int u = parseOne(line);//u is the starting vertex
                        int v = parseTwo(line);//v is the ending vertex
                        //TODO: get the weight of the edge
                        double res=graph.weight(u, v);
                        if(res==-1)
                        {
                            System.out.println("Edge does not exist");
                        }
                        else
                        {
                            DecimalFormat format = new DecimalFormat("0.###");
                            System.out.print("Weight of ("+u+", "+v+"): ");
                            System.out.print(format.format(res));
                            System.out.println("");
                        }

                    }
                    else if (command.equals("matrix")) {
                        //TODO: get the adjacency matrix of the graph
                        System.out.println("Adjacency Matrix:");
                        graph.matrix();

                    }
                    else if (command.equals("tclosure")) {
                        //TODO: get the transitive closure of the graph
                        System.out.println("Transitive Closure:");
                        graph.tclosure();
                    }
                    else if (command.equals("spath")) {
                        int u = parseOne(line);//TODO: u is the starting vertex
                        int v = parseTwo(line);//TODO: v is the ending vertex
                        //TODO: get the shortest path from u to v

                        graph.shoertestPath(u, v);

                    }
                    else if (command.equals("tsort")) {
                        //TODO: get all topological sorts of the graph
                        graph.tsort();

                    }
                    else if (command.equals("sconn")) {
                        //TODO: determine if the graph is strongly connected or not

                        if(graph.sconn())
                            System.out.println("Graph is strongly connected");
                        else
                            System.out.println("Graph is not strongly connected");
                    }
                    else if (command.equals("simple")) {
                        //TODO: determine if the graph is simple or not
                        graph.simple();
                    }
                    else if (command.equals("\n")) {
                        System.out.print("\n");
                    }
                    line = reader.readLine();
                }
            }
            else if (command.equals("ugraph")){
                int size = parseOne(line);//size is number of vertices
                //TODO: Construct an undirected graph

                ugraph = new UGraph(size);
                line = reader.readLine();
                while (line != null ) {
                    command = parseCommand(line);
                    if (command.equals("add")){
                        int u = parseOne(line);//vertex 1
                        int v = parseTwo(line);//vertex 2
                        double weight = parseThree(line);//weight of edge
                        //TODO: add edge to graph
                        ugraph.addEgde(u, v, weight);
                        //graph.addEgde(u, v, weight);

                    }
                    else if (command.equals("v")){
                        //TODO: get the number of vertices
                        int c=ugraph.verticesCount();
                        System.out.println("Number of vertices: "+c);
                    }
                    else if (command.equals("e")){
                        //TODO: get the number of edges
                        int e=ugraph.edgeCount();
                        System.out.println("Number of edges: "+e/2);
                    }
                    else if (command.equals("adj")) {
                        int v = parseOne(line);//the vertex
                        //TODO: get the adjacency list of v
                        ugraph.adjacent(v);

                    }
                    else if (command.equals("weight")) {
                        int u = parseOne(line);//the first vertex
                        int v = parseTwo(line);//the second vertex
                        //TODO: get the weight of edge (u, v)
                        double res=ugraph.weight(u, v);
                        if(res==-1)
                        {
                            System.out.println("Edge does not exist");
                        }
                        else
                        {
                            DecimalFormat format = new DecimalFormat("0.###");
                            System.out.print("Weight of ("+u+", "+v+"): ");
                            System.out.print(format.format(res));
                            System.out.println("");
                        }
                    }
                    else if (command.equals("matrix")) {
                        //TODO: get the adjacency matrix of the graph
                        System.out.println("Adjacency Matrix:");
                        ugraph.matrix();
                    }
                    else if (command.equals("spath")) {
                        int u = parseOne(line);//starting vertex
                        int v = parseTwo(line);//ending vertex
                        //TODO: get the shortest path from u to v
                        ugraph.shoertestPath(u, v);
                    }
                    else if (command.equals("mst")) {
                        //TODO: get all MSTs of the graph
                        ugraph.mst(ugraph);
                    }
                    else if (command.equals("components")) {
                        //TODO: get all the connected components of the graph
                        System.out.println("Connected components:");
                        ugraph.components();
                    }
                    else if (command.equals("simple")) {
                        //TODO: determine if the graph is simple or not
                        ugraph.simple();
                    }
                    line = reader.readLine();
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}