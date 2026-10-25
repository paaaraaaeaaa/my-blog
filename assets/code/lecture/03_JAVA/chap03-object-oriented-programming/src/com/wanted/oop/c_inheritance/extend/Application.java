package com.wanted.oop.c_inheritance.extend;

public class Application {

    /* comment.
    *   extends  -> 현실세계에서 상속의 개념을 갖는다.
    *   부모의 돈은 내 돈이고, 내 돈은 내 돈이다.
    *   부모의 돈(필드, 메서드) 을 자식이 물려받는다.
    * */

    public static void main(String[] args) {

        // 부모 객체 생성
        Car car = new Car();
        car.soundHorn();
        car.run();
        car.soundHorn();
        car.isRunning();
        car.stop();
        car.soundHorn();
        System.out.println("============================");

        // Car 를 상속받는 CapsCar 객체 생성
        CapsCar capsCar = new CapsCar();
        capsCar.run();
        capsCar.soundHorn();
        capsCar.stop();
        capsCar.무전하기();

        /* comment.
        *   상속 개념을 통해 얻을 수 있는 것.
        *   - 반복되는 메서드를 부모 클래스에 정의 후 자식 클래스에서는 상속만 받는다.
        *   - 자식 클래스에서는 다르게 동작해야 하는 메서드만 Override 해서 재정의를 한다.
        * */

    }

}
