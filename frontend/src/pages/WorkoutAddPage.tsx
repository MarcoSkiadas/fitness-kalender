import {User, Exercise, WorkoutExercise} from "../components/FiKaSchema.ts";
import {useEffect, useState} from "react";
import axios from "axios";
import {toast} from "react-toastify";

type WorkoutAddPageProps = {
    user: User | null | undefined
}

export default function WorkoutAddPage(props: Readonly<WorkoutAddPageProps>) {

    const [selectedSetId, setSelectedSetId] = useState("")
    const availableSets = props.user?.sets ?? [];
    const selectedSet = availableSets.find((set) => set.id === selectedSetId);
    const [exercises, setExercises] = useState<Exercise[]>([]);
    const [workoutExercises, setWorkoutExercises] = useState<WorkoutExercise[]>([]);

    useEffect(() => {
        if (selectedSet) {
            setExercises(selectedSet.exercise || []);
        }
    }, [selectedSet]);

    const handleInputChange = (
        index: number,
        field: keyof Exercise | `weight`, // `field` ist ein Schlüssel aus dem Typ `Exercise`
        value: string
    ) => {
        // Update Exercises
        const updatedExercises = [...exercises];
        if (field in updatedExercises[index]) {
            updatedExercises[index][field as keyof Exercise] = value;
        }
        setExercises(updatedExercises);

        // Update WorkoutExercises
        const updatedWorkoutExercises = [...workoutExercises];
        if (!updatedWorkoutExercises[index]) {
            // Falls noch nicht vorhanden, initialisieren
            updatedWorkoutExercises[index] = {
                exerciseName: '',
                sets: 0,
                repetitions: 0,
                weight: 0,
            };
        }

        // WorkoutExercise aktualisieren
        const exercise = updatedExercises[index];
        updatedWorkoutExercises[index] = {
            ...updatedWorkoutExercises[index],
            exerciseName: exercise.exerciseName,
            sets: parseInt(exercise.defaultSets || '0', 10),
            repetitions: parseInt(exercise.defaultRepetitions || '0', 10),
            weight: field === 'weight' ? parseFloat(value) : updatedWorkoutExercises[index].weight,
        };

        setWorkoutExercises(updatedWorkoutExercises);


    };

    const handleUpdateSet = () => {
        axios.put(`/api/set/${props.user?.id}/${selectedSet?.id}`, {
            id: selectedSet?.id,
            userId: props.user?.id,
            name: selectedSet?.name,
            exercise: exercises,
            createdAt: "",
            updatedAt: ""
        })
            .then(r => console.log(r.data))
            .then(() => toast.success(`Workout ${selectedSet?.name} has been saved`))
            .catch(() => toast.error(`Workout ${selectedSet?.name} has not been saved`))
    }
    const handleSaveWorkout = () => {
        axios.post(`/api/workoutSession`, {
            id:"",
            userId: props.user?.id,
            workoutDate: Date.now(),
            workoutExercise:  workoutExercises,
            createdAt: ""
        })
            .then(r => console.log(r.data))
            .then(() => toast.success(`Workout ${selectedSet?.name} has been saved`))
            .catch(() => toast.error(`Workout ${selectedSet?.name} has not been saved`))
    }


    return(
        <>
            <form>
                <p>Please select your Set:</p>
                <select onChange={event => setSelectedSetId(event.target.value)} defaultValue="">
                    <option>--Please Select--</option>
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
                                <label htmlFor={`weight-${index}`}>Exercise weight: </label>
                                <input
                                    value={workoutExercises[index]?.weight}
                                    type="number"
                                    id={`weight-${index}`}
                                    placeholder="Please enter number of weight"
                                    onChange={(e) =>
                                        handleInputChange(index, "weight", e.target.value)
                                    }
                                />
                            </div>
                        ))
                    ) : (
                        <p>No exercises found in this set.</p>
                    )}
                </div>
            ) : (
                <p>Please select a Set to see details</p>
            )}
            <button onClick={handleSaveWorkout}>Save Workout</button>
            <button onClick={handleUpdateSet}>Update Set</button>
        </>
    );
}