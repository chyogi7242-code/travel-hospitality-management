import { useEffect, useState } from "react";
import { getUserReservations } from "../api/reservationApi";

function MyReservations() {

    const [reservations, setReservations] = useState([]);
    const [loading, setLoading] = useState(true);

    const userId = 1;


    useEffect(() => {

        fetchReservations();

    }, []);



    const fetchReservations = async () => {

        try {

            const response = await getUserReservations(userId);

            setReservations(response.content);

        } catch (error) {

            console.log(error);

        } finally {

            setLoading(false);

        }

    };



    if (loading) {

        return <h3>Loading reservations...</h3>;

    }



    return (

        <div>

            <h2>My Reservations</h2>


            {
                reservations.length === 0 ?

                (
                    <p>No reservations found</p>
                )

                :

                (

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
                                Number of Guests: {reservation.numberOfGuests}
                            </p>


                            <p>
                                Status: {reservation.status}
                            </p>


                            <hr />

                        </div>

                    ))

                )

            }


        </div>

    );

}

export default MyReservations;