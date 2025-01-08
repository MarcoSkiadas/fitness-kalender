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
import SetAddPage from "./pages/SetAddPage.tsx";
import WorkoutAddPage from "./pages/WorkoutAddPage.tsx";
import WorkoutGetPage from "./pages/WorkoutGetPage.tsx";
import WorkoutHomePage from "./pages/WorkoutHomePage.tsx";
import SetHomePage from "./pages/SetHomePage.tsx";
import SetGetPage from "./pages/SetGetPage.tsx";
import FriendsHomePage from "./pages/FriendsHomePage.tsx";
import FriendsAddPage from "./pages/FriendsAddPage.tsx";
import FriendsGetPage from "./pages/FriendsGetPage.tsx";

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
              <Route element={<SetHomePage/>} path={"/set"}/>
              <Route element={<SetAddPage user={user}/>} path={"/set/add"}/>
              <Route element={<SetGetPage user={user}/>} path={"/set/see"}/>
              <Route element={<WorkoutHomePage/>} path={"/workout"}/>
              <Route element={<WorkoutAddPage user={user}/>} path={"/workout/add"}/>
              <Route element={<WorkoutGetPage user={user}/>} path={"/workout/see"}/>
              <Route element={<FriendsHomePage/>} path={"/friends"}/>
              <Route element={<FriendsAddPage user={user}/>} path={"/friends/add"}/>
              <Route element={<FriendsGetPage user={user}/>} path={"/friends/see"}/>
          </Routes>
          <ToastContainer/>
      </>
  )
}

export default App
