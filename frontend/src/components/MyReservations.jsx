import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
    getUserReservations,
    cancelReservation,
} from "../api/reservationApi";

function MyReservations() {

    const [reservations, setReservations] = useState([]);

    const [loading, setLoading] = useState(true);

    const [error, setError] = useState("");

    const navigate = useNavigate();

    const userId = 1;


    useEffect(() => {

        fetchReservations();

    }, []);



    const fetchReservations = async () => {

        try {

            setLoading(true);

            const response = await getUserReservations(userId);

            setReservations(response.content || []);

        } catch (error) {

            console.log(error);

            setError(
                "Unable to load reservations"
            );

        } finally {

            setLoading(false);

        }

    };



    const handleCancel = async (reservationId) => {

        try {

            await cancelReservation(reservationId);

            alert(
                "Reservation cancelled successfully"
            );

            fetchReservations();

        } catch (error) {

            console.log(error);

            alert(
                "Cancel failed"
            );

        }

    };



    return (

        <div>

            <h1>
                My Reservations
            </h1>


            {loading && (
                <p>
                    Loading reservations...
                </p>
            )}


            {error && (
                <p>
                    {error}
                </p>
            )}



            {!loading && reservations.length === 0 && (

                <p>
                    No reservations found.
                </p>

            )}



            {!loading && reservations.map((reservation) => (

                <div
                    key={reservation.reservationId}
                >

                    <h3>
                        Reservation ID: {reservation.reservationId}
                    </h3>


                    <p>
                        Check In: {reservation.checkInDate}
                    </p>


                    <p>
                        Check Out: {reservation.checkOutDate}
                    </p>


                    <p>
                        Guests: {reservation.numberOfGuests}
                    </p>


                    <p>
                        Status: {reservation.status}
                    </p>



                    <button
                        onClick={() =>
                            navigate(
                                `/reservations/${reservation.reservationId}`
                            )
                        }
                    >
                        View Details
                    </button>


                    {" "}



                    {reservation.status !== "CANCELLED" && (

                        <button
                            onClick={() =>
                                handleCancel(
                                    reservation.reservationId
                                )
                            }
                        >
                            Cancel Reservation
                        </button>

                    )}


                    <hr />

                </div>

            ))}


        </div>

    );

}

export default MyReservations;