import java.util.*;
import java.io.*;

public class game_2048 {

	static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static int N;
	static int[][] board;
	static int MAX = 0;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "");

		N = Integer.parseInt(st.nextToken());
		if (N < 1 || N > 20) {
			System.out.println("N input Error!!");
			System.exit(0);
		}
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			if (st.countTokens() == 0) {
				st = new StringTokenizer(br.readLine(), " ");
			}
			// System.out.println(st.countTokens());
			int idx = 0;
			while (st.hasMoreTokens()) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 0 || (value >= 2 && value <= 1024 && isPowerOfTwo(value))) {
					board[i][idx] = value;
				} else {
					System.out.println("Block input Error!!");
					System.exit(0);
				}
				idx += 1;
			}
		}

		dfs(0);
		System.out.println(MAX);

	}

	static boolean isPowerOfTwo(int x) {
		return (x != 0) && ((x & (x - 1)) == 0);
	}

	static void printBoard(int[][] board) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void copyBoard(int[][] board1, int[][] board2) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board1[i][j] = board2[i][j];
			}
		}
	}

	static void searchMaxValueBoard(int[][] inputBoard) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (MAX < inputBoard[i][j])
					MAX = inputBoard[i][j];
			}
		}
	}

	static void dfs(int depth) {
		int[][] tempMap = new int[N][N];
		copyBoard(tempMap, board);

		if (depth == 5) {
			searchMaxValueBoard(board);
			return;
		}

		for (int i = 0; i < 4; i++) {
			moveBoard(i);
			dfs(depth + 1);
			copyBoard(board, tempMap);
		}

	}

	static void moveBoard(int dir) {
		int[][] newMap = new int[N][N];
		Queue q = new LinkedList();

		// 상인경우
		if (dir == 0) {
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					if (board[y][x] != 0) {
						q.add(board[y][x]);
					}
				}
				int idx = 0;
				while (!q.isEmpty()) {
					int value = (int)q.peek();
					// System.out.println(value);
					if (newMap[idx][x] == 0) {
						newMap[idx][x] = (int)q.poll();

					} else if (newMap[idx][x] == value) { // 머징
						newMap[idx][x] += (int)q.poll();
						idx += 1;

					} else { // 0도 아니고 같지도 않을떄
						idx += 1;
						newMap[idx][x] = (int)q.poll();
					}
					// printMap(newMap);
				}
			}
		}
		// 하인경우
		if (dir == 1) {

			for (int x = 0; x < N; x++) {
				for (int y = N - 1; y >= 0; y--) {
					if (board[y][x] != 0) {
						q.add(board[y][x]);
					}
				}
				int idx = N - 1;
				while (!q.isEmpty()) {
					int value = (int)q.peek();
					// System.out.println(value);
					if (newMap[idx][x] == 0) {
						newMap[idx][x] = (int)q.poll();

					} else if (newMap[idx][x] == value) { // 머징
						newMap[idx][x] += (int)q.poll();
						idx -= 1;

					} else { // 0도 아니고 같지도 않을떄
						idx -= 1;
						newMap[idx][x] = (int)q.poll();
					}
					// printMap(newMap);
				}
			}
		}

		// 좌측인경우
		if (dir == 2) {

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (board[y][x] != 0) {
						q.add(board[y][x]);
					}
				}
				int idx = 0;
				while (!q.isEmpty()) {
					int value = (int)q.peek();
					// System.out.println(value);
					if (newMap[y][idx] == 0) {
						newMap[y][idx] = (int)q.poll();

					} else if (newMap[y][idx] == value) { // 머징
						newMap[y][idx] += (int)q.poll();
						idx += 1;

					} else { // 0도 아니고 같지도 않을떄
						idx += 1;
						newMap[y][idx] = (int)q.poll();
					}
					// printMap(newMap);
				}

			}
		}
		// 우측인경우
		if (dir == 3) {

			for (int y = 0; y < N; y++) {
				for (int x = N - 1; x >= 0; x--) {
					if (board[y][x] != 0) {
						q.add(board[y][x]);
					}
				}
				int idx = N - 1;
				while (!q.isEmpty()) {
					int value = (int)q.peek();
					// System.out.println(value);
					if (newMap[y][idx] == 0) {
						newMap[y][idx] = (int)q.poll();

					} else if (newMap[y][idx] == value) { // 머징
						newMap[y][idx] += (int)q.poll();
						idx -= 1;

					} else { // 0도 아니고 같지도 않을떄
						idx -= 1;
						newMap[y][idx] = (int)q.poll();
					}
					// printMap(newMap);
				}
			}
		}
		copyBoard(board, newMap);
	}
}
