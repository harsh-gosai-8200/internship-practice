import { useState, useContext } from "react";
import BooksContaxt from "../context/books";
import BookEdit from "./BookEdit";


function BookShow({book}){
    
    const [showEdit, setShowEdit] = useState(false);
    const{deletBookById} = useContext(BooksContaxt);

    const handelEditClick = () =>{
        setShowEdit(!showEdit);
    };

    const handelSubmit = () => {
        setShowEdit(false);
    }

    let content = <h3>{book.title}</h3>;
    if(showEdit){
        content = <BookEdit onSubmit={handelSubmit} book={book}/>;
    }

    const handleDeleteClick = () =>{
        deletBookById(book.id);
    };

    return <div className="book-show">
        <img src={`https://picsum.photos/seed/${book.id}/300/200`} alt="books" />
        <div>{content}</div>
        <div className="actions">
            <button className="edit" onClick={handelEditClick}>
                Edit
            </button>
            <button className="delete" onClick={handleDeleteClick}></button>
        </div>
        </div>
}

export default BookShow;