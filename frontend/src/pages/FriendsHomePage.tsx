import {Link} from "react-router-dom";



export default function FriendsHomePage() {

    return(
        <>
            <div className="nav-container">
                <Link to="/Friends/add" className="nav-box">
                    <img src="https://img.icons8.com/?size=100&id=110229&format=png&color=000000" alt="Add Friends"/>
                    <span>Add Friends</span>
                </Link>
                <Link to="/Friends/see" className="nav-box">
                    <img src="https://img.icons8.com/?size=100&id=HWvGM2ovOHDt&format=png&color=000000" alt="See Friends"/>
                    <span>See Friends</span>
                </Link>
            </div>
        </>
    )

}