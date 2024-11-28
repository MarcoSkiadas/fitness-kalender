import {User} from "../components/FiKaSchema.ts";
import React, {FormEvent, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {toast} from "react-toastify";

type LoginPageProps = {
    setUser: React.Dispatch<React.SetStateAction<User | null | undefined>>
    login: () => void
        me: () => void
}
export default function LoginPage(props: Readonly<LoginPageProps>) {

    const [username, setUsername] = useState<string>("")
    const [password, setPassword] = useState<string>("")

    const nav = useNavigate();

    function submitLogin(e: FormEvent<HTMLFormElement>) {
        e.preventDefault()
        axios.post("/api/auth/login", undefined, {auth: {username, password}})
            .then(r => props.setUser(r.data))
            .then(props.me)
            .then(() => nav("/"))
            .catch(() => toast.error(`${username} is not registered`))
    }

    function register() {
        nav(`/register`)
    }

    return (
        <div className="login-page">
            <div className="login-form-container">
                <form onSubmit={submitLogin} className="login-form">
                    <input value={username} placeholder={"Please enter your Username"} type={"text"}
                           onChange={e => setUsername(e.target.value)}
                           className="login-input"/>
                    <input value={password} placeholder={"Please enter your Password"} type={"password"}
                           onChange={e => setPassword(e.target.value)}
                           className="login-input"/>
                    <button className="login-button">Login</button>
                </form>
                <div className="additional-actions">
                    <button onClick={register} className="register-button">Register</button>
                </div>
            </div>
        </div>
    )
}