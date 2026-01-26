#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {

            if (i == j) {
                dp[i][j] = 0;
            } else {
                int ans = INT_MAX;

                // horizontal cuts
                for (int k = 1; k < i; k++) {
                    ans = min(ans, dp[k][j] + dp[i - k][j] + 1);
                }

                // vertical cuts
                for (int k = 1; k < j; k++) {
                    ans = min(ans, dp[i][k] + dp[i][j - k] + 1);
                }

                dp[i][j] = ans;
            }
        }
    }

    cout << dp[n][m] << "\n";
    return 0;
}
