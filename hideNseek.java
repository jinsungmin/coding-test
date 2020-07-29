import java.util.*;
import java.io.*;

/*
	문제 1697
	<문제>
	수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 
	수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
         수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
    <입력>
         첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
    <출력>
         수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 */

public class hideNseek {
	static class infor {
		private int pos;
		private int time;
		
		public infor(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
	}
	
	static int N, K;
	static boolean[] visited;
	
	static boolean isInside(int x) {
		return x >= 0 && x < 100030;
	}
	
	static void seek(int N, int K) {
		LinkedList<infor>queue = new LinkedList<infor>();
		int curPos = N;
		int curTime = 0;
		
		queue.add(new infor(curPos, curTime));
		visited[curPos] = true;
			
		while(queue.size() != 0) {
			infor cur = (infor)queue.poll();
			
			curPos = cur.pos;
			curTime = cur.time;
			
			if(curPos != K) {		
				if(isInside(curPos + 1) && !visited[curPos + 1]) {
					visited[curPos + 1] = true;
					queue.add(new infor(curPos + 1, curTime + 1));
				}
				if(isInside(curPos - 1) && !visited[curPos - 1]) {
					visited[curPos - 1] = true;
					queue.add(new infor(curPos - 1, curTime + 1));
				}
				if(isInside(curPos * 2) && !visited[curPos * 2]) {
					visited[curPos * 2] = true;
					queue.add(new infor(curPos * 2, curTime + 1));
				}
			} else {
				System.out.println(curTime);
				break;
			}
		}	
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[100030];
		seek(N, K);
	}

}
