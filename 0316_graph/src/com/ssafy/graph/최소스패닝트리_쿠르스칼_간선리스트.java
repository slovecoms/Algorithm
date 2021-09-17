package com.ssafy.graph;

import java.io.*;
import java.util.*;

/*
 * 1. 간선을 정렬한다
 * 2. 간선을 고르는데 싸이클안생기면 고르고 싸이클이 생기면 넘어간다 (n-1)개의 간선의 갯수가 될때 까지 고른다
 * 
 */
public class 최소스패닝트리_쿠르스칼_간선리스트 {
	static int[] parents;
	static int[] rank;
	static class Edge implements Comparable<Edge>{
		int s,e,c;
		Edge(int s,int e, int c){
			this.s=s;
			this.e=e;
			this.c=c;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.c, o.c);
		}
	}
	// 입력은 첫줄에 정점의 갯수와 간선의 갯수가 들어오고
	// 그 다음줄부터 간선의 정보가 정점1 정점2 가중치로 간선의 갯수만큼 들어움
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("최소스패닝트리.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			
			int V = sc.nextInt();
			int E = sc.nextInt();
			ArrayList<Edge>edges = new ArrayList<Edge>();
			for (int i = 0; i < E; i++) {
				edges.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
			}
			// 간선들을 가중치 오름차순 정렬
			Collections.sort(edges);
			parents = new int[V+1];
			rank=new int[V+1];
			// 각정점에 대해 유니온파인드 연산준비
			for (int i = 0; i < V; i++) {
				makeSet(i);
			}
			long result = 0;
			// 정점의 갯수-1번 반복하면서
			int cnt=0;
			for (int i = 0; i < E; i++) {
				int a = findSet(edges.get(i).s);
				int b = findSet(edges.get(i).e);
				// 같은 팀이면 패스
				if (a == b) {
					continue;
				}
				// 간선이 연결하는 두 정점이 같은 팀이 아니라면 한팀으로 합쳐주고 간선 선택
				union(a, b);
				result += edges.get(i).c;
				cnt++;
				if(cnt==V-1) {
					break;
				}
			}
			System.out.printf("#%d %d\n",tc,result);
		}
	}

	static void makeSet(int x) {
		parents[x] = x;
	}

	static int findSet(int x) {
		if (x == parents[x])
			return x;
		else {
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (rank[px] > rank[py]) {
			parents[py] = px;
		} else {
			parents[px] = py;
			if (rank[px] == rank[py]) {
				rank[py]++;
			}
		}
	}

}
