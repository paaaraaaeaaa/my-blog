import { user } from "./user";

export default function NameCard() {
    return <h1 style={{color : 'red'}}>{user.name}</h1>
}