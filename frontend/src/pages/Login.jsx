import { useState } from "react";
import api from "../services/api";
import { useNavigate } from "react-router-dom";


function Login(){

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

            const response = await api.post("/auth/login", login);


            localStorage.setItem(
                "token",
                response.data.token
            );


            alert("Login Successful");


            navigate("/customer");


        } catch(error) {

            console.log(error);
            alert("Login Failed");

        }

    };


    return (

        <div>

            <h2>Customer Login</h2>


            <form onSubmit={loginUser}>


                <input
                    name="email"
                    placeholder="Email"
                    onChange={handleChange}
                />


                <input
                    name="password"
                    type="password"
                    placeholder="Password"
                    onChange={handleChange}
                />


                <button>
                    Login
                </button>


            </form>

<p>
    Don't have an account?
    <a href="/register"> Register</a>
</p>
        </div>

    );

}


export default Login;