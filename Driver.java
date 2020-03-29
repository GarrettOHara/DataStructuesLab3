package cs310Lab3;
import java.util.*;

public class Driver extends InputReader
{
    public static void main(String [] args)
    {
        String commandArgument = args[0];
        Graph graph = new Graph();
        reader(vertex, commandArgument);
        graph.printMap();
        System.out.println("Number of verticies: " + vertexNumber(vertex));
        heaviest(vertex);
        inbound(vertex);
        outbound(vertex);
        self(vertex);
    }
    public static int vertexNumber(Map<String, HashMap<String, 
            Integer>> vertex)
    {
        int vertexNumber = 0;
        for (@SuppressWarnings("unused") Map.Entry<String, 
                HashMap<String,Integer>> entry : 
            vertex.entrySet())
        {
            vertexNumber++;
        }
        return vertexNumber;
    }
    public static void heaviest(Map<String, HashMap<String, Integer>> vertex)
    {
        ArrayList<String> heaviestEdge = new ArrayList<String>();
        int heaviest = 0;
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
                Integer weight = destinations.get(destination);
                int weightCompare = (Integer) weight;
                if (weightCompare > heaviest)
                {
                    heaviestEdge.clear();
                    heaviest = weightCompare;
                    heaviestEdge.add(destination);
                }
                else if (weightCompare == heaviest)
                    heaviestEdge.add(destination);
            }
        }
        if (heaviestEdge.size()==1)
        {
            System.out.print("The heaviest edge is: ");
            printer(heaviestEdge);
        }
        if(heaviestEdge.size()>1)
        {
            System.out.print("The heaviest edges are: ");
            printer(heaviestEdge);
        }

    }
    public static void inbound(Map<String, HashMap<String, Integer>> vertex)
    {
        ArrayList<String> vertexList = vertexList(vertex);

        Iterator<String> vertexIterator = vertex.keySet().iterator();
        while (vertexIterator.hasNext())
        {
            int index = 0;
            String singleVertex = vertexIterator.next();
            HashMap<String, Integer> destinations = vertex.get(singleVertex);            
            Iterator<String> destinationIterator = 
                    destinations.keySet().iterator();
            while (destinationIterator.hasNext())
            {
                String destination = destinationIterator.next();
                if(destination.equalsIgnoreCase(vertexList.get(index)))
                {
                    vertexList.remove(index);
                }
            }
            index++;
        }
        if(vertexList.size() == 0)
            System.out.println("There are no verticies without inbound edges");
        else
        {
            System.out.print("Verticies with no inbound edges: ");
            printer(vertexList);
        }
    }
    public static void outbound(Map<String, HashMap<String, Integer>> vertex)
    {
       ArrayList<String> vertexList = vertexList(vertex);
       ArrayList<String> noOutbound = new ArrayList<String>();
       Boolean found = false;
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
               for (int index = 0; index < vertexList.size(); index++)
               {
                   if(destination.equalsIgnoreCase(vertexList.get(index)))
                   {
                       found = true;
                   }
                   if(index == vertexList.size()-1 && found == false)
                       noOutbound.add(destination);
                   else if (index == vertexList.size()-1)
                       found = false;
               }
           }
       }
       if(noOutbound.size() == 0)
       {
           System.out.println("There are no verticies without outbound edges");
       }
       else
       {
           System.out.print("Vertices with no outbound edges: ");
           printer(noOutbound);
       }
    }
    public static void self(Map<String, HashMap<String, Integer>> vertex)
    {
        ArrayList<String> self = new ArrayList<String>();
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
                if(singleVertex.equals(destination))
                    self.add(singleVertex);
            }
        }
        if(self.size() == 0)
        {
            System.out.println("There are no verticies with self edges");
        }
        else
        {
            System.out.print("The list of self verticies is: ");
            printer(self);
        }
    }
    private static ArrayList<String> vertexList(Map<String, 
            HashMap<String, Integer>> vertex)
    {
        ArrayList<String> vertexList = new ArrayList<String>();
        for (Map.Entry<String, HashMap<String, Integer>> verticies : 
            vertex.entrySet())
        {
            vertexList.add(verticies.getKey());
        }
        return vertexList;
    }
    private static void printer(ArrayList<String> list)
    {
        if (list.size() > 0)
        {
            if(list.size() > 1)
            {
                for (int i = 0; i < list.size(); i++)
                {
                    System.out.print(list.get(i) + ", ");
                }
                System.out.println();
            }
            else
            {
                for (int i = 0; i < list.size(); i++)
                {
                    System.out.print(list.get(i));
                }
                System.out.println();
            }
        }
    }
}