import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getReservationById } from "../api/reservationApi";

function ReservationDetails() {
    const { id } = useParams();
    const navigate = useNavigate();

    const [reservation, setReservation] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadReservation();
    }, [id]);

    const loadReservation = async () => {
        try {
            const response = await getReservationById(id);
            setReservation(response);
        } catch (error) {
            console.log(error);
        } finally {
            setLoading(false);
        }
    };

    if (loading) {
        return <h2>Loading...</h2>;
    }

    if (!reservation) {
        return <h2>Reservation not found.</h2>;
    }

    return (
        <div>
            <h1>Reservation Details</h1>

            <p><strong>Reservation ID:</strong> {reservation.reservationId}</p>
            <p><strong>User ID:</strong> {reservation.userId}</p>
            <p><strong>Room ID:</strong> {reservation.roomId}</p>
            <p><strong>Check In:</strong> {reservation.checkInDate}</p>
            <p><strong>Check Out:</strong> {reservation.checkOutDate}</p>
            <p><strong>Guests:</strong> {reservation.numberOfGuests}</p>
            <p><strong>Status:</strong> {reservation.status}</p>

            <button onClick={() => navigate("/reservations")}>
                Back to My Reservations
            </button>
        </div>
    );
}

export default ReservationDetails;