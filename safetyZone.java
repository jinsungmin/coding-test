package codingTest;

import java.util.*;
import java.io.*;

/*
	<����>
	�糭����û������ ���� �� ������ �帶ö�� ����ؼ� ������ ���� ���� ��ȹ�ϰ� �ִ�. 
	���� � ������ ���� ������ �ľ��Ѵ�. �� ������ �� ������ ���� �� ������ �� ���� ����� �ʴ� 
	������ ������ �ִ�� �� ���� ����� ���� ���� �����Ϸ��� �Ѵ�. �̶�, ������ �����ϰ� �ϱ� ���Ͽ�, 
	�帶ö�� ������ ���� �翡 ���� ������ ���� ������ ��� ������ ���� ���ٰ� �����Ѵ�.
	<�Է�>
	ù° �ٿ��� � ������ ��Ÿ���� 2���� �迭�� ��� ���� ������ ��Ÿ���� �� N�� �Էµȴ�. 
	N�� 2 �̻� 100 ������ �����̴�. ��° �ٺ��� N���� �� �ٿ��� 2���� �迭�� ù ��° ����� N��° ����� ������� �� �྿ ���� ������ �Էµȴ�. 
	�� �ٿ��� �� ���� ù ��° ������ N��° ������ N���� ���� ������ ��Ÿ���� �ڿ����� �� ĭ�� ���̿� �ΰ� �Էµȴ�. ���̴� 1�̻� 100 ������ �����̴�.
	<���>
	ù° �ٿ� �帶ö�� ���� ����� �ʴ� ������ ������ �ִ� ������ ����Ѵ�.
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
