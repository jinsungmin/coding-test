package codingTest;

/*
 	¹®Á¦ 17779 - »ï¼º sw
*/

import java.util.*;
import java.io.*;

public class garyMandering {

	
	static int N;
	static int[][] map;
	static int[][] tBoard;
	static int[] dx = {1,1};
	static int[] dy = {-1,1};
	static int[] arr = new int[5];
	
	static int calculate(int x, int y, int d1, int d2) {
		int i,j;

		for(i = 0; i <= d1; i++) {
			tBoard[y + i * dy[0]][x + i * dx[0]] = 5;
		}
		
		for(j = 0; j<= d2; j++) {
			tBoard[y + (i-1) * dy[0] + j * dy[1]][x + (i-1) * dx[0] + j * dy[1]] = 5;
			tBoard[y + j * dy[1]][x + j * dy[1]] = 5;
		}
		
		for(i = 0; i<= d1; i++) {
			tBoard[y + (j-1) * dy[1] + i * dy[0]][x + (j-1) * dy[1] + i * dx[0]] = 5;
		}
		for(i = (-1 *d2); i < d1; i++) {
			LinkedList<Integer>queue = new LinkedList<Integer>();
			for(j = 0; j < N; j++) {
				if(tBoard[y-i][j] == 5) {
					queue.add(j);
				}
			}
			if(queue.size() != 1) {
				for(j = queue.get(0); j < queue.get(1); j++) {
					tBoard[y-i][j] = 5;
				}
			}
		}
		
		for(i = 0; i < y; i++) {
			for(j = 0; j <= x + d1; j++) {
				if(tBoard[i][j] != 5)
					tBoard[i][j] = 1;
			}
		}
		for(i = y; i < N; i++) {
			for(j = 0; j < x+d2; j++) {
				if(tBoard[i][j] != 5)
					tBoard[i][j] = 3;
			}
		}
		
		for(i = y - d1 + d2 + 1 ; i < N; i++) {
			for(j = x+d2; j < N; j++) {
				if(tBoard[i][j] != 5)
					tBoard[i][j] = 4;
			}
		}
		
		for(i = 0; i <= y - d1 + d2; i++) {
			for(j = x+d1+1; j < N; j++) {
				if(tBoard[i][j] != 5)
					tBoard[i][j] = 2;
			}
		}
		
		for(i =0; i< N; i++) {
			for(j=0; j<N; j++) {
				switch(tBoard[i][j]) {
				case 1:
					arr[0] += map[i][j];
					break;
				case 2:
					arr[1] += map[i][j];
					break;
				case 3:
					arr[2] += map[i][j];
					break;
				case 4:
					arr[3] += map[i][j];
					break;
				case 5:
					arr[4] += map[i][j];
					break;
				}
			}
		}
		
		Arrays.sort(arr);
		int gap = arr[4] - arr[0];
		
		initialize();
		
		return gap;
		
	}
	
	static void initialize() {
		for(int i = 0; i< N; i++) {
			for(int j =0; j<N; j++) {
				tBoard[i][j] = 0;
			}
		}
		for(int i = 0; i< 5; i++) {
			arr[i] = 0;
		}
	}
	
	static int solve(int x, int y) {
		int min = 99999;
		int d1 = 1, d2 = 1;
		
		for(d1 = 1; d1 < N; d1++) {
			for(d2 = 1; d2 < N; d2++) {
				if(isInside(x + d1 * dx[0], y + d1 * dy[0]) 
						&& isInside(x + d1 * dx[0] + d2 * dx[1], y + d1 * dy[0] + d2 * dy[1]) 
						&& isInside(x + d2 * dx[1], y + d2 * dy[1])) {
					int gap = calculate(x,y,d1,d2);
					if(min > gap) {
						min = gap;
					}
				}
			}
		}
		
		return min;
	}
	
	static void print(int[][] map) {
		for(int i = 0; i< N; i++) {
			for(int j = 0; j< N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void solution() {
		int min = 99999;
		
		for(int i = 0; i < N - 2; i++) {
			for(int j = 1; j < N - 1; j++) {
				int temp = solve(i,j);
				
				if(min > temp) {
					min = temp;
				}
			}
		}
		
		System.out.println(min);
	}
	
	static boolean isInside(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		tBoard = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solution();
		//calculate(1,4,3,2);
		//print(tBoard);
	}

}
