import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { TextField, Button, Box } from "@mui/material";

const Register = () => {
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [address, setAddress] = useState('');
  const [phone, setPhone] = useState('');
  const [name, setName] = useState('');
  const [emailError, setEmailError] = useState('');

  const navigate = useNavigate();

  const validateEmail = (email) => {
    const emailPattern = /^[^\s@]+@gmail\.com$/;
    return emailPattern.test(email);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateEmail(email)) {
      setEmailError('Email must be in the format of example@email.com');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/customers', {
        password,
        email,
        name,
        phone,
        address,
      });

      alert("Registration successful! Please log in.");
      navigate('/login');

    } catch (error) {
      console.error("Failed to register", error);
      alert("Registration failed! Please try again.");
    }
  };

  return (
    <Box component="form" onSubmit={handleSubmit} noValidate>
      <TextField
        label="Email"
        type="email"
        value={email}
        onChange={(e) => {
          setEmail(e.target.value);
          if (!validateEmail(e.target.value)) {
            setEmailError('Email must be in the format of example@email.com');
          } else {
            setEmailError('');
          }
        }}
        error={!!emailError}
        helperText={emailError}
        fullWidth
        margin="normal"
        required
      />

      <TextField
        label="Password"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        fullWidth
        margin="normal"
        required
      />

      <TextField
        label="Address"
        type="text"
        value={address}
        onChange={(e) => setAddress(e.target.value)}
        fullWidth
        margin="normal"
        required
      />

      <TextField
        label="Phone"
        type="text"
        value={phone}
        onChange={(e) => setPhone(e.target.value)}
        fullWidth
        margin="normal"
        required
      />

      <TextField
        label="Name"
        type="text"
        value={name}
        onChange={(e) => setName(e.target.value)}
        fullWidth
        margin="normal"
        required
      />

      <Button
        type="submit"
        variant="contained"
        color="primary"
        fullWidth
        sx={{ mt: 2 }}
      >
        Sign Up
      </Button>
    </Box>
  );
};

export default Register;
