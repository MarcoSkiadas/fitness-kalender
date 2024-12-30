import {User} from "../components/FiKaSchema.ts";

type FriendsAddPageProps = {
    user: User | null | undefined
}

export default function FriendsAddPage(props: Readonly<FriendsAddPageProps>) {

    return(
        <>
            <p>{props.user?.id}</p>
        </>
    )

}