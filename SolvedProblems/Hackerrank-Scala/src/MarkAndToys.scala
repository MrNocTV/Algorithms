/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/mark-and-toys/problem
 * @idea: sort and count
 */


import scala.io._
import scala.math._
import java.io.PrintWriter


object MarkAndToys {
    def maximumToys(prices: Array[Int], k: Int): Int = {
        var maxCanBy: Int = 1
        var money: Int = k
        
        for(p <- prices.sorted) {
            if (money - p >= 0) {
                money -= p
                maxCanBy += 1
            }
        }
        
        maxCanBy
    }
    
    def main(args: Array[String]) {
        val stdin = StdIn
        val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))
        val nk = stdin.readLine().split(" ")
        val n = nk(0).trim().toInt
        val k = nk(1).trim().toInt
        
        val prices = stdin.readLine().split(" ").map((e: String) => e.trim().toInt)
        val result = maximumToys(prices, k)
        printWriter.println(result)
        printWriter.close()
    }
    
    
}