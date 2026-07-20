import { useState } from "react";
import api from "../services/api";
import { useNavigate } from "react-router-dom";


function CreateReservation() {


    const navigate = useNavigate();


    const [reservation, setReservation] = useState({
        userId: 1,
        checkInDate: "",
        checkOutDate: "",
        numberOfGuests: 1
    });



    const handleChange = (e) => {

        setReservation({
            ...reservation,
            [e.target.name]: e.target.value
        });

    };



    const createReservation = async (e) => {

        e.preventDefault();


        try {

            const response = await api.post(
                "/reservations",
                reservation
            );


            alert(
                "Reservation created successfully"
            );


            console.log(response.data);


            navigate("/customer");


        } catch(error) {

            console.log(
                "Reservation Error:",
                error
            );


            alert(
                "Failed to create reservation"
            );

        }

    };



    return (

        <div>

            <h2>
                Book Hotel Reservation
            </h2>


            <form onSubmit={createReservation}>


                <label>
                    Check In:
                </label>

                <input
                    type="date"
                    name="checkInDate"
                    onChange={handleChange}
                    required
                />


                <br/>


                <label>
                    Check Out:
                </label>

                <input
                    type="date"
                    name="checkOutDate"
                    onChange={handleChange}
                    required
                />


                <br/>


                <label>
                    Guests:
                </label>

                <input
                    type="number"
                    name="numberOfGuests"
                    min="1"
                    value={reservation.numberOfGuests}
                    onChange={handleChange}
                    required
                />


                <br/>


                <button>
                    Book Reservation
                </button>


            </form>


        </div>

    );

}


export default CreateReservation;