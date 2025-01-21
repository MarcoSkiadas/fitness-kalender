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


    return(
        <>
                {messages?.filter((message) => !message.accepted) // Nur Nachrichten mit accept === false
                    .map((message,messageIndex) => {
                    return (
                        <div key={messageIndex} className="set-container">
                            <p>Type: {message.messageType}</p>
                            <p>From: {message.senderId}</p>
                            <p>To: {message.recipientId}</p>
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