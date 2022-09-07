import { useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { authorization } from "../../http/userAPI";
import { setAuthUser, setUser } from "../../store/user/action";
import { MAIN_ROUT, REGISTRATION_ROUT } from "../../untils/ConstRout";
import s from "./Auth.module.css"

const Auth = () => {

    const navigate = useNavigate()

    const email = useRef('')
    const password = useRef('')

    const [checkEmail, setCheckEmail] = useState(false)
    const [checkPassword, setCheckPassword] = useState(false)
    const [warn, setWarn] = useState('')

    const dispatch = useDispatch()

    const auth = async () => {

        setWarn('')

        if (!email.current.value) {
            setCheckEmail(true)
        } else {
            setCheckEmail(false)
        }
        if (!password.current.value) {
            setCheckPassword(true)
        } else {
            setCheckPassword(false)
        }
        if (email.current.value && password.current.value) {
            
            try {
                let data = await authorization(email.current.value, password.current.value)

                data = {...data, isAuth: true}
                dispatch(setUser(data))
                navigate(MAIN_ROUT)

            } catch (e) {
                setWarn(e.response.data)
                email.current.value = ''
                password.current.value = ''
            }
        }
    }

    const user = useSelector(state => state.user)
    return (
        <div className={s.auth}>
            <div className={s.form}>
                <h1>Авторизация</h1>
                <input ref={email} className={checkEmail ? s.warning : ''} placeholder="Введите email"></input>
                <input ref={password} className={checkPassword ? s.warning : ''} type="password" placeholder="Введите пароль"></input>
                <h4 className={warn ? s.warn_mes_y : s.warn_mes_n}>{warn}</h4>
                <div className={s.redirect}>
                    <div className={s.auth}>
                        <Link to={REGISTRATION_ROUT}>Регистрация</Link>
                    </div>
                    <div className={s.registration}>
                        <button onClick={auth}>Войти</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Auth;