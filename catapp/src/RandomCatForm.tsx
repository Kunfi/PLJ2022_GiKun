import { Button, Grid, TextField } from '@mui/material'
import { useFormik } from 'formik'
import React, { useEffect, useState } from 'react'
import CatImageService from './service/CatImageService';



const RandomCatForm = ({onSubmit}: {onSubmit: (card: any) => void}) => {

  const [catImageUrl, setCatImageUrl] = useState<string>('');

  const formik = useFormik({
    initialValues: {
      name: '',
      imageUrl: ''
    },
    onSubmit: values => {
      const card = {
        name: values.name,
        imageUrl: catImageUrl
      }
      CatImageService().getRandomCatImage().then((catImageUrl) => {
        setCatImageUrl(catImageUrl);})
      onSubmit(card);
    }
  });
    

  useEffect(() => {
      CatImageService().getRandomCatImage().then((catImageUrl) => {
        setCatImageUrl(catImageUrl);
      });
    },[]);

  return (
    <div>
    <Grid container>
        <Grid item xs={12}>
            <form name='form' onSubmit={formik.handleSubmit}>
              <div><img src = {catImageUrl} height="600" alt="Cat Picture" /></div>
                <div><TextField name='name' id='name'></TextField></div>
                <div><Button color='warning' type='submit'>Submit</Button></div>
            </form>
        </Grid>
    </Grid>  
    </div>
    )
}

export default RandomCatForm;