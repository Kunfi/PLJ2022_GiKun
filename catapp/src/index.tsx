import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import RandomCatPage from "./pages/RandomCatPage";
import HomePage from "./pages/HomePage";
import {ProtectedRoute, ProtectedRouteProps} from './auth/ProtectedRoute';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

const defaultProtectedRouteProps: Omit<ProtectedRouteProps, 'outlet'> = {
    isAuthenticated: true, // TODO: !!sessionContext.isAuthenticated,
    authenticationPath: '/login', // TODO: Write login route and page
};

root.render(
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/home" element={<HomePage />} />
            <Route path="/cats" element={<ProtectedRoute {...defaultProtectedRouteProps} outlet={<RandomCatPage />} />} />
        </Routes>
    </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
