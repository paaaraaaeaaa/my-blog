package com.wanted.a_generic.b_use.run;

import com.wanted.a_generic.b_use.*;

public class Application01 {
    public static void main(String[] args) {

        // Mammal 은 Rabbit 을 상속받지 않았기 때문에 T 에 들어갈 수 없어서 컴파일 에러가 발생한다.
//        RabbitFarm<Mammal> farm1 = new RabbitFarm();
        RabbitFarm<Rabbit> farm1 = new RabbitFarm();
        RabbitFarm<Bunny> farm2 = new RabbitFarm();
        RabbitFarm<DrunkenBunny> farm3 = new RabbitFarm();

        // farm2 는 Bunny 를 위한 농장인데 Rabbit 은 Bunny 의 부모이기 때문에 들어갈 수 없다.
//        Rabbit rabbit = new Rabbit();
//        farm2.setAnimal(rabbit);

        DrunkenBunny drunkenBunny = new DrunkenBunny();
        farm2.setAnimal(drunkenBunny);
        farm2.getAnimal().cry();

    }
}
