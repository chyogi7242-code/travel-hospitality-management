import axios from "axios";


const api = axios.create({

    baseURL: "http://localhost:8080/api",

});





export const getUserReservations = (

    userId,

    page = 0,

    size = 5,

    sortBy = "id"

) => {


    return api.get(

        `/reservations/user/${userId}?page=${page}&size=${size}&sortBy=${sortBy}`

    );


};





export default api;