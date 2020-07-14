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
			{ -1, 1 } }; // -, ��, ��, ��, ��, ��, ��, ��, ��
	static int[][] map = new int[4][4];
	static boolean[] isLive = new boolean[17]; // ����Ⱑ �׾����� ��Ҵ��� üũ�ϴ� �迭
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

		isLive[map[0][0]] = false; // �� ó�� ���� ����⸦ ���δ�.
		int sum = map[0][0];
		map[0][0] = -1; // ��� ǥ��
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

		for (int i = 1; i <= 3; i++) {	// �̵� ���� ��ǥ �ִ� 3��

			int nr = r + (dir[direction][0] * i);
			int nc = c + (dir[direction][1] * i);

			if (isInside(nr, nc)) { // ��� ���� ���̰� ����ִ� ������

				if (map[nr][nc] == 0)	// ����Ⱑ ������ ���
					continue;

				map[r][c] = 0;	// ����� ��� ǥ��
				int n = map[nr][nc];
				map[nr][nc] = -1;
				isLive[n] = false;	// n�� ����� ��� ǥ��
				// System.out.println("[�� �����̴� ���� ��ǥ] nr : " + nr + " / nc : " + nc + " /
				// fishes[n].d : " + fishes[n].d + " / n : " + n);
				// System.out.println("�� ���� ���� ���� ������� �� : " + (sum+n));
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
		// �� �̵��� �� �ִ� ĭ�� ������ �������� ��� ������ ����.
		answer = Math.max(sum, answer);
	}

	public static void fishMove() {
		for (int i = 1; i <= 16; i++) {
			if (!isLive[i])
				continue;

			Fish cur = fishes[i]; // ���� ������ �����

			//System.out.println("���� �����̴� ����� ��ȣ : " + i + " / ���� ������� �̵� ���� : " + cur.d);

			int nd = cur.d;
			int nx = cur.x, ny = cur.y;
			boolean flag = false;

			int[] changeDir = { 0, 2, 3, 4, 5, 6, 7, 8, 1 };

			for (int j = 0; j < 8; j++) { // ���� ��ȯ �ִ� 7��

				nx = cur.x + dir[nd][0]; // �̵��� ��ġ ����
				ny = cur.y + dir[nd][1];

				if (isInside(nx, ny)) { // �̵� �� ��ġ�� map ������ ���
					if (map[nx][ny] == -1) { // ��ġ�� �� ���� ���
						nd = changeDir[nd];
						continue;
					}
					if (map[nx][ny] == 0 || map[nx][ny] != -1) { // ��ġ�� ����Ⱑ �����ϰų� ��ġ�� �� ���� ���
						flag = true;
						break;
					}
				} else { // ���� ��ȯ
					nd = changeDir[nd];
				}
			}

			if (!flag)
				continue;

			// ���� ������ ����� ��ǥ�� �ִ� ������ �ڸ��� �ٲ۴�.
			int temp = map[nx][ny];
			map[nx][ny] = map[cur.x][cur.y];
			map[cur.x][cur.y] = temp;

			// �� ����
			fishes[i] = new Fish(nx, ny, nd); // ���� ������ �����

			if (temp != 0) { // ��ĭ�� �ƴϸ� ����
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
