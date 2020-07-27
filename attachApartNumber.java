import java.util.*;
import java.io.*;

/* 문제 2667 - 단지 번호 붙이기
 <문제>
 	<그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 
 	철수는 이 지도를 가지고 연결된 집들의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 
 	여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 
 	대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 
 	지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
 <입력>
 	첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
 <출력>
 	첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
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
