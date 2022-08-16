import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { LOGIN_ROUT } from "../../untils/ConstRout";
import s from "./Registration.module.css"
import icon from "../../assets/icons/upload.png"
import { useRef, useState } from "react";
import { registration } from "../../http/userAPI";

const Registration = () => {

    const navigate = useNavigate()

    const [warn, setWarn] = useState('')

    const name = useRef('')
    const surname = useRef('')
    const email = useRef('')
    const password = useRef('')

    const [checkName, setCheckName] = useState(false)
    const [checkSurname, setCheckSurname] = useState(false)
    const [checkEmail, setCheckEmail] = useState(false)
    const [checkPassword, setCheckPassword] = useState(false)

    const reg = async () => {

        if (!name.current.value) {
            setCheckName(true)
        } else {
            setCheckName(false)
        }
        if (!surname.current.value) {
            setCheckSurname(true)
        } else {
            setCheckSurname(false)
        }

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

        if (name.current.value && surname.current.value && email.current.value && password.current.value) {

            try {

                if (!email.current.value.match(/\S+@\S+\.\S+/)) {
                    throw new Error("Логин введён некорректно!")
                }

                const formData = new FormData()
                formData.append('name', name.current.value)
                formData.append('surname', surname.current.value)
                formData.append('email', email.current.value)
                formData.append('password', password.current.value)
                formData.append('avarar', 'avatar')

                try {
                    await registration(formData)
                    navigate(LOGIN_ROUT)
                } catch (e) {
                    setWarn(e.response.data)
                }
            } catch (e) {
                setCheckEmail(true)
                setWarn(e.message)
            }
        }
    }

    return (
        <div className={s.registration}>
            <div className={s.form}>
                <h1>Регистрация</h1>
                <input ref={name} className={checkName ? s.warning : ''} placeholder="Введите имя"></input>
                <input ref={surname} className={checkSurname ? s.warning : ''} placeholder="Введите Фамилию"></input>
                <input ref={email} className={checkEmail ? s.warning : ''} placeholder="Введите email"></input>
                <input ref={password} className={checkPassword ? s.warning : ''} type="password" placeholder="Введите пароль" />
                <div className={s.input_wrapper}>
                    <input className={s.input_file} type="file" id="input_file" />
                    <label htmlFor="input_file" className={s.input_file_button}>
                        <span className={s.input_file_icon_wrapper}>
                            <img className={s.input_file_icon} src={icon} alt="Выбрать файл" width="25" />
                        </span>
                        <span className={s.input_file_button_text}>Выберите фото</span>
                    </label>
                </div>
                <h4 className={warn ? s.warn_mes_y : s.warn_mes_n}>{warn}</h4>
                <div className={s.redirect}>
                    <div className={s.auth}>
                        <Link to={LOGIN_ROUT}>Авторизация</Link>
                    </div>
                    <div className={s.registration}>
                        <button onClick={reg}>Зарегестрироваться</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Registration