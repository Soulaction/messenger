import { useRef, useState } from "react";
import { registration } from "../../http/userAPI";
import icon from "../../assets/icons/upload.png"
import s from "./UpdateProfile.module.css"
import { useSelector } from "react-redux";


const UpdateProfile = ({ view, onChangeView }) => {

    const user = useSelector(state => state.user)

    const closeModale = (e) => {
        const modale = document.querySelector(`.${s.context_menu}`)
        const outButton = document.querySelector(`.${s.out}`)

        if (e.target == modale || e.target == outButton)
            onChangeView()
    }

    const name = useRef('')
    const surname = useRef('')
    const password = useRef('')


    const [warn, setWarn] = useState('')
    const [checkName, setCheckName] = useState(false)
    const [checkSurname, setCheckSurname] = useState(false)
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

        if (!password.current.value) {
            setCheckPassword(true)
        } else {
            setCheckPassword(false)
        }

        if (name.current.value && surname.current.value && password.current.value) {

            try {

                const formData = new FormData()
                formData.append('name', name.current.value)
                formData.append('surname', surname.current.value)
                formData.append('email', user.email)
                formData.append('password', password.current.value)
                formData.append('avarar', 'avatar')

                try {
                    await registration(formData)
                } catch (e) {
                    setWarn(e.response.data)
                }
            } catch (e) {
                setWarn(e.message)
            }
        }
    }


    return <div className={view ? s.context_menu : s.context_menu_none}
        onClick={(e) => closeModale(e)}>
        <div className={s.menu}>
            <h1>???????????????????????????? ??????????????</h1>
            <input defaultValue={user.name} ref={name} className={checkName ? s.warning : ''} placeholder="?????????????? ??????"></input>
            <input defaultValue={user.surname} ref={surname} className={checkSurname ? s.warning : ''} placeholder="?????????????? ??????????????"></input>
            <input defaultValue={user.password} ref={password} className={checkPassword ? s.warning : ''} type="password" placeholder="?????????????? ????????????" />
            <div className={s.input_wrapper}>
                <input className={s.input_file} type="file" id="input_file" />
                <label htmlFor="input_file" className={s.input_file_button}>
                    <span className={s.input_file_icon_wrapper}>
                        <img className={s.input_file_icon} src={icon} alt="?????????????? ????????" width="25" />
                    </span>
                    <span className={s.input_file_button_text}>???????????????? ????????</span>
                </label>
            </div>
            <h4 className={warn ? s.warn_mes_y : s.warn_mes_n}>{warn}</h4>
            <div className={s.redirect}>
                <div className={s.out_block}>
                    <button className={s.out} onClick={(e) => closeModale(e)}>??????????</button>
                </div>
                <div className={s.change}>
                    <button onClick={reg}>????????????????</button>
                </div>
            </div>
        </div>
    </div>
}

export default UpdateProfile;