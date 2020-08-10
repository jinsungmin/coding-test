package codingTest;

import java.util.*;
import java.io.*;

/*
	<문제>
	재난방재청에서는 많은 비가 내리는 장마철에 대비해서 다음과 같은 일을 계획하고 있다. 
	먼저 어떤 지역의 높이 정보를 파악한다. 그 다음에 그 지역에 많은 비가 내렸을 때 물에 잠기지 않는 
	안전한 영역이 최대로 몇 개가 만들어 지는 지를 조사하려고 한다. 이때, 문제를 간단하게 하기 위하여, 
	장마철에 내리는 비의 양에 따라 일정한 높이 이하의 모든 지점은 물에 잠긴다고 가정한다.
	<입력>
	첫째 줄에는 어떤 지역을 나타내는 2차원 배열의 행과 열의 개수를 나타내는 수 N이 입력된다. 
	N은 2 이상 100 이하의 정수이다. 둘째 줄부터 N개의 각 줄에는 2차원 배열의 첫 번째 행부터 N번째 행까지 순서대로 한 행씩 높이 정보가 입력된다. 
	각 줄에는 각 행의 첫 번째 열부터 N번째 열까지 N개의 높이 정보를 나타내는 자연수가 빈 칸을 사이에 두고 입력된다. 높이는 1이상 100 이하의 정수이다.
	<출력>
	첫째 줄에 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 출력한다.
*/


public class safetyZone {

	static int N;
	static int map[][];
	static boolean visited[][];
	static int dr[] = {0,0,-1,1};
	static int dc[] = {1,-1,0,0};
	static boolean temp[][];
	
	static void dfs(int row, int col) {
		visited[row][col] = true;
		for(int i = 0; i < 4; i++) {
			if(isInside(row + dr[i], col + dc[i]) && !visited[row + dr[i]][col + dc[i]] && temp[row + dr[i]][col + dc[i]]) {
				dfs(row + dr[i], col + dc[i]);
			}
		}
	}
	
	static void copy(int depth) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] > depth) {
					temp[i][j] = true;
				} else {
					temp[i][j] = false;
				}
			}
		}
	}
	
	static void search() {
		int max = 0;
		temp = new boolean[N][N];
		for(int depth = 100; depth >= 0; depth--) {
			initArr();
			copy(depth);
			
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j] && temp[i][j]) {
						dfs(i,j);
						cnt++;
					}
				}
			}
			
			if(max <= cnt) {
				max = cnt;
			}
		}
		System.out.println(max);
	}
	
	static void initArr() {
		visited = new boolean[N][N];
	}
	
	static boolean isInside(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N; 
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		search();
		
	}

}
