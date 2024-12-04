import {Link} from "react-router-dom";

type NavigationProps = {
    currentRole: string | undefined
}
export default function Navigation(props: Readonly<NavigationProps>) {

    return (
        <>
            <div className="navigation">
                {props.currentRole != undefined && (
                <Link to="/" className="nav-link">
                    <img src="https://img.icons8.com/?size=100&id=74811&format=png&color=000000" alt="Homepage"/>
                    <span>Homepage</span>
                </Link>
                )}
                {props.currentRole === `ADMIN` && (
                    <Link to="/admin" className="nav-link">
                        <img src="https://img.icons8.com/?size=100&id=114317&format=png&color=000000" alt="Admin"/>
                        <span>Admin</span>
                    </Link>
                )}
            </div>
        </>
    )
}