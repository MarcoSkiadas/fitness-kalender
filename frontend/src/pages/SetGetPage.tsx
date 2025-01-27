import {User, Set} from "../components/FiKaSchema.ts";
import {useState} from "react";
import { Collapse } from "react-collapse";
import axios from "axios";
import {toast} from "react-toastify";
import Swal from "sweetalert2";


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

    const handleDelete = (SetGet:Set) => {
        axios.post(`/api/set/delete/${SetGet.id}/${props.user?.id}`)
            .then(() => toast.success(`WorkoutSession has been deleted`))
            .catch((r) => toast.error(r.data))
    }

    const handleDeleteWithConfirmation = (SetGet:Set) => {
        Swal.fire({
            title: 'Are you sure?',
            text: 'This set will be deleted permanently!',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            confirmButtonText: 'Yes, delete!',
            cancelButtonText: 'Cancel',
        }).then((result) => {
            if (result.isConfirmed) {
                handleDelete(SetGet);
                Swal.fire('Deleted!', 'this set has been deleted.', 'success');
            }
        });
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
                        <button onClick={() => handleDeleteWithConfirmation(SetGet)}>Delete Set</button>

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