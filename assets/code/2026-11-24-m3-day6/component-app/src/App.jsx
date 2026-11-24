// 함수 이름의 앞글자가 대문자면 컴포넌트이다.
// 컴포넌트는 엘리먼트(html 태그)를 리턴하는 함수이다.

/* 
  React 에서는 import/export 가 굉장히 중요하다.
  만약 import 를 했는데 실제로 사용하지 않으면 오류가 발생할 수 있기 때문에 오류 발생 시 첫 번째로 확인하면 좋은 것은 import 구문이다.
*/

import Hello from "./module/01_intro/01_intro"
import UserInfo from "./module/02_module/UserInfo"

/* 
  현재 02_module 폴더까지 진행했을 때, 사용자 화면에 우리가 만든 컴포넌트를 보여주기 위해서는 App 컴포넌트에 집어 넣어야 한다.
  하지만, 현실 세계의 프로젝트는 여러 페이지가 존재하며 페이지 별로 보여야 하는 컴포넌트, 보이면 안 되는 컴포넌트가 존재한다.
*/

/*
  react-router-dom 의존성

  라우팅 : 요청한 URL 주소에 따라 보여줄 컴포넌트를 정의하는 것이다.
  주요 속성
  1. BrowserRouter : 라우팅 사용 영역을 감싸는 상위 속성
  2. Routes : Route 를 묶는 부모 속성
  3. Route : path(주소)와 element 를 연결
*/

import { BrowserRouter, Routes , Route} from 'react-router-dom';
import Layout from "./layouts/Layout";
import Main from "./pages/Main";

function App() {

  return (
    // <> 
    //   <Hello/>
    //   <UserInfo/>
    //  </>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout/>}>
          <Route index element={<Main/>}/>
          <Route path="vite/intro" element={<Hello/>}/>
          <Route path="vite/module" element={<UserInfo/>}/>
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
