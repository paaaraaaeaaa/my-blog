package com.wanted.c_method;

public class Application02 {
    public static void main(String[] args) {

        // 1. 항상 main() 가 가장 먼저 동작을 한다.
        System.out.println("main() 시작됨!");

        // 2. main() 영역 밖에 methodA() 추가한다.

        // 5. 작성한 methodA 를 호출할 준비
        Application02 app2 = new Application02();
        // 6. 준비가 완료. 호출 시작
        app2.methodA();

        // 7. methodA() 흐름 확인 후 methodB() 추가

        // end.
        System.out.println("main() 종료됨!");

    }

    // 3. main() 영역에서 methodA() 호출되는지 확인
    public void methodA() {
        // void : 반환값이 없을 때 사용한다.

        // 4. 호출 확인을 위한 출력 구문
        System.out.println("methodA() 호출됨!");

        // 12. methodB() 호출 구문 작성
        methodB();

        // 13. methodA() 종료 시점 출력
        System.out.println("methodA() 종료됨!");
    }

    // 8. 호출 확인을 위한 method() 작성
    public void methodB() {

        // 9. 호출 확인을 위한 출력 구문
        System.out.println("methodB() 호출됨!");

        // 10. 작성 후 프로그램 시작하면 methodB() 의 출력구문은 출력되지 않는다.
        // why? 부르질 않았는데 어떻게 나와요

        // 11. main() 영역이 아닌 methodA() 영역 내부에서 B 호출
    }
}
