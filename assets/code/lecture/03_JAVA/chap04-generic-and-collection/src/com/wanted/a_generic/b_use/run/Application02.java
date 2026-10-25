package com.wanted.a_generic.b_use.run;

import com.wanted.a_generic.b_use.*;

public class Application02 {

    public static void main(String[] args) {

        /* comment.
        *   와일드카드 ?
        *   제네릭 클래스 타입의 객체를 메소드의 매개변수로 전달받을 때, 그 객체의 타입 변수를 제한할 수 있다.
        *   <?> : 제한 없다. 아무거나 들어와도 된다.
        *   <? extends Type> : 와일드카드 상한 제한
        *   <? super Type> : 와일드카드 하한 제한
        * */

        WildcardFarm wildcardFarm = new WildcardFarm();
        wildcardFarm.anyType(new RabbitFarm<Rabbit>(new Rabbit()));
        wildcardFarm.anyType(new RabbitFarm<Bunny>(new Bunny()));
        wildcardFarm.anyType(new RabbitFarm<DrunkenBunny>(new DrunkenBunny()));

        System.out.println("===============와일드 카드 상한 제한===============");
        // <? extends Bunny> : Bunny 이거나 Bunny 의 자식만 전달받을 수 있다.
//        wildcardFarm.extendsType(new RabbitFarm<Rabbit>(new Rabbit()));
        wildcardFarm.extendsType(new RabbitFarm<Bunny>(new Bunny()));
        wildcardFarm.extendsType(new RabbitFarm<DrunkenBunny>(new DrunkenBunny()));
        System.out.println("===============와일드 카드 상한 제한===============");

        System.out.println("===============와일드 카드 하한 제한===============");
        wildcardFarm.superType(new RabbitFarm<Rabbit>(new Rabbit()));
        wildcardFarm.superType(new RabbitFarm<Bunny>(new Bunny()));
        // <? super Bunny> : Bunny 이거나, Bunny 의 부모만 전달받을 수 있다.
//        wildcardFarm.superType(new RabbitFarm<DrunkenBunny>(new DrunkenBunny()));
        System.out.println("===============와일드 카드 하한 제한===============");



    }
}
