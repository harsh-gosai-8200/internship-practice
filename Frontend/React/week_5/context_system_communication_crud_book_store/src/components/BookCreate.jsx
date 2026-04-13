import { useState, useContext } from "react";
import BooksContaxt from "../context/books";

function BookCreate(){
    const [title, setTitle] = useState("");
    const{createBook} = useContext(BooksContaxt);

    const handleChange = (event) => {
        setTitle(event.target.value);
    };

    const handelSubmit = (event) =>{
        event.preventDefault();
        createBook(title);
        setTitle('');
    }

    return (
    <div className="book-create">
        <h3>Add a Book</h3>
        <form onSubmit={handelSubmit}>
            <label >Title</label>
            <input className="input" value={title} onChange={handleChange} />
            <button className="button">Create!</button>
        </form>
    </div>
    )
}

export default BookCreate;