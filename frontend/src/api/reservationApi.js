import axios from "axios";

const API_URL = "http://localhost:8080/api/reservations";

// Create Reservation
export const createReservation = async (reservationData) => {
    const response = await axios.post(API_URL, reservationData);
    return response.data;
};

// Get User Reservations
export const getUserReservations = async (userId) => {
    const response = await axios.get(`${API_URL}/user/${userId}`);
    return response.data;
};

// Get Reservation By ID
export const getReservationById = async (reservationId) => {
    const response = await axios.get(`${API_URL}/${reservationId}`);
    return response.data;
};

// Get All Reservations (Admin)
export const getAllReservations = async (
    page = 0,
    size = 5,
    sortBy = "id"
) => {
    const response = await axios.get(
        `${API_URL}?page=${page}&size=${size}&sortBy=${sortBy}`
    );

    return response.data;
};

// Update Reservation Status
export const updateReservationStatus = async (
    reservationId,
    status
) => {
    const response = await axios.put(
        `${API_URL}/${reservationId}/status?status=${status}`
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