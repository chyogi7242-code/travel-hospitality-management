import { useEffect, useState } from "react";
import { getReservations } from "../services/api";

function CustomerPortal() {

    const [reservations, setReservations] = useState([]);

    const [page, setPage] = useState(0);
    const [size, setSize] = useState(5);
    const [sortBy, setSortBy] = useState("id");

    const [totalPages, setTotalPages] = useState(0);



    useEffect(() => {

        loadReservations();

    }, [page, size, sortBy]);



    const loadReservations = () => {

        getReservations(page, size, sortBy)
            .then((response) => {

                console.log(response.data);

                setReservations(response.data.content || []);

                setTotalPages(response.data.totalPages);

            })
            .catch((error) => {

                console.log(error);

            });

    };



    return (
        <div>

            <h1>
                Welcome to Customer Portal
            </h1>


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
                        setSize(e.target.value);
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
                    </tr>

                </thead>


                <tbody>

                    {
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

                            </tr>

                        ))
                    }


                </tbody>

            </table>



            <br />


            <button
                disabled={page === 0}
                onClick={() => setPage(page - 1)}
            >
                Previous
            </button>



            <button
                disabled={page + 1 >= totalPages}
                onClick={() => setPage(page + 1)}
            >
                Next
            </button>


        </div>
    );
}


export default CustomerPortal;