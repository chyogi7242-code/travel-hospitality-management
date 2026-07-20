import { useState } from "react";
import api from "../services/api";
import { useNavigate } from "react-router-dom";


function Login() {

    const navigate = useNavigate();


    const [login, setLogin] = useState({
        email: "",
        password: ""
    });


    const handleChange = (e) => {

        setLogin({
            ...login,
            [e.target.name]: e.target.value
        });

    };


    const loginUser = async (e) => {

        e.preventDefault();

        try {

            const response = await api.post(
                "/auth/login",
                login
            );


            localStorage.setItem(
                "token",
                response.data.token
            );


            alert(response.data.message);


            navigate("/customer");


        } catch(error) {

            console.log("LOGIN ERROR:", error);

            alert("Invalid email or password");

        }

    };


    return (

        <div>

            <h2>
                Customer Login
            </h2>


            <form onSubmit={loginUser}>


                <input
                    name="email"
                    type="email"
                    placeholder="Email"
                    value={login.email}
                    onChange={handleChange}
                    required
                />


                <br/>


                <input
                    name="password"
                    type="password"
                    placeholder="Password"
                    value={login.password}
                    onChange={handleChange}
                    required
                />


                <br/>


                <button type="submit">
                    Login
                </button>


            </form>


            <p>
                Don't have an account?
                <button
                    onClick={() => navigate("/register")}
                >
                    Register
                </button>
            </p>


        </div>

    );

}


export default Login;