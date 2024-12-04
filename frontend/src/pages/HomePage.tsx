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
            {props.user == undefined && (
                <p> Please Login or Register to proceed!</p>
            )
            }
            <button onClick={routeLogin}>Login</button>
            <button onClick={routeRegister}>Register</button>
            <p>User: {props.user}</p>
        </div>
    )
}