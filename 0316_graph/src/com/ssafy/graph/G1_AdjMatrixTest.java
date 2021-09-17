package com.ssafy.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 7
 8
 0 1
 0 2
 1 3
 1 4
 2 4
 3 5
 4 5
 5 6 
 */

public class G1_AdjMatrixTest {
	
	static int N;
	static boolean[][] adjMatrix;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(in.readLine());
		int C = Integer.parseInt(in.readLine());
		adjMatrix=new boolean[N][N];
		
		StringTokenizer st = null;
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(in.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			adjMatrix[to][from]=adjMatrix[from][to]=true;
		}
		
		bfs();
	}
	
	private static void bfs() {
		// TODO Auto-generated method stub
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];
		
		// 탐색시작정점 : 0으로 출발
		int start=0;
		queue.offer(start);
		visited[start] = true;
		
		while (!queue.isEmpty()) {
			int current = queue.poll();
			// 현재 정점에 관련된 처리
			System.out.println((char)(current+65));
			// 인접정점 탐색
			for (int i = 0; i < N; i++) {
				if(adjMatrix[current][i]  // 인접정점
						&& !visited[i]) { // 미방문 접점
					queue.offer(i);
					visited[i]=true;
				}
			}
		}
	}

}
