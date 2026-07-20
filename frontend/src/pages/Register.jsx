import { useState } from "react";
import api from "../services/api";
import { useNavigate } from "react-router-dom";


function Register() {

    const navigate = useNavigate();


    const [user, setUser] = useState({
        name: "",
        email: "",
        password: ""
    });



    const handleChange = (e) => {

        setUser({
            ...user,
            [e.target.name]: e.target.value
        });

    };



    const registerUser = async (e) => {

        e.preventDefault();


        try {

            const response = await api.post(
                "/auth/register",
                user
            );


            alert(response.data.message);


            navigate("/login");


        } catch(error) {

            console.log("REGISTER ERROR:", error);

            alert("Registration Failed");

        }

    };



    return (

        <div>

            <h2>
                Customer Registration
            </h2>


            <form onSubmit={registerUser}>


                <input
                    name="name"
                    placeholder="Name"
                    value={user.name}
                    onChange={handleChange}
                    required
                />


                <br/>


                <input
                    name="email"
                    type="email"
                    placeholder="Email"
                    value={user.email}
                    onChange={handleChange}
                    required
                />


                <br/>


                <input
                    name="password"
                    type="password"
                    placeholder="Password"
                    value={user.password}
                    onChange={handleChange}
                    required
                />


                <br/>


                <button type="submit">
                    Register
                </button>


            </form>



            <p>
                Already have an account?

                <button
                    onClick={() => navigate("/login")}
                >
                    Login
                </button>

            </p>


        </div>

    );

}


export default Register;