import java.util.*;
import java.io.*;

/*
	���� 1697
	<����>
	�����̴� ������ ���ٲ����� �ϰ� �ִ�. �����̴� ���� �� N(0 �� N �� 100,000)�� �ְ�, ������ �� K(0 �� K �� 100,000)�� �ִ�. 
	�����̴� �Ȱų� �����̵��� �� �� �ִ�. ����, �������� ��ġ�� X�� �� �ȴ´ٸ� 1�� �Ŀ� X-1 �Ǵ� X+1�� �̵��ϰ� �ȴ�. �����̵��� �ϴ� ��쿡�� 1�� �Ŀ� 2*X�� ��ġ�� �̵��ϰ� �ȴ�.
         �����̿� ������ ��ġ�� �־����� ��, �����̰� ������ ã�� �� �ִ� ���� ���� �ð��� �� �� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
    <�Է�>
         ù ��° �ٿ� �����̰� �ִ� ��ġ N�� ������ �ִ� ��ġ K�� �־�����. N�� K�� �����̴�.
    <���>
         �����̰� ������ ã�� ���� ���� �ð��� ����Ѵ�.
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
