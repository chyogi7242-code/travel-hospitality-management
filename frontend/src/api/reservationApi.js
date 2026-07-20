import axios from "axios";

const API_URL = "http://localhost:8080/api/reservations";


// Create Reservation
export const createReservation = async (reservationData) => {

    const response = await axios.post(
        API_URL,
        reservationData
    );

    return response.data;
};



// Get User Reservations
export const getUserReservations = async (userId) => {

    const response = await axios.get(
        `${API_URL}/user/${userId}`
    );

    return response.data;
};



// Cancel Reservation
export const cancelReservation = async (reservationId) => {

    const response = await axios.delete(
        `${API_URL}/${reservationId}`
    );

    return response.data;
};