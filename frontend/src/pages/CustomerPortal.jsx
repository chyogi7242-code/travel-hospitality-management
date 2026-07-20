import { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { getReservations, cancelReservation } from "../services/api";


function CustomerPortal() {


    const navigate = useNavigate();

    const location = useLocation();


    const [reservations, setReservations] = useState([]);

    const [page, setPage] = useState(0);

    const [size, setSize] = useState(5);

    const [sortBy, setSortBy] = useState("id");

    const [totalPages, setTotalPages] = useState(0);





    useEffect(() => {

        loadReservations();

    }, [page, size, sortBy, location]);






    const loadReservations = () => {


        getReservations(page, size, sortBy)

            .then((response) => {


                console.log(
                    "RESERVATIONS:",
                    response.data
                );


                setReservations(
                    response.data.content || []
                );


                setTotalPages(
                    response.data.totalPages || 0
                );


            })


            .catch((error) => {


                console.log(
                    "ERROR:",
                    error
                );


            });


    };






    const handleCancel = (id) => {


        const confirmCancel = window.confirm(
            "Are you sure you want to cancel this reservation?"
        );



        if (!confirmCancel) {

            return;

        }





        cancelReservation(id)

            .then(() => {


                alert(
                    "Reservation cancelled"
                );


                loadReservations();


            })


            .catch((error) => {


                console.log(
                    "Cancel Error:",
                    error
                );


                alert(
                    "Failed to cancel reservation"
                );


            });


    };







    return (

        <div>


            <h1>
                Welcome to Customer Portal
            </h1>




            <button
                onClick={() =>
                    navigate("/reservation/create")
                }
            >
                Book Reservation
            </button>





            <h2>
                My Reservations
            </h2>






            <div>


                <label>
                    Sort By:
                </label>



                <select

                    value={sortBy}

                    onChange={(e) => {

                        setSortBy(e.target.value);

                        setPage(0);

                    }}

                >


                    <option value="id">
                        ID
                    </option>


                    <option value="checkInDate">
                        Check In Date
                    </option>


                    <option value="status">
                        Status
                    </option>


                </select>






                <label>
                    Page Size:
                </label>




                <select

                    value={size}

                    onChange={(e) => {

                        setSize(Number(e.target.value));

                        setPage(0);

                    }}

                >


                    <option value="5">
                        5
                    </option>


                    <option value="10">
                        10
                    </option>


                    <option value="20">
                        20
                    </option>


                </select>


            </div>







            <p>
                Page {page + 1} of {totalPages}
            </p>







            <table border="1">


                <thead>

                    <tr>

                        <th>ID</th>

                        <th>Check In</th>

                        <th>Check Out</th>

                        <th>Guests</th>

                        <th>Status</th>

                        <th>Action</th>


                    </tr>

                </thead>






                <tbody>


                    {reservations.length === 0 ? (


                        <tr>

                            <td colSpan="6">
                                No reservations found
                            </td>

                        </tr>


                    ) : (



                        reservations.map((reservation) => (


                            <tr key={reservation.reservationId}>


                                <td>
                                    {reservation.reservationId}
                                </td>



                                <td>
                                    {reservation.checkInDate}
                                </td>



                                <td>
                                    {reservation.checkOutDate}
                                </td>



                                <td>
                                    {reservation.numberOfGuests ?? "N/A"}
                                </td>



                                <td>
                                    {reservation.status}
                                </td>





                                <td>


                                    {
                                        reservation.status !== "CANCELLED" && (

                                            <button

                                                onClick={() =>
                                                    handleCancel(
                                                        reservation.reservationId
                                                    )
                                                }

                                            >

                                                Cancel

                                            </button>

                                        )
                                    }


                                </td>



                            </tr>


                        ))


                    )}



                </tbody>


            </table>






            <br />





            <button

                disabled={page === 0}

                onClick={() =>
                    setPage(page - 1)
                }

            >

                Previous

            </button>






            <button

                disabled={page + 1 >= totalPages}

                onClick={() =>
                    setPage(page + 1)
                }

            >

                Next

            </button>





        </div>

    );

}



export default CustomerPortal;