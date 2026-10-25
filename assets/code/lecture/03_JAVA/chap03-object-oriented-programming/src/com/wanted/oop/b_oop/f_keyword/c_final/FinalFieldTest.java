package com.wanted.oop.b_oop.f_keyword.c_final;

public class FinalFieldTest {

    /* comment. final 키워드
    *   final 키워드는 변경 불가의 의미를 갖는다.
    *   즉, 최초에 초기화 이후에 값을 대입 후 변경 불가능하게 만들고자 할 때 사용하게 된다.
    *   -  final 키워드가 붙은 변수들은 식별을 위해 예외적으로 대문자와 _(언더바) 를 사용한다. (camel case)
    *  */

    // final 키워드는 초기화 이후에 값을 변경할 수 없기 때문에 선언만 하게 된다면 JVM이 설정한 기본값인 0이 들어가게 되는데 이를 허용하지 않는다.
//    private final int NON_STATIC_NUM;

    // 1. final 키워드가 붙은 변수는 무조건 선언과 동시에 초기화를 해주어야 한다.
    private final int NON_STATIC_NUM = 1;

    // 2. 생성자의 특징을 이용해서 선언만 할 수 있다.
    // 단, 무조건 생성자를 통해 초기화가 되어야 한다.
    private final int NON_STATIC_NUM2;

    public FinalFieldTest(int num) {
        this.NON_STATIC_NUM2 = num;
    }

    public int getNON_STATIC_NUM() {
        return NON_STATIC_NUM;
    }

    public int getNON_STATIC_NUM2() {
        return NON_STATIC_NUM2;
    }

    // final 키워드가 붙은 변수는 값을 다시 대입할 수 없다.
//    public void setNON_STATIC_NUM(int num) {
//        this.NON_STATIC_NUM = num;
//    }
}
