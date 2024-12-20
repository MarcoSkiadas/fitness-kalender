import {Link} from "react-router-dom";



export default function WorkoutHomePage() {

    return(
        <>
            <div className="nav-container">
                <Link to="/Workout/add" className="nav-box">
                    <img src="https://img.icons8.com/?size=100&id=110229&format=png&color=000000" alt="Add Workouts"/>
                    <span>Add Workouts</span>
                </Link>
                <Link to="/Workout/see" className="nav-box">
                    <img src="https://img.icons8.com/?size=100&id=HWvGM2ovOHDt&format=png&color=000000" alt="See Workouts"/>
                    <span>See Workouts</span>
                </Link>
            </div>
        </>
    )

}