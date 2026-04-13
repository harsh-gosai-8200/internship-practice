import { useContext } from "react";
import BooksContaxt from "../context/books";

function UseBooksContext() {
    return useContext(BooksContaxt);
}

export default UseBooksContext;