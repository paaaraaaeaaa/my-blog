/*
    구조 분해 할당
*/

const menuArr = ['치킨' , '피자'];
// 구조 분해 할당 적용
const [chi1 , piz1] = menuArr;
console.log(chi1);
console.log(piz1);

// 요구사항 : 치킨과 피자를 각각 출력하라!
const chi = menuArr[0];
const piz = menuArr[1];

console.log(chi);
console.log(piz);

// 객체의 구조 분해 할당
const menu = {
    name : '치즈알밥',
    price : 7500,
    category : '퓨전한식'
};

// 요구사항 : 이름 , 가격 , 카테고리를 각각 출력
// const name = menu.name;
// const price = menu.price;
// const category = menu.category;

// 객체 구조 분해 할당 시에는 프로퍼티 key 와
// 사용할 변수명이 일치해야 된다.
const {name , price , category} = menu;

// 스프레드 문법과 구조 분해 할당을 결합하여
// 객체의 복사도 진행할 수 있다.
const {...rest} = menu;
console.log(rest);

console.log(name , price , category);
// console.log(name1 , price1 , category1);