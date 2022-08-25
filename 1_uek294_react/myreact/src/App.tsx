import React from 'react';
import logo from './logo.svg';
import MyComponent from './MyComponent';
import { ExampleCounter } from './MyComponent';
import './App.css';
import { AppBar, Box, Container, Grid } from '@mui/material';


function App() {
  return (
    <div className="App">
      <Box>
        <Container>
          <AppBar>
            <Grid container>
              <Grid item xs={12}>
                <div>Form Here</div>
              </Grid>
            </Grid>
            <Grid container>
              <Grid item xs={12}>
                <div>List here!</div>
              </Grid>
            </Grid>
          </AppBar>
        </Container>
      </Box>
    </div>
  );
}

export default App;
