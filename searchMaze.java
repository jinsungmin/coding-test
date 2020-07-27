import java.io.*;
import java.util.*;

/* 문제 2178
  <문제>
  	미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 
  	이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 
  	한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
  <입력>
  	첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.
  <출력>
  	첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
  
*/

public class searchMaze {

	static class Pos {
		int row;
		int col;
		int dist;

		public Pos(int row, int col, int dist) {
			this.row = row;
			this.col = col;
			this.dist = dist;
		}
	}

	static int N, M;
	static int map[][];
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };
	static boolean visited[][];
	static int shortestDist = 0;

	static boolean isInside(int y, int x) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}

	private static int bfs() {
		int ret = 0;

		int curRow = 0;
		int curCol = 0;
		int curDist = 1;

		LinkedList<Pos> queue = new LinkedList<Pos>();
		queue.add(new Pos(curRow, curCol, curDist));

		while (!queue.isEmpty()) {
			Pos pos = (Pos) queue.poll();
			curRow = pos.row;
			curCol = pos.col;
			curDist = pos.dist;

			ret = curDist;
			
			visited[curRow][curCol] = true;

			// 목적지에 도착하면 루핑 종료
			if (curRow == N - 1 && curCol == M - 1) {
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				if (isInside(curRow + dy[i], curCol + dx[i]) && map[curRow + dy[i]][curCol + dx[i]] == 1
						&& !visited[curRow + dy[i]][curCol + dx[i]]) {
					queue.add(new Pos(curRow + dy[i], curCol + dx[i], curDist+1));
					visited[curRow + dy[i]][curCol + dx[i]] = true;
				}
			}
		}
		
		queue.clear();
        
        return ret;
	}

	static void dfs(Pos pos) {
		visited[pos.row][pos.col] = true;

		if (pos.row == N - 1 && pos.col == M - 1) {
			if (pos.dist < shortestDist)
				shortestDist = pos.dist;
		}

		for (int i = 0; i < 4; i++) {
			if (isInside(pos.row + dy[i], pos.col + dx[i]) && map[pos.row + dy[i]][pos.col + dx[i]] == 1
					&& !visited[pos.row + dy[i]][pos.col + dx[i]]) {
				dfs(new Pos(pos.row + dy[i], pos.col + dx[i], pos.dist + 1));
			}
		}
		visited[pos.row][pos.col] = false;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N][M];

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken().toString();

			for (int j = 0; j < str.length(); j++) {
				map[i][j] = (int) str.charAt(j) - 48;
			}
		}
		/*
		shortestDist = Integer.MAX_VALUE;
		dfs(new Pos(0, 0, 1));
		System.out.println(shortestDist);
		*/
		
		int dep = bfs();
		System.out.println(dep);
		
	}

}
