package space;
import java.awt.Point;

public class run {
	public static void probe(Point s, Point e, int n){
		int start = SpaceGraph.toInt(s, n);
		int end = SpaceGraph.toInt(e, n);
		System.out.println(end);
		SpaceGraph graph = new SpaceGraph(n);
		System.out.println(graph.V());
		Paths search = new Paths(graph, start);
		graph.setData(end, 'E');
		graph.setData(start, 'S');
		if(search.hasPathTo(end)){
			for(int x: search.pathTo(end)){
				if(x == start)	continue;
				graph.setData(x, 'O');
			}
				
		}
		System.out.println(graph);
	}
	public static void main(String args[ ]){
		Point a, b;
		a = new Point(0,0);
		//b = new Point(3,3);
		b = new Point(7,7);
		probe(a, b, 10);
		}
		
	
}
