package com.wanted.a_generic.a_basic;

public class GenericTest<T> {

    /* comment.
    *   제네릭을 설정하는 방법은 클래스 선언부 끝에 <> 다이어몬드 연산자를 사용하면 된다.
    *   <T> T 는 타입 변수로 불리우며 관례상 T 라고 작성을 하게 된다.
    >*/

    private T value;

    /* word. alt + insert
    *   getter & setter */
    /* comment.
    *   getter 와 setter 메서드
    *   해당 메서드는 private 으로 캡슐화가 된 필드를 외부에서 조회하거나(getter) 값을 초기화(setter) 할 때 사용할 수 있는 메서드이다.
    *  */
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    /* word. toString() 추가 */
    /* comment. toString()
    *   클래스 자료형은 기본적으로 참조자료형이기 때문에 변수 출력 시 주소값이 출력된다.
    *   toString 메서드는 변수 내부에 들어있는 값을 주소값으로 출력해주는 것이 아닌 실제 값을 출력해주는 역할을 한다.
    * */
    @Override
    public String toString() {
        return "GenericTest{" +
                "value=" + value +
                '}';
    }
}
