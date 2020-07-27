import java.util.*;
import java.io.*;


/*
   ���� 2644
 <����>
   �츮 ����� ���� Ȥ�� ģô�� ������ ���踦 �̼���� ������ ǥ���ϴ� ��Ư�� ��ȭ�� ������ �ִ�. 
   �̷��� �̼��� ������ ���� ������� ���ȴ�. �⺻������ �θ�� �ڽ� ���̸� 1������ �����ϰ� �̷κ��� ����� ���� �̼��� ����Ѵ�. 
   ���� ��� ���� �ƹ���, �ƹ����� �Ҿƹ����� ���� 1������ ���� �Ҿƹ����� 2���� �ǰ�, 
   �ƹ��� ������� �Ҿƹ����� 1��, ���� �ƹ��� ��������� 3���� �ȴ�.
   ���� ����鿡 ���� �θ� �ڽĵ� ���� ���谡 �־����� ��, �־��� �� ����� �̼��� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
 <�Է�>
  ������� 1, 2, 3, ��, n (1��n��100)�� ���ӵ� ��ȣ�� ���� ǥ�õȴ�. �Է� ������ ù° �ٿ��� ��ü ����� �� n�� �־�����, 
  ��° �ٿ��� �̼��� ����ؾ� �ϴ� ���� �ٸ� �� ����� ��ȣ�� �־�����. �׸��� ��° �ٿ��� �θ� �ڽĵ� ���� ������ ���� m�� �־�����. 
  ��° �ٺ��ʹ� �θ� �ڽİ��� ���踦 ��Ÿ���� �� ��ȣ x,y�� �� �ٿ� ���´�. �̶� �տ� ������ ��ȣ x�� �ڿ� ������ ���� y�� �θ� ��ȣ�� ��Ÿ����.
  �� ����� �θ�� �ִ� �� �� �־�����.
 <���>
 �Է¿��� �䱸�� �� ����� �̼��� ��Ÿ���� ������ ����Ѵ�. � ��쿡�� �� ����� ģô ���谡 ���� ���� �̼��� ����� �� ���� ���� �ִ�. �̶����� -1�� ����ؾ� �Ѵ�.
   
*/

public class CacRL {
	static class human {
		static class Infor {
			int pos;
			int dist;

			public Infor(int pos, int dist) {
				this.pos = pos;
				this.dist = dist;
			}
		}
		
		private int V;
		private LinkedList<Integer> adj[];
		private boolean visited[];
		
		human (int v) {
			this.V = v;
			this.adj = new LinkedList[v];
			this.visited = new boolean[V];

			for (int i = 0; i < v; ++i) // ���� ����Ʈ �ʱ�ȭ
				adj[i] = new LinkedList();
		}
		
		
		void addEdge(int v, int w) {
			adj[v].add(w);
		}
		
		void initVisited(int v) {
			for (int i = 0; i < v; i++) {
				visited[i] = false;
			}
		}
		
		void BFS(int start, int end) {
			LinkedList<Infor> queue = new LinkedList<Infor>();
			this.initVisited(this.V);
			int curDist = 0;
			
			visited[start] = true;
			queue.add(new Infor(start, curDist));

			while (queue.size() != 0) {
				Infor temp = (Infor) queue.poll();
				start = temp.pos;
				curDist = temp.dist;
				
				if(start == end) {
					System.out.println(curDist);
					break;
				}

				Iterator<Integer> i = adj[start].listIterator();
				while (i.hasNext()) {
					int n = i.next();

					if (!visited[n]) {
						visited[n] = true;
						queue.add(new Infor(n, curDist + 1));
					}
				}
			}
			if(start != end) {
				System.out.println("1");
			}
		}
	}
	
	static int humanNumber;
	static int A, B;
	static int lineCount;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		humanNumber = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		lineCount = Integer.parseInt(st.nextToken());
		
		human human = new human(humanNumber);
		for(int i = 0; i< lineCount; i++) {
			st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			human.addEdge(v - 1, w - 1);
			human.addEdge(w - 1, v - 1);
		}
		human.BFS(A - 1, B - 1);
		
	}

}
