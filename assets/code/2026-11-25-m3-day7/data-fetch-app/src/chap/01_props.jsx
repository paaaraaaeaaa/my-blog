/*
    props
    부모 컴포넌트가 자식 컴포넌트에게 전달하는 값이다.   <Title name="홍길동"/>
    - 전달한 속성들은 하나의 객체(props)로 묶여 함수의 매개변수로 들어온다.
    - props는 읽기 전용이다. 자식 컴포넌트에서 수정하지 않는다.
    - 문자열이 아닌 값(숫자, 배열, 함수 등)은 {}로 전달한다.   age={20}

    children
    컴포넌트 태그 사이에 작성한 내용은 props.children으로 전달된다.   <Box>여기 내용</Box>

    프론트는 컴포넌트들의 집합이라고 볼 수 있다.
    컴포넌트는 부모, 자식, 형제 등의 관계를 통해서 구성이 되며 부모와 자식 사이에서는 props 를 통해서 값을 전달해줄 수 있다.
*/

/* 1. props 기본 */
function Title(props) {
    // TODO 1-2: console.log(props)로 들어온 값을 확인하고, '홍길동' 자리에 props.name을 출력하자.
    console.log(props);
    return <h1>안녕하세요 {props.name}님 반갑습니다.</h1>;
}

/* 2. 매개변수에서 구조 분해 할당 (가장 많이 쓰는 형태) */
// TODO 2-2: 매개변수를 {name, age}로 받아서 출력하자.
function Profile({name, age}) {
    // console.log(Props);
    // 구조분해 할당은 객체 내부의 키값을 통해 바로 변수이자 값으로 사용할 수 있게 만드는 JS 문법이다.
    return <h3>{name}님은 {age}살입니다.</h3>;
}

/* 3. children */
// TODO 3: 매개변수를 {children}으로 받아서 div 안에 출력하자.
function Box({children}) {
    return <div style={{border: '1px solid orangered', padding: 10, margin: 5}}>{children}</div>;
}


/*
    Props 컴포넌트는 자식 컴포넌트를 가지고 있는 부모 컴포넌트이다.
*/
export default function Props() {
    return (
        <>
            {/* TODO 1-1: name 속성으로 서로 다른 이름을 전달하자. 
                <Title/> 은 실제로 function Title(name) {} 이라는 컴포넌트이다.
            */}
            <Title name="김예린"/>
            <Title name="paraea"/>

            {/* TODO 2-1: name과 age를 전달하자. (숫자는 {}로) */}
            <Profile name="김예린" age={23}/>

            <Box>텍스트 노드</Box>
            <Box><Profile name="강감찬" age={30}/></Box>
        </>
    );
}
