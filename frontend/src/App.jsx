import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import CustomerPortal from "./pages/CustomerPortal";


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


      </Routes>

    </BrowserRouter>

  );

}


export default App;