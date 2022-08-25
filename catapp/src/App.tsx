import React from 'react';
import logo from './logo.svg';
import './App.css';
import { AppBar, Box, Container, Grid } from '@mui/material';
import RandomCatForm from './components/RandomCatForm';
import RandomCatList from './components/RandomCatList';

function App() {
  return (
    <div className="App">
      <Box>
        <Container>
          <AppBar>
            <RandomCatForm />
            <RandomCatList />
          </AppBar>
        </Container>
      </Box>
    </div>
  );
}

export default App;
