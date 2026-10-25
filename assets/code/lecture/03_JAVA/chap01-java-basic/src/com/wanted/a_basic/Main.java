package com.wanted.a_basic;

public class Main {

    /* comment.
    *   java 의 기본 구조 및 실행 방식
    *   모든 Java 기반의 프로그램은 클래스 내부에서 동작한다.
    *   우리가 만든 main() 이라는 메서드가 프로그램의 시작점이 된다.
    * */
    /* word. main */
    public static void main(String[] args) {

        /* comment.
        *   Java 의 실행 과정
        *   1. Java 코드를 작성한다. (.java)
        *   2. JDK 설치 시 들어있는 javac(컴파일러)가 .java 파일을 .class 파일로 번역한다.
        *   3. JVM이 .class 파일을 로드하고 실행한다.
        *   4. JVM 은 바이트코드를 해석하여 각 OS에서 실행 가능한 기계어로 변환한다.
        *   5. 변환된 기계어를 실행한다.
        * */

        /* word. sout */
        System.out.println("Hello World!!!");

    }
}
