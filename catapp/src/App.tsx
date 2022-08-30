import React from 'react';
import './App.css';
import {AppBar, Box, Container} from '@mui/material';
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import HomePage from "./pages/HomePage";
import {ProtectedRoute, ProtectedRouteProps} from "./auth/ProtectedRoute";
import RandomCatPage from "./pages/RandomCatPage";
import LoginPage from "./pages/LoginPage";

function App() {

    const defaultProtectedRouteProps: Omit<ProtectedRouteProps, 'outlet'> = {
        isAuthenticated: true, // TODO: !!sessionContext.isAuthenticated,
        authenticationPath: '/login', // TODO: Write login route and page
    };

    return (
    <div className="App">
      <Box>
        <Container>
            <BrowserRouter>
                <AppBar>
                    <Link to = "/home" color="#FFF"></Link>
                    <Link to = "/cats" color="#FFF"></Link>
                </AppBar>
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/home" element={<HomePage />} />
                    <Route path="/cats" element={<ProtectedRoute {...defaultProtectedRouteProps} outlet={<RandomCatPage />} />} />
                    <Route path="/login" element={<LoginPage />} />
                </Routes>
            </BrowserRouter>
        </Container>
      </Box>
    </div>
  );
}

export default App;
