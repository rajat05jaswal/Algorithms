

import java.util.Scanner;

public class UFClient {
	public static void main(String args[]) {
		for(int i=0;i<=20;i++) {
			int n=(int) Math.pow(2, i);
			int numberOfPairs=0;
			for(int j=0;j<100;j++) {
				UF_HWQUPC h=new UF_HWQUPC(n);
				while(h.components()!=1) {
					int random[]=randomPair(n);
					h.connect(random[0], random[1]);
					numberOfPairs++;
				}
			}
			int avg=numberOfPairs/100;
			System.out.print("\nM/N for 2^"+i+": "+(double)avg/(double)n);
			System.out.print("\t\t\tLN(N) value is: "+(Math.log(n)));
		}
	}
	public static int[] randomPair(int n) {
		int[] arr=new int[2];
		arr[0]=(int)(Math.random() * n);
		arr[1]=(int)(Math.random() * n);
		return arr;
	}
}
