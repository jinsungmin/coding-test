package codingTest;

import java.util.*;
import java.io.*;

/*
	문제 15683 - samsung
*/

public class watch {
	
	static class Point {
		private int row;
		private int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	static int N, M;
	static int[][] map;
	static int[][] temp;
	
	static LinkedList<Point>queue = new LinkedList<Point>();
	static int[] dr = {0,1,0,-1};
	static int[] dc = {-1,0,1,0};
	static int size;
	static int min = 99999;
	static int count;
	static int[] arr;
	
	static void solution() {
		size = queue.size();
		arr = new int[size];
		count = 0;
		
		if (size == 0) {
            check();
            min = count;
        } else {
            search(0);
        }
		
		System.out.println(min);
	}
	
	static void search(int depth) {
        if (depth == size) {
        	
            for (int i = 0; i < size; i++) {
                Point p = queue.get(i);
               
                watch(p, temp[p.row][p.col], arr[i]);
            }
            
            check(); //사각지대가 얼마나 있는지 체크
            min = Math.min(min, count);    //사각지대가 최소일때 저장
            reset(); 	//감시하는 장소 초기화
            
            return;
        }
 
        for (int i = 0; i < 4; i++) {
            arr[depth] = i + 1;
            search(depth + 1);
        }
    }
	
	static void watch(Point p, int num, int dir) {
		if(num == 1) {
			if(dir >= 1)
				move(p, dir);
		} else if(num == 2) {
			if (dir == 1 || dir == 3) {
                move(p, 1);
                move(p, 3);
            } else if (dir == 2 || dir == 4) {
                move(p, 2);
                move(p, 4);
            }
		} else if(num == 3) {
			if(dir != 4) {
				move(p, dir);
				move(p, dir+1);
			} else {
				move(p, 4);
				move(p, 1);
			}
		} else if(num == 4){
			move(p, dir);
			dir++;
			if(dir > 4)
				dir = 1;
			move(p, dir);
			dir++;
			if(dir > 4)
				dir = 1;
			move(p, dir);
		} else if(num == 5) {
			move(p, 1);
            move(p, 2);
            move(p, 3);
            move(p, 4);
		}
	}
	
	static void move(Point p, int dir) {
		dir--;
		int row = p.row;
		int col = p.col;
		while(isInside(row + dr[dir], col + dc[dir]) && map[row + dr[dir]][col + dc[dir]] != 6) {
			if(map[row + dr[dir]][col + dc[dir]] == 0) {
				temp[row + dr[dir]][col + dc[dir]] = -1;
			}
			row += dr[dir];
			col += dc[dir];
		}		
	}
	
	static void check() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j< M; j++) {
				if(temp[i][j] == 0) {
					count++;
				}
			}
		}
	}
	
	static void reset() {
		count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j< M; j++) {
				temp[i][j] = map[i][j];
			}
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
		
		map = new int[N][M];
		temp = new int[N][M];
		
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j< M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				temp[i][j] = map[i][j];
				if(map[i][j] != 0 && map[i][j] != 6) {
					queue.add(new Point(i,j));
				}
			}
		}
		
		solution();
	}

}
