import React from 'react';
import { Card, CardMedia, CardContent, Typography, CardActions, Button } from '@mui/material';
import { useDispatch } from 'react-redux';
import { addToCart } from '../features/cart/cartSlice';

function ProductCard({ product }) {
  const dispatch = useDispatch();

  const handleAddToCart = () => {
    dispatch(addToCart(product));
  };

  return (
    <Card>
      <CardMedia
        component="img"
        height="140"
        image={product.imageUrl}
        alt={product.name}
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {product.name}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          ${product.price}
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small" color="primary" onClick={handleAddToCart}>
          Add to Cart
        </Button>
        <Button size="small" color="primary">
          View Details
        </Button>
      </CardActions>
    </Card>
  );
}

export default ProductCard;
