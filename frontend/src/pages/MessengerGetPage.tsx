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

    return(
        <>
                {messages?.map((message,messageIndex) => {
                    return (
                        <div key={messageIndex} className="set-container">
                            <p>Type: {message.messageType}</p>
                            <p>From: {message.senderId}</p>
                            <p>To: {message.recipientId}</p>
                            <p>{message.messageContent}</p>
                        </div>
                    )
                })}

        </>
    )

}