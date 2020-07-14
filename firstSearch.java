import java.io.*;
import java.util.*;
/* ���� 1260
 <����>
 �׷����� DFS�� Ž���� ����� BFS�� Ž���� ����� ����ϴ� ���α׷��� �ۼ��Ͻÿ�. 
 ��, �湮�� �� �ִ� ������ ���� ���� ��쿡�� ���� ��ȣ�� ���� ���� ���� �湮�ϰ�, 
 �� �̻� �湮�� �� �ִ� ���� ���� ��� �����Ѵ�. ���� ��ȣ�� 1������ N�������̴�.
 
 <�Է�>
 ù° �ٿ� ������ ���� N(1 �� N �� 1,000), ������ ���� M(1 �� M �� 10,000), 
 Ž���� ������ ������ ��ȣ V�� �־�����. ���� M���� �ٿ��� ������ �����ϴ� �� ������ ��ȣ�� �־�����. 
 � �� ���� ���̿� ���� ���� ������ ���� �� �ִ�. �Է����� �־����� ������ ������̴�.
 
 <���>
 ù° �ٿ� DFS�� ������ �����, �� ���� �ٿ��� BFS�� ������ ����� ����Ѵ�. V���� �湮�� ���� ������� ����ϸ� �ȴ�.
*/
public class firstSearch {

	static int N, M, start;

	static class Graph {
		private int V; // ����� ����
		private LinkedList<Integer> adj[]; // ���� ����Ʈ
		private boolean visited[];

		Graph(int v) {
			this.V = v;
			this.adj = new LinkedList[v];
			this.visited = new boolean[V];

			for (int i = 0; i < v; ++i) // ���� ����Ʈ �ʱ�ȭ
				adj[i] = new LinkedList();
		}

		void addEdge(int v, int w) {
			adj[v].add(w);
		}

		public void DFS(int index) {
			visited[index] = true;

			System.out.print(index + 1 + " ");

			Iterator<Integer> u = adj[index].listIterator();
			while (u.hasNext()) {
				int n = u.next();

				if (!visited[n]) {
					DFS(n);
				}
			}

		}

		void initVisited(int v) {
			for (int i = 0; i < v; i++) {
				visited[i] = false;
			}
		}

		void initSorted(int v) {
			for (int i = 0; i < v; ++i) // ���� ����Ʈ �ʱ�ȭ
				Collections.sort(adj[i]);
		}

		void BFS(int s, int v) {
			LinkedList<Integer> queue = new LinkedList<Integer>();
			this.initVisited(this.V);

			visited[s] = true;
			queue.add(s);

			while (queue.size() != 0) {
				s = queue.poll();
				System.out.print(s + 1 + " ");

				Iterator<Integer> i = adj[s].listIterator();
				while (i.hasNext()) {
					int n = i.next();

					if (!visited[n]) {
						visited[n] = true;
						queue.add(n);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());

		Graph g = new Graph(N);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			g.addEdge(v - 1, w - 1);
			g.addEdge(w - 1, v - 1);
		}
		g.initSorted(N);

		g.DFS(start - 1);
		System.out.println();
		g.BFS(start - 1, N);
	}

}
