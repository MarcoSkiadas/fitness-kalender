import {User} from "../components/FiKaSchema.ts";
import {useState} from "react";
import { useCollapse } from 'react-collapsed'


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
                const isExpanded = expandedStates[setIndex] || false;
                // eslint-disable-next-line react-hooks/rules-of-hooks
                const { getCollapseProps, getToggleProps } = useCollapse({ isExpanded });

                return (
                    <div key={setIndex} className="set-container">
                        <p className="set-name">Set Name: {SetGet.name}</p>
                        <button
                            {...getToggleProps({
                                onClick: () => toggleSet(setIndex),
                            })}
                        >
                            {isExpanded ? 'Collapse' : 'Expand'}
                        </button>
                        <section {...getCollapseProps()}>
                            {SetGet.exercise.map((exercise, exerciseIndex) => (
                                <div key={exerciseIndex} className="exercise">
                                    <p className="exercise-name">Exercise Name: {exercise.exerciseName}</p>
                                    <p className="exercise-sets">Exercise Sets: {exercise.defaultSets}</p>
                                    <p className="exercise-reps">Exercise Repetitions: {exercise.defaultRepetitions}</p>
                                </div>
                            ))}
                        </section>
                    </div>
                );
            })}
        </>
    );
}