import {User, WorkoutSession} from "../components/FiKaSchema.ts";
import {useEffect, useState} from "react";
import axios from "axios";
import {toast} from "react-toastify";
import {useParams} from "react-router-dom";


type WorkoutGetPageProps = {
    user: User | null | undefined
}

export default function WorkoutGetPage(props: Readonly<WorkoutGetPageProps>) {

    const [workoutSessions, setWorkoutSessions] = useState<WorkoutSession[]>([])
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

    return(<>
        {workoutSessions?.map((workoutSession, workoutSessionIndex) => (
            <div key={workoutSessionIndex} className="set-container">
            <p className="set-name">Workout Date: {formatDate(workoutSession.workoutDate)}</p>

                {workoutSession.workoutExercise.map((workoutExercise,workoutExerciseIndex) => (
                            <div key={workoutExerciseIndex} className="exercise">
                                <p className="exercise-name">Exercise Name: {workoutExercise.exerciseName}</p>
                                <p className="exercise-sets">Exercise Sets: {workoutExercise.sets}</p>
                                <p className="exercise-reps">Exercise Repetitions: {workoutExercise.repetitions}</p>
                                <p className="exercise-reps">Exercise Weight: {workoutExercise.weight}</p>
                            </div>
                        ))}
                        </div>
                ))}
            </>);
        }