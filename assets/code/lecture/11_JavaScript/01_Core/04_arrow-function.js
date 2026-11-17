let message;

message = function() {
    return '주문 접수 OK! 👌'
}
console.log(message());

message = () => {
    return '주문 접수 OK! 👌'
}
console.log(message());

// 화살표 함수란?
// function 키워드와 중괄호 블럭, return 키워드를 생략하는 문법이다.
// 주의 : 중괄호 블럭 내에 코드가 많으면 생략할 수 없다.
message = () => '주문 접수 OK! 👌'

// 매개변수가 있는 화살표 함수
message = (menuName, menuPrice) => `${menuName} 의 가격 : ${menuPrice}`

// 매개변수가 1개 있을 때는 소괄호도 생략이 가능하다.
message = menuName => `${menuName} !!`
console.log(message("신전떡볶이")); 

/*
    화살표 함수는 추후 다룰 고차함수(내장 함수)에 굉장히 많이 쓰인다.
    또한 이 화살표 함수를 본따서 Java 에서는 람다 표현식이 등장하기도 하였다.
*/

// 메뉴가격 10% 할인!
const menuPriceList = [4000, 5000, 140000];
console.log(menuPriceList.map( function(price) {return price * 0.9} ))
console.log(menuPriceList.map( price => price * 0.9 ))