package workspace;

import helperClasses.InputReader;
import java.io.PrintWriter;

public class test_project {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String s = in.next();
        char []a = s.toCharArray();
        char []b = in.next().toCharArray();
        int n = a.length, m = b.length;

        int ans = 0, pos = 0;
        int [][]LCSuffix = new int[n][m];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                if(i == 0 && j == 0 && a[i] == b[j])
                    LCSuffix[i][j] = 1;
                else if(a[i] == b[j])
                    LCSuffix[i][j] = LCSuffix[i - 1][j - 1] + 1;

                if(LCSuffix[i][j] > ans) {
                    ans = LCSuffix[i][j];
                    pos = i;
                }
            }
        }

        out.println(ans);
        out.println(s.substring(pos - ans + 1, pos + 1));
    }
}