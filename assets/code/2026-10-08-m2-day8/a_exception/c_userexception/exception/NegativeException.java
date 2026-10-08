package com.wanted.a_exception.c_userexception.exception;
/* comment. 예외 클래스로 만드는 방법
*   모든 예외의 부모 클래스인 Exception 클래스 상속
* */
public class NegativeException extends Exception{

    public NegativeException(String message) {
        super(message);
    }

}
