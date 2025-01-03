import {User} from "../components/FiKaSchema.ts";
import {useState} from "react";
import axios from "axios";
import {toast} from "react-toastify";

type FriendsAddPageProps = {
    user: User | null | undefined
}

export default function FriendsAddPage(props: Readonly<FriendsAddPageProps>) {

    const [friendId, setFriendId] = useState("");

    const handleAddFriend = () => {
        axios.post(`/api/user/friend/${props.user?.id}/${friendId}`)
            .then(r => console.log(r.data))
            .then(() => toast.success(`Friend with ID: ${friendId} has been registered`))
            .catch(error => toast.error(error.response.data.errorMsg))
    }

    return(
        <>
            <p>{props.user?.id}</p>
            <div className="exercise-form">
                <label htmlFor={`friendId`}>Insert Friend-Id: </label>
                <form>
                    <input value={friendId}
                           placeholder="Please enter the If of your Friend"
                           type="text"
                           onChange={(e) =>
                               setFriendId(e.target.value)
                           }
                           className="login-input"
                           id={`friendId`}
                    />
                </form>
                <button onClick={handleAddFriend}>Add Friend</button>
            </div>
        </>
    )

}