import java.util.Arrays;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author mthai
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		CF_467C solver = new CF_467C();
		solver.solve(1, in, out);
		out.close();
	}
}

class CF_467C {
    long[] cul;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        int []a = new int[n + 1];
        for(int i = 1; i <= n; ++i)
            a[i] = in.nextInt();

        cul = new long[n + 1];
        for(int i = 1; i <= n; ++i)
            cul[i] = cul[i - 1] + a[i];

        long ans = 0;
        long [][]dp = new long[3][n + 1];
        for(int j = m; j <= n; ++j) {
            dp[1][j] = Math.max(dp[1][j - 1], sum(j - m + 1, j));
            ans = Math.max(ans, dp[1][j]);
        }

        int prev = 1, cur = 2;
        for(int i = 2; i <= k; ++i) {
            for(int j = m; j <= n; ++j) {
                if(dp[prev][j - m] > 0) {
                    dp[cur][j] += dp[prev][j - m] + sum(j - m + 1, j);
                    dp[cur][j] = Math.max(dp[cur][j - 1], dp[cur][j]);
                    ans = Math.max(ans, dp[cur][j]);
                }
            }
            cur = prev;
            prev = 3 - cur;
            Arrays.fill(dp[cur], 0);
        }

//        for(int i = 1; i <= 2; ++i)
//            System.out.println(Arrays.toString(dp[i]));

        out.println(ans);
    }

    long sum(int l, int r) {
        return cul[r] - cul[l - 1];
    }
}

class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

}

