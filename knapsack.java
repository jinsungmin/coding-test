import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class knapsack {
	
	static int items[][];
	static int number, maxWeight;
	static int dp[][];
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		number = Integer.parseInt(st.nextToken());
		
		items = new int[number][2];
		
		maxWeight = Integer.parseInt(st.nextToken());
		
		dp = new int[number][maxWeight + 1];
		
		for (int i = 0; i < number; i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solve(0,maxWeight));
	}
	
	public static void print(int number) {
		for(int i = 0; i< number; i++) {
			System.out.println("(" + items[i][0] + ", " + items[i][1] + ")");
		}
	}
	
	public static int solve(int num, int capacity) {
		if(num == number) return 0;
		
		int ret = dp[num][capacity];
		if(items[num][0] <= capacity) {
			ret = solve(num + 1, capacity - items[num][0]) + items[num][1];
		}
		ret = Math.max(ret, solve(num+1, capacity));
		
		return dp[num][capacity] = ret;
	}
	
}
