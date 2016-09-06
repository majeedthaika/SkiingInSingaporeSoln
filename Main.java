import java.util.*;

public class Main {
	static int[][] arr;
	static int[][] dp;
	
	static int n;
	static int m;

	static int length;
	static int drop;
	
	static void findPaths(int start, int len_so_far, int x, int y){
		if (dp[x][y]==0){
			if (length<len_so_far){
				length = len_so_far;
				drop = arr[x][y]-start;
			} else if (length==len_so_far){
				drop = Math.max(drop, arr[x][y]-start);
			} else {}
		} else {
			if (x!=0) if (dp[x][y]==(dp[x-1][y]+1)) findPaths(start, len_so_far+1, x-1, y);
			if (y!=0) if (dp[x][y]==(dp[x][y-1]+1)) findPaths(start, len_so_far+1, x, y-1);
			if (x!=(n-1)) if (dp[x][y]==(dp[x+1][y]+1)) findPaths(start, len_so_far+1, x+1, y);
			if (y!=(m-1)) if (dp[x][y]==(dp[x][y+1]+1)) findPaths(start, len_so_far+1, x, y+1);
		}
	}
	
    public static void main(String[] args) throws java.lang.Exception {
    	Scanner cin = new Scanner(System.in);

    	n = cin.nextInt();
    	m = cin.nextInt();
    	
    	length = -1;
    	drop = -1;

    	arr = new int[n][m];
    	dp = new int[n][m];
    	
    	int maxPeak = -1;
    	for (int i=0;i<n;i++){
    		for (int j=0;j<m;j++){
        		arr[i][j] = cin.nextInt();
        		maxPeak = Math.max(maxPeak, arr[i][j]);
        	}
    		Arrays.fill(dp[i], 0);
    	}
    	
    	int maxPath = 0;
    	for (int k=0;k<Math.min(maxPeak,n*m);k++){
    		boolean changed = false;
        	for (int i=0;i<n;i++){
	    		for (int j=0;j<m;j++){
	    			int before = dp[i][j];
	        		if (j!=0) if (arr[i][j]<arr[i][j-1]) dp[i][j] = Math.max(dp[i][j], dp[i][j-1]+1);
	        		if (i!=0) if (arr[i][j]<arr[i-1][j]) dp[i][j] = Math.max(dp[i][j], dp[i-1][j]+1);
	        		if (i!=(n-1)) if (arr[i][j]<arr[i+1][j]) dp[i][j] = Math.max(dp[i][j], dp[i+1][j]+1);
	        		if (j!=(m-1)) if (arr[i][j]<arr[i][j+1]) dp[i][j] = Math.max(dp[i][j], dp[i][j+1]+1);
	        		
	        		if (before != dp[i][j]) changed = true;
	        		
	        		maxPath = Math.max(maxPath, dp[i][j]);
	        	}
	    	}
        	if (!changed) break;
    	}

    	ArrayList<String> starts = new ArrayList<String>();
    	for (int i=0;i<n;i++){
    		for (int j=0;j<m;j++){
    			if (dp[i][j] == maxPath) starts.add(i+","+j);
        	}
    	}

    	for(int i=0;i<starts.size();i++){
    		String[] idx = starts.get(i).split(",");
    		findPaths(arr[Integer.parseInt(idx[0])][Integer.parseInt(idx[1])], 1, Integer.parseInt(idx[0]),Integer.parseInt(idx[1]));
    	}
    	System.out.println(length+""+drop);
    }
}
