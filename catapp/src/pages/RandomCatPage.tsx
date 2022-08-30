import React, { useState } from 'react';
import {AppBar, Box, Container, css} from '@mui/material';
import RandomCatForm, { Card } from '../RandomCatForm';
import RandomCatList from '../RandomCatList';


function RandomCatPage() {

    const [cards, setCards] = useState<Card[]>([]);

    const handleRandomCatFormSubmit = (newCard: any) => {
        setCards(cards => [...cards, newCard].sort((a, b) => (b.timeStamp - a.timeStamp)))
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

export default RandomCatPage;
