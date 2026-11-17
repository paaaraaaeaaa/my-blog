// {} : javaScript 에서 중괄호는 객체(object)를 의미한다.
// 객체는 Key-Value 형태로 구성한다.
// 프로퍼티 key, 프로퍼티 value

// Java 로 치면 
// Store 라는 클래스
// name, category : 클래스의 필드(변수)
// getInfo : 클래스의 메서드
const store = {
    name : '원티드 식당',
    category : '분식', 
    getInfo : function() {
        return `${this.name} 은 ${this.category} 전문점입니다!`
    }
};

console.log(store);
console.log(store.getInfo());

/* 
    JavaScript 의 객체는 프로퍼티의 집합이다.
    암묵적으로 프로퍼티 Key 는 문자열로 작성을 하며 프로퍼티 Value 는 어떤 타입이던 다 들어갈 수 있다.
*/

const order = {menuName : '치킨'};

console.log(order);
// 동적으로 프로퍼티 추가
order.sauce = "뿌링클 시즈닝";
console.log(order);

// 프로퍼티 수정
order.sauce = "맛초킹 시즈닝";
console.log(order);