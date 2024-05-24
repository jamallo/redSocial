import { TextField, RadioGroup, FormControlLabel, Radio } from "@mui/material";
import { blue } from "@mui/material/colors";
import { ErrorMessage, Field, Form, Formik } from "formik";
import React, { useState } from "react";
import "../../styles/Login.scss";
import * as Yup from "yup";
import { useDispatch } from "react-redux";
import { registerUserAction } from "../../Redux/Auth/auth.action";
import { useNavigate } from "react-router-dom";

const initialValues = {
  nombre: "",
  apellidos: "",
  email: "",
  password: "",
  genero: "",
};
const validationSchema = {
  emil: Yup.string()
    .email("Email inválido")
    .required("El email es obligatorio"),
  password: Yup.string()
    .min(6, "La cobtraseña debe tener 6 carácteres")
    .required("La contraseña es obligatoria"),
};
const Registro = () => {
  const [genero, setGenero] = useState("");
  const dispath = useDispatch();

  const handleSubmit = (values) => {
    values.genero = genero;
    console.log("al registrarse", values);

    dispath(registerUserAction({ data: values }));
  };

  const [value, setValue] = React.useState("female");
  const navegacion = useNavigate();

  const handleChange = (event) => {
    setGenero(event.target.value);
  };

  return (
    <>
      <Formik
        onSubmit={handleSubmit}
        //validationSchema={validationSchema}
        initialValues={initialValues}
      >
        <Form className="Formulario">
          <div className="Formulario__contenedor">
            <div className="Formulario__contenedor__nombre">
              <Field
                className="Formulario__contenedor__nombre_caja"
                as={TextField}
                name="nombre"
                placeholder="Nombre"
                type="text"
                variante="outlined"
                fullWidth
              />
              <ErrorMessage
                name="nombre"
                component={"div"}
                className="MensajeError"
              />
            </div>
            <div className="Formulario__contenedor__apellidos">
              <Field
                className="Formulario__contenedor__apellidos_caja"
                as={TextField}
                name="apellidos"
                placeholder="Apellidos"
                type="text"
                variante="outlined"
                fullWidth
              />
              <ErrorMessage
                name="apellidos"
                component={"div"}
                className="MensajeError"
              />
            </div>
            <div className="Formulario__contenedor__email">
              <Field
                className="Formulario__contenedor__email_caja"
                as={TextField}
                name="email"
                placeholder="Email"
                type="email"
                variante="outlined"
                fullWidth
              />
              <ErrorMessage
                name="email"
                component={"div"}
                className="MensajeError"
              />
            </div>
            <div className="Formulario__contenedor__password">
              <Field
                as={TextField}
                name="password"
                placeholder="Contraseña"
                type="password"
                variant="outlined"
                fullWidth
              />
              <ErrorMessage
                name="password"
                component="div"
                className="MensajeError"
              />
            </div>
            <div className="Formulario__contenedor__genero">
              <RadioGroup
                onChange={handleChange}
                row
                aria-labelledby="genero"
                defaultValue="female"
                name="genero"
              >
                <FormControlLabel
                  value="Femenino"
                  control={
                    <Radio
                      sx={{
                        color: blue[50],
                      }}
                    />
                  }
                  label="Feminino"
                  sx={{
                    typography: {
                      fontFamily: ["Russo One"].join(","),
                    },
                  }}
                />
                <FormControlLabel
                  value="Masculino"
                  control={
                    <Radio
                      sx={{
                        color: blue[50],
                      }}
                    />
                  }
                  label="Masculino"
                />
              </RadioGroup>
            </div>
            <button className="boton" type="submit">
              Registro
            </button>
          </div>
        </Form>
      </Formik>
      <div className="DescripcionRed">
        <p>
          ¿Ya tienes una cuenta?
          <button
            className="boton"
            type="submit"
            onClick={() => navegacion("/")}
          >
            iniciar sesión
          </button>
        </p>
      </div>
    </>
  );
};

export default Registro;
