import { TextField } from '@mui/material';
import { Field, Form, Formik } from 'formik'
import React, { useState } from 'react'
import "../../styles/Login.scss"

const initialValues={}
const validationSchema={}
const Login = () => {
    const [formValue, setFormValue] = useState();

    const handleSubmit=() => {

    }
  return (
    <>
      <Formik 
        onSubmit={handleSubmit}
        validationSchema={validationSchema} 
        initialValues={initialValues}>
        <Form
            className='Formulario'>
            <div className='Formulario__contenedor'>
                <div>
                    <Field as={TextField} name="email" placeholder="Email"/>
                </div>
            </div>

        </Form>

      </Formik>
    </>
  )
}

export default Login


