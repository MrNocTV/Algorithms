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

using namespace std; 

int n, m, q;
map<int, pair<int, int> > range_map;

int main() 
{ 
    // fast IO
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> n;

    int val;
    int prev_val = -1;
    int start_idx = 1;
    for (int i = 1; i <= n; ++i) {
        cin >> val;
        if (prev_val != -1) {
            if (val != prev_val) {
                pair<int, int> p = make_pair(start_idx, i-1);
                range_map[prev_val] = p;
                prev_val = val;
                start_idx = i;
            }
        } else {
            prev_val = val;
        }
    }
    range_map[val] = make_pair(start_idx, n);

    cin >> m;
    for (int i = 1; i <= m; ++i) {
        cin >> q;
        pair<int, int> p = range_map[q];
        if (p.first == 0)
            cout << "-1 -1\n";
        else
            cout << p.first << " " << p.second << "\n";
    }

    return 0; 
} 