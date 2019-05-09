package Week4;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Week4Exercise8 {

	static class Scan {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Scan(InputStream in) {
			din = new DataInputStream(in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public int nextInt() throws Exception {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			do {
				ret = ret * 10 + c - '0';
				c = read();
			} while (c > ' ');
			return ret;
		}

		private void fillBuffer() throws Exception {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws Exception {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}
	}

	 public static void sort(String[] a, int k, int[] order) {
	        int n = a.length;
	        int R = 123;   // extend ASCII alphabet size
	        String[] aux = new String[n];
	        int[] auxO = new int[n];
	        int o;
	        int w = a[0].length();

	        for (int d = w-1; d >= w-k; d--) {
	            // sort by key-indexed counting on dth character

	            // compute frequency counts
	            int[] count = new int[R+1];
	            for (int i = 0; i < n; i++)
	                count[a[i].charAt(d) + 1]++;

	            // compute cumulates
	            for (int r = 0; r < R; r++)
	                count[r+1] += count[r];

	            // move data
	            for (int i = 0; i < n; i++) {
	            	o = count[a[i].charAt(d)]++;
	            	auxO[o] = order[i];
	                aux[o] = a[i];
	            }
	            // copy back
	            for (int i = 0; i < n; i++) {
	                a[i] = aux[i];
	                order[i] = auxO[i];
	            }
	        }
	    }

	public static void main(String[] args) throws Exception {
		Scan scan = new Scan(new FileInputStream(new File("input.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		int n = scan.nextInt();
		int m = scan.nextInt();
		int k = scan.nextInt();
		int c;
		int offset = 0;
		StringBuilder[] input = new StringBuilder[n];
		int[] order = new int[n];
		
		for (int i = 0; i < input.length; ++i)
			input[i] = new StringBuilder();
		
		while ((c = scan.read()) != -1) {
			if (c >= 97 && c <= 122) {
				input[offset].append((char)c);
				if (offset >= n - 1) {
					offset = 0;
				} else {
					++offset;
				}

			}
		}
		
		for (int i = 1; i <= n; ++i) {
			order[i - 1] = i;
		}
		
		String[] strings = new String[n];
		for (int i = 0; i < strings.length; ++i)
			strings[i] = input[i].toString();
		
		sort(strings, k, order);
		
		StringBuilder sb = new StringBuilder();
		for (int j : order)
			sb.append(j + " ");
		bw.write(sb.toString().trim() + "\n");
		bw.close();

	}
}
