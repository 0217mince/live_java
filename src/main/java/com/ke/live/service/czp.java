package com.ke.live.service;

import java.util.Arrays;
import java.util.Scanner;

public class czp {

    public static void main1(String[] args) {
        int[] a = new int[50];
        a[0] = 0;
        a[1] = 1;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i=2 ;i <= n;i++ ){
            a[i] = a[i-1] + a[i-2];
        }
        System.out.println(a[n]);
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = 1;
        for (int i = 1; i<=n; i++){
            for (int j =1 ;j <= i; j++){
                System.out.print(a%10);
                a++;
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] tj = new int[n];
        int[] king = new int[n];
        int count = 0;
        int tj_min = 0, tj_max = n - 1;
        int king_min = 0, king_max = n - 1;
        for (int i=0;i<n;i++){
            tj[i] = sc.nextInt();
        }
        for (int i=0;i<n;i++){
            king[i] = sc.nextInt();
        }

        Arrays.sort(tj);
        Arrays.sort(king);

        while (n>0){
            if (tj[tj_max] > king[king_max]){
                count++;
                tj_max--;
                king_max--;
            }else if (tj[tj_max] < king[king_max]){
                count--;
                tj_min++;
                king_max--;
            }else {
                if (tj[tj_min] > king[king_min]){
                    count++;
                    tj_min++;
                    king_min++;
                }else {
                    if (tj[tj_min]<king[king_max]) {
                        count--;
                        tj_min++;
                        king_max--;
                    }
                }
            }
            n--;
        }
        System.out.println(count*200);
    }
}


