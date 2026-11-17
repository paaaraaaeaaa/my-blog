// 단일 행 주석 실행: ctrl + alt + n

/*  
    여러 행 주석
*/

// var, let, const

// Java : 자료형 변수명 = 리터럴;
// JavaScript : 변수표현식 변수명 = 리터럴;

// const 변수 표현식은 상수의 의미를 가지고 있다.
// 즉 java 에서의 final
// const shop = "원티드 식당";
// shop = 1;

// String str = "문자열";
str = 1;

let test = 1;
test = "문자열"

console.log(test);

/* 
    JavaScript 는 동적 타입을 지원하는 언어이다.
    변수의 선언 시점에 타입이 결정되는 것이 아닌, 변수의 할당 시점에 타입이 결정이 된다.

    장점
    - 자료형을 신경쓰지 않고 변수를 선언할 수 있다.

    단점
    - 변수의 값이 언제든 다른 타입으로 변경될 수 있기 때문에 값을 확인하기 전에는 타입을 확신할 수 없다.
    let test = "문자열"
    test.length(); == 3

    test = 10;
    - 특정 타입만 지원하는 내장 함수 / 문법을 작성했을 때 이 코드가 끝까지 잘 동작할 것이라는 확신이 들 수 없다.
*/

let test1;
// typeof : 타입을 반환하는 연산자
console.log(typeof test1);

test1 = 1;
console.log(typeof test1);

test1 = "java";
console.log(typeof test1);

test1 = true;
console.log(typeof test1);

test1 = null;
console.log(typeof test1);

test1 = {};
console.log(typeof test1);

test1 = [];
console.log(typeof test1);

test1 = function() {};
console.log(typeof test1);