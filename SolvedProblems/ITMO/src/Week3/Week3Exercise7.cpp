#ifdef JUDGE
#include <fstream>
std::ifstream cin("input.txt");
std::ofstream cout("output.txt");
#else
#include <iostream>
using std::cin;
using std::cout;
#endif
#include <map>
#include <algorithm>

using namespace std;

int b, n, m;
int l, r;
long long d;
map<int, long long> value_at;
map<int, long long> sum_at;

long long get_sum_upto(int key) {
    map<int, long long>::iterator it = value_at.upper_bound(key);
    --it;
    int index = it->first;
    return sum_at[index] + value_at[index] * (key - index);
}

int main() {
    cin >> b >> n;
    value_at[0] = sum_at[0] = 0;
    for(int i = 0; i < n; ++i) {
        cin >> d >> l >> r;
        value_at[l] += d;
        value_at[r+1] -= d;
    }
    long long last_index = 0;
    for(map<int, long long>::iterator it = value_at.begin(); it != value_at.end(); ++it) {
        int index = it->first;
        value_at[index] += value_at[last_index];
        sum_at[index] = sum_at[last_index] + value_at[last_index] * (index - last_index);
        last_index = index;
    }
    cin >> m;
    for(int i = 0; i < m; ++i) {
        cin >> l >> r;
        cout << get_sum_upto(r + 1) - get_sum_upto(l) << '\n';
    }
    return 0;
}