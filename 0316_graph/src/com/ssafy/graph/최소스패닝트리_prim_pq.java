package com.ssafy.graph;

import java.io.*;
import java.util.*;

public class 최소스패닝트리_prim_pq {
	static class Node implements Comparable<Node> {
		int d;
		int w;

		Node(int d, int w) {
			this.d = d;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.w, o.w);
		}
	}

	private static final int INFINITY = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("최소스패닝트리.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		ArrayList<Node>[] adjList = new ArrayList[V+1];
		for (int i = 0; i < V+1; i++) adjList[i] = new ArrayList<>();
		int[] dist = new int[V + 1];
		boolean[] v = new boolean[V+1];
		int start = 1;
		int cnt = 0;
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			adjList[A].add(new Node(B, C));
			adjList[B].add(new Node(A, C));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(dist, INFINITY);
		dist[start] = 0;
		pq.offer(new Node(start, dist[start]));

		int sumWeight = 0;
		Node node = null;
		while (!pq.isEmpty()) {
			node = pq.poll();
			if(v[node.d]) continue;
			cnt++;
			sumWeight += node.w;
			v[node.d] = true;
			if(cnt == V) break;
			int size = adjList[node.d].size();
			for(int i=0; i<size; i++) {
				Node curr = adjList[node.d].get(i);
				if(!v[curr.d] && curr.w != 0 && dist[curr.d] > curr.w) {
					dist[curr.d] = curr.w;
					pq.offer(new Node(curr.d, dist[curr.d]));
				}
			}
		}
		System.out.println(sumWeight);
	}
}
