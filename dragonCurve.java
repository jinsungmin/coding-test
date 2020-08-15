package codingTest;

import java.util.*;
import java.io.*;

public class dragonCurve {
	static class curve {
		private int x;
		private int y;
		private int dir;
		private int gen;
		
		public curve(int x, int y , int dir, int gen) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.gen = gen;
		}
	}
	
	static class Point {
		private int x;
		private int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static curve[] dc;
	static int[][] map = new int[101][101];
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,-1,0,1};
	
	static void draw(curve curve) {
		LinkedList<Point>queue = new LinkedList<Point>();
		LinkedList<Integer>dirQueue = new LinkedList<Integer>();

		int gen = curve.gen;
		int dir = curve.dir;
		
		queue.add(new Point(curve.x, curve.y));
		
		map[curve.y][curve.x] = 1;
		
		map[curve.y + dy[dir]][curve.x + dx[dir]] = 1;
		
		queue.add(new Point(curve.x + dx[dir], curve.y + dy[dir]));
		
		dirQueue.add(dir);
		
		for(int g = 1; g <= gen; g++) {
			Point cur = (Point)queue.get(queue.size()-1);
			
			int num = (int)Math.pow(2, g-1);
			
			int count = dirQueue.size();
			for(int i = 0; i < num; i++) {
				int curDir = dirQueue.get(count - 1 - i);
				
				if(curDir == 3) {
					curDir = 0;
				} else {
					curDir++;
				}
				
				Point tempCur = (Point)queue.get(queue.size()-1);
				
				if(isInside(tempCur.y + dy[curDir], tempCur.x + dx[curDir])) {
					map[tempCur.y + dy[curDir]][tempCur.x + dx[curDir]] = 1;
				}
				
				queue.add(new Point(tempCur.x + dx[curDir], tempCur.y + dy[curDir]));
				dirQueue.add(curDir);
			}
		}

	}
	
	static void count() {

		int count = 0;
		for(int i = 0; i < 101; i++) {
			for(int j = 0; j<101; j++) {
				if(map[i][j] == 1) {
					for(int k = 0; k < 4; k++) {
						if(isInside(i+dy[k], j+dx[k]) && map[i+dy[k]][j+dx[k]] == 1) {
							int nk = -1;
							if(k == 0) {
								nk = 3;
							} else {
								nk = k - 1;
							}
							if(isInside(i+ dy[k] + dy[nk],j+ dx[k]) 
									&& isInside(i+dy[nk],j+dx[nk]) 
									&& map[i+ dy[k] + dy[nk]][j+ dx[k] + dx[nk]] == 1 
									&& map[i+dy[nk]][j+dx[nk]] == 1) {
								count++;
							}
						}
					}
				}
			}
		}
		System.out.println(count / 4);
	}
	
	static boolean isInside(int x, int y) {
		return x >= 0 && x <= 100 && y >= 0 && y <= 100;
	}
	
	static void print() {
		for(int i = 0; i< 15; i++) {
			for(int j =0; j< 15; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		dc = new curve[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int gen = Integer.parseInt(st.nextToken());
			
			dc[i] = new curve(x,y,dir,gen);
			draw(dc[i]);
		}
		
		count();
		
		
	}

}
