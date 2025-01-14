import {MessageType, User} from "../components/FiKaSchema.ts";
import {useState} from "react";
import axios from "axios";
import {toast} from "react-toastify";

type MessengerAddPageProps = {
    user: User | undefined | null
}

export default function MessengerAddPage(props: Readonly<MessengerAddPageProps>) {

    const [friendId, setFriendId] = useState("");
    const [messageContent, setMessageContent] = useState("");

    const handleSendMessage = () => {
        if (friendId === "") {
            toast.error("Please select a friend before sending a message!");
            return;
        }
        axios.post(`/api/message`, {
            id: "",
            recipientId: friendId,
            senderId: props.user?.id,
            messageContent: messageContent,
            accepted: false,
            read: false,
            messageType: MessageType.MESSAGE,
            createdAt: null
        })
            .then(r => console.log(r.data))
            .then(() => toast.success(`Message has been sent to your Friend!`))
            .catch(error => toast.error(error.response.data.errorMsg))
    }

    return(
        <>
            <div className="exercise-form">
                <form>
                    <label htmlFor={`friendId`}>Insert Friend-Id: </label>
                    <select
                        value={friendId}
                        onChange={(e) => setFriendId(e.target.value)}
                        className="login-input"
                        id={`friendId`}
                    >
                        <option value="" disabled>
                            Select a friend
                        </option>
                        {props.user?.friends.map((friend) => (
                            <option key={friend.id} value={friend.id}>
                                {friend.username}
                            </option>
                        ))}
                    </select>
                    <p></p>
                    <label htmlFor={`messageContent`}>Insert Message: </label>
                    <p></p>
                    <div className="input-wrapper">
                    <textarea
                        value={messageContent}
                        placeholder="Please enter the Message to your Friend"
                        onChange={(e) => setMessageContent(e.target.value)}
                        className="message-input"
                        id="messageContent"
                        rows={4} /* Anzahl der sichtbaren Zeilen */
                    ></textarea>
                    </div>
                </form>
                <button onClick={handleSendMessage}>Send Message</button>
            </div>
        </>
    )


}