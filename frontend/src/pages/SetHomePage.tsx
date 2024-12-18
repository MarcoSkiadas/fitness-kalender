import {Link} from "react-router-dom";


export default function SetHomePage() {

    return(
        <>
            <div className="nav-container">
                <Link to="/Set/add" className="nav-box">
                    <img src="https://img.icons8.com/?size=100&id=110229&format=png&color=000000" alt="Add Sets"/>
                    <span>Add Sets</span>
                </Link>
                <Link to="/Set/see" className="nav-box">
                    <img src="https://img.icons8.com/?size=100&id=HWvGM2ovOHDt&format=png&color=000000" alt="See Sets"/>
                    <span>See Sets</span>
                </Link>
            </div>
        </>
    )

}