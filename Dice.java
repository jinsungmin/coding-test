import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dice {
	
	static int N, M;
	static int x, y;
	static int[] cmd;
	static int[][] Map;
	static int cmdCount;
	static int[] Dice = new int[6];
	static int[] EWSN = new int[4];
	static int btmNum = 6;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		cmdCount = Integer.parseInt(st.nextToken());
		
		Map = new int[N][M];
		cmd = new int[cmdCount];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			if (st.countTokens() == 0) {
				st = new StringTokenizer(br.readLine(), " ");
			}
			int idx = 0;
			while (st.hasMoreTokens()) {
				int value = Integer.parseInt(st.nextToken());
				Map[i][idx] = value;
				idx += 1;
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		int i = 0;
		while (st.hasMoreTokens()) {
			cmd[i] = Integer.parseInt(st.nextToken());
			i++;
		}
		Dice[btmNum] = Map[x][y];
		EWSN[0] = 3;
		EWSN[1] = 4;
		EWSN[2] = 5;
		EWSN[4] = 2;
		
		printMap(Map);
	}
	
	public static void moveDice(int dir) {
		switch(btmNum) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			switch(dir) {
			case 1:	// 悼率
				if(y == M-1)
					break;
				btmNum = 3;
				Dice[btmNum] = Map[x][++y];
				System.out.println(Dice[7 - btmNum]);
				break;
			case 2:	// 辑率
				if(y == 0)
					break;
				btmNum = 4;
				Dice[btmNum] = Map[x][--y];
				System.out.println(Dice[7 - btmNum]);
				break;
			case 3:	// 合率
				if(x == 0)
					break;
				btmNum = 2;
				Dice[btmNum] = Map[--x][y];
				System.out.println(Dice[7 - btmNum]);
				break;
			case 4:	// 巢率
				if(x == N-1)
					break;
				btmNum = 5;
				Dice[btmNum] = Map[++x][y];
				System.out.println(Dice[7 - btmNum]);
				break;
			}
			break;
		case 6:
			switch(dir) {
			case 1:	// 悼率
				if(y == M-1)
					break;
				btmNum = 3;
				Dice[btmNum] = Map[x][++y];
				System.out.println(Dice[7 - btmNum]);
				break;
			case 2:	// 辑率
				if(y == 0)
					break;
				btmNum = 4;
				Dice[btmNum] = Map[x][--y];
				System.out.println(Dice[7 - btmNum]);
				break;
			case 3:	// 合率
				if(x == 0)
					break;
				btmNum = 2;
				Dice[btmNum] = Map[--x][y];
				System.out.println(Dice[7 - btmNum]);
				break;
			case 4:	// 巢率
				if(x == N-1)
					break;
				btmNum = 5;
				Dice[btmNum] = Map[++x][y];
				System.out.println(Dice[7 - btmNum]);
				break;
			}
			break;
		}
	}
	
	public static void printMap(int [][] map) {
		for(int i=0; i< N; i++) {
			for(int j = 0; j< M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
