package space;
import java.util.Stack;
import java.util.ArrayDeque;

public class Paths {
//searches recursively (depth first) for paths and then, determines paths to the point
//indicated by the user
	private int[] edgeTo;
	private boolean[] marked;
	private final int s;
	private SpaceGraph graph;
	public Paths(SpaceGraph G, int s){
		this.graph = G;
		marked = new boolean[graph.V()];
		edgeTo = new int[graph.V()];
		this.s = s;
		bfs(graph, s);
	}
	private void bfs(SpaceGraph G, int s){
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		marked[s] = true;
		queue.add(s);
		if(!noGo(s))	graph.setData(s, 'S');
		else{
			System.out.println("This is an invalid starting point, as it is already occupied by an object");
			System.out.println("Repopulating the the space environment");
			System.out.println("");
			while(noGo(s)){
				SpaceGraph.clearData();
			}
			graph.setData(s, 'S');
			//return;
		}

		while(!queue.isEmpty()){
			int v = queue.remove();
			for(int w: graph.adj(v)){
				if(noGo(w))	continue;
				else if(!marked[w]){
					edgeTo[w] = v;
					marked[w] = true;
					queue.add(w);
				}
					//break;
				}
			//System.out.println("there are no available paths for this source node");
			//return;
			}
		}
		

	
	public boolean hasPathTo(int v){ return marked[v];}
	private boolean noGo(int v){
		// && ((graph.getData(w) != 'G') && (graph.getData(w) != 'A') && (G.getData(w) != 'X'))
		char[] bad = {'A', 'G', 'X'};
		for(char a: bad)
			if(graph.getData(v) == a) return true; //noGo
		return false; //goGo
	}
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v))	return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
}
