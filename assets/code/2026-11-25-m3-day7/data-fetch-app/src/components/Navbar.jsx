import { NavLink } from 'react-router-dom';
import styles from './Navbar.module.css';

const activeStyle = ({isActive}) => isActive ? {backgroundColor: 'orangered', color: 'white'} : undefined;

function Navbar() {
    return (
        <nav className={styles.nav}>
            <h2><NavLink to="/">React 챕터</NavLink></h2>

            <h4>컴포넌트와 데이터</h4>
            <NavLink to="/props" style={activeStyle}>01. props</NavLink>
            <NavLink to="/state" style={activeStyle}>02. state</NavLink>
            <NavLink to="/event" style={activeStyle}>03. event</NavLink>
            <NavLink to="/iteration" style={activeStyle}>04. 반복 렌더링</NavLink>

            <h4>렌더링 이후 작업</h4>
            <NavLink to="/effect" style={activeStyle}>05. useEffect</NavLink>
            <NavLink to="/fetch" style={activeStyle}>06. 데이터 요청</NavLink>
            <NavLink to="/pokemon" style={activeStyle}>07. 포켓몬 도감</NavLink>
        </nav>
    );
}

export default Navbar;
