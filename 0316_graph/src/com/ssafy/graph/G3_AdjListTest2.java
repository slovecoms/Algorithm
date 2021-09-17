package com.ssafy.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import com.ssafy.graph.G2_AdjListTest.Node;

/*
 * 7
 * 8
 * 0 1
 * 0 2
 * 1 3
 * 1 4
 * 2 4
 * 3 5
 * 4 5
 * 5 6 
 */

// 어레이리스트(ArrayList)
public class G3_AdjListTest2 {

	static int N;
	static ArrayList<Integer>[] adjList;
	static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		int C = Integer.parseInt(in.readLine());
		adjList = new ArrayList[N]; // 배열 객체 생성 cf. Null Pointer Exception
		for (int i = 0; i < N; i++) {
			adjList[i]= new ArrayList<Integer>();
		}
		
		StringTokenizer st = null;
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		System.out.println("==========================bfs==========================");
		bfs();

		visited = new boolean[N];
		dfs(0);
	}

	private static void bfs() {
		// TODO Auto-generated method stub
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];

		int start = 0;
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			System.out.println((char) (current + 65));

			for ( int temp : adjList[current]) { // temp : current와 인접정점인 해당정점의 번호

				if (!visited[temp]) {
					queue.offer(temp);
					visited[temp] = true;
				}
			}

		}

	}

	private static void dfs(int current) {
		visited[current] = true;
		System.out.println((char)(current+65));
		
		for( int temp : adjList[current]) {
			if(!visited[temp]) {
				dfs(temp);
			}
		}

	}
}
