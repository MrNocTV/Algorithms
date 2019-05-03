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
#include <iomanip>
#include <queue>
#include <functional>

using namespace std;

typedef pair<int,pair<int,int> > ppi;

void print_matrix(long **matrix, int m, int n) {
    for(int i = 0; i < m; ++i) {
        for(int j = 0; j < n; ++j) {
            cout << setw(4) << matrix[i][j];
        }
        cout << endl;
    }
}

long long sorted_array_from_matrix(long **matrix, int m, int n) {
    int ith = 0;
    long long sum = 0;
    for (int j = 0; j < n; ++j) {
        for (int i = 0; i < m; i += 10) {
            sum += matrix[i][j];
            cout << matrix[i][j] << " ";
        }
    }

    return sum;
}

//  a is input array, b is working array
uint32_t * radixSort(uint32_t * a, uint32_t *b, size_t count)
{
size_t mIndex[4][256] = {0};            // count / index matrix
size_t i,j,m,n;
uint32_t u;
    for(i = 0; i < count; i++){         // generate histograms
        u = a[i];
        for(j = 0; j < 4; j++){
            mIndex[j][(size_t)(u & 0xff)]++;
            u >>= 8;
        }       
    }
    for(j = 0; j < 4; j++){             // convert to indices
        m = 0;
        for(i = 0; i < 256; i++){
            n = mIndex[j][i];
            mIndex[j][i] = m;
            m += n;
        }       
    }
    for(j = 0; j < 4; j++){             // radix sort
        for(i = 0; i < count; i++){     //  sort by current lsb
            u = a[i];
            m = (size_t)(u>>(j<<3))&0xff;
            b[mIndex[j][m]++] = u;
        }
        std::swap(a, b);                //  swap ptrs
    }
    return(a);
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    size_t n, m;
    cin >> n >> m;
    uint32_t *A = new uint32_t[n];
    uint32_t *B = new uint32_t[m];
    uint32_t *C = new uint32_t[n*m];
    uint32_t *CP = new uint32_t[n*m];
    for (int i = 0; i < n; ++i)
        cin >> A[i];
    for (int i = 0; i < m; ++i)
        cin >> B[i];
 
    int k = 0;
    for(int i = 0; i < m; ++i) {
        for(int j = 0; j < n; ++j) {
            C[k++] = A[j] * B[i];
        }
    }
    size_t cSize = n*m;
    C = radixSort(C, CP, cSize);
    long long sum = 0;
    for (size_t i = 0; i < cSize; i+=10)
        sum += C[i];
    cout << sum << endl;
    return 0;
}