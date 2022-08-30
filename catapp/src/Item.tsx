import { Grid } from '@mui/material'
import React from 'react'
import { Card } from './RandomCatForm'

type ItemProps = {
    card: Card
}

const Item = (props: ItemProps) => {
  return (
      <Grid item xs={3}>
        <img src={props.card.catImageUrl} alt="Cat" width='128' height='128'/>
        <div>{props.card.description}</div>
      </Grid>
  )
}

export default Item