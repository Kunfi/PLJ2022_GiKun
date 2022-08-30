import React, { useState } from 'react';
import './App.css';
import {AppBar, Box, Container} from '@mui/material';
import RandomCatForm, { Card } from './RandomCatForm';
import RandomCatList from './RandomCatList';
import {Link} from "react-router-dom";

function App() {

  const [cards, setCards] = useState<Card[]>([]);

  const handleRandomCatFormSubmit = (newCard: any) => {
    setCards(cards => [...cards, newCard].sort((a, b) => (b.timeStamp - a.timeStamp)))
  }
  return (
    <div className="App">
      <Box>
        <Container>
            <AppBar>
                <Link to = "/home" color="#FFF"></Link>
                <Link to = "/cats" color="#FFF"></Link>
            </AppBar>
            <RandomCatForm onSubmit={ handleRandomCatFormSubmit } />
            <RandomCatList cards={cards} />
        </Container>
      </Box>
    </div>
  );
}

export default App;
