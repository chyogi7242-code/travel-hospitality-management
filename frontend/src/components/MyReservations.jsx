import { useEffect, useState } from "react";
import { getUserReservations, cancelReservation } from "../api/reservationApi";


function MyReservations() {

    const [reservations, setReservations] = useState([]);

    const userId = 1;


    useEffect(() => {

        fetchReservations();

    }, []);



    const fetchReservations = async () => {

        try {

            const response = await getUserReservations(userId);

            setReservations(response.content || []);

        } catch (error) {

            console.log(error);

        }

    };



    const handleCancel = async (reservationId) => {

        try {

            await cancelReservation(reservationId);

            alert("Reservation cancelled successfully");

            fetchReservations();

        } catch(error) {

            console.log(error);

            alert("Cancel failed");

        }

    };



    return (

        <div>

            <h1>My Reservations</h1>


            {
                reservations.map((reservation) => (

                    <div key={reservation.reservationId}>

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


                        {
                            reservation.status !== "CANCELLED" &&
                            <button
                                onClick={() =>
                                    handleCancel(reservation.reservationId)
                                }
                            >
                                Cancel Reservation
                            </button>
                        }


                        <hr />

                    </div>

                ))
            }


        </div>

    );

}


export default MyReservations;