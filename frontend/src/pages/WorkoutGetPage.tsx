import {User, WorkoutSession} from "../components/FiKaSchema.ts";
import {useEffect, useState} from "react";
import axios from "axios";
import {toast} from "react-toastify";
import {useParams} from "react-router-dom";
import { Collapse } from "react-collapse";


type WorkoutGetPageProps = {
    user: User | null | undefined
}

export default function WorkoutGetPage(props: Readonly<WorkoutGetPageProps>) {

    const [workoutSessions, setWorkoutSessions] = useState<WorkoutSession[]>([])
    const [openSessions, setOpenSessions] = useState<Record<number, boolean>>({}); // Track the open state of each session
    const {id} = useParams<{ id: string }>();

    useEffect(() => {

            fetchWorkoutSessions();

    }, [id]);

    const formatDate = (date: string | number | Date) => {
        if (!date) return '';
        return new Date(date).toLocaleDateString(undefined, { day: '2-digit', month: 'short', year: 'numeric' });
    };

    const fetchWorkoutSessions = () => {
        axios.get(`/api/workoutSession/user/${props.user?.id}`)
            .then(r => setWorkoutSessions(r.data))
            .then(() => toast.success(`WorkoutSession has been loaded`))
            .catch((r) => toast.error(r.data))
    }

    const toggleSession = (index: number) => {
        setOpenSessions((prevState) => ({
            ...prevState,
            [index]: !prevState[index],
        }));
    };

    return(<>
        <div>
            {workoutSessions
                ?.slice() // Erstellt eine Kopie, um die Originaldaten nicht zu mutieren
                .sort(
                    (a, b) =>
                        new Date(b.workoutDate).getTime() -
                        new Date(a.workoutDate).getTime()
                )
                .map((workoutSession, workoutSessionIndex) => (
                    <div key={workoutSessionIndex} className="set-container">
                        <button
                            onClick={() => toggleSession(workoutSessionIndex)}
                            className="toggle-button"
                        >
                            {openSessions[workoutSessionIndex] ? "Hide Details" : "Show Details"}
                        </button>
                        <p className="set-name">
                            Workout Date: {formatDate(workoutSession.workoutDate)}
                        </p>

                        <Collapse isOpened={openSessions[workoutSessionIndex]}>
                            <div className="exercise-list">
                                {workoutSession.workoutExercise.map(
                                    (workoutExercise, workoutExerciseIndex) => (
                                        <div key={workoutExerciseIndex} className="exercise">
                                            <p className="exercise-name">
                                                Exercise Name: {workoutExercise.exerciseName}
                                            </p>
                                            <p className="exercise-sets">
                                                Exercise Sets: {workoutExercise.sets}
                                            </p>
                                            <p className="exercise-reps">
                                                Exercise Repetitions: {workoutExercise.repetitions}
                                            </p>
                                            <p className="exercise-weight">
                                                Exercise Weight: {workoutExercise.weight}
                                            </p>
                                        </div>
                                    )
                                )}
                            </div>
                        </Collapse>
                    </div>
                ))}
        </div>
    </>);
}