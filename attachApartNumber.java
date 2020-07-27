import java.util.*;
import java.io.*;

/* ���� 2667 - ���� ��ȣ ���̱�
 <����>
 	<�׸� 1>�� ���� ���簢�� ����� ������ �ִ�. 1�� ���� �ִ� ����, 0�� ���� ���� ���� ��Ÿ����. 
 	ö���� �� ������ ������ ����� ������ ������ ������ �����ϰ�, ������ ��ȣ�� ���̷� �Ѵ�. 
 	���⼭ ����Ǿ��ٴ� ���� � ���� �¿�, Ȥ�� �Ʒ����� �ٸ� ���� �ִ� ��츦 ���Ѵ�. 
 	�밢���� ���� �ִ� ���� ����� ���� �ƴϴ�. <�׸� 2>�� <�׸� 1>�� �������� ��ȣ�� ���� ���̴�. 
 	������ �Է��Ͽ� �������� ����ϰ�, �� ������ ���ϴ� ���� ���� ������������ �����Ͽ� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
 <�Է�>
 	ù ��° �ٿ��� ������ ũ�� N(���簢���̹Ƿ� ���ο� ������ ũ��� ������ 5��N��25)�� �Էµǰ�, �� ���� N�ٿ��� ���� N���� �ڷ�(0Ȥ�� 1)�� �Էµȴ�.
 <���>
 	ù ��° �ٿ��� �� �������� ����Ͻÿ�. �׸��� �� ������ ���� ���� ������������ �����Ͽ� �� �ٿ� �ϳ��� ����Ͻÿ�.
*/
public class attachApartNumber {
	
	static class Apart {
		int row;
		int col;
		int count;
		
		public Apart(int row, int col, int count) {
			this.row = row;
			this.col = col;
			this.count = count;
		}
	}
	
	static int N;
	static int map[][];
	static boolean visited[][];
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };
	
	static boolean isInside(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
	
	static int bfs(int row, int col) {
		int curRow = row;
		int curCol = col;
		int curCount = 0;
		
		LinkedList<Apart> queue = new LinkedList<Apart>();
		queue.add(new Apart(curRow,curCol, curCount));
		
		int number = 1;
		
		while (!queue.isEmpty()) {
			Apart apt = (Apart) queue.poll();
			curRow = apt.row;
			curCol = apt.col;
			curCount = apt.count + 1;
			
			visited[curRow][curCol] = true;
			
			for (int i = 0; i < 4; i++) {
				if (isInside(curRow + dr[i], curCol + dc[i]) && map[curRow + dr[i]][curCol + dc[i]] == 1
						&& !visited[curRow + dr[i]][curCol + dc[i]]) {
					queue.add(new Apart(curRow + dr[i], curCol + dc[i], curCount));
					visited[curRow + dr[i]][curCol + dc[i]] = true;
					number++;
				}
			}
		}
		return number;
		
	}
	
	static void search() {
		int apart = 0;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for(int i = 0; i< N; i++) {
			for(int j =0; j< N; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					queue.push(bfs(i,j));
					apart++;
				}
			}
		}
		Collections.sort(queue);
		
		System.out.println(apart);
		for (int item : queue) {
		    System.out.println(item);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken().toString();

			for (int j = 0; j < str.length(); j++) {
				map[i][j] = (int) str.charAt(j) - 48;
			}
		}
		/*
		bfs(0,1);
		bfs(0,4);
		bfs(4,1);
		*/
		search();
		
	}

}
