import java.io.*;
import java.util.*;
/* 문제 1260
 <문제>
 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 
 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 
 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.
 
 <입력>
 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 
 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 
 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.
 
 <출력>
 첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
*/
public class firstSearch {

	static int N, M, start;

	static class Graph {
		private int V; // 노드의 개수
		private LinkedList<Integer> adj[]; // 인접 리스트
		private boolean visited[];

		Graph(int v) {
			this.V = v;
			this.adj = new LinkedList[v];
			this.visited = new boolean[V];

			for (int i = 0; i < v; ++i) // 인접 리스트 초기화
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
			for (int i = 0; i < v; ++i) // 인접 리스트 초기화
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
