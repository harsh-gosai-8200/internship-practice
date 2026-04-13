import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Root from "./pages/Root";
import HomePage from "./pages/HOME/HonePage";
import SearchPage from "./pages/search/SearchPage";
import DetailsPage from "./pages/details/DetailsPage";
import { searchLoader } from "./pages/search/sarchLoader";
import { detailsLoader } from "./pages/details/detailsLoader";
import { homeLoader } from "./pages/HOME/homeLoader";


const router = createBrowserRouter([
  {
    path: '/',
    element: <Root />,
    children: [
      {
        index: true,
        element: <HomePage />,
        loader: homeLoader,
      },
      {
        path: "/search",
        element: <SearchPage />,
        loader: searchLoader
      },
      {
        path: "/packages/:name",
        element: <DetailsPage />,
        loader: detailsLoader,
      }
    ]
  }
])

function App() {

  return <RouterProvider router={router} />  
}

export default App
