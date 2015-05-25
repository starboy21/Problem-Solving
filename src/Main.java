import java.util.Scanner;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author mthai
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		Scanner in = new Scanner(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		HKR_SupermanCelebratesDiwali solver = new HKR_SupermanCelebratesDiwali();
		solver.solve(1, in, out);
		out.close();
	}
}

class HKR_SupermanCelebratesDiwali {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int N = in.nextInt();
        int H = in.nextInt();
        int I = in.nextInt();
        int []count = new int[N + 1];
        int [][]people = new int[N + 1][H + 1];
        for(int i = 1; i <= N; ++i) {
            count[i] = in.nextInt();
            for(int j = 0; j < count[i]; ++j) {
                int floor = in.nextInt();
                people[i][floor]++;
            }
        }

        int [][]dp = new int[N + 1][H + 1];
        int []prev = new int[H + 1];
        for(int i = 1; i <= N; ++i) {
            dp[i][H] = people[i][H];
            prev[H] = Math.max(dp[i][H], prev[H]);
        }

        for(int j = H - 1; j > 0; --j) {
            for(int i = 1; i <= N; ++i) {
                dp[i][j] = dp[i][j + 1] + people[i][j];
                if(j + I <= H)
                    dp[i][j] = Math.max(prev[j + I] + people[i][j], dp[i][j]);
                prev[j] = Math.max(dp[i][j], prev[j]);
            }
        }

int ans = 0;
        for(int i = 1; i <= N; ++i)
            ans = Math.max(dp[i][1], ans);

        out.println(ans);
    }
}

