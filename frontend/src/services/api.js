import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080/api",
});


export const getReservations = (
    page = 0,
    size = 5,
    sortBy = "id"
) => {

    return api.get(
        `/reservations?page=${page}&size=${size}&sortBy=${sortBy}`
    );

};


export default api;