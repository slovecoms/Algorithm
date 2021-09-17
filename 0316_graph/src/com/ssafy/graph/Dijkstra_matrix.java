package com.ssafy.graph;

import java.io.*;
import java.util.*;

public class Dijkstra_matrix {
	static int V, E, Ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner("7 11\n" + "0 1 31\r\n" + "0 2 31\r\n" + "0 6 31\r\n" + "0 5 60\r\n" + "1 2 21\r\n"
				+ "2 4 46\r\n" + "2 6 25\r\n" + "3 4 34\r\n" + "4 6 51\r\n" + "5 3 18\r\n" + "5 4 40\r\n");
		V = sc.nextInt();
		E = sc.nextInt();
		int[][] adj = new int[V][V];
		for (int i = 0; i < E; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			int w = sc.nextInt();
			// dijkstra 는 단방향이면되
			adj[s][e] = w;
		}
		// print(adj);
		int[] vertex = new int[V];
		boolean[] v = new boolean[V];
		Arrays.fill(vertex, Integer.MAX_VALUE);
		vertex[0] = 0;
		for (int cnt = 0; cnt < V - 1; cnt++) {
			int minV = -1;
			int minD = Integer.MAX_VALUE;
			for (int i = 0; i < vertex.length; i++) {
				if (!v[i] && minD > vertex[i]) {
					minV = i;
					minD = vertex[i];
				}

			}
			// System.out.println(minV);

			/**
			 * minV : 기준정점 (시작정점) i : 도착정점
			 */
			System.out.println(Arrays.toString(vertex) + " " + minV);
			v[minV] = true;
			for (int i = 0; i < V; i++) {
				if (adj[minV][i] != 0 && vertex[i] > adj[minV][i] + vertex[minV] && !v[i]) {
					vertex[i] = adj[minV][i] + vertex[minV];
				}
			}
			// System.out.println(Arrays.toString(vertex));

		}
		for (int i = 0; i < vertex.length; i++) {
			Ans += vertex[i];
		}
		System.out.println(Ans);

	}

	private static void print(int[][] adj) {
		for (int r = 0; r < adj.length; r++) {
			for (int c = 0; c < adj[r].length; c++) {
				System.out.print(adj[r][c] + "\t");
			}
			System.out.println();
		}
	}

}
