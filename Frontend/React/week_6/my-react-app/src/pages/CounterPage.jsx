import { useReducer, useState } from "react";
import Button from "../components/Button";
import Panel from "../components/Panel";
import { produce } from "immer";



const ADD_VALUE_TO_COUNT = 'add-value-to-count'

const reducer = (state, action) => {

    switch (action.type) {
        case 'increment':
            // return {
            //     ...state,
            //     count: state.count + 1
            // };
            //use of immer
            state.count = state.count + 1;
            return;

        case 'decrement':
            // return {
            //     ...state,
            //     count: state.count - 1
            // };
            state.count = state.count - 1;
            return;

        case 'change-value-to-add':
            return {
                ...state,
                valueToAdd: action.payload,
            };

        case ADD_VALUE_TO_COUNT:
            return {
                ...state,
                count: state.count + state.valueToAdd,
                valueToAdd: 0
            }

        default: 
            return state;
    }

    // if(action.type === 'increment'){
    //     return {
    //         ...state,
    //         count: state.count + 1
    //     };
    // }

    // if(action.type === 'decrement'){
    //     return {
    //         ...state,
    //         count: state.count - 1
    //     };
    // }

    // if(action.type === 'change-value-to-add'){
    //     return {
    //         ...state,
    //         valueToAdd: action.payload,
    //     }; 
    // }


    // return state;
};

function CounterPage({ initialCounter }) {
    // const [count, setCount] = useState(initialCounter);
    // const [valueToAdd, setValueToAdd] = useState(0);
    const [state, dispatch] = useReducer(produce(reducer), {
        count: initialCounter,
        valueToAdd: 0,
    });

    const increament = () => {
        // setCount(count+1);
        dispatch({ type: 'increment' });
    }

    const decrement = () => {
        // setCount(count-1);
        dispatch({
            type: 'decrement',
        })
    }

    const handleChange = (event) => {
        const value = parseInt(event.target.value) || 0;
        // setValueToAdd(value);
        dispatch({
            type: 'change-value-to-add',
            payload: value
        });
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        // setCount(count+valueToAdd);
        // setValueToAdd(0);
        dispatch({
            type: ADD_VALUE_TO_COUNT,
        })
    }



    return (
        <Panel className="m-3">
            <h1 className="text-lg">Count is {state.count}</h1>
            <div className="flex flec-row">
                <Button onClick={increament}>Increament</Button>
                <Button onClick={decrement}>Decrement</Button>
            </div>

            <form onSubmit={handleSubmit}>
                <label>Add a lot!</label>
                <input value={state.valueToAdd || ''} onChange={handleChange} type="number" className="p-1 m-3 bg-gray-50 border border-gray-300" />
                <Button>Add it!</Button>
            </form>
        </Panel>
    );
}

export default CounterPage;