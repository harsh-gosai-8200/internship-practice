import { configureStore } from "@reduxjs/toolkit";
import {
    carsReducer,
    addCar,
    removeCar, 
    changeSearchTerm
} from './slices/carsSlice';
import {
    formSlice,
    changeName,
    changeCost,
    formReducer
} from './slices/formSlice';



const store = configureStore({
    reducer:{
        cars: carsReducer,
        form: formReducer
    }
});


export{
    store,
    changeName,
    changeCost,
    addCar,
    removeCar,
    changeSearchTerm
};

