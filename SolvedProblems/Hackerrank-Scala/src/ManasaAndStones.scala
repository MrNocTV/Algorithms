/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/manasa-and-stones/problem
 * @idea: Hint: look at the smallest and largest value in the output array
 * 	- the smallest one is b*0 + a * (n - 1 - 0)
 *  - the largest one is a*0 + b * (n - 1 - 0)
 *  Now you get an upper bound and lower bound, it's just a simple for loop
 */

import java.io._
import scala.collection.mutable._
import scala.io._

object Solution1 {

    // Complete the stones function below.
    def stones(n: Int, a: Int, b: Int): Array[Int] = {
        val set = SortedSet[Int]()
        for(i <- 0 to (n-1)) {
            val temp1 = a*i + b * (n - 1 - i)
            val temp2 = b*i + a * (n - 1 - i)
            set += temp1
            set += temp2
        }
        set.toArray
    }

    def main(args: Array[String]) {
        val stdin = StdIn

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