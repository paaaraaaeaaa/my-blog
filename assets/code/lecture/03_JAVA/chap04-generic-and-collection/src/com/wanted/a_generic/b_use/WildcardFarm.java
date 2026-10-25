package com.wanted.a_generic.b_use;

public class WildcardFarm {

    public void anyType(RabbitFarm<?> farm) {
        farm.getAnimal().cry();
    }
    public void extendsType(RabbitFarm<? extends Bunny> farm) {
        farm.getAnimal().cry();
    }
    public void superType(RabbitFarm<? super Bunny> farm) {
        farm.getAnimal().cry();
    }

}
