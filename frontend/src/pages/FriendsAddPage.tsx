import {MessageType, User} from "../components/FiKaSchema.ts";
import {useState} from "react";
import axios from "axios";
import {toast} from "react-toastify";

type FriendsAddPageProps = {
    user: User | null | undefined
}

export default function FriendsAddPage(props: Readonly<FriendsAddPageProps>) {

    const [friendId, setFriendId] = useState("");



    const handleSendMessage = () => {
        if (friendId === "") {
            toast.error("Please select a friend before sending a message!");
            return;
        }
        const messageContent:string = `Do you want to add ${props.user?.id} as your Friend?`;

        axios.post(`/api/message`, {
            id: "",
            recipientId: friendId,
            senderId: props.user?.id,
            messageContent: messageContent,
            accepted: false,
            read: false,
            messageType: MessageType.REQUEST,
            createdAt: null
        })
            .then(r => console.log(r.data))
            .then(() => toast.success(`Friend request has been sent to your Friend!`))
            .catch(error => toast.error(error.response.data.errorMsg))
    }

    return(
        <>
            <p>{props.user?.id}</p>
            <div className="exercise-form">
                <label htmlFor={`friendId`}>Insert Friend-Id: </label>
                <form>
                    <input value={friendId}
                           placeholder="Please enter the Id of your Friend"
                           type="text"
                           onChange={(e) =>
                               setFriendId(e.target.value)
                           }
                           className="login-input"
                           id={`friendId`}
                    />
                </form>
                <button onClick={handleSendMessage}>Add Friend</button>
            </div>
        </>
    )

}