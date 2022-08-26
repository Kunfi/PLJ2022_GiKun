import { Button, Grid, TextField } from "@mui/material";
import {  FormikValues, useFormik } from "formik";
import React, { useEffect, useState } from "react";
import CatImageService from "./service/CatImageService";

const RandomCatForm = ({ onSubmit }: { onSubmit: (card: any) => void }) => {

  const [catImageUrl, setCatImageUrl] = useState<string>("");

  const validateForm = (values: FormikValues) => {
    const errors: { name?: string } = {};

    if (!values.name) {
      errors.name = "Please provide a name";
    }

    return errors;
  };

  const formik = useFormik({
    initialValues: {
      name: "",
    },
    validate: validateForm,
    onSubmit: (values) => {
      const card : Card = {
        description: values.name,
        catImageUrl: catImageUrl
      };
      CatImageService()
        .getRandomCatImage()
        .then((catImageUrl) => {
          setCatImageUrl(catImageUrl);
        });

      formik.resetForm({ values: { name: ""} });
      onSubmit(card)
      formik.setSubmitting(false)
    },
  });

  useEffect(() => {
    CatImageService()
      .getRandomCatImage()
      .then((catImageUrl) => {
        setCatImageUrl(catImageUrl);
      });
  }, [])

  return (
    <div>
      <Grid container>
        <Grid item xs={12}>
          <form name="form" onSubmit={formik.handleSubmit}>
            <div>
              <img src={catImageUrl} height="600" alt="Cat Picture" />
            </div>
            <div>
              <TextField name="name" id="name" onChange={formik.handleChange} value={formik.values.name}></TextField>
            </div>
            <div>
              <Button color="warning" type="submit">
                Submit
              </Button>
            </div>
            {formik.touched.name && formik.errors.name && (
              <div id="nameError">{formik.errors.name}</div>
            )}
          </form>
        </Grid>
      </Grid>
    </div>
  );
};

export default RandomCatForm;

export interface Card {
  description: string,
  catImageUrl: string
}
