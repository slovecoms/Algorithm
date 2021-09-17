package com.ssafy.graph;

import java.io.*;
import java.util.*;

public class 최소스패닝트리_kruskal {
	static int parent[], V, E, sum;
	static Scanner sc = new Scanner(System.in);

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
		System.setIn(new FileInputStream("최소스패닝트리.txt"));
		V = sc.nextInt();
		E = sc.nextInt();

		Kruskal();

		System.out.println(sum);

		sc.close();
	}

	private static void Kruskal() {
		ArrayList<Edge> list = new ArrayList<Edge>();

		for (int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			list.add(new Edge(a, b, c));
		}
		parent = new int[V + 1];

		Collections.sort(list);

		for (int i = 1; i <= V; i++)
			makeSet(i);

		sum = 0;
		int cnt = 0;

		for (int i = 0; i < E; i++) {
			Edge edge = list.get(i);
			if (findSet(edge.s) != findSet(edge.e)) {
				sum += edge.c;
				union(edge.s, edge.e);
				cnt++;
				if (cnt == V - 1)
					break;
			}
		}

	}

	private static void union(int px, int py) {
		if (findSet(px) != findSet(py))
			parent[findSet(py)] = findSet(px);
	}

	private static int findSet(int i) {
		if (parent[i] == i)
			return i;
		return findSet(parent[i]);
	}

	private static void makeSet(int i) {
		parent[i] = i;
	}
}
