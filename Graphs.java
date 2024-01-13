import java.util.*;

public class Graphs{

    public static HashMap<Integer,Integer> BFS(ArrayList<ArrayList<Integer>> G){
        ArrayList<Integer> vertices = getVertices(G);
        ArrayList<ArrayList<Integer>> edges = getEdges(G);
        ArrayList<Integer> visitedVertices = new ArrayList<Integer>();
        LinkedList<Integer> verticesQueue = new LinkedList<Integer>();
        LinkedList<Integer> levelQueue = new LinkedList<Integer>();
        HashMap<Integer,Integer> vertexLevel = new HashMap<>();

        if(!vertices.isEmpty()){
            verticesQueue.addLast(vertices.get(0));
            levelQueue.addLast(0);
            visitedVertices.add(vertices.get(0)); 
        }
            
        while(!verticesQueue.isEmpty()){
            for(ArrayList<Integer> edge : edges){
                if(edge.get(0) == verticesQueue.get(0)){
                    if(!visitedVertices.contains(edge.get(1))){
                        verticesQueue.addLast(edge.get(1));
                        levelQueue.addLast(levelQueue.getFirst()+1);
                        visitedVertices.add(edge.get(1));
                    }  
                }                    
            }
           vertexLevel.put(verticesQueue.removeFirst(), levelQueue.removeFirst());
        }
        
        return vertexLevel;
    }

    public static HashMap<Integer, ArrayList<Integer>> DFS(ArrayList<ArrayList<Integer>> G){
        ArrayList<Integer> vertices = getVertices(G);
        ArrayList<ArrayList<Integer>> edges = getEdges(G);
        ArrayList<Integer> visitedVertices = new ArrayList<Integer>();
        ArrayList<String> cycles = new ArrayList<String>();
        HashMap<Integer, ArrayList<Integer>> vertexStartFinish = intializeStartFinish(vertices);
        Stack<Integer> currSearch = new Stack<Integer>();
        int currVertex, count = 1, index = 0;

        for(int vertex : vertices){
            if(!visitedVertices.contains(vertex)){
                currSearch.push(vertex);
                currVertex = currSearch.peek();
                visitedVertices.add(currVertex);
                ArrayList<Integer> startFinish = vertexStartFinish.get(currVertex);
                startFinish.add(count);
                vertexStartFinish.put(currVertex, startFinish);
                count++;
                while(!currSearch.empty()){
                    currVertex = currSearch.peek();
                    while(index < edges.size()){
                        if(edges.get(index).get(0) == currVertex ){
                            int neighbor = edges.get(index).get(1);
                            if(!visitedVertices.contains(neighbor)){
                                currVertex = edges.get(index).get(1);
                                visitedVertices.add(currVertex);
                                currSearch.push(currVertex);
                                startFinish = vertexStartFinish.get(currVertex);
                                startFinish.add(count);
                                vertexStartFinish.put(currVertex, startFinish);
                                count++;
                                index = 0;
                            } else if (currSearch.contains(neighbor)) {
                                // Back edge found, indicating a cycle
                                String cycle = "";
                                System.out.println("Cycle found:");
                                for (int i = currSearch.indexOf(neighbor); i < currSearch.size(); i++) {
                                    cycle += currSearch.get(i);
                                    System.out.print(currSearch.get(i) + " ");
                                }
                                cycle += neighbor;
                                cycles.add(cycle);
                                System.out.println(neighbor);
                            }  
                        }
                        index++;
                    }
                    index = 0;
                    startFinish = vertexStartFinish.get(currVertex);
                    startFinish.add(count);
                    vertexStartFinish.put(currVertex, startFinish);
                    count++;
                    currSearch.pop();
                }
            }
        }
        System.out.println("Bipartiteness: " + checkBipartiteness(cycles));
        return vertexStartFinish;
    }

    public static boolean checkBipartiteness(ArrayList<String> cycles){
        for(String s : cycles){
            if((s.length()-1) % 2 == 1){
                return false;
            }
        }
        return true;
    }

    public static void drawDFSTree(ArrayList<Integer> vertices, HashMap<Integer, ArrayList<Integer>> vertexStartFinish){
        String tree = "";


    }

    public static ArrayList<Integer> getVertices(ArrayList<ArrayList<Integer>> G){
        ArrayList<Integer> vertices = new ArrayList<Integer>();
        for (ArrayList<Integer> arrayList : G) {
            vertices.add(arrayList.get(0));
        }
        return vertices;
    }

    public static ArrayList<ArrayList<Integer>> getEdges(ArrayList<ArrayList<Integer>> G){
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < G.size(); i++){
            for(int j = 1; j < G.get(i).size(); j++){
                ArrayList<Integer> edge = new ArrayList<Integer>();
                edge.add(G.get(i).get(0));
                edge.add(G.get(i).get(j));
                edges.add(edge);
            }
        }
        return edges;
    }

    public static HashMap<Integer, ArrayList<Integer>> intializeStartFinish(ArrayList<Integer> vertices){
        HashMap<Integer, ArrayList<Integer>> result = new HashMap<>();
        for(int vertex: vertices){
            ArrayList<Integer> startFinish = new ArrayList<>();
            result.put(vertex,startFinish);
        }
        return result;
    }
    
    public static void main(String[] args){
        ArrayList<ArrayList<Integer>> G = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(1);
        array.add(3);
        array.add(4);
        G.add(array);
        array = new ArrayList<Integer>();
        array.add(2);
        array.add(1);
        array.add(3);
        G.add(array);
        array = new ArrayList<Integer>();
        array.add(3);
        array.add(4);
        G.add(array);
        array = new ArrayList<Integer>();
        array.add(4);
        array.add(1);
        array.add(2);
        G.add(array);

        System.out.println("DFS Vertex START AND FINISH:"  + DFS(G));
        System.out.println("BFS Vertix Level:"  + BFS(G));
    }
}