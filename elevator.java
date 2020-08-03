import java.util.*;
import java.io.*;

/*
  	���� 5014
  	<����>
  	��ȣ�� �ڵ� ������ �ϴ� ��ŸƮ�� ��ŸƮ��ũ�� �����ߴ�. ������ ��ȣ�� �������̴�. ������, ������ �� ��ȣ�� ��ŸƮ��ũ�� �ִ� �ǹ��� �ʰ� �����ϰ� ���Ҵ�.
	��ŸƮ��ũ�� �� F������ �̷���� ���� �ǹ��� �繫���� �ְ�, ��ŸƮ��ũ�� �ִ� ���� ��ġ�� G���̴�. ��ȣ�� ���� �ִ� ���� S���̰�, ���� ���������͸� Ÿ�� G������ �̵��Ϸ��� �Ѵ�.
	���� ���������Ϳ��� � ������ �̵��� �� �ִ� ��ư�� ������, ��ȣ�� ź ���������ʹ� ��ư�� 2���ۿ� ����. U��ư�� ���� U���� ���� ��ư, 
	D��ư�� �Ʒ��� D���� ���� ��ư�̴�. (����, U�� ��, �Ǵ� D�� �Ʒ��� �ش��ϴ� ���� ���� ����, ���������ʹ� �������� �ʴ´�)
	��ȣ�� G���� �����Ϸ���, ��ư�� ��� �� �� ������ �ϴ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�. ����, ���������͸� �̿��ؼ� G���� �� �� ���ٸ�, "use the stairs"�� ����Ѵ�.
  	<�Է�>
  	ù° �ٿ� F, S, G, U, D�� �־�����. (1 �� S, G �� F �� 1000000, 0 �� U, D �� 1000000) �ǹ��� 1������ �����ϰ�, ���� ���� ���� F���̴�.
  	<���>
  	ù° �ٿ� ��ȣ�� S������ G������ ���� ���� ������ �ϴ� ��ư�� ���� �ּڰ��� ����Ѵ�. ����, ���������ͷ� �̵��� �� ���� ���� "use the stairs"�� ����Ѵ�.
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
