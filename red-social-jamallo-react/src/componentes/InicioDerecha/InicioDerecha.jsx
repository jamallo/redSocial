import React from "react";
import "../../styles/Derecha.scss";
import SearchUser from "../SearchUser/SearchUser";
import UsuarioPopulares from "./UsuarioPopulares";
import { Card } from "@mui/material";

const usuariosPopulares = [1, 1, 1];

const InicioDerecha = () => {
  return (
    <div className="Derecha">
      <SearchUser />

      <Card sx={{bgcolor : "#b1cffbae", borderRadius: "20px"}}>
        <div className="Derecha__Usuario">
          <p className="Derecha__Usuario__Sugerencias">Sugerencias para ti</p>
          <p className="Derecha__Usuario__Sugerencias__ver">Ver todas</p>
        </div>
        {usuariosPopulares.map(() => (
          <UsuarioPopulares />
        ))}

        <div></div>
      </Card>
    </div>
  );
};

export default InicioDerecha;
