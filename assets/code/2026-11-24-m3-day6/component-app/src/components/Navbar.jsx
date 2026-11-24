import { NavLink } from 'react-router-dom';
/*
    CSS Module
    파일명을 '이름.module.css'로 작성하고 객체로 import 해서 className에 넣는다.
    클래스명이 고유한 이름으로 바뀌어(Navbar-module__nav__xxxxx) 다른 컴포넌트와 충돌하지 않는다.
    (일반 CSS는 import './style.css'; 처럼 가져오며 페이지 전체에 적용된다.)
*/
import styles from './Navbar.module.css';

/*
    Link / NavLink
    <a href>는 페이지 전체를 새로 불러오지만, Link는 새로고침 없이 주소만 바꿔 컴포넌트를 교체한다.
        <Link to="/props">props</Link>

    NavLink는 Link와 같고, 현재 주소와 일치하면 isActive가 true가 되어 스타일을 바꿀 수 있다.
*/
const activeStyle = ({isActive}) => isActive ? {backgroundColor: 'orangered', color: 'white'} : undefined;

function Navbar() {
    return (
        <nav className={styles.nav}>
            <h2><NavLink to="/">React 챕터</NavLink></h2>

            <h4>01. Vite</h4>
            <NavLink to="/vite/intro" style={activeStyle}>프로젝트 생성</NavLink>
            <NavLink to="/vite/module" style={activeStyle}>모듈</NavLink>

        </nav>
    );
}

export default Navbar;