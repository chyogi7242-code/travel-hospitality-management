import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import CustomerPortal from "./pages/CustomerPortal";


function ProtectedRoute({ children }) {

    const token = localStorage.getItem("token");


    if (!token) {

        return <Navigate to="/login" />;

    }


    return children;

}



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
                    element={
                        <ProtectedRoute>
                            <CustomerPortal />
                        </ProtectedRoute>
                    }
                />


            </Routes>

        </BrowserRouter>

    );

}


export default App;