import java.util.*;
import java.io.*;

/*
   문제 7569
 <문제>
  철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다. 토마토는 아래의 그림과 같이 격자모양 상자의 칸에 하나씩 넣은 다음, 상자들을 수직으로 쌓아 올려서 창고에 보관한다.
  창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다. 보관 후 하루가 지나면, 
  익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 
  하나의 토마토에 인접한 곳은 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 여섯 방향에 있는 토마토를 의미한다. 
  대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 
  철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지 그 최소 일수를 알고 싶어 한다.
  토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 
  며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라. 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
 <입력>
 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N과 쌓아올려지는 상자의 수를 나타내는 H가 주어진다. 
 M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다. 단, 2 ≤ M ≤ 100, 2 ≤ N ≤ 100, 1 ≤ H ≤ 100 이다. 
 둘째 줄부터는 가장 밑의 상자부터 가장 위의 상자까지에 저장된 토마토들의 정보가 주어진다. 즉, 둘째 줄부터 N개의 줄에는 하나의 상자에 담긴 토마토의 정보가 주어진다. 
 각 줄에는 상자 가로줄에 들어있는 토마토들의 상태가 M개의 정수로 주어진다. 정수 1은 익은 토마토, 정수 0 은 익지 않은 토마토, 
 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다. 이러한 N개의 줄이 H번 반복하여 주어진다.
 <출력>
 여러분은 토마토가 모두 익을 때까지 최소 며칠이 걸리는지를 계산해서 출력해야 한다. 
 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
*/

public class Tomato {
	static class infect {
		static class Pos {
			int m;
			int n;
			int h;
			
			public Pos(int m, int n, int h) {
				this.m = m;
				this.n = n;
				this.h = h;
			}
		}
		
		private Pos pos; 
		private int dist;
		
		public infect(Pos pos, int dist) {
			this.pos = pos;
			this.dist = dist;
		}
		
		
		public static boolean isInside(int i, int j, int k) {
			return i >= 0 && i < H && j >= 0 && j < N && k >= 0 && k < M; 
		}
		
		static void bfs() {	// h, n, m
			LinkedList<infect>queue = new LinkedList<infect>();
			int curDist = 0;
			for(int i = 0; i < H; i++) {
				for(int j = 0; j< N; j++) {
					for(int k = 0; k < M; k++) {
						if(tomato[i][j][k] == 1) {
							visited[i][j][k] = true;
							Pos curPos = new Pos(i,j,k);
							queue.add(new infect(curPos, curDist));
						}
					}
				}
			}
			
			while (queue.size() != 0) {
				infect temp = (infect) queue.poll();
				Pos curPos = temp.pos;
				curDist = temp.dist;
				
				for(int v = 0; v< 6; v++) {
					if(isInside(curPos.m + dm[v], curPos.n + dn[v], curPos.h + dh[v]) 
						&& !visited[curPos.m + dm[v]][curPos.n + dn[v]][curPos.h + dh[v]]
						&& tomato[curPos.m + dm[v]][curPos.n + dn[v]][curPos.h + dh[v]] == 0) {
						
						visited[curPos.m + dm[v]][curPos.n + dn[v]][curPos.h + dh[v]] = true;
						
						queue.add(new infect(new Pos(curPos.m + dm[v],curPos.n + dn[v],curPos.h + dh[v]), curDist + 1));
					}
				}	
			}
			boolean check = false;
			boolean ripe = false;
			for(int i = 0; i < H; i++) {
				for(int j = 0; j< N; j++) {
					for(int k = 0; k < M; k++) {
						if(tomato[i][j][k] == 0 &&visited[i][j][k] == false) {
							check = true;
						}
						if(tomato[i][j][k] == 0) {
							ripe = true;
						}
					}
					
				}
			}
			if(!ripe) {
				System.out.println("0");
			} else if(check) {
				System.out.println("-1");
			} else {
				System.out.println(curDist);
			}
			
		}
	}
	
	static int[][][] tomato;
	static boolean visited[][][];
	static int[] dm = {0,0,-1,1,0,0};
	static int[] dn = {1,-1,0,0,0,0};
	static int[] dh = {0,0,0,0,1,-1};
	static int M,N,H;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		tomato = new int[H][N][M];
		visited = new boolean[H][N][M];
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j< N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < M; k++) {
					tomato[i][j][k] = Integer.parseInt(st.nextToken());	
				}
			}
		}
		
		infect.bfs();
		
	}

}
