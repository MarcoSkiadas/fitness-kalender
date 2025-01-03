export interface User {
    id: string,
    username: string,
    role: string,
    createDate: Date,
    sets: Set[]
    friends: Friend[]
}
export interface Set {
    id: string,
    userId: string,
    name: string,
    exercise: Exercise[],
    createAt: Date,
    updatedAt: Date
}
export interface Exercise {
    exerciseName: string,
    defaultSets: string,
    defaultRepetitions: string,
}
export interface WorkoutExercise {
    exerciseName: string,
    sets: number,
    repetitions: number,
    weight: number
}

export interface WorkoutSession {
    id: string,
    userId: string,
    workoutDate: Date,
    workoutExercise: WorkoutExercise[],
    createAt: Date,
}
export interface Friend {
    id: string,
    username: string,
}


