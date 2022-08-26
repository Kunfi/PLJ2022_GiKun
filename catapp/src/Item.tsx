import { Grid } from '@mui/material'
import React from 'react'
import { Card } from './RandomCatForm'

type ItemProps = {
    card: Card
}

const Item = (props: ItemProps) => {
  return (
    <Grid container>
      <Grid item xs={4} spacing={1}>
        <img src={props.card.catImageUrl} alt="Cat" width='128' height='128'/>
        <div>{props.card.description}</div>
      </Grid>
    </Grid> 
  )
}

export default Item