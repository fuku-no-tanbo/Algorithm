import java.io.*;
import java.util.Random;

class Shortest_path_1170362 {

    private static int n; //節点の数
    private static int e[][]; //辺
    private static int D[]; //最短経路を格納
    private static int S[]; //選んだ節点の集合
    private static int start = 0; //始点
    private static int count = 0; //実行回数のカウント

    public static void main(String[] args) {
	Random rnd = new Random();

	try {
	    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	    System.out.print("節点の数 : ");
	    n = Integer.parseInt(input.readLine());
	} catch (IOException e) {
	    System.out.println("Your input is Invalid.");
	}

	e = new int[n][n];
	D = new int[n];
	S = new int[n];

	//辺の重みを初期化
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		e[i][j] = 0;
	    }
	}

	addValue(); //辺に重みを加える
	display(); //隣接行列を表示
	dijkstra(); //ダイクストラ法を用いて最短経路をもとめる
	System.out.println();
	shortestDisplay(); //始点からの最短経路を表示

    }
    
    public static void addValue() {
	Random rnd = new Random();
	for (int i = 0; i < n; i++) {
	    int j = rnd.nextInt(n);
	    while(i == j || e[i][j] != 0) {
		j = rnd.nextInt(n);
	    }
	    int r = rnd.nextInt(50) + 1;
	    e[i][j] = r;
	    e[j][i] = r;
	}
	
	for (int i = 0; i < n * 2; i++) {
	    int j = rnd.nextInt(n);
	    int k = rnd.nextInt(n);
	    while (j == k || e[j][k] != 0) {
	        j = rnd.nextInt(n);
		k = rnd.nextInt(n);
	    }
	    int r = rnd.nextInt(50) + 1;
	    e[j][k] = r;
	    e[k][j] = r;
	}
    }	

    //隣接行列の表示
    public static void display() {
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		if (e[i][j] < 10) {
		    System.out.print(" ");
		}
		System.out.print(e[i][j]);
		System.out.print(" ");
	    }
	    System.out.println();
	}	
    }

    //ダイクストラ法を用いて最短経路をもとめる
    public static void dijkstra() {
	Random rnd = new Random();
	int k = 0; //最小の頂点を記録
	
	for (int i = 0; i < n; i++) {
	    D[i] = Integer.MAX_VALUE; //無限大を代入
	    S[i] = 0; //選択済み1 未選択0
	}
	
	int s = rnd.nextInt(n); //始点をランダムで決定
	start = s; //始点を記録
	D[s] = 0;
	
	for (int i = 0; i < n; i++) {
	    count++;
	    //Sに含まれない頂点から、配列Dの値が最小の頂点をもとめる
	    int min = Integer.MAX_VALUE;
	    for (int j = 0; j < n; j++) {
		count++;
		if (min > D[j] && S[j] == 0) {
		    min = D[j];
		    k = j;
		}
	    }
	    
	    S[k] = 1; //最小の頂点をSに追加する
	    
	    for (int j = 0; j < n; j++) {
		count++;
		if (e[j][k] != 0 && S[j] == 0) {
		    D[j] = Math.min(D[j], D[k] + e[k][j]);
		}
	    }
	} 
    }
    
    //始点からの最短経路を表示
    public static void shortestDisplay() {
	System.out.println("始点 : " + (start + 1));
	for (int i = 0; i < n; i++) {
	    System.out.println((start + 1) + "から" + (i + 1) + "への最短経路" + " = " + D[i]);
	}
	System.out.println();
	System.out.println("実行回数 : " + count);
    } 
}