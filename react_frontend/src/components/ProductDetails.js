import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useParams } from 'react-router-dom';
import { fetchProductById, selectProductById, selectProductStatus, selectProductError } from '../features/products/productsSlice';
import { CircularProgress, Typography, Button } from '@mui/material';

function ProductDetails() {
  const { id } = useParams();
  const dispatch = useDispatch();
  const product = useSelector(selectProductById);
  const status = useSelector(selectProductStatus);
  const error = useSelector(selectProductError);

  useEffect(() => {
    if (id) {
      dispatch(fetchProductById(id));
    }
  }, [dispatch, id]);

  if (status === 'loading') {
    return <CircularProgress />;
  }

  if (status === 'failed') {
    return <Typography variant="body1" color="error">Error: {error}</Typography>;
  }

  if (!product) {
    return <Typography variant="body1">Product not found</Typography>;
  }

  return (
    <div>
      <Typography variant="h4">{product.name}</Typography>
      <img src={product.imageUrl} alt={product.name} style={{ width: '100%', maxWidth: '400px' }} />
      <Typography variant="body1">{product.description}</Typography>
      <Typography variant="h6">Price: ${product.price}</Typography>
      <Button variant="contained" color="primary">Add to Cart</Button>
    </div>
  );
}

export default ProductDetails;
