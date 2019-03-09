/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/manasa-and-stones/problem
 * @idea: This is the brute force approach, can pass only really small test cases
 */


import java.io._
import scala.io._
import scala.collection.mutable._

object Solution {

    // Complete the stones function below.
    def stones(n: Int, a: Int, b: Int): Array[Int] = {
        val A = new Array[Int](n)
        A(0) = 0
        var s = SortedSet[Int]()
        recursive(n, a, b, 1, A, s)
        s.toArray
    }

    def recursive(n: Int, a: Int, b: Int, i: Int, A: Array[Int], s: SortedSet[Int]) : Unit = {
        if (i == n - 1) {
            A(i) = A(i - 1) + a
            println(A.deep.mkString(" ")) // debug log
            s += A(i)
            A(i) = A(i - 1) + b
            println(A.deep.mkString(" ")) // debug log
            s += A(i)
        }
        if (i + 1 < n) {
            A(i) = A(i - 1) + a
            recursive(n, a, b, i + 1, A, s)
            A(i) = A(i - 1) + b
            recursive(n, a, b, i + 1, A, s)
        }
        
    }

    def main(args: Array[String]) {
        val stdin = scala.io.StdIn

        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

        val T = stdin.readLine.trim.toInt

        for (TItr <- 1 to T) {
            val n = stdin.readLine.trim.toInt
            val a = stdin.readLine.trim.toInt
            val b = stdin.readLine.trim.toInt
            val result = stones(n, a, b)
            printWriter.println(result.mkString(" "))
        }

        printWriter.close()
    }
}
