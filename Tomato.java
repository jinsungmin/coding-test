import java.util.*;
import java.io.*;

/*
   ���� 7569
 <����>
  ö���� �丶�� ���忡���� �丶�並 �����ϴ� ū â�� ������ �ִ�. �丶��� �Ʒ��� �׸��� ���� ���ڸ�� ������ ĭ�� �ϳ��� ���� ����, ���ڵ��� �������� �׾� �÷��� â�� �����Ѵ�.
  â�� �����Ǵ� �丶��� �߿��� �� ���� �͵� ������, ���� ���� ���� �丶��鵵 ���� �� �ִ�. ���� �� �Ϸ簡 ������, 
  ���� �丶����� ������ ���� �ִ� ���� ���� �丶����� ���� �丶���� ������ �޾� �Ͱ� �ȴ�. 
  �ϳ��� �丶�信 ������ ���� ��, �Ʒ�, ����, ������, ��, �� ���� ���⿡ �ִ� �丶�並 �ǹ��Ѵ�. 
  �밢�� ���⿡ �ִ� �丶��鿡�Դ� ������ ���� ���ϸ�, �丶�䰡 ȥ�� ������ �ʹ� ���� ���ٰ� �����Ѵ�. 
  ö���� â�� ������ �丶����� ��ĥ�� ������ �� �Ͱ� �Ǵ��� �� �ּ� �ϼ��� �˰� �;� �Ѵ�.
  �丶�並 â�� �����ϴ� ���ڸ���� ���ڵ��� ũ��� ���� �丶���� ���� ���� �丶����� ������ �־����� ��, 
  ��ĥ�� ������ �丶����� ��� �ʹ���, �� �ּ� �ϼ��� ���ϴ� ���α׷��� �ۼ��϶�. ��, ������ �Ϻ� ĭ���� �丶�䰡 ������� ���� ���� �ִ�.
 <�Է�>
 ù �ٿ��� ������ ũ�⸦ ��Ÿ���� �� ���� M,N�� �׾ƿ÷����� ������ ���� ��Ÿ���� H�� �־�����. 
 M�� ������ ���� ĭ�� ��, N�� ������ ���� ĭ�� ���� ��Ÿ����. ��, 2 �� M �� 100, 2 �� N �� 100, 1 �� H �� 100 �̴�. 
 ��° �ٺ��ʹ� ���� ���� ���ں��� ���� ���� ���ڱ����� ����� �丶����� ������ �־�����. ��, ��° �ٺ��� N���� �ٿ��� �ϳ��� ���ڿ� ��� �丶���� ������ �־�����. 
 �� �ٿ��� ���� �����ٿ� ����ִ� �丶����� ���°� M���� ������ �־�����. ���� 1�� ���� �丶��, ���� 0 �� ���� ���� �丶��, 
 ���� -1�� �丶�䰡 ������� ���� ĭ�� ��Ÿ����. �̷��� N���� ���� H�� �ݺ��Ͽ� �־�����.
 <���>
 �������� �丶�䰡 ��� ���� ������ �ּ� ��ĥ�� �ɸ������� ����ؼ� ����ؾ� �Ѵ�. 
 ����, ����� ������ ��� �丶�䰡 �;��ִ� �����̸� 0�� ����ؾ� �ϰ�, �丶�䰡 ��� ������ ���ϴ� ��Ȳ�̸� -1�� ����ؾ� �Ѵ�.
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
