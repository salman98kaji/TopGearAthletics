import { createTheme } from "@mui/material";

export const Colors = {
  primary: "#3ABF75",
  secondary: "#3ABF75",
  primaryLight: "#4a9a91",
  primaryDark: "#2F945C",
  secondaryLight: "#ff8f6d",
  secondaryDark: "#b25133",

  //Standard colors
  white: "#fff",
  black: "#363636",
  grayLight: "#bdbdbd",
  grayDark: "#535353",
};

const theme = createTheme({
  palette: {
    primary: {
      main: Colors.primary,
      light: Colors.primaryLight,
      dark: Colors.primaryDark,
    },
    secondary: {
      main: Colors.secondary,
      light: Colors.secondaryLight,
      dark: Colors.secondaryDark,
    },
  },
});

export default theme;

/*#ff7449
#26a69a*/
