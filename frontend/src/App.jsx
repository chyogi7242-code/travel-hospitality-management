import { BrowserRouter, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";
import BookingForm from "./components/BookingForm";
import MyReservations from "./components/MyReservations";
import ReservationDetails from "./components/ReservationDetails";
import AdminReservations from "./components/AdminReservations";
import NotFound from "./components/NotFound";

function App() {
  return (
    <BrowserRouter>

      <Navbar />

      <Routes>

        <Route
          path="/"
          element={<BookingForm />}
        />

        <Route
          path="/reservations"
          element={<MyReservations />}
        />

        <Route
          path="/reservations/:id"
          element={<ReservationDetails />}
        />

        <Route
          path="/admin"
          element={<AdminReservations />}
        />

        <Route
          path="*"
          element={<NotFound />}
        />

      </Routes>

    </BrowserRouter>
  );
}

export default App;