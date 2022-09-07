import { useSelector } from "react-redux";
import s from "./Menu.module.css";

const Menu = () => {
  const isAuth = useSelector((state) => state.user.isAuth);
  console.log(isAuth);
  return (
    <div className={isAuth ? s.menu : s.no_menu}>
      <div class={s.rotate_shadows}>Меню The Best</div>
    </div>
  );
};

export default Menu;
