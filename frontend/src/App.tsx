import {useEffect, useState} from 'react'
import './App.css'
import Navigation from "./components/Navigation.tsx";
import axios from "axios";
import {toast, ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {Route, Routes} from "react-router-dom";
import {User} from "./components/FiKaSchema.ts";
import LoginPage from "./pages/LoginPage.tsx";
import RegisterPage from "./pages/RegisterPage.tsx";
import HomePage from "./pages/HomePage.tsx";



function App() {

    const [user, setUser] = useState<User | null | undefined>(undefined)
    const currentRole = user?.role

    useEffect(() => {
        me()
    }, [])

    const login = () => {
        const host = window.location.host === `localhost:5173` ? `http://localhost:8080` : window.location.origin
        window.open(host + `/oauth2/authorization/github`, `_self`)
    }

    function logout() {
        axios.get("/api/auth/logout")
            .then(() => setUser(null))
            .then(() => toast.success("you have been logged out"))
    }

    const me = () => {
        axios.get(`/api/auth/me`)
            .then(response => setUser(response.data))
            .catch(() => {
                setUser(null)
            })
    }

    if (user === undefined) {
        return <><p>Loading...</p></>
    }

    return (
      <>
          <header>
              <Navigation currentRole={currentRole}/>
          </header>
          <Routes>
              <Route element={<RegisterPage/>} path={"/register"}/>
              <Route element={<LoginPage setUser={setUser} me={me} login={login}/>} path={"/login"}/>
              <Route path={"/"}
                     element={<HomePage login={login} logout={logout}
                                        user={user?.username}/>}/>
          </Routes>
          <ToastContainer/>
      </>
  )
}

export default App
