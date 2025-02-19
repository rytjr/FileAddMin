package FileAddMin;

import java.io.*;
import java.util.*;
public class FileAddMin{
	public static int[] sum = new int[501];		//파일 비용 합 저장 배열
	public static int[][] DP = new int[501][501];	//j->i 합치는 최소 비용 저장 배열
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //입력값 처리하는 BufferedReader
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //결과값 출력하는 BufferedWriter
        //-----입력값 저장 및 배열 초기화--------
    	int T = Integer.parseInt(br.readLine());
    	StringTokenizer st;
    	for(int i=0;i<T;i++) {
    		int K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine()," ");
    		for(int j=1;j<=K;j++) {
    			sum[j] = sum[j-1] + Integer.parseInt(st.nextToken());
    		}
    		fileMerge(K);		//함수 실행
    		bw.write(DP[1][K] + "\n");		//결과 저장
    	}
    	bw.flush();			//결과 출력
    	bw.close();
    	br.close();
    }
    //----------파일 최소 비용 구하는 함수---------
    public static void fileMerge(int K) {
    	for(int i=2;i<=K;i++) {		//목적지 2부터 시작
    		for(int j=i-1;j>=1;j--) {		//출발지	i-1부터 시작
    			DP[j][i] = Integer.MAX_VALUE;
    			for(int s=j;s<i;s++) {		//중간지점 j부터 시작
    				DP[j][i] = Math.min(DP[j][i], DP[j][s] + DP[s+1][i]);	//최소값 정하기
    			}
    			DP[j][i] += sum[i] - sum[j-1];		//비용합 더하기
    		}
    	}
    }
}