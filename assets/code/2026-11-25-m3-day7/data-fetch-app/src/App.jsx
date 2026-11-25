/*
    React 마지막 파트: props · state · event · 반복 렌더링 · useEffect · 비동기 요청
    라우터와 메뉴는 미리 만들어 두었다. 수업에서는 src/chap 파일만 작성한다.
*/
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './layouts/Layout';
import Main from './pages/Main';
import Error404 from './errors/Error404';

import Props from './chap/01_props';
import State from './chap/02_state';
import Event from './chap/03_event';
import Iteration from './chap/04_iteration';
import Effect from './chap/05_useEffect';
import Fetch from './chap/06_fetch';
import Pokemon from './chap/07_pokemon';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<Main/>}/>
                    <Route path="props" element={<Props/>}/>
                    <Route path="state" element={<State/>}/>
                    <Route path="event" element={<Event/>}/>
                    <Route path="iteration" element={<Iteration/>}/>
                    <Route path="effect" element={<Effect/>}/>
                    <Route path="fetch" element={<Fetch/>}/>
                    <Route path="pokemon" element={<Pokemon/>}/>
                </Route>
                <Route path="*" element={<Error404/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;