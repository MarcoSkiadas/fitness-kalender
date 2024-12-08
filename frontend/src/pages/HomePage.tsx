import {useNavigate} from "react-router-dom";

type HomepageProps = {
    login: () => void
    logout: () => void
    user: string | undefined | null
}

export default function HomePage(props: Readonly<HomepageProps>) {

    const nav = useNavigate();

    const routeLogin = () => {
        nav("/login");
    }
    const routeRegister = () => {
        nav("/register");
    }
    return (
        <div>

            {props.user != undefined && (
                <>
                    <button onClick={props.logout}>Logout</button>
                    <p>User: {props.user}</p></>
            )
            }
            <h1>Willkommen zur FiKa-App</h1>
            {props.user == undefined && (
                <>
                    <p> Please Login or Register to proceed!</p>
                    <button onClick={routeLogin}>Login</button>
                    <button onClick={routeRegister}>Register</button>
                </>
            )
            }

        </div>
    )
}