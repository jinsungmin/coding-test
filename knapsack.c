#define _CRT_SECURE_NO_WARNINGS

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>

/*
    ���� 12865 - knapsack
    <�Է�>
    ù �ٿ� ��ǰ�� �� N(1 �� N �� 100)�� �ؼ��� ��ƿ �� �ִ� ���� K(1 �� K �� 100,000)�� �־�����. 
    �� ��° �ٺ��� N���� �ٿ� ���� �� ������ ���� W(1 �� W �� 100,000)�� �ش� ������ ��ġ V(0 �� V �� 1,000)�� �־�����.
    �Է����� �־����� ��� ���� �����̴�.

    <���>
    �� �ٿ� �賶�� ���� �� �ִ� ���ǵ��� ��ġ���� �ִ��� ����Ѵ�.
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