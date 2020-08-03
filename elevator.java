import java.util.*;
import java.io.*;

/*
  	문제 5014
  	<문제>
  	강호는 코딩 교육을 하는 스타트업 스타트링크에 지원했다. 오늘은 강호의 면접날이다. 하지만, 늦잠을 잔 강호는 스타트링크가 있는 건물에 늦게 도착하고 말았다.
	스타트링크는 총 F층으로 이루어진 고층 건물에 사무실이 있고, 스타트링크가 있는 곳의 위치는 G층이다. 강호가 지금 있는 곳은 S층이고, 이제 엘리베이터를 타고 G층으로 이동하려고 한다.
	보통 엘리베이터에는 어떤 층으로 이동할 수 있는 버튼이 있지만, 강호가 탄 엘리베이터는 버튼이 2개밖에 없다. U버튼은 위로 U층을 가는 버튼, 
	D버튼은 아래로 D층을 가는 버튼이다. (만약, U층 위, 또는 D층 아래에 해당하는 층이 없을 때는, 엘리베이터는 움직이지 않는다)
	강호가 G층에 도착하려면, 버튼을 적어도 몇 번 눌러야 하는지 구하는 프로그램을 작성하시오. 만약, 엘리베이터를 이용해서 G층에 갈 수 없다면, "use the stairs"를 출력한다.
  	<입력>
  	첫째 줄에 F, S, G, U, D가 주어진다. (1 ≤ S, G ≤ F ≤ 1000000, 0 ≤ U, D ≤ 1000000) 건물은 1층부터 시작하고, 가장 높은 층은 F층이다.
  	<출력>
  	첫째 줄에 강호가 S층에서 G층으로 가기 위해 눌러야 하는 버튼의 수의 최솟값을 출력한다. 만약, 엘리베이터로 이동할 수 없을 때는 "use the stairs"를 출력한다.
*/

public class elevator {
	static class Pos {
		private int pos;
		private int dist;
		
		public Pos(int pos, int dist) {
			this.pos = pos;
			this.dist = dist;
		}
	}
	
	static int floor;
	static int start;
	static int dest;
	static int up;
	static int down;
	static boolean[] visited;
	
	static void search(int start, int dest) {
		LinkedList<Pos> queue = new LinkedList<Pos>();
		int result = -1;
		queue.add(new Pos(start, 0));
		visited[start] = true;
		
		while(queue.size() != 0) {
			Pos temp = (Pos) queue.poll();
			int curPos = temp.pos;
			int curDist = temp.dist;
			
			if(curPos == dest) {
				result = curDist;
				break;
			}
			if(isInside(curPos + up) && !visited[curPos + up]) {
				visited[curPos + up] = true;
				queue.add(new Pos(curPos + up, curDist + 1));
			}
			if(isInside(curPos - down) && !visited[curPos - down]) {
				visited[curPos - down] = true;
				queue.add(new Pos(curPos -down, curDist + 1));
			}
		}
		
		if(result != -1) {
			System.out.println(result);
		} else {
			System.out.println("use the stairs");
		}
	}
	
	static boolean isInside(int x) {
		return x >= 1 && x <= floor;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		floor = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());
		up = Integer.parseInt(st.nextToken());
		down = Integer.parseInt(st.nextToken());
		
		visited = new boolean[floor + 100];

		search(start, dest);

	}

}
