package cs310Lab3;
import java.util.*;

public class Graph
{
    public static Map<String, HashMap<String, Integer>> vertex = 
            new HashMap<String, HashMap<String, Integer>>();
    
    public boolean addEdge(String source, String dest, int cost)
    {
        if (vertex.containsKey(source))
        {
            HashMap<String, Integer> newEdge = new HashMap<String, Integer>();
            newEdge = vertex.get(source);
            newEdge.put(dest, cost);
            vertex.put(source, newEdge);
            return true;
        }
        else
            return false;
    }
    public boolean removeEdges(String source, String destination)
    {
        if (vertex.containsKey(source))
        {
            removeVertex(source);
            addVertex(source);
        }
        return false;
    }
    public void addVertex(String name)
    {
        if (!vertex.containsKey(name)) {
            HashMap<String,Integer> edge = new HashMap<String, Integer>();
            vertex.put(name, edge);
        }
    }
    public boolean removeVertex(String name)
    {
        if (vertex.containsKey(name))
        {
            vertex.remove(name);
            return true;
        }
        else
            return false;
    }
    public boolean contains(String name)
    {
        if (vertex.containsKey(name))
            return true;
        else
            return false;
    }
    public boolean contains(String source, String dest)
    {
        Iterator<String> vertexIterator = vertex.keySet().iterator();
        while (vertexIterator.hasNext())
        { 
            String singleVertex = vertexIterator.next();
            HashMap<String, Integer> destinations = vertex.get(singleVertex);          
            Iterator<String> destinationIterator = 
                    destinations.keySet().iterator();
            while (destinationIterator.hasNext())
            {
                String destination = destinationIterator.next();
                if(source.equals(singleVertex) && dest.equals(destination))
                    return true;
            }
        }
        return false;

    }
    public void printMap()
    {
        for (Map.Entry<String, HashMap<String,Integer>> entry : 
            vertex.entrySet())
        {
            System.out.println(entry.getKey() + ":" + 
        entry.getValue().toString());
        }
    }
}