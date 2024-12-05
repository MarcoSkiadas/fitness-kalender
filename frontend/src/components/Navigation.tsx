import {Link} from "react-router-dom";

type NavigationProps = {
    currentRole: string | undefined
}
export default function Navigation(props: Readonly<NavigationProps>) {

    return (
        <>
            <div className="navigation">
                {props.currentRole != undefined && (
                    <>
                        <Link to="/" className="nav-link">
                            <img src="https://img.icons8.com/?size=100&id=74811&format=png&color=000000" alt="Homepage"/>
                            <span>Homepage</span>
                        </Link>
                        <Link to="/Set" className="nav-link">
                            <img src="https://img.icons8.com/?size=100&id=aaamNJ9jOdo4&format=png&color=000000" alt="Set"/>
                            <span>Create a Set</span>
                        </Link>
                        <Link to="/Workout" className="nav-link">
                            <img src="https://img.icons8.com/?size=100&id=v0g0392df6zn&format=png&color=000000" alt="Set"/>
                            <span>Workout</span>
                        </Link>

                    </>
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