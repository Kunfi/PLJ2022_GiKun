import { Button, Grid, TextField } from "@mui/material";
import {  FormikValues, useFormik } from "formik";
import React, { useEffect, useState } from "react";

const LoginPage = () => {

    const validateForm = (values: FormikValues) => {
        const errors: {
            username?: string,
            password?: string
        } = {};

        if (!values.username) {
            errors.username = "Please enter a username";
        }
        else if (!values.password) {
            errors.password = "Please enter a password";
        }

        return errors;
    };

    const formik = useFormik({
        initialValues: {
            username: "",
            password: "",
        },
        validate: validateForm,
        onSubmit: (values) => {
            const login: Login = {
                username: values.username,
                password: values.password
            };
        }
    })

    return (

        <div>
            <div>
                <TextField name="username" id="username" placeholder="Username"></TextField>
            </div>
            <div>
                <TextField type="password" placeholder="Password"></TextField>
            </div>
            <div>
                <Button type="submit">Login</Button>
            </div>
        </div>
    )
}

export default LoginPage;

export interface Login {
    username: string,
    password: string
}