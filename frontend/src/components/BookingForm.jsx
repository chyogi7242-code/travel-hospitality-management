import { useState } from "react";
import { createReservation } from "../api/reservationApi";

function BookingForm() {

    const [reservation, setReservation] = useState({
        userId: 1,
        roomId: "",
        checkInDate: "",
        checkOutDate: "",
        numberOfGuests: ""
    });

    const [message, setMessage] = useState("");

    const handleChange = (e) => {
        setReservation({
            ...reservation,
            [e.target.name]: e.target.value
        });
    };


    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            const response = await createReservation(reservation);

            setMessage(
                "Booking successful! Reservation ID: " + response.reservationId
            );

            // Clear form after successful booking
            setReservation({
                userId: 1,
                roomId: "",
                checkInDate: "",
                checkOutDate: "",
                numberOfGuests: ""
            });

        } catch (error) {

            setMessage(
                "Booking failed. Please try again."
            );

            console.log(error.response?.data || error.message);
        }
    };


    return (
        <div>

            <h2>Book Room</h2>

            <form onSubmit={handleSubmit}>

                <input
                    type="number"
                    name="roomId"
                    placeholder="Room ID"
                    value={reservation.roomId}
                    onChange={handleChange}
                    required
                />

                <br />

                <input
                    type="date"
                    name="checkInDate"
                    value={reservation.checkInDate}
                    onChange={handleChange}
                    required
                />

                <br />

                <input
                    type="date"
                    name="checkOutDate"
                    value={reservation.checkOutDate}
                    onChange={handleChange}
                    required
                />

                <br />

                <input
                    type="number"
                    name="numberOfGuests"
                    placeholder="Number of Guests"
                    value={reservation.numberOfGuests}
                    onChange={handleChange}
                    required
                />

                <br />

                <button type="submit">
                    Confirm Booking
                </button>

            </form>

            <p>{message}</p>

        </div>
    );
}

export default BookingForm;