import { configureStore } from '@reduxjs/toolkit';
import { songsReducer, addSong, removeSong } from './slices/songSlice';
import { moviesReducer, addMovie, removeMovie } from './slices/movieSlice';



const store = configureStore({
    reducer: {
        songs: songsReducer,
        movies: moviesReducer
    }
});

store.dispatch(
    {
        type: "song/addSong",
        payload: "New song!!!",
    },
);


export { store };
export { addSong, removeMovie, removeSong, addMovie};