import {Link} from "react-router-dom";


export default function MessengerHomePage() {

    return(
        <>
            <div className="nav-container">
                <Link to="/Messenger/add" className="nav-box">
                    <img src="https://img.icons8.com/?size=100&id=110229&format=png&color=000000" alt="Send Messages"/>
                    <span>Send Messages</span>
                </Link>
                <Link to="/Messenger/see" className="nav-box">
                    <img src="https://img.icons8.com/?size=100&id=HWvGM2ovOHDt&format=png&color=000000" alt="See Messages"/>
                    <span>See Messages</span>
                </Link>
            </div>
        </>
    )
}
