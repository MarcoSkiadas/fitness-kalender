import {User} from "../components/FiKaSchema.ts";

type FriendsSeePageProps = {
    user: User | null | undefined
}

export default function FriendsGetPage(props: Readonly<FriendsSeePageProps>) {


    return(
        <>
            {props.user?.friends !== undefined && (
                <div>
                    <p>Friends List:</p>
                    {props.user?.friends.map((friend, index) => (
                        <div key={index}>
                            <li>{friend.username}</li>
                        </div>
                    ))}
                </div>
            )}
            {props.user?.friends == undefined && (
                <>
                    <p>No Friends available!</p>
                    </>)}

        </>
    )

}