package com.wanted.a_exception.c_userexception;

import com.wanted.a_exception.c_userexception.exception.MoneyNegativeException;
import com.wanted.a_exception.c_userexception.exception.NotEnoughMoneyException;
import com.wanted.a_exception.c_userexception.exception.ProductPriceNegativeException;

public class Application {
    public static void main(String[] args) {

        /* comment.
        *   사용자 정의의 예외 클래스 정의하기
        *   JDK 설치하면 사전에 정의된 예외 클래스를 사용할 수 있다.
        *   하지만, 현실 세계에서 발생할 수 있는 수많은 예외를 처리하기에는 너무 추상적이고 제한적이다.
        * */

        ExceptionTest et = new ExceptionTest();

        /* 프로그램
        *   상품 가격, 내가 가진 돈
        *   5000, 직접 입력 (정수)
        *   1. 음수를 입력할 때
        *   2. 가진 돈이 충분하지 않을 때
        *  */

        try {
            et.checkMoney(50000,30000);
        } catch (ProductPriceNegativeException e) {
            System.out.println(e.getMessage());
        } catch (MoneyNegativeException e) {
            System.out.println(e.getMessage());
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }

    }
}
