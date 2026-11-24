/* 
    export default 는 {} 없이 이름으로 import 를 받을 수 있지만,
    파일 당 1개로 제한이 된다.
    export 는 {} 를 통해서 import 받을 수 있으며,
    여러 개를 하나의 파일에서 내보낼 수 있다.
*/

import { user } from "./user";

export default function AgeCard() {
    return <h2 style={{color : 'orange'}}>{user.age}</h2>
}