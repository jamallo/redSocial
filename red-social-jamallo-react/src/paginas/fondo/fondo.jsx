import React from "react";
import "../../styles/Fondo.scss";
import video from "../../imagenes/FondoRed.mp4";

const Fondo = () => {
  return (
    <>
      <div className="shadow-overlay"></div>
      <video
        playsInline
        autoPlay
        muted
        loop
        preload="auto"
        id="bg"
      >
        <source src={video} type="video/mp4" />
      </video>
    </>
  );
};

export default Fondo;
