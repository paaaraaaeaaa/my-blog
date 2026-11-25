// 확인용 예시 코드(수업 코드 아님): state/key 동작 비교
import { useState } from 'react';

function LetCounter() {
    console.log('LetCounter 렌더링');
    let count = 0;
    const onClick = () => { count = count + 1; console.log('let count: ' + count); };
    return (<div id="let"><h2>let: {count}</h2><button id="letBtn" onClick={onClick}>let +1</button></div>);
}

function ThreeTimes() {
    const [a, setA] = useState(0);
    const [b, setB] = useState(0);
    return (
        <div id="three">
            <h2 id="a">값 방식: {a}</h2>
            <button id="aBtn" onClick={() => { setA(a + 1); setA(a + 1); setA(a + 1); }}>값 방식 x3</button>
            <h2 id="b">함수 방식: {b}</h2>
            <button id="bBtn" onClick={() => { setB(p => p + 1); setB(p => p + 1); setB(p => p + 1); }}>함수 방식 x3</button>
        </div>
    );
}

function NoKey() {
    return (<ul id="nokey">{['가', '나'].map(t => <li>{t}</li>)}</ul>);
}

export default function Exp() {
    return (<><LetCounter/><ThreeTimes/><NoKey/></>);
}
