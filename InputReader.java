package cs310Lab3;
import java.util.*;
import java.io.*;

public class InputReader extends Graph
{
    public static int vertexCounter = 0;
    public static void reader (Map<String, HashMap<String, Integer>> vertex,
            String commandArgument)
    {
        String line = "";
        
        Graph graph = new Graph();
        
        try
        {
            FileReader file = new FileReader(commandArgument);
            BufferedReader read = new BufferedReader(file);
            
            while ((line = read.readLine()) != null)
            {
                String [] data = line.split(",");
                if (data.length == 1)
                {
                    graph.addVertex(data[0]);
                }
                if (data.length == 3)
                {
                    if (!vertex.containsKey(data[0]))
                    {
                        graph.addVertex(data[0]);
                    }
                    String source = data[0];
                    String destination = data[1];
                    int cost = Integer.parseInt(data[2]);
                    if(graph.addEdge(source, destination, cost))
                    {
                        graph.addEdge(source, destination, cost);
                    }
                }
            }
            read.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }    
}
