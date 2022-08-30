import React from "react";
import {Link} from "react-router-dom";
import {AppBar} from "@mui/material";

function HomePage() {

    return (
        <div>
            <AppBar>
                <Link to = "/home" color="#FFF">Homepage</Link>
                <Link to = "/cats" color="#FFF">Cat List</Link>
            </AppBar>
            <h1>Hello Cat World</h1>
        </div>
    );
}

export default HomePage;
