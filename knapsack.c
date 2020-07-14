#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>

/*
    문제 12865 - knapsack
    <입력>
    첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 
    두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.
    입력으로 주어지는 모든 수는 정수이다.

    <출력>
    한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.
*/

#define N 100
#define MAX_CAPACITY 1000001
#define MAX(a,b) a>b ? a:b;

int dp[N][MAX_CAPACITY] = { 0 };
int items[N][2];

int number;

int knapsack(int pos, int capacity) {
    if (pos == number) return 0;

    int ret = dp[pos][capacity];
    if (ret != -1) return ret;
    if (items[pos][0] <= capacity) {
        ret = knapsack(pos + 1, capacity - items[pos][0])
            + items[pos][1];
    }
    ret = MAX(ret, knapsack(pos + 1, capacity));
    return dp[pos][capacity] = ret;
};

int main(void) {
    int capacity;
    scanf("%d %d", &number, &capacity);
    
    for (int i = 0; i < number; i++) {
        scanf("%d %d", &items[i][0], &items[i][1]);
    }

    memset(dp, -1, sizeof(dp));

    printf("%d",knapsack(0, capacity));

    return 0;
}