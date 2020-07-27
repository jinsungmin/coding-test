import java.util.*;
import java.io.*;


/*
   문제 2644
 <문제>
   우리 나라는 가족 혹은 친척들 사이의 관계를 촌수라는 단위로 표현하는 독특한 문화를 가지고 있다. 
   이러한 촌수는 다음과 같은 방식으로 계산된다. 기본적으로 부모와 자식 사이를 1촌으로 정의하고 이로부터 사람들 간의 촌수를 계산한다. 
   예를 들면 나와 아버지, 아버지와 할아버지는 각각 1촌으로 나와 할아버지는 2촌이 되고, 
   아버지 형제들과 할아버지는 1촌, 나와 아버지 형제들과는 3촌이 된다.
   여러 사람들에 대한 부모 자식들 간의 관계가 주어졌을 때, 주어진 두 사람의 촌수를 계산하는 프로그램을 작성하시오.
 <입력>
  사람들은 1, 2, 3, …, n (1≤n≤100)의 연속된 번호로 각각 표시된다. 입력 파일의 첫째 줄에는 전체 사람의 수 n이 주어지고, 
  둘째 줄에는 촌수를 계산해야 하는 서로 다른 두 사람의 번호가 주어진다. 그리고 셋째 줄에는 부모 자식들 간의 관계의 개수 m이 주어진다. 
  넷째 줄부터는 부모 자식간의 관계를 나타내는 두 번호 x,y가 각 줄에 나온다. 이때 앞에 나오는 번호 x는 뒤에 나오는 정수 y의 부모 번호를 나타낸다.
  각 사람의 부모는 최대 한 명만 주어진다.
 <출력>
 입력에서 요구한 두 사람의 촌수를 나타내는 정수를 출력한다. 어떤 경우에는 두 사람의 친척 관계가 전혀 없어 촌수를 계산할 수 없을 때가 있다. 이때에는 -1을 출력해야 한다.
   
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

			for (int i = 0; i < v; ++i) // 인접 리스트 초기화
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
