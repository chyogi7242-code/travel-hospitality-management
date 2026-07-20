import { useState } from "react";
import api from "../services/api";
import { useNavigate } from "react-router-dom";


function Register(){

    const navigate = useNavigate();

    const [user, setUser] = useState({
        name: "",
        email: "",
        password: "",
        phone: ""
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

            await api.post("/auth/register", user);

            alert("Registration Successful");

            navigate("/login");

        } catch(error) {

            console.log(error);
            alert("Registration Failed");

        }

    };


    return (

        <div>

            <h2>Customer Registration</h2>


            <form onSubmit={registerUser}>


                <input
                    name="name"
                    placeholder="Name"
                    onChange={handleChange}
                />


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


                <input
                    name="phone"
                    placeholder="Phone"
                    onChange={handleChange}
                />


                <button>
                    Register
                </button>


            </form>
<p>
    Already have an account?
    <a href="/login"> Login</a>
</p>

        </div>

    );

}


export default Register;