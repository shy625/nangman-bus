package src.q15401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int[] T, P;
    public static int N, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N + 1];
        P = new int[N + 1];
        answer = 0;

        for (int i = 1; i < N + 1; i++) {
            String[] str = br.readLine().split(" ");
            T[i] = Integer.parseInt(str[0]);
            P[i] = Integer.parseInt(str[1]);
        }
        /*
        해결방법: 부분집합
        int n (현재 날짜)
        int p (현재 포인트)
        int cnt..?
         */
        f(1, 0);
        System.out.println(answer);
    }

    public static void f(int n, int p) {
        if(n == N + 1){
            if(p > answer) answer = p;
            return;
        }
        if(n > N) return;
        if(p > answer) answer = p;



        f(n + T[n], p + P[n]);

        f(n+1, p);

    }
}
