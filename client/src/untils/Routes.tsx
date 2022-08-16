import Auth from "../pages/Auth/Auth";
import Correspondence from "../pages/Correspondence/Correspondence";
import Dialogs from "../pages/Dialogs/Dialogs";
import Friends from "../pages/Friends/Friends";
import Main from "../pages/Main/Main";
import Registration from "../pages/Registration/Registration";
import { CORRESPONDENCE_ROUT, DIALOGS_ROUT, FRIENDS_ROUT, LOGIN_ROUT, MAIN_ROUT, REGISTRATION_ROUT } from "./ConstRout";

export const publicRouters = [
    {
        path: LOGIN_ROUT,
        component: Auth 
    },
    {
        path: REGISTRATION_ROUT,
        component: Registration 
    },
]

export const authRouters = [
    {
        path: MAIN_ROUT,
        component: Main
    },
    {
        path: DIALOGS_ROUT,
        component: Dialogs
    },
    {
        path: CORRESPONDENCE_ROUT,
        component: Correspondence 
    },
    {
        path: FRIENDS_ROUT,
        component: Friends 
    },
]