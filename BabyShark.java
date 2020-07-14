import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BabyShark {

	static class Fish {
		int x;
		int y;
		int d;

		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	static int[][] dir = { { 0, 0 }, { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 },
			{ -1, 1 } }; // -, ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[][] map = new int[4][4];
	static boolean[] isLive = new boolean[17]; // 물고기가 죽었는지 살았는지 체크하는 배열
	static int answer = Integer.MIN_VALUE;
	static Fish[] fishes = new Fish[17];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Arrays.fill(isLive, true);

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				fishes[num] = new Fish(i, j, dir);
			}
		}

		isLive[map[0][0]] = false; // 상어가 처음 먹은 물고기를 죽인다.
		int sum = map[0][0];
		map[0][0] = -1; // 상어 표시
		eatFish(0, 0, fishes[sum].d, sum, 1);

		System.out.println(answer);
	}

	public static void move() {
		for (int i = 0; i < 16; i++) {

		}
	}

	public static void eatFish(int r, int c, int direction, int sum, int cnt) {
		int[][] copyMap = new int[4][4];
		Fish[] copyFishes = new Fish[17];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copyMap[i][j] = map[i][j];
			}
		}

		for (int i = 1; i <= 16; i++) {
			copyFishes[i] = fishes[i];
		}

		fishMove();

		for (int i = 1; i <= 3; i++) {	// 이동 가능 좌표 최대 3개

			int nr = r + (dir[direction][0] * i);
			int nc = c + (dir[direction][1] * i);

			if (isInside(nr, nc)) { // 경계 범위 안이고 살아있는 물고기면

				if (map[nr][nc] == 0)	// 물고기가 없으면 통과
					continue;

				map[r][c] = 0;	// 물고기 사망 표시
				int n = map[nr][nc];
				map[nr][nc] = -1;
				isLive[n] = false;	// n번 물고기 사망 표시
				// System.out.println("[상어가 움직이는 다음 좌표] nr : " + nr + " / nc : " + nc + " /
				// fishes[n].d : " + fishes[n].d + " / n : " + n);
				// System.out.println("상어가 지금 까지 먹은 물고기의 양 : " + (sum+n));
				eatFish(nr, nc, fishes[n].d, sum + n, cnt + 1);
				isLive[n] = true;
				map[nr][nc] = n;
				map[r][c] = -1;

			} else
				break;
		}
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				map[i][j] = copyMap[i][j];
			}
		}
		
		for(int i=1; i<=16; i++) {
			fishes[i] = copyFishes[i];
		}
		
		//System.out.println("sum : " + sum + " / cnt : " + cnt);
		// 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.
		answer = Math.max(sum, answer);
	}

	public static void fishMove() {
		for (int i = 1; i <= 16; i++) {
			if (!isLive[i])
				continue;

			Fish cur = fishes[i]; // 현재 움직일 물고기

			//System.out.println("현재 움직이는 물고기 번호 : " + i + " / 현재 물고기의 이동 방향 : " + cur.d);

			int nd = cur.d;
			int nx = cur.x, ny = cur.y;
			boolean flag = false;

			int[] changeDir = { 0, 2, 3, 4, 5, 6, 7, 8, 1 };

			for (int j = 0; j < 8; j++) { // 방향 전환 최대 7번

				nx = cur.x + dir[nd][0]; // 이동후 위치 설정
				ny = cur.y + dir[nd][1];

				if (isInside(nx, ny)) { // 이동 후 위치가 map 내부일 경우
					if (map[nx][ny] == -1) { // 위치에 상어가 있을 경우
						nd = changeDir[nd];
						continue;
					}
					if (map[nx][ny] == 0 || map[nx][ny] != -1) { // 위치에 물고기가 존재하거나 위치에 상어가 없을 경우
						flag = true;
						break;
					}
				} else { // 방향 전환
					nd = changeDir[nd];
				}
			}

			if (!flag)
				continue;

			// 현재 움직일 물고기 좌표에 있는 물고기와 자리를 바꾼다.
			int temp = map[nx][ny];
			map[nx][ny] = map[cur.x][cur.y];
			map[cur.x][cur.y] = temp;

			// 값 갱신
			fishes[i] = new Fish(nx, ny, nd); // 현재 움직일 물고기

			if (temp != 0) { // 빈칸이 아니면 갱신
				fishes[temp] = new Fish(cur.x, cur.y, fishes[temp].d);
			}
		}
	}

	public static boolean isInside(int x, int y) {
		return x >= 0 && x < 4 && y >= 0 && y < 4;
	}
	
	public static void print() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
