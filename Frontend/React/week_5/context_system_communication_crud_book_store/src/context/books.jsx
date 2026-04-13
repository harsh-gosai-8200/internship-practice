import { createContext, useState } from "react";
import axios from "axios";

const BooksContaxt = createContext();

function Provider({ children }) {
    const [books, setBooks] = useState([]);

    const fetchBooks = async () => {
        const response = await axios.get('http://localhost:3001/books');
        setBooks(response.data);
    }

    const editBookById = async (id, newTitle) => {
        const response = await axios.put(`http://localhost:3001/books/${id}`, { title: newTitle });
        const updatedBooks = books.map((book) => {
            if (book.id === id) {
                return { ...book, ...response.data };
            }
            return book;
        })
        setBooks(updatedBooks);
    }

    const deletBookById = async (id) => {
        await axios.delete(`http://localhost:3001/books/${id}`);
        const updatedBooks = books.filter((book) => {
            return book.id !== id;
        });
        setBooks(updatedBooks);
    }

    const createBook = async (title) => {
        const responce = await axios.post("http://localhost:3001/books", { title: title })
        const updatedBooks = [
            ...books,
            responce.data
        ];
        setBooks(updatedBooks);
    };

    const valueToShare = {
        books,
        deletBookById,
        editBookById,
        createBook,
        fetchBooks
    }

    return <BooksContaxt.Provider value={valueToShare}>
        {children}
    </BooksContaxt.Provider>
}

export { Provider };
export default BooksContaxt;