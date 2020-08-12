package codingTest;

import java.util.*;
import java.io.*;

/*
 	문제 13460 구슬 탈출 2
 */

public class marbleEscape {
	
	static class Point {
		private int rowRed;
		private int colRed;
		private int rowBlue;
		private int colBlue;
		//private int dist;
		
		public Point(int rowRed, int colRed, int rowBlue, int colBlue) {
			this.rowRed = rowRed;
			this.colRed = colRed;
			this.rowBlue = rowBlue;
			this.colBlue = colBlue;
		}
	}
	
	static int N, M;
	static String[][] map;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	
	static void print() {
		for(int i = 0; i< N; i++) {
			for(int j = 0; j< M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static boolean isInside(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
	
	static void solution() {
		LinkedList<Point>queue = new LinkedList<Point>();
		
		boolean visited[][] = new boolean[N][M];
		
		queue.add(find());	// 처음 Red, Blue 위치 큐에 삽입
		visited[find().rowRed][find().colRed] = true;	// 처음 Red 위치 방문 표시
		boolean out = false;
		
		int dist = 0;
		
		while(queue.size() != 0) {
			Point cur = queue.poll();
			int curRedRow = cur.rowRed;
			int curRedCol = cur.colRed;
			
			int curBlueRow = cur.rowBlue;
			int curBlueCol = cur.colBlue;
			
			for(int i = 0; i < 4; i++) {
				if(map[curRedRow + dr[i]][curRedCol + dc[i]].equals("O")) {
					dist++;
					
					map[curRedRow + dr[i]][curRedCol + dc[i]] = "R";
					map[curRedRow][curRedCol] = ".";
					print();
					
					System.out.println("dist: " + dist);
					System.exit(0);
				}
				
				if(map[curRedRow + dr[i]][curRedCol + dc[i]].equals("B") // R B . 일때 → 이동
						&& isInside(curBlueRow + 2*dr[i], curBlueCol + 2*dc[i]) 
						&& map[curBlueRow + 2*dr[i]][curBlueCol + 2*dc[i]].equals(".")
						&& !visited[curRedRow + dr[i]][curRedCol + dc[i]]) {
					int j = 2;
					int k = 1;
					
					dist++;
					
					while(!map[curBlueRow + j*dr[i]][curBlueCol + j*dc[i]].equals("#")) {
						visited[curRedRow + k*dr[i]][curRedCol + k*dc[i]] = true;
						
						map[curBlueRow + j*dr[i]][curBlueCol + j*dc[i]] = "B";
						map[curBlueRow + (j-1)*dr[i]][curBlueCol + (j-1)*dc[i]] = ".";
						
						map[curRedRow + k*dr[i]][curRedCol + k*dc[i]] = "R";
						map[curRedRow + (k-1)*dr[i]][curRedCol + (k-1)*dc[i]] = ".";
						k++;
						j++;
					}
				}
				
				if(map[curRedRow + dr[i]][curRedCol + dc[i]].equals(".") && !visited[curRedRow + dr[i]][curRedCol + dc[i]]) {
					int j = 1;
					int k = 1;
					
					dist++;
					
					while(!map[curRedRow + j*dr[i]][curRedCol + j*dc[i]].equals("#")) {
						visited[curRedRow + j*dr[i]][curRedCol + j*dc[i]] = true;
						
						if(map[curRedRow + j*dr[i]][curRedCol + j*dc[i]].equals("O")) {
							map[curRedRow + j*dr[i]][curRedCol + j*dc[i]] = "R";
							map[curRedRow + (j-1)*dr[i]][curRedCol + (j-1)*dc[i]] = ".";
							print();
							System.out.println("dist: " + dist);
							System.exit(0);
						}
						
						map[curRedRow + j*dr[i]][curRedCol + j*dc[i]] = "R";
						map[curRedRow + (j-1)*dr[i]][curRedCol + (j-1)*dc[i]] = ".";
						
						j++;
						
						print();
						System.out.println();
					}
					
					while(!map[curBlueRow + k*dr[i]][curBlueCol + k*dc[i]].equals("#")) {
						if(isInside(curBlueRow + k*dr[i], curBlueCol + k*dc[i]) && map[curBlueRow + k*dr[i]][curBlueCol + k*dc[i]].equals("O")) {
							map[curBlueRow + k*dr[i]][curBlueCol + k*dc[i]] = "B";
							map[curBlueRow + (k-1)*dr[i]][curBlueCol + (k-1)*dc[i]] = ".";
							
							print();
							System.out.println(-1);
							System.exit(0);
						}
						
						map[curBlueRow + k*dr[i]][curBlueCol + k*dc[i]] = "B";
						map[curBlueRow + (k-1)*dr[i]][curBlueCol + (k-1)*dc[i]] = ".";
						
						k++;
						
						print();
						System.out.println();
					}
					
					if(tempBlueRow == -1) {
						tempBlueRow = curBlueRow + (j-1)*dr[i];
						tempBlueCol = curBlueCol + (j-1)*dc[i];
					}
					
					queue.add(new Point(curRedRow + (j-1)*dr[i], curRedCol + (j-1)*dc[i], tempBlueRow, tempBlueCol));
				}
			}
		}
	}
	
	static Point find() {
		int rr = -1, rc = -1, br = -1, bc = -1;
		for(int i = 0; i< N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j].equals("R")) {
					rr = i;
					rc = j;
				} else if(map[i][j].equals("B")) {
					br = i;
					bc = j;
				}
			}
		}
		//System.out.println(rr + " " + rc + " " + br + " " + bc);
		return new Point(rr, rc, br, bc);
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new String[N][M];
		
		for(int i = 0; i< N; i++) {
			String[] str;
			str = br.readLine().split("");
			
			for(int j = 0; j < M; j++) {
				map[i][j] = new String(str[j]);
			}
		}
		print();
		System.out.println();
		solution();
	}

}
