import { Link } from 'react-router-dom';

function Error404() {
    return (
        <>
            <h1>404 NOT FOUND</h1>
            <h3>준비가 안 된 페이지 입니다.</h3>
            <Link to="/">메인으로</Link>
        </>
    );
}

export default Error404;
