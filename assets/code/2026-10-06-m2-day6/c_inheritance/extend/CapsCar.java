package com.wanted.oop.c_inheritance.extend;

// 경찰차는 차다. IS-A 성립된다.
public class CapsCar extends Car{

    // 경찰차도 차이기 때문에 달리는 기능, 경적을 울리는 기능 모두를 가지고 있다.
    // 그렇다는 건 같은 메소드를 여기에다가도 똑같이 작성하는 것이 아닌, 부모의 메소드를 재활용하면 좋지 않을까?
    // 라는 생각을 가지고 나온 개념이 상속이라는 개념이다.

    /* comment. Override
    *   메소드를 재정의 하는 것을 의미한다.
    *   부모가 가지는 메소드 선언부를 그대로 사용하면서 자식 클래스가 정의한 메소드 대로 동작할 수 있도록 구현 몸체를 새롭게 작성하는 것을 의미한다.
    * */

    public CapsCar() {
        System.out.println("CapsCar 의 기본 생성자 호출됨..");
    }

    @Override
    public void run() {
        // this -> 자기 자신의 인스턴스 주소
        // super -> 부모의 인스턴스 주소
//        super.run();
        System.out.println("경찰차는 삐용삐용~~ 하면서 달립니다!!");
    }

    @Override
    public void soundHorn() {
//        super.soundHorn();
        System.out.println("삐~~~~~~용~~~~~~~~~~삐~~~~~~~");
    }

    // 부모의 메소드를 재정의할 수 있고 본인만의 고유한 필드/메소드도 작성 가능하다.
    public void 무전하기 () {
        System.out.println("치지지ㅣㅈㄱ....... 421호에 몬스터 등장ㄷㄷ");
    }

}
