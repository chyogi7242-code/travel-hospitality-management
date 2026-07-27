import { BrowserRouter, Routes, Route } from "react-router-dom";

import BookingForm from "./components/BookingForm";
import MyReservations from "./components/MyReservations";

// These pages/components will be created in the next steps
import ReservationDetails from "./components/ReservationDetails";
import NotFound from "./components/NotFound";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<BookingForm />} />
        <Route path="/reservations" element={<MyReservations />} />
        <Route
          path="/reservations/:id"
          element={<ReservationDetails />}
        />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;