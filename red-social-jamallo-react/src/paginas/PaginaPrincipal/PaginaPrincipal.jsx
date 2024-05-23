import { Grid } from "@mui/material";
import React from "react";
import Sidebar from "../../componentes/Sidebar";
import { useLocation, Routes, Route } from "react-router-dom";
import MiddlePart from "../../componentes/MiddlePart/MiddlePart";
import Reels from "../../componentes/Reels/Reels";
import CreateReelsFrom from "../../componentes/CreateReelsFrom/CreateReelsFrom";
import Profile from "../../componentes/Profile/Profile";

const PaginaPrincipal = () => {
  const location = useLocation();
  return (
    <div 
        className="px-20">
      <Grid 
        container 
        spacing={0}>
        <Grid 
            item 
            xs={0} 
            lg={3}>
          <div 
            className="sticky top-0">
            <Sidebar />
          </div>
        </Grid>
        <Grid
          lg={location.pathname === "/" ? 6 : 9}
          item
          className="px-5 flex justify-center"
          xs={12}>
            <Routes>
                <Route 
                    path="/" 
                    element={<MiddlePart/>}/>
                <Route 
                    path="/reels" 
                    element={<Reels/>}/>
                <Route 
                    path="/create-reels" 
                    element={<CreateReelsFrom/>}/>
                <Route 
                    path="/profile/:id" 
                    element={<Profile/>}/>
            </Routes>
        </Grid>
      </Grid>
    </div>
  );
};

export default PaginaPrincipal;
