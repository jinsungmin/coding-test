package codingTest;

import java.util.*;
import java.io.*;

public class chickenDelivery {
	static class store {
		private int row;
		private int col;
		
		public store(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	
	static int N,M;
	static int[][] map;
	static store[] arr;
	static int[] min;
	static int sum;
	static int result;
	
	static LinkedList<store>home = new LinkedList<store>();
	static LinkedList<store>queue = new LinkedList<store>();
	
	static void solution() {
		result = 99999;
		
		while(M > 0) {
			if(queue.size() <= M) {
				arr = new store[queue.size()];
				
				for(int i = 0; i< queue.size(); i++) {
					arr[i] = queue.get(i);
				}
				distance();
			} else {
				arr = new store[M];
				for(int i = 0 ; i < queue.size(); i++) {
					arr[0] = queue.get(i);
					pick(i,0);
				}
				//pick(0,0);
			}
			M--;
		}
		
		System.out.println(result);
	}
	
	static void pick(int start, int depth) {
		if(depth == M) {
			
			distance();
			
			if(result > sum)
				result = sum;
			return;
		}
		
		if(start >= queue.size()) {
			return;
		}
		
		for(int i = start; i < queue.size(); i++) {
			arr[depth] = queue.get(i);
			pick(i + 1, depth + 1);
		}
		
	}
	
	static void distance() {
		min = new int[home.size()];
		sum = 0;
		int tmp;
		for(int i = 0; i < home.size(); i++) {
			min[i] = 99999;
			for(int j = 0; j < arr.length; j++) {
				tmp = Math.abs(home.get(i).row - arr[j].row) + Math.abs(home.get(i).col - arr[j].col);
				if(min[i] > tmp)
					min[i] = tmp;
			}
			sum += min[i];
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
			
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j< N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					queue.add(new store(i,j));
				}
				if(map[i][j] == 1) {
					home.add(new store(i,j));
				}
			}
		}
		
		solution();
		
		long end = System.currentTimeMillis();

		System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
	}

}
