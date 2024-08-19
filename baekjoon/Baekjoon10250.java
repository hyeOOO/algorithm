package baekjoon;

import java.util.Scanner;

public class Baekjoon10250 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		sc.nextLine();
		
		while(input>0) {
			String str = sc.nextLine();
			String[] arr = str.split(" ");
			int h = Integer.parseInt(arr[2])%Integer.parseInt(arr[0]) == 0 ? Integer.parseInt(arr[0]) : Integer.parseInt(arr[2])%Integer.parseInt(arr[0]);
			int w = Integer.parseInt(arr[2])%Integer.parseInt(arr[0]) == 0 ? Integer.parseInt(arr[2])/Integer.parseInt(arr[0]) : Integer.parseInt(arr[2])/Integer.parseInt(arr[0])+1;
			
			String num1 = Integer.toString(h);
			String num2 = w<10? "0"+Integer.toString(w):Integer.toString(w);
			
			System.out.println(num1+num2);
			input--;
		}
	}
}
