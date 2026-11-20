/*
    스프레드 문법을 이용한 배열/객체의 복사
    ... : 복사의 의미
*/

const cart = ['치킨' , '피자'];
const cartRef = cart;   // 깊은 복사 (주소값을 복사)
const cartCopy = [...cart]; // 얕은 복사 (값만 복사해서 새로운 배열 생성)

cartRef.push('파스타');
console.log(cart , cartRef);

cartCopy.push('빠네파스타');
console.log(cart , cartCopy);

/*
    깊은 복사의 단점
    - 메뉴 데이터를 이곳 저곳에서 사용한다고 했을 때
    - 깊은 복사를 하게 된다면 한 군데에서 수정하면
    - 다른 곳에서는 영문도 모른 채 값이 변경된다.
*/

// js 에서 객체는 프로퍼티들의 집합이다.
// 프로퍼티란? key-value 구조를 가지고 있다.
const order = {
    menuName : '뿌링클',
    qty : 2
}

const orderRef = order;
// ...복사하고자 하는 대상 (주의 : 값만 복사 / 주소는 새롭게 만들어짐)
const orderCopy = {...order};

console.log(order === orderRef); // t / f
console.log(order === orderCopy); // t / f
