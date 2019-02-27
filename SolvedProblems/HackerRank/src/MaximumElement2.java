
/**
 * 
 * @author Loc Truong Van
 * @category Algorithm
 * @site HackerRank
 * @link https://www.hackerrank.com/challenges/maximum-element/problem
 */

import java.util.*;

public class MaximumElement2 {

    static class StackNode {
        int val;
        int max;

        StackNode(int val, int max) {
            this.val = val;
            this.max = max;
        }
    }

    private static int max = Integer.MIN_VALUE;
    private static final Stack<StackNode> stack = new Stack<>();

    public static void doCommand(int code, int x) {
        switch(code) {
            case 1:
                max = Math.max(x, max);
                stack.push(new StackNode(x, max));
                break;
            case 2:
                if (stack.size() > 0) {
                    stack.pop();
                    if (stack.size() == 0)
                        max = Integer.MIN_VALUE;
                    else
                        max = Math.min(stack.peek().max, max);
                }
                break;
            case 3:
                if (stack.size() > 0)
                    System.out.println(stack.peek().max);
                break;
        }
    }


    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        for(int i = 1; i <= n; ++i) {
            String[] tokens = input.nextLine().trim().split(" ");
            int command = Integer.valueOf(tokens[0]);
            int x = tokens.length == 2 ? Integer.valueOf(tokens[1]) : -1;
            doCommand(command, x);
        }
    }
}
