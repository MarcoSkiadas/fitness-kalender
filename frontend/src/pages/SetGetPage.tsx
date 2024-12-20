import {User} from "../components/FiKaSchema.ts";
import {useState} from "react";
import { Collapse } from "react-collapse";


type SetGetPageProps = {
    user: User | undefined | null
}

export default function SetGetPage(props: Readonly<SetGetPageProps>) {

    const userSets= props.user?.sets
    const [expandedStates, setExpandedStates] = useState<Record<number, boolean>>({});



    const toggleSet = (index: number) => {
        setExpandedStates((prevStates) => ({
            ...prevStates,
            [index]: !prevStates[index], // Zustand des aktuellen Sets togglen
        }));
    };

    return (
        <>
            {userSets?.map((SetGet, setIndex) => {
                return (
                    <div key={setIndex} className="set-container">
                        <p className="set-name">Set Name: {SetGet.name}</p>
                        <button
                            onClick={() => toggleSet(setIndex)}
                            className="toggle-button"
                        >
                            {expandedStates[setIndex] ? "Hide Details" : "Show Details"}
                        </button>
                        <Collapse isOpened={expandedStates[setIndex]}>
                            {SetGet.exercise.map((exercise, exerciseIndex) => (
                                <div key={exerciseIndex} className="exercise">
                                    <p className="exercise-name">Exercise Name: {exercise.exerciseName}</p>
                                    <p className="exercise-sets">Exercise Sets: {exercise.defaultSets}</p>
                                    <p className="exercise-reps">Exercise Repetitions: {exercise.defaultRepetitions}</p>
                                </div>
                            ))}
                        </Collapse>
                    </div>
                );
            })}
        </>
    );
}