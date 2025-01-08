import {User, WorkoutSession} from "../components/FiKaSchema.ts";
import {useEffect, useState} from "react";
import axios from "axios";

type FriendsSeePageProps = {
    user: User | null | undefined
}

export default function FriendsGetPage(props: Readonly<FriendsSeePageProps>) {

    const [workouts, setWorkouts] = useState<Record<string, any>>({});
    const [loading, setLoading] = useState<boolean>(false);

    const fetchFriendsWorkout = async () => {
        if (!props.user?.friends) return;

        setLoading(true);

        try {
            const workoutsData: Record<string, any> = {};

            // Parallel alle Workouts abholen
            const requests = props.user.friends.map((friend) =>
                axios
                    .get(`/api/workoutSession/user/${friend.id}`)
                    .then((response) => {
                        workoutsData[friend.id] = response.data;
                    })
                    .catch((error) => {
                        workoutsData[friend.id] = {error: 'Workout not found'};
                        console.error(`Failed to fetch workout for ${friend.id}:`, error);
                    })
            );

            await Promise.all(requests);
            setWorkouts(workoutsData);
        } catch (error) {
            console.error('Failed to fetch workouts:', error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchFriendsWorkout();
    }, [props.user?.friends]);

    return(
        <>
            {props.user?.friends ? (
                <div>
                    <p>Friends List:</p>
                    {props.user.friends.map((friend) => (
                        <div key={friend.id} className="set-container">
                            <h3 className="friend-username">{friend.username}</h3>
                            {loading && <p>Loading workout...</p>}
                            {!loading && workouts[friend.id] && !workouts[friend.id].error && (
                                <div>
                                    <p>Workout:</p>
                                    {Array.isArray(workouts[friend.id]) && workouts[friend.id].map((session:WorkoutSession) => (
                                        <div key={session.id} className="workout-session">
                                            <p>
                                                <strong>Date:</strong>{' '}
                                                {new Date(session.workoutDate).toLocaleDateString()}
                                            </p>
                                            <div className="exercises-container">
                                                {session.workoutExercise.map((exercise, idx) => (
                                                    <div key={idx} className="exercise">
                                                        <p className="exercise-name">{exercise.exerciseName}</p>
                                                        <p className="exercise-sets">
                                                            Sets: {exercise.sets}
                                                        </p>
                                                        <p className="exercise-reps">
                                                            Reps: {exercise.repetitions}, Weight: {exercise.weight} kg
                                                        </p>
                                                    </div>
                                                ))}
                                            </div>
                                        </div>
                                    ))}
                                </div>
                            )}
                            {!loading && workouts[friend.id]?.error && (
                                <p>{workouts[friend.id].error}</p>
                            )}
                        </div>
                    ))}
                </div>
            ) : (
                <p>No Friends available!</p>
            )}
        </>
    )

}