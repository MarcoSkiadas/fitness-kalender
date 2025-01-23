import {Message, User} from "../components/FiKaSchema.ts";
import axios from "axios";
import {toast} from "react-toastify";
import {useEffect, useState} from "react";

type MessengerGetPageProps = {
    user: User | undefined | null
}

export default function MessengerGetPage(props: Readonly<MessengerGetPageProps>) {

    const [messages, setMessages] = useState<Message[]>()

    useEffect(() => {
        FetchMessages()
    }, [])

    const FetchMessages = () => {
        axios.get(`/api/message/${props.user?.id}`)
            .then(r => setMessages(r.data))
            .then(() => toast.success("Messages has been loaded"))
            .catch(() => toast.error("Messages has not been loaded"))
    }

    const handleAccept = (message:Message) => {
        axios.post(`/api/user/friend/${props.user?.id}/${message.senderId}`)
            .then(r => console.log(r.data))
            .then(() => toast.success(`Friend with ID: ${message.senderId} has been registered`))
            .catch(error => toast.error(error.response.data.errorMsg));
        axios.put(`/api/message/${message.id}`)
            .then(r => console.log(r.data))
            .then(() => toast.success(`Message has been Accepted`))
            .catch(error => toast.error(error.response.data.errorMsg));
    };

    const handleDecline = (message:Message) => {
        axios.post(`/api/message/delete/${message.id}`)
            .then(r => console.log(r.data))
            .then(() => toast.success(`Message has been Deleted`))
            .catch(error => toast.error(error.response.data.errorMsg));
    }
    const handleMarkAsRead = async (messageId: string) => {
        try {
            await axios.put(`/api/message/read/${messageId}`);
            toast.success(`Message with ID ${messageId} has been marked as read.`);
        } catch (error) {
            toast.error(`Error while marking the message: ${error}`);
        }
    };



    return(
        <>
                {messages?.filter((message) => !message.accepted) // Nur Nachrichten mit accept === false
                    .map((message,messageIndex) => {
                        function getFriendUsernameById(_user: User | undefined | null, friendId: string): string | null {
                            const friend = props.user?.friends.find(f => f.id === friendId);
                            return friend ? friend.username : null;
                        }
                        const senderUsername = getFriendUsernameById(props.user, message.senderId);
                    return (
                        <div key={messageIndex}
                             onClick={() => handleMarkAsRead(message.id)}// PUT-Request ausführen
                             onKeyDown={() => handleMarkAsRead(message.id)}
                             style={{
                                 cursor: 'pointer',
                                 backgroundColor: message.read ? '#fff' : '#f0f0f0',
                                 border: message.read ? '1px solid #000' : '1px solid #ccc',
                             }}>
                            <p>Type: {message.messageType}</p>
                            {senderUsername ? (
                                <p>From: {senderUsername}</p>
                            ) : (
                                <p>From: {message.senderId}</p>
                            )}
                            <p>To: {props.user?.username}</p>
                            <p>{message.messageContent}</p>
                            {message.messageType === 'REQUEST' && (
                                <div className="button-container">
                                    <button onClick={() => handleAccept(message)}>Accept</button>
                                    <button onClick={() => handleDecline(message)}>Decline</button>
                                </div>
                            )}
                        </div>
                    )
                })}

        </>
    )

}