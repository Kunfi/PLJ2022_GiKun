import { Button, Grid, TextField } from '@mui/material'
import { useFormik } from 'formik'
import React, { useEffect, useState } from 'react'
import CatImageService from './service/CatImageService';



const RandomCatForm = () => {

  const [catImageUrl, setCatImageUrl] = useState<string>('');

  useEffect(() => {
      CatImageService().getRandomCatImage().then((catImageUrl) => {
        setCatImageUrl(catImageUrl);
      });
    });

  return (
    <div>
    <Grid container>
        <Grid item xs={12}>
            <form name='form'>
                <TextField name='name' id='name'>

                </TextField>
                <Button>

                </Button>
                <img src = {catImageUrl} alt="Cat Picture" />
            </form>
        </Grid>
    </Grid>  
    </div>
    )
}

export default RandomCatForm;