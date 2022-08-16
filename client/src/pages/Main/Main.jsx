import { useSelector } from "react-redux";
import s from "./Main.module.css"

const Main = () => {

    const user = useSelector(state => state.user)

    return (
        <div className={s.main}>
            <div className={s.profile}>
                <div className={s.avatar}>
                    <img src={user.avatar} alt="" />
                </div>
                <div className={s.info}>
                    <h2 className={s.title}>{user.surname} {user.name}</h2>
                    <hr />
                </div>
            </div>
        </div>
    )
}

export default Main;