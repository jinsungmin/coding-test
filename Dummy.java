
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;

class makeDummy {
	
	static class Dot {
		int x,y;
		
		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public int[][] board;
	Deque<Dot> snake;

	public Map<Integer, Character> hashMap = new HashMap<Integer, Character>();
	
	public void makeBoard(int boardSize) {
		board = new int [boardSize+1][boardSize+1];		
	}
	
	public void makeApple(int x, int y) {
		board[x][y] = 1;
	}
	public void storeMove(int timer, char dir) {
		hashMap.put(timer, dir);
	}
	
	public void makeSnake() {
		board[1][1] = 2;
		snake = new ArrayDeque<Dot>();
		snake.add(new Dot(1,1));
	}
	
	public void moveSnake(int boardSize) {
		int time = 0;
		int i = 1,j = 1;
		int key = 0;
		TreeMap<Integer, Character> tm = new TreeMap<Integer, Character>(hashMap);
		Iterator<Integer> iteratorKey = tm.keySet().iterator();   //키값 오름차순 정렬(기본)
		int num = 4;
		int crush = 0;
		
		if(iteratorKey.hasNext()) {
			key = iteratorKey.next();
		}
		while(true) {
			switch(num) {
			case 1: 	// RIGHT	
				i++;
				if(i > boardSize) {	// 범위 초과
					time++;
					break;
				}
				if(board[i][j] == 1) {	// apple 있을 경우 
					snake.addFirst(new Dot(i,j));
					board[i][j] = 2;
				} else if(board[i][j] == 0){
					board[snake.getLast().x][snake.getLast().y] = 0;
					snake.removeLast();
					board[i][j] = 2;
					snake.addFirst(new Dot(i,j));
				} else {
					time++;
					crush = 1;
					break;
				}
				break;
			case 2:		// UP
				j--;
				if(j <= 0) {
					time++;
					break;
				}
				if(board[i][j] == 1) {
					snake.addFirst(new Dot(i,j));
					board[i][j] = 2;
				} else if(board[i][j] == 0){
					board[snake.getLast().x][snake.getLast().y] = 0;
					snake.removeLast();
					board[i][j] = 2;
					snake.addFirst(new Dot(i,j));
				} else {
					time++;
					crush = 1;
					break;
				}
				break;
			case 3:		// LEFT
				i--;
				if(i <= 0) {
					time++;
					break;
				}
				if(board[i][j] == 1) {
					snake.addFirst(new Dot(i,j));
					board[i][j] = 2;
				} else if(board[i][j] == 0){
					board[snake.getLast().x][snake.getLast().y] = 0;
					snake.removeLast();
					board[i][j] = 2;
					snake.addFirst(new Dot(i,j));
				} else {
					time++;
					crush = 1;
					break;
				}
				break;
			case 4:		// DOWN
				j++;
				if(j > boardSize) {
					time++;
					break;
				}
				if(board[i][j] == 1) {
					snake.addFirst(new Dot(i,j));
					board[i][j] = 2;
				} else if(board[i][j] == 0){
					board[snake.getLast().x][snake.getLast().y] = 0;
					snake.removeLast();
					board[i][j] = 2;
					snake.addFirst(new Dot(i,j));
				} else {
					time++;
					crush = 1;
					break;
				}
				
				break;
			}
				
			if(crush == 1)
				break;
			
			if(i <= 0 || i > boardSize || j <= 0 || j > boardSize) {	// Snake이 보드를 빠져나가면 game over
				break;
			}
			
			time++;
			System.out.println(i + ", " + j);
			if(time == key) {
				if(tm.get(key) == 'D') {
					if(num == 4) {
						num = 1;
					} else {
						num++;
					}
				} else if(tm.get(key) == 'L') {
					if(num == 1) {
						num = 4;
					} else {
						num--;
					}
				}
				
				if(iteratorKey.hasNext()) {
					key = iteratorKey.next();
				}
			}
			
		}
		System.out.println(time);
	}
}

public class Dummy {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int boardSize, appleCount;
		int x,y;
		int timer;
		char dir;
		int moveCount;
		makeDummy dm = new makeDummy();
		Scanner scan = new Scanner(System.in);
		
		boardSize = scan.nextInt();
		dm.makeBoard(boardSize);
		appleCount = scan.nextInt();
		
		for(int i = 0; i< appleCount; i++) {
			x = scan.nextInt();
			y = scan.nextInt();
			dm.makeApple(x, y);
		}
		
		moveCount = scan.nextInt();
		
		for(int i = 0; i< moveCount; i++) {
			timer = scan.nextInt();
			dir = scan.next().charAt(0);
			dm.storeMove(timer,  dir);
		}
		dm.makeSnake();
		dm.moveSnake(boardSize);
		
		scan.close();
	}

}
