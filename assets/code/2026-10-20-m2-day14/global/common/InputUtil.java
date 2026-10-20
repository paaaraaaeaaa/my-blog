package com.eulji.global.common;

import java.util.Scanner;

/* System.in을 읽는 Scanner를 하나만 만들어 모든 InputView가 함께 쓴다 */
public class InputUtil {

    public static final Scanner SC = new Scanner(System.in);

    private InputUtil() {
    }
}
