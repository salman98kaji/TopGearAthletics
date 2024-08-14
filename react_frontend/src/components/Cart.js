import React from 'react';
import { useSelector } from 'react-redux';
import { selectCartItems } from '../features/cart/cartSlice';
import { List, ListItem, ListItemText, Typography } from '@mui/material';

function Cart() {
  const cartItems = useSelector(selectCartItems);

  return (
    <div>
      <Typography variant="h4" gutterBottom>
        My Cart
      </Typography>
      <List>
        {cartItems.map((item) => (
          <ListItem key={item.id}>
            <ListItemText
              primary={`${item.name} x${item.quantity}`}
              secondary={`$${item.price * item.quantity}`}
            />
          </ListItem>
        ))}
      </List>
    </div>
  );
}

export default Cart;
