package com.wanted.a_exception.c_userexception;

import com.wanted.a_exception.c_userexception.exception.MoneyNegativeException;
import com.wanted.a_exception.c_userexception.exception.NotEnoughMoneyException;
import com.wanted.a_exception.c_userexception.exception.ProductPriceNegativeException;

public class ExceptionTest {

    public void checkMoney(int productPrice, int money) throws ProductPriceNegativeException, MoneyNegativeException, NotEnoughMoneyException {

        // 상품 가격 음수일 때
        if(productPrice < 0) {
            throw new ProductPriceNegativeException(
                    "상품의 가격은 음수일 수 없습니다!!!!!!"
            );
        }

        // 내가 가진 돈이 음수일 때
        if (money < 0) {
            throw new MoneyNegativeException(
                    "가진 돈이 음수일 수 없습니다!!!!!"
            );
        }

        // 상품 가격이 내가 가진 돈보다 클 때
        if (money < productPrice) {
            throw new NotEnoughMoneyException(
                    "가진 돈 보다 상품의 가격이 더 비싸요........."
            );
        }

        System.out.println("가진 돈이 충분합니다. 즐쇼!");
    }

}
