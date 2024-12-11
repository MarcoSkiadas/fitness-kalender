import {User, WorkoutExercise} from "../components/FiKaSchema.ts";
import {useEffect, useState} from "react";

type WorkoutPageProps = {
    user: User | null | undefined
}

export default function WorkoutPage(props: Readonly<WorkoutPageProps>) {

    const [selectedSetId, setSelectedSetId] = useState("")
    const availableSets = props.user?.sets ?? [];
    const selectedSet = availableSets.find((set) => set.id === selectedSetId);
    const [exercises, setExercises] = useState<WorkoutExercise[]>([]);

    useEffect(() => {
        if (selectedSet) {
            setExercises(selectedSet.exercise || []);
        }
    }, [selectedSet]);

    const handleInputChange = (
        index: number,
        field: keyof WorkoutExercise, // `field` ist ein Schlüssel aus dem Typ `Exercise`
        value: string
    ) => {
        const updatedExercises = [...exercises];
        updatedExercises[index][field] = value;
        setExercises(updatedExercises);
    };

    return(
        <>
        <form>
            <p>Please select your Set:</p>
            <select onChange={event => setSelectedSetId(event.target.value)} defaultValue="">
                {availableSets && availableSets.length > 0 ? (
                    availableSets.map((set) => (
                        <option key={set.id} value={set.id}>
                            {set.name}
                        </option>
                    ))
                ) : (
                    <option disabled>No sets available</option>
                )}
            </select>
        </form>
            {selectedSet ? (
                <div>
                    <h2>{selectedSet.name}</h2>
                    <p>{selectedSet.userId}</p>
                    {selectedSet.exercise && selectedSet.exercise.length > 0 ? (
                        selectedSet.exercise.map((exercise, index) => (
                            <div key={index} className="exercise-form">
                                <p>{`Exercise number:${index + 1}`}</p>
                                <label htmlFor={`exerciseName-${index}`}>Exercise name: </label>
                                <input
                                    value={exercise.exerciseName}
                                    placeholder="Please enter the Name of Exercise"
                                    type="text"
                                    onChange={(e) =>
                                        handleInputChange(index, "exerciseName", e.target.value)
                                    }
                                    className="login-input"
                                    id={`exerciseName-${index}`}
                                />
                                <p></p>
                                <label htmlFor={`defaultSets-${index}`}>Exercise sets: </label>
                                <input
                                    value={exercise.defaultSets}
                                    type="number"
                                    id={`defaultSets-${index}`}
                                    placeholder="Please enter number of Sets"
                                    onChange={(e) =>
                                        handleInputChange(index, "defaultSets", e.target.value)
                                    }
                                />
                                <p></p>
                                <label htmlFor={`defaultRepetitions-${index}`}>Exercise repetition: </label>
                                <input
                                    value={exercise.defaultRepetitions}
                                    type="number"
                                    id={`defaultRepetitions-${index}`}
                                    placeholder="Please enter number of Repetition"
                                    onChange={(e) =>
                                        handleInputChange(index, "defaultRepetitions", e.target.value)
                                    }
                                />
                                <p></p>
                            </div>
                        ))
                    ) : (
                        <p>No exercises found in this set.</p>
                    )}
                </div>
            ) : (
                <p>Please select a Set to see details</p>
            )}
        </>
    );
}