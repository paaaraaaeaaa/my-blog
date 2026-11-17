/*
    JS 에서의 배열
    - Java 에서의 배열은 같은 자료형만 들어갈 수 있다.
    - JS 의 배열은 자료형을 명시하지 않기 때문에 무슨 값이든 들어갈 수 있게 된다.
*/

// 1. 배열 리터럴을 통해 생성
const menus = ['제육', '떡볶이', '순대', 1, {name : 'hi'}, function(){}];

for(let i = 0; i < menus.length; i++) {
    console.log(menus[i]);
}