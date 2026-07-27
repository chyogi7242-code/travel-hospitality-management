import { useEffect, useState } from "react";
import {
    getAllReservations,
    updateReservationStatus,
    cancelReservation
} from "../api/reservationApi";

function AdminReservations() {

    const [reservations, setReservations] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const [search, setSearch] = useState("");
    const [statusFilter, setStatusFilter] = useState("ALL");

    useEffect(() => {
        fetchReservations(page);
    }, [page]);

    const fetchReservations = async (currentPage) => {

        try {

            const response = await getAllReservations(currentPage);

            setReservations(response.content);
            setTotalPages(response.totalPages);

        } catch (error) {

            console.log(error);

        }

    };

    const handleStatusUpdate = async (id, status) => {

        try {

            await updateReservationStatus(id, status);

            alert("Status updated successfully");

            fetchReservations(page);

        } catch (error) {

            console.log(error);

            alert("Failed to update status");

        }

    };

    const handleCancel = async (id) => {

        try {

            await cancelReservation(id);

            alert("Reservation cancelled");

            fetchReservations(page);

        } catch (error) {

            console.log(error);

            alert("Cancel failed");

        }

    };

    const filteredReservations = reservations.filter((reservation) => {

        const matchesSearch =
            reservation.reservationId
                .toString()
                .includes(search);

        const matchesStatus =
            statusFilter === "ALL" ||
            reservation.status === statusFilter;

        return matchesSearch && matchesStatus;

    });

    return (

        <div>

            <h1>Admin Reservation Management</h1>

            <input
                type="text"
                placeholder="Search Reservation ID"
                value={search}
                onChange={(e) => setSearch(e.target.value)}
            />

            {" "}

            <select
                value={statusFilter}
                onChange={(e) => setStatusFilter(e.target.value)}
            >
                <option value="ALL">All</option>
                <option value="PENDING">Pending</option>
                <option value="CONFIRMED">Confirmed</option>
                <option value="COMPLETED">Completed</option>
                <option value="CANCELLED">Cancelled</option>
            </select>

            <hr />

            {filteredReservations.map((reservation) => (

                <div
                    key={reservation.reservationId}
                    style={{
                        border: "1px solid gray",
                        padding: "10px",
                        marginBottom: "10px"
                    }}
                >

                    <h3>
                        Reservation #{reservation.reservationId}
                    </h3>

                    <p>User ID: {reservation.userId}</p>

                    <p>Check In: {reservation.checkInDate}</p>

                    <p>Check Out: {reservation.checkOutDate}</p>

                    <p>Guests: {reservation.numberOfGuests}</p>

                    <p>Status: {reservation.status}</p>

                    <button
                        onClick={() =>
                            handleStatusUpdate(
                                reservation.reservationId,
                                "CONFIRMED"
                            )
                        }
                    >
                        Confirm
                    </button>

                    {" "}

                    <button
                        onClick={() =>
                            handleStatusUpdate(
                                reservation.reservationId,
                                "COMPLETED"
                            )
                        }
                    >
                        Complete
                    </button>

                    {" "}

                    <button
                        onClick={() =>
                            handleCancel(
                                reservation.reservationId
                            )
                        }
                    >
                        Cancel
                    </button>

                </div>

            ))}

            <br />

            <button
                disabled={page === 0}
                onClick={() => setPage(page - 1)}
            >
                Previous
            </button>

            {" "}

            <span>
                Page {page + 1} of {totalPages}
            </span>

            {" "}

            <button
                disabled={page + 1 >= totalPages}
                onClick={() => setPage(page + 1)}
            >
                Next
            </button>

        </div>

    );

}

export default AdminReservations;