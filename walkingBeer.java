package codingTest;

import java.util.*;
import java.io.*;

/*
	문제 9205
 */
public class walkingBeer {
	static class Point {
		private int x;
		private int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int testCaseNumber;
	static int marketNumber;
	static int[] Pos;
	static Point[] point;
	
	static void solution() {
		LinkedList<Point>queue = new LinkedList<Point>();
		boolean[] visited = new boolean[marketNumber + 2];
		boolean arrived = false;
		
		Point start = point[0];    //시작 위치
        Point end = point[marketNumber + 1];    //도착 위치
        queue.add(start);    //시작 위치로 부터 출발
        
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if(cur.equals(end)){
                arrived = true;
                break;
            }
           
            for (int i = 1; i < marketNumber + 2; i++) {
                if (!visited[i] && Math.abs(cur.x - point[i].x) + Math.abs(cur.y - point[i].y) <= 1000) {
                    queue.add(point[i]);
                    visited[i] = true;   
                }
            }
        }
        if(arrived){
            System.out.println("happy");
        } else{
            System.out.println("sad");
        }
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int j = 0;
		
		testCaseNumber = Integer.parseInt(st.nextToken());
		for(int i = 0; i< testCaseNumber; i++) {
			st = new StringTokenizer(br.readLine());
			marketNumber = Integer.parseInt(st.nextToken());
			
			point = new Point[marketNumber + 2];
			
			Pos = new int[marketNumber + 2];
			
			st = new StringTokenizer(br.readLine());
			point[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			for(j = 0; j< marketNumber; j++) {
				st = new StringTokenizer(br.readLine());
				point[j+1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			point[marketNumber+1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			solution();
		}
		
		
	}

}
