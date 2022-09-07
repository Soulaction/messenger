import { useState } from "react";
import { useSelector } from "react-redux";
import s from "./Main.module.css"
import UpdateProfile from "../../components/contextMenu/UpdateProfile";
import { getAllUsers } from "../../http/userAPI";

const Main = () => {

    const user = useSelector(state => state.user)
    


    const [view, setView] = useState(false);

    const onChangeView = () => {
        setView(!view)
        getAllUsers().then(
            data => console.log(data)
        )
    }

    return (
        <div className={s.main}>
            <div className={s.profile}>
                <div className={s.avatar}>
                    <img src={user.avatar} alt="" />
                </div>
                <div className={s.info}>
                    <h2 className={s.title}>{user.surname} {user.name}</h2>
                    <hr/>
                    <button onClick={onChangeView}>Редактировать</button>
                </div>
            </div>
            <UpdateProfile view={view} onChangeView={onChangeView}/>
        </div>
    )
}

export default Main;