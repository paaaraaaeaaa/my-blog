package com.wanted.b_variable.module01;

import java.util.Scanner;

public class Application02 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("당신의 이름을 입력하세요 : ");
        String name = sc.nextLine();

        System.out.println("이름 : " + name + " 입니다!");

    }
}
