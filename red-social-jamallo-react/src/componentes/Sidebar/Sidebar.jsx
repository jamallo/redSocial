import React from "react";
import { menuNavegacion } from "./SidebarNavegacion";
import "../../styles/Sidebar.scss";
import { Avatar } from "@mui/material";
import BotonPuntos from "../Botones/BotonPuntos"

const Sidebar = () => {
  return (
    <div className="pantalla">
      <div className="pantalla__contenedor">
        <div className="pantalla_contenedor_text">
          <span className="pantalla__contenedor__logo">
            Jamallo
          </span>
        </div>

        <div className="pantalla__contenedor__menu">
          {menuNavegacion.map((item) => (
            <div className="pantalla_contenedor__menu__iconos">
              {item.icono}
              <p className="pantalla__contenedor__menu__textoIconos">
                {item.titulo}
              </p>
            </div>
          ))}
        </div>
      </div>
      <div>
        <div className="pantalla__perfil">
            <div className="pantalla__perfil__avatar">
            <Avatar src=""/>
            <div>
              <p className="titulo__perfil">
                Jamallo
              </p>
              <p>
                @jamallo
              </p>
            </div>
            <div className="boton_puntos">
              <BotonPuntos/>
            </div>
              
            </div>
        </div>
      </div>
    </div>
  );
};

export default Sidebar;
