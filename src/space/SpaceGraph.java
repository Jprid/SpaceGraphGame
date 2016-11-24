package space;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class SpaceGraph {
	//builds the space (graph) that the user interacts with
	private static int n;	
	//creates square graphs--n, (n-1 for arrays), is row and column length
	private static int n2;
	private int Etest;
	private int E = 0;
	private static Point[] coord; //needs a pop variable
	private static char[] data;
	public static ArrayList<Integer>[] adj;
	//directional relations based on index
	private static int up(int x){ 
		//finds and returns index above x
		if(x<n-1)		return -1;
		else if(x-n<0)	return -1;
		return x-n;}

	private static int down(int x){
		//finds and returns index below x
		if((x+n)>=n2)	return -1;
		return x+n; }

	private static int left(int x){
		//finds and returns index to left of x, does not wrap back to previous row	
		if((x--<0) || (x%n == n-1) || (x==0))	return -1;
		return x--; }

	private static int right(int x){
		//finds and returns index to the right of x, does not wrap to next row
		if(x%n == n-1)	return -1;
		return x+1; }

	private static int Ul(int x){
		//finds and returns index above and to the right of x	
		if((((x+n) - 1)> n2) || (x == 0) || ((x%10) == 0))	return -1;
		return (x-n) - 1;}

	private static int Ur(int x){	
		//finds index up and to right of index x
		if(x%n == n-1)	return -1;
		return (x-n)+1; }

	private static int Bl(int x){
		//finds and returns index to bottom-left of index x
		if((x == 0) || ((x%n) == 0))			return -1;
		//if((((x-n) - 1) < 0) || (x == 0) || ((x%10) == 0) || (x+n)>=2)	return -1;
		return (x+n) -1; }

	private static int Br(int x){
		//finds and returns index to bottom-right of index x
		if((x%n == n-1) || (((x+n)+1)>=n2))	return -1;
		return (x+n)+1; }
	
	@SuppressWarnings("unchecked")
	public SpaceGraph(int v){
		n = v;
		n2 = n * n;
		System.out.println(n + " " + n2);
		this.Etest = ((v-2)^2 * 8) + (((v-2)*4)*5) + 12;
		adj = ((ArrayList<Integer>[]) new ArrayList[n2]);
		data = new char[n2];
		coord = new Point[n2];
		for(int z = 0; z <= n2-1 ; z++){
			//System.out.println(z);
			adj[z] = new ArrayList<Integer>();
			data[z] = '.';
			popAdj(z);	
		}
		popCoord();
		popNg();
		System.out.println(this);
		//System.out.println("c:" + coord[]);
	}

	private static void popCoord(){
		//create the thing, then find its index position in the 1d array and make the entry
		for(int i = 0; i<n-1; i++)
			for(int j = 0; j<n-1; j++){
				Point temp = new Point(i, j);
				int t = toInt(temp, n);
				coord[t] = temp;
			}
	}

	private static void popAdj(int v){   //takes data index of 
		int[] i = {up(v), down(v), left(v), right(v), Ul(v), Ur(v), Bl(v), Br(v)};
		for(int y: i){
			if((y < 0) || (y>=n2))	continue;	
			adj[v].add(y);
		}
	}

	private static void popNg(){
		//int amt = 0;
		 Random r = new Random();
		 for(int i = 0; i<n2-1; i++){
			float aChance = r.nextFloat();
			float gChance = r.nextFloat();
			/*if( (i == 0) || (i == 36) || (i==99)){
				data[i] = 'G';
				for(int y: adj(i)){
					if( y < 0) continue;
					else if(data[y] == 'G') continue;
					else{
						int d = y;
						data[d] = 'X';
					}					
				}
			}*/
			//continue;
			if(aChance < 0.30f){
				data[i] = 'A';
				//adj[i].set(i, -1);
				
			}
			else if (gChance < 0.05){
				data[i] = 'G';
				for(int y: adj(i)){
					if(data[y] == 'G') continue;
					else{
						int d = y;
						data[d] = 'X';
					}					
				}
				
			}
		}
		 } 
	
	public static void clearData(){
		char[] temp = new char[n2];
		data = temp;
		popNg();
	}

	public static int toInt(Point p, int n){
		return (p.y*n)+p.x;
	}

	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	public char getData(Point p){
		//returns data stored at point p
		return data[toInt(p, n)]; 
		
	}
	public char getData(int x){
		//returns data stored at index x
		return data[x];
	}
	public void setData(int x, char c){	data[x] = c;}
	public static Iterable<Integer> adj(int v){
		return adj[v];
	}
	public int V(){return n2;}
	public int E(){return this.E;}
	public String toString(){
		String pSpace = " ";
		pSpace += "\n";
		//pSpace += n*"_";
		
		for(int i = 0; i<=n2-1; i++){
			if(i == 0) pSpace += "[ " + (i) + "  ]" + "   " + " | ";
			pSpace += "    " + data[i] + "   ";
			if(i%n == n-1){
				if(i == n2-1){
					pSpace += " | " + "   " + "[ " + i + " ]";
					pSpace += "\n";
					pSpace += "\n";
					//pSpace += "____________________________________________________";
				}
				else{
					//pSpace += data[i];
				
				pSpace += " | " + "   " + "[ " + i + " ]";
				pSpace += "\n";
				pSpace += "\n";
				pSpace += "\n";
				pSpace += "[ " + (i+1) + " ]" + "   "  + " | ";
				}
			}
		}
		
		return pSpace;
	}
	//Search
}
