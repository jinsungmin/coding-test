package codingTest;

import java.util.*;
import java.io.*;


/*
	문제 17144
*/
public class tinyDust {
	
	static int R, C, T;
	static int[][] map;
	static int[][] temp;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};
	
	static void spread(int row, int col) {
		int dust = (int)map[row][col] / 5;
		int count = 0;
		for(int i = 0; i< 4; i++) {
			if(isInside(row + dr[i], col + dc[i]) && map[row + dr[i]][col + dc[i]] != -1) {
				temp[row + dr[i]][col + dc[i]] += dust;
				count++;
			}
		}
		map[row][col] -= (dust * count);
	}
	
	static void findCleaner() {
		for(int i = 0; i < R; i++) {
			if(map[i][0] == -1) {
				airCleaning(i, false);
				airCleaning(i+1, true);
				break;
			}
		}
	}
	
	static void airCleaning(int pos, boolean dir) {
		if(!dir) {
			int tmp1 = map[pos][C-1];
			int tmp2 = map[0][C-1];
			for(int i = pos; i> 0; i--) {
				if(map[i][0] != -1)
					map[i][0] = map[i-1][0];
			}
			for(int i = C-1; i > 0; i--) {
				if(map[pos][i-1] != -1) {
					map[pos][i] = map[pos][i-1];
				} else {
					map[pos][i] = 0;
				}
			}
			for(int i = 0; i < pos-1; i++) {
				map[i][C-1] = map[i+1][C-1];
			}
			map[pos-1][C-1] = tmp1;
			
			for(int i = 0; i< C-2; i++) {
				map[0][i] = map[0][i+1];
			}
			map[0][C-2] = tmp2;
		} else {
			int tmp1 = map[pos][C-1];
			int tmp2 = map[R-1][C-1];
			
			for(int i = pos + 1; i < R-1; i++) {
				map[i][0] = map[i+1][0];
			}
			
			for(int i = C-2; i >= 0; i--) {
				if(map[pos][i] != -1) {
					map[pos][i+1] = map[pos][i];
				} else {
					map[pos][i+1] = 0;
				}
			}
			for(int i = R-1; i >= pos+1; i--) {
				map[i][C-1] = map[i-1][C-1];
			}
			map[pos+1][C-1] = tmp1;
			
			for(int i = 0; i < C-2; i++) { 		// 0 ~ C-2 
				map[R-1][i] = map[R-1][i+1];
			}
			map[R-1][C-2] = tmp2;
		}
	}
	
	static void total() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				map[i][j] += temp[i][j];
				temp[i][j] = 0;	// temp initialize
			}
		}
	}
	
	static void solution(int time) {
		while(time > 0) {
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(map[i][j] > 0) {
						spread(i,j);
					}
				}
			}
		
			total();
		
			findCleaner();
			
			time--;
		}
		
	}
	
	static void calculate() {
		int result = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] > 0) {
					result += map[i][j];
				}
			}
		}
		System.out.println(result);
	}
	
	static boolean isInside(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
	
	static void print() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		temp = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solution(T);
		
		calculate();
		
		//print();
		
	}

}