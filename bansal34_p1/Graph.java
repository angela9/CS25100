import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

class Edge {
    int source;
    int destination;
    double weight;

    public Edge(int source, int destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

}

public class Graph {


    int vertices;
    LinkedList<Edge> [] listAdj;

    Graph(int vertices) {
        this.vertices = vertices;
        listAdj = new LinkedList[vertices];

        for (int i = 0; i <vertices ; i++) {
            listAdj[i] = new LinkedList<>();
        }
    }

    public int verticesCount()
    {
        return vertices;
    }

    public int edgeCount()
    {
        int ed=0;
        for (int i = 0; i <vertices ; i++) {
            LinkedList<Edge> list = listAdj[i];
            ed=ed+list.size();
        }


        return ed;
    }

    public void addEgde(int source, int destination, double weight) {
        Edge edge = new Edge(source, destination, weight);
        listAdj[source].addLast(edge);


    }



    public double weight(int u,int v)
    {
        if(u<vertices && v<vertices)
        {
            LinkedList<Edge> indexList = listAdj[u];
            for(int i=0;i<indexList.size();i++)
            {
                if(indexList.get(i).destination==v)
                    return indexList.get(i).weight;

            }
        }
        return -1;
    }







    public void adjacent(int index){

        LinkedList<Edge> indexList = listAdj[index];
        LinkedList<String> strEdge=new LinkedList<>();
        for(int i=0;i<indexList.size();i++)
        {
            strEdge.add(indexList.get(i).destination+"");
        }

        strEdge.sort(Comparator.reverseOrder());
        System.out.print("Adjacent to vertex " +index+":");
        for (int j = indexList.size()-1; j >-1 ; j--) {
            System.out.print(" "+strEdge.get(j));
        }
        if(indexList.size()==0)
            System.out.print(" none");
        System.out.println("");

    }



    public void matrix()
    {

        System.out.print("  ");
        int decimal=0;

        for(int i=0;i<vertices;i++){

            LinkedList<Edge> indexList = listAdj[i];
            for(int j=0;j<indexList.size();j++)
            {
                double d=indexList.get(j).weight;

                if((d-(int)d)!=0){
                    decimal=1;
                    break;
                }
            }
        }

        for(int i=0;i<vertices;i++)
        {
            if(decimal==0) {
                System.out.print(i + " ");
            }
            else {
                System.out.print(i + "     ");
            }
        }

        System.out.println(" ");
        for(int i=0;i<vertices;i++)
        {
            System.out.print(i+" ");

            LinkedList<Edge> indexList = listAdj[i];
            LinkedList<String> strEdge=new LinkedList<>();
            LinkedList<Double> weightEdge=new LinkedList<>();

            for(int j=0;j<indexList.size();j++)
            {
                strEdge.add(indexList.get(j).destination+"");
                weightEdge.add(indexList.get(j).weight);

            }
            for(int k=0;k<vertices;k++)
            {
                if(strEdge.contains(k+"")==true)
                {
                    int index=strEdge.indexOf(k+"");

                    double value=(weightEdge.get(index));// weightEdge.get(index);
                    DecimalFormat format = new DecimalFormat("0.###");
                    if (decimal==0)
                        System.out.print(format.format(value)+" ");
                    else
                        System.out.printf("%.3f ", value);

                }
                else
                {
                    if(decimal==0)
                        System.out.print("X ");
                    else
                        System.out.print("X     ");

                }
            }
            System.out.println(" ");

        }
    }

    public void tclosure()
    {
        int matrix[][] = new int[vertices][vertices];

        for(int i=0;i<vertices;i++)
        {
            for(int j=0;j<vertices;j++)
            {
                matrix[i][j]=0;
                if(i==j)
                    matrix[i][j]=1;
            }
        }
        for(int i=0;i<vertices;i++)
        {
            LinkedList<Edge> indexList = listAdj[i];

            for(int j=0;j<indexList.size();j++)
            {
                int k=indexList.get(j).destination;
                matrix[i][k]=1;
            }
        }


        for (int k = 0; k < vertices; k++)
        {
            for (int i = 0; i < vertices; i++)
            {
                for (int j = 0; j < vertices; j++)
                {
                    matrix[i][j] = (matrix[i][j]!=0) ||
                            ((matrix[i][k]!=0) && (matrix[k][j]!=0))?1:0;
                }
            }
        }



        System.out.print("  ");
        for(int i=0;i<vertices;i++)
        {
            System.out.print(i+" ");
        }
        System.out.println(" ");



        for(int i=0;i<vertices;i++)
        {
            System.out.print(i+" ");
            for(int j=0;j<vertices;j++)
            {
                System.out.print(matrix[i][j]+" ");

            }
            System.out.println("");
        }


    }



    public void shoertestPath(int src,int des)
    {
        double matrix[][] = new double[vertices][vertices];

        for(int i=0;i<vertices;i++)
        {
            for(int j=0;j<vertices;j++)
            {
                matrix[i][j]=0;

            }
        }
        for(int i=0;i<vertices;i++)
        {
            LinkedList<Edge> indexList = listAdj[i];

            for(int j=0;j<indexList.size();j++)
            {
                int k=indexList.get(j).destination;
                matrix[i][k]=indexList.get(j).weight;
            }
        }

        double shortDisArr[] = new double[vertices];

        int[] parents = new int[vertices];
        parents[src] = -1;

        Boolean check[] = new Boolean[vertices];


        for(int i = 0; i < vertices; i++)
        {

            shortDisArr[i] = Integer.MAX_VALUE;
            check[i] = false;
        }
        shortDisArr[src] = 0;

        for (int count = 0; count < vertices - 1; count++) {

            double u = minDistance(shortDisArr, check);



            int hh=(int)Math.round(u);
            check[hh] = true;


            for (int v = 0; v < vertices; v++)

            {
                if (!check[v] && matrix[(int)Math.round(u)][v] != 0 &&
                        shortDisArr[(int)Math.round(u)] != Integer.MAX_VALUE && shortDisArr[(int)Math.round(u)] + matrix[(int)Math.round(u)][v] <= shortDisArr[v])
                {
                    shortDisArr[v] = shortDisArr[(int)Math.round(u)] + matrix[(int)Math.round(u)][v];
                    parents[v]=(int)Math.round(u);
                }

            }


        }

        if(shortDisArr[des]<2147483647-1)
        {
            System.out.println("Shortest path from "+src+" to "+des+":");



            System.out.print(src+" ");

            DisplayPath(parents,des);

            System.out.println("");


            DecimalFormat format = new DecimalFormat("0.###");
            System.out.print("Weight of path: ");
            System.out.println(format.format(shortDisArr[des])+" ");

        }
        else
        {
            System.out.println("Path does not exist");
        }




    }
    private void DisplayPath(int parent[], int j)
    {
        if (parent[j] == - 1)
            return;
        DisplayPath(parent, parent[j]);
        System.out.print(j+" ");
    }




    private double minDistance(double shortDisArr[], Boolean check[])
    {
        double min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < vertices; v++)
            if (check[v] == false && shortDisArr[v] <= min) {
                min = shortDisArr[v];
                min_index = v;
            }

        return min_index;
    }



    private static boolean status=true;

    void topologicalSortUtil(boolean[] passedVertices, int[] indegree, ArrayList<Integer> stack)
    {
        boolean flag = false;
        for (int i = 0; i < vertices; i++) {

            if (!passedVertices[i] && indegree[i] == 0) {
                passedVertices[i] = true;
                stack.add(i);
                for (Edge adjacent : this.listAdj[i]) {
                    indegree[adjacent.destination]--;
                }
                topologicalSortUtil(passedVertices, indegree, stack);


                passedVertices[i] = false;
                stack.remove(stack.size() - 1);
                for (Edge adjacent : this.listAdj[i]) {
                    indegree[adjacent.destination]++;
                }

                flag = true;
            }
        }

        if (!flag) {
            if(stack.isEmpty()==true)
                System.out.println("No topological ordering exists");
            else
            {
                if(status==true)
                {
                    System.out.println("Topological orderings:");
                    status=false;
                }

                stack.forEach(i -> System.out.print(i + " "));
                System.out.println("");
            }

        }

    }


    public void tsort()
    {
        boolean[] passedVertices = new boolean[this.vertices];

        int[] indegree = new int[this.vertices];

        for (int i = 0; i < vertices; i++) {

            for (Edge var : this.listAdj[i]) {
                indegree[var.destination]++;
            }
        }

        ArrayList<Integer> stack = new ArrayList<>();

        topologicalSortUtil(passedVertices, indegree, stack);



    }

    private void DFSUtil(int v, boolean passedVertices[])
    {
        passedVertices[v] = true;
        LinkedList<Edge> indexList = listAdj[v];
        for(int i=0;i<indexList.size();i++)
        {
            if (!passedVertices[indexList.get(i).destination])
                DFSUtil( indexList.get(i).destination, passedVertices);

        }
    }


    public boolean sconn()
    {
        for (int i = 0; i < vertices; i++)
        {

            boolean[] passedVertices = new boolean[vertices];


            DFSUtil( i, passedVertices);

            for (boolean b: passedVertices)
                if (!b)
                    return false;
        }
        return true;
    }

    void DFSUtilCom(int v, boolean[] passedVertices) {
        passedVertices[v] = true;
        System.out.print(v+" ");
        LinkedList<Edge> indexList = listAdj[v];
        for(int i=0;i<indexList.size();i++)
        {
            if (!passedVertices[indexList.get(i).destination])
                DFSUtilCom( indexList.get(i).destination, passedVertices);
        }

    }
    public void components()
    {
        boolean[] passedVertices = new boolean[vertices];
        for(int v = 0; v < vertices; ++v) {
            if(!passedVertices[v]) {
                DFSUtilCom(v,passedVertices);
                System.out.println();
            }
        }
    }


    public void simple()
    {
        for(int i=0;i<vertices;i++)
        {
            LinkedList<Edge> indexList = listAdj[i];
            for(int j=0;j<indexList.size();j++)
            {
                if (indexList.get(j).destination==i)
                {
                    System.out.println("Graph is not simple");
                    return;
                }

            }
            for(int j=0;j<indexList.size();j++)
            {
                Edge ed=indexList.get(j);
                for(int k=j+1;k<indexList.size();k++)
                {
                    Edge ed2=indexList.get(k);
                    if(ed.source==ed2.source && ed.destination==ed2.destination)
                    {
                        System.out.println("Graph is not simple");
                        return;
                    }
                }
            }

        }
        System.out.println("Graph is simple");
    }
}


