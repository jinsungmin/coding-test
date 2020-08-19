package codingTest;

import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
 	¹®Á¦ 14499 - samsung
 */

public class diceRolling {

	static int N, M, K;
	static int diceRow, diceCol;
	static int[][] map;
	static int[] dir;
	static int[] dice = { 0, 0, 0, 0, 0, 0 };
	static int[] queue = { 0, 1, 2, 3, 4, 5 };
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	static int bottom = -1;
	static int top = -1;
	static int tmp1, tmp2;
	
	static void solution() {
		int curRow = diceRow;
		int curCol = diceCol;
		int count = K;
		while (count > 0) {
			int idx = dir[dir.length - count] - 1;
			
			if (isInside(curRow + dr[idx], curCol + dc[idx])) {
				switch(idx) {
				case 0:
					bottom = queue[2];
					top = queue[3];
					tmp1 = queue[0];
					tmp2 = queue[2];

					queue[0] = queue[3];
					queue[3] = queue[5];
					
					queue[2] = tmp1;
					queue[5] = tmp2;
					break;
				case 1:
					bottom = queue[3];
					top = queue[2];
					tmp1 = queue[0];
					tmp2 = queue[3];

					queue[0] = queue[2];
					queue[2] = queue[5];

					queue[3] = tmp1;
					queue[5] = tmp2;
					
					break;
				case 2:
					bottom = queue[1];
					top = queue[4];
					tmp1 = queue[0];
					tmp2 = queue[1];

					queue[0] = queue[4];
					queue[4] = queue[5];

					queue[1] = tmp1;
					queue[5] = tmp2;
					break;
				case 3:
					bottom = queue[4];
					top = queue[1];
					tmp1 = queue[0];
					tmp2 = queue[4];

					queue[0] = queue[1];
					queue[1] = queue[5];

					queue[4] = tmp1;
					queue[5] = tmp2;
					break;
				}
				if(map[curRow + dr[idx]][curCol + dc[idx]] != 0) {
					dice[bottom] = map[curRow + dr[idx]][curCol + dc[idx]];
					map[curRow + dr[idx]][curCol + dc[idx]] = 0;
				} else {
					map[curRow + dr[idx]][curCol + dc[idx]] = dice[bottom];
				}
			
				System.out.println(dice[top]);
				
				curRow += dr[idx];
				curCol += dc[idx];
			}
			count--;
		}
	}

	static boolean isInside(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		diceRow = Integer.parseInt(st.nextToken());
		diceCol = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dir = new int[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < K; i++) {
			dir[i] = Integer.parseInt(st.nextToken());
		}

		solution();
	}

}
