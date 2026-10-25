package com.wanted.oop.b_oop.d_abstraction.run;
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

public class Car {

    // 데이터 후보군
    // 변하는 상태 후보군
    // 속력, 시동여부
    private int speed;
    private boolean isOn;

// 1. 자동차는 처음에 멈춘 상태로 대기한다.
// 2. 카레이서는 먼저 자동차에 시동을 건다. 이미 걸려있다면, 다시 시동을 걸 수 없다.

    public void startUp() {

        if (isOn) {
            System.out.println("시동이 이미 걸려있습니다.");
        } else {
            // 시동의 상태를 true 로 변경
            this.isOn = true;
            System.out.println("🚩 시동 걸기 완료! 출발 준비 OK 🫠");

        }

    }

    public void go() {

        if (isOn) {
            System.out.println("🙋‍♀️🙋‍♀️차가 출발합니다! 🎉");
            // go() 호출 될 때마다 10km 씩 증가!
            this.speed += 10;
            System.out.println("현재 차의 속력은 " + this.speed + "(km/h) 입니다🚡");
        } else {
            System.out.println("차의 시동이 걸려있지 않습니다. 시동 확인해주세요!🗝️");
        }

    }

    public void stop() {
        if (isOn) {
            // 시동 걸려있을 때 작성하는 곳
            if (speed > 0 ) {
                // 시동은 걸렸고, 달리는 중일 때(10km 이상) 작성하는 곳.
                this.speed = 0;
                System.out.println("끼~~~~~~~~~~~익 브레이크 밟기 OK. 차는 멈췄습니다.");
            } else {
                // 시동은 걸렸고, 달리지 않는 중일 때 (0km) 작성하는 곳.
                System.out.println("차는 이미 멈춰있습니다!!!!!!!!");
            }

        } else {
            // 시동이 꺼져있을 때 작성하는 곳
            System.out.println("🪓차의 시동이 걸려있지 않습니다!" + "시동부터 확인해주세요!🪓");
        }
    }

    public void turnOff() {

        if (isOn) {

            // 달리고 있을 때, 멈춰 있을 때
            if (speed > 0) {
                System.out.println("달리는 상태에서는 시동을 끌 수 없습니다...! 차를 먼저 멈춰주세요!");
            } else {
                this.isOn = false;
                System.out.println("시동이 꺼집니다. 다시 운행하고 싶으면 시동을 켜주세요");
            }


        } else {
            System.out.println("이미 시동이 꺼져있습니다.");
        }

    }
}
