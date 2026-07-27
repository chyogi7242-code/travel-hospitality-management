import { Link } from "react-router-dom";

function Navbar() {

    return (

        <nav
            style={{
                padding: "15px",
                backgroundColor: "#1976d2",
                marginBottom: "20px"
            }}
        >

            <Link
                to="/"
                style={{
                    color: "white",
                    marginRight: "20px",
                    textDecoration: "none"
                }}
            >
                Home
            </Link>

            <Link
                to="/reservations"
                style={{
                    color: "white",
                    marginRight: "20px",
                    textDecoration: "none"
                }}
            >
                My Reservations
            </Link>

            <Link
                to="/admin"
                style={{
                    color: "white",
                    textDecoration: "none"
                }}
            >
                Admin
            </Link>

        </nav>

    );

}

export default Navbar;