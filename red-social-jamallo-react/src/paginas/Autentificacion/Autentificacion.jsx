
import React from "react";
import "../../styles/Autenticacion.scss"
import Login from "./Login";
import Registro from "./Registro";

const Autentificacion = () => {
  return (
    <div>

          <div className="Formulario__autentificacion">
            <div>
              <div className="flex flex-col items-center mb-5 space-y-1">
                <h1 className="TituloRed">Jamallo</h1>
                <p className="DescripcionRed">
                  Conecta y comparte: di lo que piensas
                </p>
              </div>
              <Login/>
              {/*<Registro/>*/}
            </div>
          </div>

    </div>
  );
};

export default Autentificacion;
