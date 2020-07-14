import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
���� 14503 - Robot Cleaner
<����>
�κ� û�ұⰡ �־����� ��, û���ϴ� ������ ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

�κ� û�ұⰡ �ִ� ��Ҵ� N��M ũ���� ���簢������ ��Ÿ�� �� ������, 1��1ũ���� ���簢�� ĭ���� �������� �ִ�. ������ ĭ�� �� �Ǵ� �� ĭ�̴�. û�ұ�� �ٶ󺸴� ������ ������, �� ������ ��, ��, ��, ���� �ϳ��̴�. ������ �� ĭ�� (r, c)�� ��Ÿ�� �� �ְ�, r�� �������κ��� ������ ĭ�� ����, c�� �������� ���� ������ ĭ�� �����̴�.

�κ� û�ұ�� ������ ���� �۵��Ѵ�.

���� ��ġ�� û���Ѵ�.
���� ��ġ���� ���� ������ �������� ���ʹ������ ���ʴ�� Ž���� �����Ѵ�.
���� ���⿡ ���� û������ ���� ������ �����Ѵٸ�, �� �������� ȸ���� ���� �� ĭ�� �����ϰ� 1������ �����Ѵ�.
���� ���⿡ û���� ������ ���ٸ�, �� �������� ȸ���ϰ� 2������ ���ư���.
�� ���� ��� û�Ұ� �̹� �Ǿ��ְų� ���� ��쿡��, �ٶ󺸴� ������ ������ ä�� �� ĭ ������ �ϰ� 2������ ���ư���.
�� ���� ��� û�Ұ� �̹� �Ǿ��ְų� ���̸鼭, ���� ������ ���̶� ������ �� �� ���� ��쿡�� �۵��� �����.
�κ� û�ұ�� �̹� û�ҵǾ��ִ� ĭ�� �� û������ ������, ���� ����� �� ����.

<�Է�>
ù° �ٿ� ���� ũ�� N�� ���� ũ�� M�� �־�����. (3 �� N, M �� 50)
��° �ٿ� �κ� û�ұⰡ �ִ� ĭ�� ��ǥ (r, c)�� �ٶ󺸴� ���� d�� �־�����. 
d�� 0�� ��쿡�� ������, 1�� ��쿡�� ������, 2�� ��쿡�� ������, 3�� ��쿡�� ������ �ٶ󺸰� �ִ� ���̴�.
��° �ٺ��� N���� �ٿ� ����� ���°� ���ʺ��� ���� �������, �� ���� ���ʺ��� ���� ������� �־�����. 
�� ĭ�� 0, ���� 1�� �־�����. ������ ù ��, ������ ��, ù ��, ������ ���� �ִ� ��� ĭ�� ���̴�.
�κ� û�ұⰡ �ִ� ĭ�� ���´� �׻� �� ĭ�̴�.

<���>
�κ� û�ұⰡ û���ϴ� ĭ�� ������ ����Ѵ�.
*/


public class robotCleaner {

	static class robot {
		int x;
		int y;
		int dir;

	}

	static int map[][];
	static int N, M;
	static int score = 0;
	static int dx[] = { -1, 0, 1, 0 }; // ��, ��, ��, ��
	static int dy[] = { 0, 1, 0, -1 };
	static robot cleaner = new robot();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		st = new StringTokenizer(br.readLine());
		cleaner.y = Integer.parseInt(st.nextToken());
		cleaner.x = Integer.parseInt(st.nextToken());
		cleaner.dir = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if (map[cleaner.y][cleaner.x] != 1) {
			map[cleaner.y][cleaner.x] = -1;
			score += 1;
			//print();
			solve();
		}
				
		System.out.println(score);
	}

	static void solve() {
		int i;
		for (i = 0; i < 4; i++) {
			int d = ((4 - cleaner.dir) % 4 + i) % 4;
			if (isInside(cleaner.y + dy[d],cleaner.x + dx[d])) {
				if (map[cleaner.y + dy[d]][cleaner.x + dx[d]] == 0) {
					
					map[cleaner.y + dy[d]][cleaner.x + dx[d]] = -1;
					score++;
					cleaner.x += dx[d];
					cleaner.y += dy[d];
					cleaner.dir = 3 - d ;
					//print();
					solve();
					break;
				}
			}
		}
		
		if (i == 4 && isInside(cleaner.y - dy[3 - cleaner.dir], cleaner.x - dx[3 - cleaner.dir])) {
			if (map[cleaner.y - dy[3 - cleaner.dir]][cleaner.x - dx[3 - cleaner.dir]] != 1) {
				cleaner.x -= dx[3 - cleaner.dir];
				cleaner.y -= dy[3 - cleaner.dir];
				solve();
			}
		}
	}

	public static boolean isInside(int y, int x) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.printf("%2d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println(score);
	}

}
