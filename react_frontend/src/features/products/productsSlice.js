import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const initialState = {
  products: [],
  product: null,
  status: 'idle',
  error: null,
};

// Async thunk to fetch all products
export const fetchProducts = createAsyncThunk('products/fetchProducts', async () => {
  const response = await axios.get('http://localhost:8080/api/products'); // Adjust the URL based on your backend
  return response.data;
});

// Async thunk to fetch a product by ID
export const fetchProductById = createAsyncThunk('products/fetchProductById', async (productId) => {
  const response = await axios.get(`http://localhost:8080/api/products/${productId}`);
  return response.data;
});

const productsSlice = createSlice({
  name: 'products',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchProducts.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchProducts.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.products = action.payload;
      })
      .addCase(fetchProducts.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(fetchProductById.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchProductById.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.product = action.payload;
      })
      .addCase(fetchProductById.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      });
  },
});

export const selectProducts = (state) => state.products.products;
export const selectProductById = (state) => state.products.product;
export const selectProductStatus = (state) => state.products.status;
export const selectProductError = (state) => state.products.error;

export default productsSlice.reducer;
