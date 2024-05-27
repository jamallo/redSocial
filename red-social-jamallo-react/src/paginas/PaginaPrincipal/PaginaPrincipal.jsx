import { Grid } from "@mui/material";
import React, { useEffect } from "react";
import Sidebar from "../../componentes/Sidebar/Sidebar";
import { useLocation, Routes, Route } from "react-router-dom";
import MiddlePart from "../../componentes/MiddlePart/MiddlePart";
import Reels from "../../componentes/Reels/Reels";
import CreateReelsFrom from "../../componentes/CreateReelsFrom/CreateReelsFrom";
import Profile from "../../componentes/Profile/Profile";
import InicioDerecha from "../../componentes/InicioDerecha/InicioDerecha";
import "../../styles/PaginaPrincipal.scss"
import { useDispatch, useSelector } from "react-redux";
import { getProfileAction } from "../../Redux/Auth/auth.action";

const PaginaPrincipal = () => {
  const dispatch=useDispatch();
  const location = useLocation();
  const jwt = localStorage.getItem("jwt");
  const {auth} = useSelector(store=>store);

  console.log("auth", auth)

  

  return (
    <div className="contenedor">
      <Grid container spacing={0}>
        <Grid item xs={0} md={3}>
          <div className="contenedor__sidebar">
            <Sidebar />
          </div>
        </Grid>
        <Grid
          md={location.pathname === "/" ? 6 : 9}
          item
          className="contenedor__routes"
          xs={12}
        >
          <Routes>
            <Route path="/" element={<MiddlePart />} />
            <Route path="/reels" element={<Reels />} />
            <Route path="/create-reels" element={<CreateReelsFrom />} />
            <Route path="/profile/:id" element={<Profile />} />
          </Routes>
        </Grid>
        {location.pathname === "/" && <Grid item md={3} className="contenedor__grid">
          <div className="contenedor__grid__inicioDerecha">
            <InicioDerecha />
          </div>
        </Grid>}
      </Grid>
    </div>
  );
};

export default PaginaPrincipal;
