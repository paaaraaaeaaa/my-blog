/*
    반복 렌더링
    배열.map()으로 데이터 배열을 엘리먼트 배열로 바꿔서 렌더링한다.
    names.map(name => <li key={name.id}>{name.name}</li>)

    key
    - 리액트가 목록에서 어떤 항목이 추가/삭제되었는지 구분하기 위한 식별자이다.
    - 형제 요소 사이에서 고유해야 한다. key가 없으면 콘솔에 경고가 출력된다.
    - DB 데이터라면 pk값을 사용한다. index는 항목이 추가/삭제되면 바뀌므로 쓰지 않는 것이 좋다.

    state 배열 변경
    원본 배열을 수정(push, splice)하지 않고, 새 배열을 만들어 setter에 전달한다.
    - 추가: [...배열, 새값]
    - 삭제: filter
*/
import { useState } from 'react';

function NameList() {

    const [names, setNames] = useState([
        {id: 1, name: '홍길동'},
        {id: 2, name: '유관순'},
        {id: 3, name: '이순신'}
    ]);
    const [inputText, setInputText] = useState('');
    const [nextId, setNextId] = useState(4);

    /*
        TODO 2: names 뒤에 {id: nextId, name: inputText}를 추가한 새 배열을 setNames에 전달하자.
                nextId는 1 증가, 입력창은 비우자.
    */
    const onClickHandler = () => {
        // 기존 객체 배열 복사 후, id : 4, name : 김예린 객체를 배열에 추가
        setNames([...names, {id : nextId, name : inputText}])
        // 다음 추가 시에 id 가 4가 되면 안 되기 때문에 +1
        setNextId(nextId + 1);
        // 추가 완료 시 input 태그 내부에 값을 초기화
        setInputText('');
    };

    // TODO 3: filter로 전달받은 id와 다른 항목만 남기자.
    const onRemove = id => {
        // names 라는 객체배열에서 전달받은 id 를 가지지 않은 객체들로만 새로운 배열을 구성
        setNames(names.filter(name => name.id !== id));
    };

    return (
        <>
            <input value={inputText} onChange={e => setInputText(e.target.value)}/>
            <button onClick={onClickHandler}>추가</button>
            <ul>
                {/*
                    TODO 1: names.map으로 <li>{name.name}</li>를 출력하자.
                            key 없이 먼저 작성해 콘솔 경고를 확인한 뒤 key={name.id}를 추가하자.
                    TODO 3: <li>에 onDoubleClick={() => onRemove(name.id)}를 연결하자.
                    더블클릭한 li 태그를 삭제하기 위해서는 해당 li를 식별할 수 있는 유일한 값을 전달해준다.
                */}
                {/*
                    ul 태그 하위에 li를 사용자가 입력한 값 + ID 로 노출 시키고자 한다.
                    ex) * 4번 김예린
                */}

                {names.map( name =>  
                    <li key={name.id} onDoubleClick={() => onRemove(name.id)}>{name.id}번 {name.name}</li>
                )}
            </ul>
            <p>더블 클릭하면 삭제된다.</p>
        </>
    );
}

export default NameList;
