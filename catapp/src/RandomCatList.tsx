import { Grid } from '@mui/material'
import React from 'react'
import { Card } from './RandomCatForm'
import Item from './Item';

type RandomCatListProps = {
  cards: Card[]
}


const RandomCatList = (props: RandomCatListProps) => {

  return (
    <div>
      <Grid>
        {props.cards.map(card => {
        return <Item card={card}></Item>;
          })}
      </Grid>
    </div>
  )
}

export default RandomCatList