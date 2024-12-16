import React, {useState} from "react";
import {Exercise} from "../components/FiKaSchema.ts";
import {toast} from "react-toastify";
import axios from "axios";

type SetPageProps = {
    user: string | undefined | null
}

export default function SetPage(props: Readonly<SetPageProps>) {

    const [exercises, setExercises] = useState([
        { exerciseName: "", defaultSets: "", defaultRepetitions: "" }]);
    const [setName, setSetName] = useState("");


    const addExercise = (e: React.MouseEvent<HTMLButtonElement>) => {
        e.preventDefault(); // Verhindert das Standard-Submit-Verhalten
        setExercises([
            ...exercises,
            { exerciseName: "", defaultSets: "", defaultRepetitions: "" }
        ]);
        toast.success("Exercise has been added");
    };
    const removeLastExercise = (e: React.MouseEvent<HTMLButtonElement>) => {
        e.preventDefault();
        if (exercises.length > 1) {
            setExercises(exercises.slice(0, -1));
            toast.success("Exercise has been removed");
        }
        else {
            toast.error("You can´t remove more exercises");
        }
    };

    const handleInputChange = (
        index: number,
        field: keyof Exercise, // `field` ist ein Schlüssel aus dem Typ `Exercise`
        value: string
    ) => {
        const updatedExercises = [...exercises];
        updatedExercises[index][field] = value;
        setExercises(updatedExercises);
    };

    const handleSaveSet = () => {
        axios.post(`/api/set`, {
            id:"",
            userId: props.user,
            name: setName,
            exercise: exercises,
            createdAt: "",
            updatedAt: ""
        })
            .then(r => console.log(r.data))
            .then(() => toast.success(`Set ${setName} has been registered`))
            .catch(() => toast.error(`Set ${setName} has not been registered`))
    }

    return(
        <>
            <div>
                <p>Set</p>
                <div>
                    <form>
                        <label htmlFor={`setName`}>Set name: </label>
                        <input
                            value={setName}
                            placeholder="Please enter the Name of Set"
                            type="text"
                            onChange={(e) =>
                                setSetName(e.target.value)}
                            className="login-input"
                            id={`setName`}
                        />
                        {exercises.map((exercise, index) => (
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
                        ))}
                        <button onClick={addExercise}>Add another Exercise</button>
                        <button onClick={removeLastExercise}>Delete last Exercise</button>
                    </form>
                    <button onClick={handleSaveSet}>Save Set</button>

                </div>
            </div>
        </>
    )
}