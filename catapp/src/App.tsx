import React, { useState } from 'react';
import logo from './logo.svg';
import './App.css';
import { AppBar, Box, Container, Grid } from '@mui/material';
import RandomCatForm from './RandomCatForm';
import RandomCatList from './RandomCatList';

function App() {

  const [cards, setCards] = useState<any[]>([]);

  const handleRandomCatFormSubmit = (myShinyNewCard: any) => {
    setCards(cards => [...cards, myShinyNewCard]);
  }
  return (
    <div className="App">
      <Box>
        <Container>
          <AppBar>
            <RandomCatForm onSubmit={ handleRandomCatFormSubmit } />
            <RandomCatList />
          </AppBar>
        </Container>
      </Box>
    </div>
  );
}

export default App;
