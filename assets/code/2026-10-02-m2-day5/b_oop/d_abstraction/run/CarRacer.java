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


public class CarRacer {
    // 클래스도 자료형이다.
    // 따라서 변수처럼 필드에 선언할 수 있다.
    // Car 는 CarRacer 만 접근해야 한다.
    // Application 은 Car 에 접근하면 안된다. 그래서 캡슐화 씀
    private Car car = new Car();


    // Application 에서 호출한 시동 걸어 메서드
    public void startUp() {

        // 자동차에게 시동 걸어!!
        car.startUp();

    }

    public void stepAccel() {
        car.go();
    }

    public void stepBreak() {
        car.stop();
    }

    public void turnOff() {
        car.turnOff();
    }
}
