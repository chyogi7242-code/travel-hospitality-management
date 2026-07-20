import { BrowserRouter, Routes, Route } from "react-router-dom";


import Login from "./pages/Login";
import Register from "./pages/Register";
import CustomerPortal from "./pages/CustomerPortal";
import CreateReservation from "./pages/CreateReservation";



function App() {


    return (


        <BrowserRouter>


            <Routes>



                <Route

                    path="/"

                    element={<Login />}

                />



                <Route

                    path="/login"

                    element={<Login />}

                />



                <Route

                    path="/register"

                    element={<Register />}

                />



                <Route

                    path="/customer"

                    element={<CustomerPortal />}

                />



                <Route

                    path="/reservation/create"

                    element={<CreateReservation />}

                />



            </Routes>


        </BrowserRouter>


    );

}



export default App;