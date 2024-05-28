import React from "react";
import { menuNavegacion } from "./SidebarNavegacion";
import "../../styles/Sidebar.scss";
import { Avatar } from "@mui/material";
import BotonPuntos from "../Botones/BotonPuntos"
import { useSelector } from "react-redux";
import { Navigate, useNavigate } from "react-router-dom";
import Profile from "../Profile/Profile";

const Sidebar = () => {
  const{auth} = useSelector(store=>store);
  const navigate = useNavigate();

  const handleNavigate=(item)=>{
    if(item.titulo==="Perfil") {
      navigate(`/profile/${auth.user?.id}`)
    } else if (item.titulo==="Inicio") {
      navigate(`/`)
    } else if (item.titulo==="Perfil") {
      navigate(`/profile/${auth.user?.id}`)
    } else if (item.titulo==="Reels") {
      navigate(`/reels`)
    }
  } 
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
            <div onClick={() => handleNavigate(item)} className="pantalla_contenedor__menu__iconos">
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
                {auth.user?.nombre + " " + auth.user?.apellidos}
              </p>
              <p>
                @{auth.user?.nombre.toLowerCase() + "_" + auth.user?.apellidos.toLowerCase()}
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
