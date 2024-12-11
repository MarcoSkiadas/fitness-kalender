export interface User {
    id: string,
    username: string,
    role: string,
    createDate: Date,
    sets: Set[]
}
export interface Set {
    id: string,
    userId: string,
    name: string,
    exercise: WorkoutExercise[],
    createAt: Date,
    updatedAt: Date
}
export interface WorkoutExercise {
    exerciseName: string,
    defaultSets: string,
    defaultRepetitions: string,
}
export interface WorkoutSession {
    id: string,
    userId: string,
    workoutDate: Date,
    exercise: WorkoutExercise,
    createAt: Date,
}

