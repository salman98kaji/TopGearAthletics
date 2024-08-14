import React from 'react';
import { Typography, Container } from '@mui/material';

function Home() {
  return (
    <Container>
      <Typography variant="h4" gutterBottom>
        Welcome to the E-commerce Store
      </Typography>
      <Typography variant="body1">
        Explore our wide range of sports and fitness equipment.
      </Typography>
    </Container>
  );
}

export default Home;
