import React, { useState } from 'react';
import './App.css';
import { Box, Container } from '@mui/material';
import RandomCatForm, { Card } from './RandomCatForm';
import RandomCatList from './RandomCatList';

function App() {

  const [cards, setCards] = useState<Card[]>([]);

  const handleRandomCatFormSubmit = (myShinyNewCard: any) => {
    setCards(cards => [...cards, myShinyNewCard]);
  }
  return (
    <div className="App">
      <Box>
        <Container>
            <RandomCatForm onSubmit={ handleRandomCatFormSubmit } />
            <RandomCatList cards={cards} />
        </Container>
      </Box>
    </div>
  );
}

export default App;
