import {User} from "../components/FiKaSchema.ts";

type SetGetPageProps = {
    user: User | undefined | null
}

export default function SetGetPage(props: Readonly<SetGetPageProps>) {

    const userSets= props.user?.sets

    return(
        <>
        {userSets?.map(((SetGet,setIndex)=> (
            <div key={setIndex} className="set-container">
                <p className="set-name">
                    Set Name: {Set.name}</p>
                <p>{SetGet.exercise.map((exercise, exerciseIndex) => (
                    <div key={exerciseIndex} className="exercise">
                        <p className="exercise-name">Exercise Name: {exercise.exerciseName}</p>
                        <p className="exercise-sets">Exercise Sets: {exercise.defaultSets}</p>
                        <p className="exercise-reps">Exercise Repetitions: {exercise.defaultRepetitions}</p>
                    </div>
                ))}</p>
            </div>
        )))}
        </>
    )
}