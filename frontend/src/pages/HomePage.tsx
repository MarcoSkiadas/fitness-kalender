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
    return (
    <div>
        <button onClick={routeLogin}>Login</button>
        {props.user != undefined && (
            <button onClick={props.logout}>Logout</button>
        )}
        <p>User: {props.user}</p>
    </div>
    )
}