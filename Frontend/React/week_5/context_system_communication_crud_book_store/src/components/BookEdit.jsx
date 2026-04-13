import { useState, useContext } from "react";
import BooksContaxt from "../context/books";


function BookEdit({book, onSubmit}){
    const [title, setTitle] = useState(book.title);
    const{editBookById} = useContext(BooksContaxt);

    const handleChange = (event) => {
        setTitle(event.target.value);
    };

    const handelSubmit = (event) =>{
        event.preventDefault();
        onSubmit();
        editBookById(book.id, title)
    };

    return (
        <form className="book-edit" onSubmit={handelSubmit}>
            <label>Title</label>
            <input className="input" value={title} onChange={handleChange} />
            <button className="button is-primary">Save</button>
        </form>
    )
}

export default BookEdit;