package codingTest;

import java.io.*;
import java.util.*;

public class fibonacci {

	static int N;
	static int result;
	static int[] fibo;
	
	static int fib(int num) {
		
		if(num == 0 || num == 1) {
			return num;
		}
		
		fibo[0] = 0;
		fibo[1] = 1;
		
		for(int i = 2; i < num; i++) {
			fibo[i] = fibo[i-1] + fibo[i-2];
		}
		
		return fibo[num-1];
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		fibo = new int[N+1];
		
		result = fib(N+1);
		
		System.out.println(result);
	}

}
