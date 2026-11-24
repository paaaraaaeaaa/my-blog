import { Outlet } from 'react-router-dom';
import Navbar from '../components/Navbar';
import styles from './Layout.module.css';

/*
    Outlet
    현재 주소와 일치하는 하위 Route의 컴포넌트가 이 위치에 렌더링 된다.
    메뉴를 클릭하면 Navbar는 그대로 있고 Outlet 부분만 바뀐다.
*/
function Layout() {
    return (
        <div className={styles.layout}>
            <Navbar/>
            <main className={styles.content}>
                {/* 
                     JSX 내부의 주석
                     Outlet 엘리먼트는 경로(Path) 에 따라서 다른 컴포넌트로 대체되는 엘리먼트이다.
                */}
                <Outlet/>
            </main>
        </div>
    );
}

export default Layout;