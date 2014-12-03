import java.io.*;
import java.util.Random;

class BB_01knapsack_1170362 {

    private static int z = 0; //暫定解
    private static int n = 0; //荷物の数
    private static int c = 0; //ナップサックの容量
    private static int[] W;   //荷物の重さを格納
    private static int[] V;   //荷物の価値を格納
    private static int[] X;   //荷物を入れる1 荷物を入れない0
    private static int w = 0;
    private static int v = 0;
    private static int[] A;   //解の候補を格納
    private static int count = 0; //再帰回数のカウント

    public static void main(String[] args) {
	Random rnd = new Random();
	
	try {
	    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	    System.out.print("荷物の数 : ");
	    n = Integer.parseInt(input.readLine());
	    System.out.print("ナップサックの容量 : ");
	    c = Integer.parseInt(input.readLine());
	} catch (IOException e) {
	    System.out.println("Your input is Invalid.");
	}
	
	W = new int[n + 1];
	V = new int[n + 1];
	X = new int[n + 1];
	A = new int[n + 1];
	for (int i = 1; i < W.length; i++) {
	    W[i] = rnd.nextInt(n) + 1;
	    V[i] = rnd.nextInt(10 * n) + 1;
	    X[i] = 0;
	}
	
	System.out.println();
	beforeDisplay();
	BB_01knapsack(1);
	System.out.println();
	afterDisplay();
	sumValue();
    }
    
    //入力された情報を表示
    public static void beforeDisplay() {
	for (int i = 1; i <= n; i++) {
	    System.out.println("(番号" + i + ", 重さ:" + W[i] + ", 価値:" + V[i] + ")");
	}
    }
    
    //処理後の情報を表示
    public static void afterDisplay() {
	System.out.print("選択する番号 : ");
	for (int i = 1; i <= n; i++) { 
	      if (A[i] == 1) {
		  System.out.print(i + " ");
	      }
	}
	System.out.println();
    }
    
    //価値の最大値を表示
    public static void sumValue() {	
	System.out.println("価値の最大 : " + z);
	System.out.println("実行回数 : " + count);
    }
    
    //01ナップサック問題を分岐限定法で解く
    public static void BB_01knapsack(int level) {
	count++;
	if (level > n) {
	    w = knapsackWeight(level);
	    v = knapsackValue(level);
	    if (w <= c && v > z) {
		z = v;
		A = (int[])X.clone();
	    }
	} else {
	    //条件Aが成り立たない
	    if (knapsackWeight(level) <= c) {
		//条件Bが成り立つ
		if (knapsackWeight(level) + otherWeight(level) <= c) {
		    v = knapsackValue(level) + otherValue(level);
		    if (v > z) {
			z = v;
			A = (int[])X.clone();
			//level以下の荷物を全て入れる
			for (int i = level; i <= n; i++) {
			    A[i] = 1;
			}
		    }
		} else {
		    //条件Aも条件Bも成り立たない
		    X[level] = 0;
		    BB_01knapsack(level + 1);
		    X[level] = 1;
		    BB_01knapsack(level + 1);
		}
	    }
	}
    }
    
    //ナップサック内の重さの合計を返す
    public static int knapsackWeight(int leval) {
	int knapW = 0;
	for (int i = 1; i < leval; i++) {
	    knapW += X[i] * W[i];
	}
	return knapW;
    } 
    
    //ナップサック内の荷物の価値の合計を返す
    public static int knapsackValue(int leval) {
	int knapV = 0;
	for (int i = 1; i < leval; i++) {
	    knapV += X[i] * V[i];
	}
	return knapV;
    }
    
    //未選択のすべての荷物の重さの合計を返す
    public static int otherWeight(int level) {
	int otherW = 0;
	for (int i = level; i <= n; i++) {
	    otherW += W[i];
	}
	return otherW;
    }
    
    //未選択のすべての荷物の価値の合計を返す
    public static int otherValue(int level) {
	int otherV = 0;
	for (int i = level; i <= n; i++) {
	    otherV += V[i];
	}
	return otherV;
    }
}

