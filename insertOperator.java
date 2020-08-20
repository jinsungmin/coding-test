package codingTest;

import java.util.*;
import java.io.*;

public class insertOperator {
	
	static int N;
	static int[] ex;
	static int[] op = new int[4];
	static int[] order;
	static int[] temp = new int[4];
	static int tp;
	static int result;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int[] eq;
	
	static void solution() {
		copy(temp, op);
		check(op,0);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static void check(int[] tp, int depth) {
		copy(temp, tp);
		
		while(depth == N - 1) {
			result = calculate();
			if(max < result)
				max = result;
			if(min > result)
				min = result;
			/*
			for(int i = 0; i< N - 1; i++) {
				System.out.print(order[i] + " ");
			}
			System.out.println();
			*/
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(temp[i] != 0) {
				order[depth] = i;
				temp[i]--;
				check(temp, depth + 1);
				temp[i]++;
			}
		}
	}
	
	static int calculate() {
		copy(eq,ex);
		for(int i = 0; i < N-1; i++) {
			switch(order[i]) {
			case 0:
				tp = eq[i] + eq[i+1];
				eq[i+1] = tp;
				break;
			case 1:
				tp = eq[i] - eq[i+1];
				eq[i+1] = tp;
				break;
			case 2:
				tp = eq[i] * eq[i+1];
				eq[i+1] = tp;
				break;
			case 3:
				tp = eq[i] / eq[i+1];
				eq[i+1] = tp;
				break;
			}
		}
		return eq[N-1];
	}
	
	static void copy(int[] arr1, int[] arr2) {
		for(int i = 0; i< arr1.length; i++) {
			arr1[i] = arr2[i];
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		ex = new int[N];
		eq = new int[N];
		order = new int[N-1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< N; i++) {
			ex[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		solution();
		
	}

}
