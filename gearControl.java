package codingTest;

import java.util.*;
import java.io.*;

/*
 	¹®Á¦ 14891 - »ï¼º
*/

public class gearControl {

	static class infor {
		private int number;
		private int dir;

		public infor(int number, int dir) {
			this.number = number;
			this.dir = dir;
		}
	}

	static int[][] gear = new int[4][8];
	static int count;
	static LinkedList<infor> queue = new LinkedList<infor>();
	
	static void solution(int count) {
		while (count > 0) {
			boolean[] move = new boolean[4];
			int[] nDir = new int[4];
			infor cur = (infor) queue.poll();
			nDir[cur.number] = cur.dir;
			move[cur.number] = true;

			if (cur.number == 1 || cur.number == 2) {
				if (gear[cur.number][2] != gear[cur.number + 1][6]) {
					move[cur.number + 1] = true;
					nDir[cur.number + 1] = cur.dir * -1;
				}

				if (gear[cur.number][6] != gear[cur.number - 1][2]) {
					move[cur.number - 1] = true;
					nDir[cur.number - 1] = cur.dir * -1;
				}
				
				if(cur.number == 1) {
					if (move[cur.number+1] && gear[cur.number+1][2] != gear[cur.number + 2][6]) {
						move[cur.number + 2] = true;
						nDir[cur.number + 2] = nDir[cur.number + 1] * -1;
					}
				} else {
					if (move[cur.number-1] && gear[cur.number-2][2] != gear[cur.number-1][6]) {
						move[cur.number - 2] = true;
						nDir[cur.number - 2] = nDir[cur.number - 1] * -1;
					}
				}
			} else if(cur.number == 0) {
				for(int i = 0; i< 3; i++) {
					if(move[cur.number+i] && gear[cur.number+i][2] != gear[cur.number + i+1][6]) {
						move[cur.number + i + 1] = true;
						nDir[cur.number+ i + 1] = nDir[cur.number+ i] * -1;
					}
				}
			} else {
				for(int i = 0; i < 3; i++) {
					if(move[cur.number-i] && gear[cur.number-i][6] != gear[cur.number-i-1][2]) {
						move[cur.number-i-1] = true;
						nDir[cur.number-i-1] = nDir[cur.number-i] * -1;
					}
				}
			}
			
			for(int i = 0; i< 4; i++) {
				if(move[i]) 
					rotate(new infor(i, nDir[i]));
			}
			
			count--;
		}
		
		totalScore();
	}
	
	static void totalScore() {
		int score  = 0;
		for(int i = 0; i< 4; i++) {
			if(gear[i][0] == 1) {
				score += Math.pow(2, i);
			}
		}
		System.out.println(score);
	}

	static void rotate(infor infor) {
		int num = infor.number;
		int[] temp = new int[8];

		if (infor.dir == -1) {
			for (int i = 0; i < 8; i++) {
				if (i == 7) {
					temp[i] = gear[num][0];
				} else {
					temp[i] = gear[num][i + 1];
				}
			}
		} else {
			for (int i = 0; i < 8; i++) {
				if (i == 0) {
					temp[i] = gear[num][7];
				} else {
					temp[i] = gear[num][i - 1];
				}
			}
		}
		for(int i = 0; i< 8; i++) {
			gear[num][i] = temp[i];
		}
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			String[] str;
			
			str = br.readLine().split("");
			for (int j = 0; j < 8; j++) {
				gear[i][j] = Integer.parseInt(str[j]);
			}
		}
		st = new StringTokenizer(br.readLine());
		count = Integer.parseInt(st.nextToken());

		for (int i = 0; i < count; i++) {
			st = new StringTokenizer(br.readLine());
			queue.add(new infor(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
		}

		solution(count);
	}

}
