#ifdef JUDGE
#include <fstream>
std::ifstream cin("input.txt");
std::ofstream cout("output.txt");
#else
#include <iostream>
using std::cin;
using std::cout;
#endif

#include <algorithm>    // nth_element, std::random_shuffle
#include <vector>       // vector

using namespace std;

void gen_vector(vector<int> &V, int A, int B, int C, int a1, int a2, int N) {
    V.push_back(a1);
    V.push_back(a2);
    int ai;
    for (int i = 2; i < N; ++i) {
        ai = A * a1 + B * a2 + C;
        V.push_back(ai);
        a1 = a2;
        a2 = ai;
    }
}

void print(vector<int> V) {
    cout << "V contains:";
    for (vector<int>::iterator it=V.begin(); it!=V.end(); ++it)
        cout << ' ' << *it;
    cout << '\n';
}

void solve(vector<int> &V, int k1, int k2) {
    if (V.size() <= 100000) {
        sort(V.begin(), V.end());
        --k1;
        --k2;
        for(int i = k1; i <= k2; ++i) {
            cout << V[i] << (i < k2 ? " " : "\n");
        }
    } else {
        if (k1 == k2) {
            if (k1 == V.size()) {
                int max = *max_element(V.begin(), V.end()); 
                cout << max << "\n";
            } else {
                nth_element(V.begin(), V.begin() + k1, V.end());
                cout << V[k1-1] << "\n";
            }

        } else {
            int size = k2 - k1 + 1;
            int l_bound, u_bound;
            
            // get upper bound
            if (k2 == V.size()) {
                u_bound = *max_element(V.begin(), V.end());
            } else {
                nth_element(V.begin(), V.begin() + k2, V.end());
                u_bound = V[k2-1];
            }
            
            // get lower bound
            nth_element(V.begin(), V.begin() + k1, V.begin() + k2);
            l_bound = V[k1-1];

            vector<int> result;
            for(vector<int>::iterator it=V.begin() + (k1-1); it!=V.begin() + k2; ++it) {
                if (*it >= l_bound && *it <= u_bound)
                    result.push_back(*it);
                if (result.size() == size)
                    break;
            }
            sort(result.begin(), result.end());
            for(int i = 0; i < result.size(); ++i) {
                cout << result[i] << (i == result.size() - 1 ? "\n" : " ");
            }
        }
    }
}

int main () {
    vector<int> V;
    int N, k1, k2;
    int A, B, C, a1, a2;
    cin >> N >> k1 >> k2;
    cin >> A >> B >> C >> a1 >> a2;
    gen_vector(V, A, B, C, a1, a2, N);
    solve(V, k1, k2);

    return 0;
}