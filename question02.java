import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
 
public class question02 
{
    public static Map<Integer, List<Integer>> adjacencyList;    
    public static List<Integer> weight1;    
    public static List<Integer> toList;     
    public static List<Integer> fromList;   
    
    public question02(int v)     
    {
        adjacencyList = new HashMap<Integer, List<Integer>>();
        for (int i = 1; i <= v; i++)
            adjacencyList.put(i, new LinkedList<Integer>());
        weight1=new ArrayList<>();
        toList=new ArrayList<>();
        fromList=new ArrayList<>();
    }
 
    public void setEdge(int to, int from,int weight)        
    {
        if (to > adjacencyList.size() || from > adjacencyList.size())
            System.out.println("The vertices does not exists");
 
        List<Integer> sls = adjacencyList.get(to);
        sls.add(from);
        List<Integer> dls = adjacencyList.get(from);
        dls.add(to);
        //System.out.println("weight for to : "+to+" from : "+from +" : "+weight);
        weight1.add(weight);
    }
 
    public static List<Integer> getEdge(int to)         
    {
        if (to > adjacencyList.size()) 
        {
            System.out.println("The vertices does not exists");
            return null;
        }
        return adjacencyList.get(to);
    }
 
    public static void main(String args[]) 
    {
        
        Scanner sc = new Scanner(System.in);       
        System.out.println("Enter the the name of file ");
        String name=sc.nextLine();      
        System.out.println("Enter the number of nodes: ");
        int e = sc.nextInt();       
        
        try 
        {
            PrintWriter out = new PrintWriter(new File(name));      
            int minV = (int) Math.ceil((1 + Math.sqrt(1 + 8 * e)) / 2);     
            int maxV = e + 1;       //calculating the maximum value
 
            Random random = new Random();   
 
            question02 rug = new question02(e);     
            int count = 1, to, from,weight;
            boolean flag=false,flag1=false; 

            
            while (count <= e) 
            {
                flag1=false;
                flag=false;
                to = Math.abs(random.nextInt(e + 1 - 1)+1);
                from = Math.abs(random.nextInt(e + 1 - 1)+1);
                for(int k=0;k<toList.size();k++)
                {
                    if(to==toList.get(k) && from==fromList.get(k)){     
                        flag=true;
                        break;
                    }
                    if(to==fromList.get(k) && from==toList.get(k))  
                    {
                        flag1=true;
                        break;
                    }
                }
                if(flag==true)      
                    continue;
                if(flag1==true)     
                    continue;
                if(to==from)        
                    continue;

                toList.add(to);     
                fromList.add(from); 
                weight = Math.abs(random.nextInt(20))+1;    
                
                rug.setEdge(to,from,weight);       
                count++;
            }
 
            System.out.println("The Adjacency List Representation of the graph is: ");
 
            for(int i = 0; i < toList.size(); i++) {
                System.out.print(toList.get(i)+" -> ");     
                System.out.print(fromList.get(i)+" ");
                System.out.println(weight1.get(i));
                out.write(fromList.get(i)+" "+toList.get(i)+" "+weight1.get(i)+"\n");   
            }
            out.close();
        } 
        catch (Exception E) 
        {
            System.out.println("Something went wrong");
        }
        sc.close();
    }
}