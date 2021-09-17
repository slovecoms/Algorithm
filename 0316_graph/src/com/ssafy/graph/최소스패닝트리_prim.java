package com.ssafy.graph;

import java.io.*;
import java.util.*;

public class 최소스패닝트리_prim {
	static int parent[], V, E, sum;
	static Scanner sc;

	static class Edge implements Comparable<Edge> {
		int s, e, c;

		public Edge(int s, int e, int c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}

		public Edge(int e, int c) {
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.c, o.c);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/최소스패닝트리.txt"));
		sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();

		prim();

		System.out.println(sum);

		sc.close();
	}

	private static void prim() {
		ArrayList<Edge> adj[] = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<Edge>();
		}

		for (int i = 0; i < E; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			int c = sc.nextInt();
			adj[s].add(new Edge(e, c));
			adj[e].add(new Edge(s, c));
		}

		boolean[] v = new boolean[V + 1];
		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;

		for (int cnt = 1; cnt <= V - 1; cnt++) {
			int minIdx = -1;
			int minD = Integer.MAX_VALUE;
			for (int i = 1; i <= V; i++) {
				if (dist[i] < minD && !v[i]) {
					minD = dist[i];
					minIdx = i;
				}
			}

			/**
			 * minIdx : 시작정점
			 */
			v[minIdx] = true;

			/**
			 * i : 도착정점
			 */
			for (int i = 0; i < adj[minIdx].size(); i++) {
				int checkIndex = adj[minIdx].get(i).e;
				int checkIndexC = adj[minIdx].get(i).c;
				if (!v[checkIndex] && checkIndexC < dist[checkIndex])
					dist[checkIndex] = checkIndexC;
			}
		}
		sum = 0;
		System.out.println(Arrays.toString(dist));
		for (int i = 1; i < dist.length; i++)
			sum += dist[i];

	}

}
