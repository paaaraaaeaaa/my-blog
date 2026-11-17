/*
    JavaScript 에서의 function 은 굉장히 중요한 요소이다.
    Java 는 Class 기반의 객체지향 언어라고 한다면 JS 는 function 기반의 객체지향 언어라고 할 수 있다.
*/


// 함수 선언문
function welcome(name) {
    return `${name} 님, 반갑습니다~~!!`;
}

console.log(welcome("김예린"));

// 함수 표현식
const welcome1 = function welcome1(name) {
    return `${name} 님, 반갑습니다~~!!`;
}

const welcome2 = function (name) {
    return `${name} 님, 반갑습니다~~!!`;
}

const welcome3 = function hello(name) {
    return `${name} 님, 반갑습니다~~!!`;
}

console.log(welcome1("김예린"));
console.log(welcome2("김예린"));
// console.log(hello("김예린"));
console.log(welcome3("김예린"));
/* 함수 호이스팅 */
/* 
    함수 선언문은 런타임 이전에 JS 엔진에 의해 먼저 실행된다(인식).
    따라서 함수 선언문 이전에 해당 함수를 참조하거나 호출할 수 있게 된다. 이를 호이스팅이라고 한다.
*/

/* 
    오늘의 미션
    - Java 언어의 동작 원리와 
    - JavaScript 언어의 동작 원리를 비교하고 
    서로에게 이해시킬 수 있는 자료 구성하기
*/
console.log(cook('떡볶이'));

// 함수 선언
function cook(menuName) {
    return `${menuName} 조리 시~~작~~!!`
}

console.log(test1("떡볶이"));

// 함수 표현식
const test1 = function cook(menuName) {
    return `${menuName} 조리 시~~작~~!!`
}