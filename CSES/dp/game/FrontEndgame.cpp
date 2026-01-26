#include <bits/stdc++.h>
using namespace std;

int n;
vector<long> a;
vector<long> prefix;
vector<vector<long>> dp;

long solve(long l, long r) {
    if (l == r) return a[l];
    if (dp[l][r] != -1) return dp[l][r];

    long total = prefix[r + 1] - prefix[l]; // sum of a[l..r]

    long takeLeft  = total - solve(l + 1, r);
    long takeRight = total - solve(l, r - 1);

    return dp[l][r] = max(takeLeft, takeRight);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n;
    a.resize(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    prefix.assign(n + 1, 0);
    for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + a[i];

    dp.assign(n, vector<long>(n, -1));

    long aMax = solve(0, n - 1);
    cout << aMax << "\n";
    return 0;
}
