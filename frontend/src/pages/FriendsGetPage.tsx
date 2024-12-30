import {User} from "../components/FiKaSchema.ts";

type FriendsSeePageProps = {
    user: User | null | undefined
}

export default function FriendsGetPage(props: Readonly<FriendsSeePageProps>) {

    return(
        <>
            <p>{props.user?.id}</p>
        </>
    )

}