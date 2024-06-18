import React from "react";
import "../../styles/ChatMensajes.scss";

const ChatMensajes = () => {
  return (
    <div className={`sms ${true ? "smscontenedorsi" : "smscontenedorno"}`}>
      <div className={`mensaje ${true ? "mensajesSi" : "mensajesNo"}`}>
        {true && <img className="imagenChat" src="" alt="algo"/>}
        <p className={`${true ? "si" : "no"}`}>mensajes</p>
      </div>
    </div>
  );
};

export default ChatMensajes;
