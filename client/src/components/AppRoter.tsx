import { Routes, Route, Navigate } from "react-router-dom"
import { LOGIN_ROUT } from "../untils/ConstRout"
import { publicRouters, authRouters } from "../untils/Routes"

export const AppRouter = () => {

    return (
        <Routes>
            {publicRouters.map(({ path, component }) =>
                <Route key={path} path={path} element={component()} />
            )}
            {authRouters.map(({ path, component }) =>
                <Route key={path} path={path} element={component()} />
            )}
            <Route path="*" element={<Navigate to={LOGIN_ROUT} replace />} />

        </Routes>
    )
}