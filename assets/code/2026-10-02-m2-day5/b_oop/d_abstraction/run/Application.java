package com.wanted.oop.b_oop.d_abstraction.run;

import java.util.Scanner;

public class Application {

    // 요구사항 명세서, 클래스 설계 ? 힌트같은거 ㅇㅇ 에이전트 만들기
    /* comment. 
    *   추상화란?
    *   공통된 부분을 추출하고, 공통되지 않는 부분은 제거하는 의미를 가진다.
    *   복잡한 현실세계를 프로그램으로 설계할 때 현실세계를 그대로 반영하기에는 너무 방대하고 복잡하다.
    *   따라서 추상화란 현실세계를 프로그램의 목적에 맞게 단순화 하는 것을 의미한다.
    *  */

    /* comment. 프로그램 요구사항 작성
     *   주제 : 카레이서가 자동차를 운전하는 프로그램
     *   1. 자동차는 처음에 멈춘 상태로 대기한다.
     *   2. 카레이서는 먼저 자동차에 시동을 건다. 이미 걸려있다면, 다시 시동을 걸 수 없다.
     *   3. 카레이서가 엑셀을 밟으면 시동이 걸려있다면 시속이 10km/h 증가하며 앞으로 나간다.
     *   4. 자동차가 달리고 있는 중이면 브레이크를 밟을 시 시속이 0으로 떨어지며 멈춘다.
     *   5. 브레이크를 밟을 때 자동차가 달리는 중이 아니라면 이미 멈춰있는 상태라고 안내한다.
     *   6. 카레이서가 시동을 끄면, 더 이상 자동차는 움직이지 않는다.
     *   7. 자동차가 달리는 중이라면 시동을 끌 수 없다.
     *  */

    /* comment. 은/는 , 이/가 <- 이 키워드 앞 단어가 대부분 클래스 후보이다.
     *   여기서 필요한 객체는 카레이서와 자동차 객체이다.
     *   카레이서가 수신할 수 있는 메세지는 카레이서가 해야 할 일과 동일하다.
     *   1. 시동을 걸어라
     *   2. 엑셀을 밟아라
     *   3. 브레이크 밟아라
     *   4. 시동 꺼라
     *   자동차가 수신할 수 있는 메세지는 자동차가 해야 할 일과 동일하다.
     *   1. 시동을 걸어라
     *   2. 앞으로 가라
     *   3. 멈춰라
     *   4. 시동을 꺼라
     *  */

    public static void main(String[] args) {

        // 콘솔로 입력받는 게임 Scanner 활용!
        Scanner sc = new Scanner(System.in);

        // CarRacer 객체 생성
        CarRacer racer = new CarRacer();

        // 사용자가 입력할 수 있는 화면
        while (true) {
            System.out.println("==============카레이싱 프로그램===============");
            System.out.println("1. 시동 걸기");
            System.out.println("2. 전진!");
            System.out.println("3. 정지");
            System.out.println("4. 시동 끄기");
            System.out.println("9. 프로그램 종료");
            System.out.println("===========================================");
            System.out.print("메뉴를 선택해주세요: ");
            // 입력한 메뉴 no 변수에 담기
            int no = sc.nextInt();

            /* word. alt + enter 로 오류 탐색 */

            switch (no) {
                case 1 : racer.startUp();
                    break;
                case 2 :
                    racer.stepAccel();
                    break;
                case 3 :
                    racer.stepBreak();
                    break;
                case 4 :
                    racer.turnOff();
                    break;
                case 9 : break;
                default:
                    System.out.println("잘못된 번호 입력!");
                    break;
            }

            if (no == 9) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }


        }

    }

}
