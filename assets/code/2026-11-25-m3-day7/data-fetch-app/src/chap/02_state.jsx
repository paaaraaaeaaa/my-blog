/*
    state
    컴포넌트 내부에서 바뀔 수 있는 값이다. (props는 부모가 주는 읽기 전용 값)

    const [값, setter함수] = useState(초기값);

    - setter로 state를 바꾸면 컴포넌트 함수가 다시 실행되어(리렌더링) 화면이 갱신된다.
    - 일반 변수(let)를 바꾸면 리렌더링이 일어나지 않으므로 화면이 바뀌지 않는다.

    이전 state(prev)를 이용한 변경
    setter를 호출해도 state는 즉시 바뀌지 않고, 이벤트 핸들러가 끝난 뒤 한 번에 반영된다.
    그래서 setCount(count + 1)을 세 번 호출해도 1만 증가한다.
    함수를 전달하면 이전 결과를 받아 누적해서 계산한다.   setCount(prev => prev + 1);
*/
import { useState } from 'react';

function Counter() {
    console.log('렌더링!');      // state가 바뀔 때마다 출력된다.

    /*
        TODO 1: 버튼을 눌러 콘솔의 count는 바뀌지만 화면은 그대로인 것을 확인하자.
                그 다음 let 대신 useState로 바꾸고, setCount로 값을 바꾸자.
    */
    // let count = 0;

    // const onClickPlusOne = () => {
    //     count = count + 1;
    //     console.log(`count: ${count}`);
    // };

    const [count, setCount] = useState(0);

    
    const onClickPlusOne = () => {
        // count = count + 1;
        // set 함수는 바꾸고자 하는 값을 변경하는 함수이며 변경 시 리렌더링이 일어나게 해준다.
        setCount(count + 1);
        console.log(`count: ${count}`);
    };

    /*
        TODO 2: setCount(count + 1)을 세 번 호출해 보자. 몇이 증가할까?
                그 다음 setCount(prev => prev + 1)로 바꿔서 3씩 증가하게 하자.
    */
    const onClickPlusThree = () => {
        // 이전 state 를 전달 받아 +1 씩 더해주는 함수 호
        setCount(prev => prev + 1);
        setCount(prev => prev + 1);
        setCount(prev => prev + 1);
     
        console.log(`count: ${count}`);
    };

    return (
        <>
            <h1>count : {count}</h1>
            <button onClick={onClickPlusOne}>+ 1</button>
            <button onClick={onClickPlusThree}>+ 3</button>
            {/* TODO 3: 클릭하면 count를 0으로 바꾸자. */}
            <button onClick={() => setCount(0)}>초기화</button>
        </>
    );
}

export default Counter;
