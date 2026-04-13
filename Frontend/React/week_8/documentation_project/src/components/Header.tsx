import { Link } from "react-router-dom";
import SearchInput from "./SearchInput";


export default function Header(){

    return (
        <div className="flex item-center justify-between px-4 border-b h-14">
            <div className="flex item-center space-x-2 text-sm ">
                 <Link to="/" className="text-lg font-bold">NPM registry</Link>
            </div>
            <div className="w-full max-w-xl ml-4">
                <SearchInput />
            </div>
        </div>
    )
}