import java.util.*;
import java.io.*;

/* ���� 2606
   <����>
   	���� ���̷����� �� ���̷����� ��Ʈ��ũ�� ���� ���ĵȴ�. �� ��ǻ�Ͱ� �� ���̷����� �ɸ��� �� ��ǻ�Ϳ� ��Ʈ��ũ �󿡼� ����Ǿ� �ִ� ��� ��ǻ�ʹ� �� ���̷����� �ɸ��� �ȴ�.
	���� ��� 7���� ��ǻ�Ͱ� <�׸� 1>�� ���� ��Ʈ��ũ �󿡼� ����Ǿ� �ִٰ� ����. 
	1�� ��ǻ�Ͱ� �� ���̷����� �ɸ��� �� ���̷����� 2���� 5�� ��ǻ�͸� ���� 3���� 6�� ��ǻ�ͱ��� ���ĵǾ� 2, 3, 5, 6 �� ���� ��ǻ�ʹ� �� ���̷����� �ɸ��� �ȴ�. 
	������ 4���� 7�� ��ǻ�ʹ� 1�� ��ǻ�Ϳ� ��Ʈ��ũ�󿡼� ����Ǿ� ���� �ʱ� ������ ������ ���� �ʴ´�.
	��� �� 1�� ��ǻ�Ͱ� �� ���̷����� �ɷȴ�. ��ǻ���� ���� ��Ʈ��ũ �󿡼� ���� ����Ǿ� �ִ� ������ �־��� ��, 
	1�� ��ǻ�͸� ���� �� ���̷����� �ɸ��� �Ǵ� ��ǻ���� ���� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
   <�Է�>
   	ù° �ٿ��� ��ǻ���� ���� �־�����. ��ǻ���� ���� 100 �����̰� �� ��ǻ�Ϳ��� 1�� ���� ���ʴ�� ��ȣ�� �Ű�����. 
   	��° �ٿ��� ��Ʈ��ũ �󿡼� ���� ����Ǿ� �ִ� ��ǻ�� ���� ���� �־�����. 
   	�̾ �� ����ŭ �� �ٿ� �� �־� ��Ʈ��ũ �󿡼� ���� ����Ǿ� �ִ� ��ǻ���� ��ȣ ���� �־�����.
   <���>
   	1�� ��ǻ�Ͱ� �� ���̷����� �ɷ��� ��, 1�� ��ǻ�͸� ���� �� ���̷����� �ɸ��� �Ǵ� ��ǻ���� ���� ù° �ٿ� ����Ѵ�.
   
*/
public class virus {

	static class CPU {
		private int V; // ����� ����
		private LinkedList<Integer> adj[]; // ���� ����Ʈ
		private boolean visited[];
		
		CPU(int v) {
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
		
		void BFS(int s) {
			LinkedList<Integer> queue = new LinkedList<Integer>();
			this.initVisited(this.V);
			int count = -1;

			visited[s] = true;
			queue.add(s);

			while (queue.size() != 0) {
				s = queue.poll();
				//System.out.print(s + 1 + " ");
				count++;

				Iterator<Integer> i = adj[s].listIterator();
				while (i.hasNext()) {
					int n = i.next();

					if (!visited[n]) {
						visited[n] = true;
						queue.add(n);
					}
				}
			}
			System.out.println(count);
		}
		
	}
	
	static int cpuNumber;
	static int lineCount;
	static int start = 1;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		cpuNumber = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		lineCount = Integer.parseInt(st.nextToken());
		
		CPU cpu = new CPU(cpuNumber);
		for(int i = 0; i< lineCount; i++) {
			st = new StringTokenizer(br.readLine());

			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			cpu.addEdge(v - 1, w - 1);
			cpu.addEdge(w - 1, v - 1);
		}
		cpu.BFS(start - 1);
	}

}
