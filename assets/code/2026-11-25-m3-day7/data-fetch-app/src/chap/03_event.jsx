/*
    이벤트 처리
    1. 이벤트 속성명은 카멜 표기법   onclick -> onClick, onchange -> onChange
    2. 실행할 코드 문자열이 아니라 함수를 전달한다.   <button onClick={() => ...}>
    3. 이벤트는 DOM 요소(button, input 등)에만 연결된다.
       컴포넌트에 onClick을 주면 props로 전달될 뿐이므로, 내부의 DOM 요소에 다시 연결해야 한다.

    입력값 관리 (제어 컴포넌트)
    input의 value를 state로 두고, onChange에서 state를 바꾼다.
    입력값이 여러 개면 객체 state 하나와 핸들러 하나로 관리할 수 있다.
*/
import { useState } from 'react';

function SignupForm() {

    const [form, setForm] = useState({
        username: '',
        email: ''
    });

    /*
        TODO 2: 기존 form을 복사(...form)하고, 이벤트가 발생한 input의 name에 해당하는 값만 바꾸자.
                [e.target.name]: e.target.value
    */
    const onChangeHandler = e => {
        // console.log(e);
        // console.log(e.target.name, e.target.value);
        // 프로퍼티 키에 [] 를 씌우게 되면 값이 곧 key 가 된다.
        // ex) username : 123
        setForm({
            ...form,    // 기존 값을 스프레드 문법으로 복사하지 않으면 form 에 묶여있는 다른 값ㅇ르 수정하면 기존 값이 초기화 된다.
            [e.target.name] : e.target.value
        });
    };

    // TODO 4: form을 빈 값으로 초기화하자.
    const onResetHandler = () => {
        setForm({username : '', email : ''})
    };

    return (
        <>
            <h1>회원가입</h1>
            {/*
                TODO 1: 두 input에 onChange={onChangeHandler}를 연결하고 콘솔을 확인하자.
                TODO 3: value={form.username}, value={form.email}도 연결하자. (초기화 버튼이 input까지 비우려면 필요)
            */}
            <input name="username" placeholder="이름" value={form.username} onChange={onChangeHandler}/>
            <br/>
            <input name="email" placeholder="이메일" value={form.email} onChange={onChangeHandler}/>
            <br/>
            <button onClick={onResetHandler}>초기화</button>

            <p>입력한 이름: {form.username} / 이메일: {form.email}</p>
        </>
    );
}

export default SignupForm;
