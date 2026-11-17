/*
    고차함수란?
    함수를 인자로 전달받거나, 함수를 반환하는 함수
    배열의 고차 함수는 콜백 함수를 인자로 받아 배열의 요소를 순화하며 처리하게 된다.
*/

const menus = [
    { name : '치즈떡볶이', category : '분식', price : 14000},
    { name : '짜장면', category : '중식', price : 9000},
    { name : '김치찌개', category : '한식', price : 12000},
    { name : '마라탕', category : '중식', price : 13000},
    { name : '라멘', category : '일식', price : 13000}
];

// forEach : 배열의 각 요소마다 콜백 함수를 실행(반환값 없음)
console.log("=======================forEach==========================")
// forEach 함수는 배열의 요소 하나를 인자로 전달받아 동작시킬 코드를 실행한다.
menus.forEach( menu => console.log(menu) );

console.log("=======================map==========================")
// map : 콜백 함수의 반환 값으로 새로운 배열을 return
const names = menus.map( menu => menu.name );
console.log(names);

console.log("=======================filter==========================")
// filter : 콜백 함수의 반환값이 true 인 요소만 모아 새로운 배열 return 
// 5개의 메뉴 중, 분식만 모아서 출력
// ===, == 
// == : 동등 비교   (암묵적으로 타입 변환을 통해 같은 값인지 비교)
// === : 일치 비교  (타입과 값이 모두 일치)

console.log(` 1 == '1' : ${1 == '1'}`);
console.log(` 1 === '1' : ${1 === '1'}`);

const snacks = menus.filter( menu => menu.category === '중식' );
// 가격이 10000 미만인 메뉴들만 필터링
const menu = menus.filter( menu => menu.price < 10000 );
console.log(snacks);
console.log(menu);

// filter, map 조합
// 분식 중에서 가격이 1만원 미만인 메뉴의 이름 목록

const result = menus
                // 첫 번째 필터를 마무리 하면 분식들만 모인 메뉴 배열이 된다.
                .filter(menu => menu.category === '중식')
                // 두 번째 필터를 마무리 하면 10000원 미만 분식이 모인 메뉴 배열이 된다.
                .filter(menu => menu.price < 10000)
                // 배열 요소를 순회하며 메뉴 이름 return 해서 배열을 만든다.
                .map(menu => menu.name)

console.log(result);