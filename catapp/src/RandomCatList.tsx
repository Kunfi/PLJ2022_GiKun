import { Grid } from '@mui/material'
import React from 'react'
import { Card } from './RandomCatForm'
import Item from './Item';


const RandomCatList = ({cards}: {cards: any[]}) => {

  return (
    <div>
        <Grid container>
            {cards.map(card => {
            return <Item card={card}></Item>;
              })}
        </Grid>
    </div>
  )
}

export default RandomCatList