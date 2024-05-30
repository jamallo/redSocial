import { TextField } from '@mui/material';
import { ErrorMessage, Field, Form, Formik } from 'formik'
import React, { useState } from 'react'
import "../../styles/Login.scss"
import * as Yup from "yup";
import { useDispatch } from 'react-redux';
import { loginUserAction } from '../../Redux/Auth/auth.action';
import { useNavigate } from 'react-router-dom';


const initialValues={email:"", password:""}
const validationSchema={email:Yup.string().email("Email inválido").required("El email es obligatorio"),
password: Yup.string()
  .min(6, "La contraseña debe tener 6 carácteres")
  .required("La contraseña es obligatoria")}
const Login = () => {
    const [formValue, setFormValue] = useState();
    const dispath = useDispatch();
    const navegacion = useNavigate();

    const handleSubmit=(values) => {
      console.log("Envío", values);
      dispath(loginUserAction({data:values}))
    };

  return (
    <>
      <Formik 
        onSubmit={handleSubmit}
        //validationSchema={validationSchema} 
        initialValues={initialValues}>
        <Form
            className='Formulario'>
            <div className='Formulario__contenedor'>
                <div className='Formulario__contenedor__email'>
                    <Field 
                        className= "Formulario__contenedor__email_caja"
                        as={TextField} 
                        name="email" 
                        placeholder="Email" 
                        type="email" 
                        variante="outlined" 
                        fullWidth/>
                    <ErrorMessage 
                        name='email' 
                        component={"div"} 
                        className='MensajeError'/>
                </div>
                <div className='Formulario__contenedor__password'>
                    <Field 
                        as={TextField} 
                        name="password" 
                        placeholder="Contraseña" 
                        type="password" 
                        variant="outlined" 
                        fullWidth/>
                    <ErrorMessage 
                        name='password' 
                        component="div"
                        className='MensajeError'/>
                </div>
                <div>
                  <button  
                    className='boton__login'
                    type="submit">
                    Acceder
                </button>
                </div>

            </div>
       </Form>
      </Formik>
      <div className='DescripcionRed'>
        <p>
          ¿No tienes una cuenta?
          <button  
            className='boton__login'
            type="submit"
            onClick={()=>navegacion("/registro")}>
            Regístrate
          </button>
        </p>
      </div>
    </>
  )
}

export default Login


