import BookShow from "./BookShow";
import UseBooksContext from "../hooks/use-books-contaxt";


function BookList() {
    // const{books} = useContext(BooksContext);
    const{books} = UseBooksContext();

    const renderedBooks = books.map((book) => {
        return <BookShow key={book.id} book={book} />
    })

    return <div className="book-list">

        {renderedBooks}
    </div>
}

export default BookList;