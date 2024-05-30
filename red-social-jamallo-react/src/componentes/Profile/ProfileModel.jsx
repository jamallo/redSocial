import * as React from 'react';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { useFormik } from 'formik';
import { Avatar, Button, IconButton } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import { updateProfileAction} from "../../Redux/Auth/auth.action";
import "../../styles/ProfileModel.scss"
import Boton from '../Botones/Boton';



export default function ProfileModel({open, handleClose}) {
    const dispatch=useDispatch();

    const handleSubmit=(values) => {
        console.log("valores", values)
    }

    
      const{auth} = useSelector(store=>store);
    
    const formik=useFormik({
        initialValues: {
            nombre: "",
            apellidos: ""
        },
        onSubmit:(values,) => {
            console.log("valores", values)
            dispatch(updateProfileAction(values))
        },
    })
  

  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box className='ventana'>
          <form onSubmit={formik.handleSubmit}>
            <div>
            
                <div className='ventana__contenedor'>
                    
                    <IconButton onClick={handleClose}>
                        <CloseIcon/>
                    </IconButton>
                    <div className= "ventana__boton">
                    <Boton text="Guardar" type='submit' onSubmit={handleSubmit}/>
                    </div>
                    <p className='ventana__titulo'>Editar Perfil</p>
                </div>
                
            </div>
            <div className='ventana__imagen'>
                <div>
                    <img src='' alt='imagen__perfil'/>
                </div>
            </div>
            <div className='ventana__avatar'>
                <Avatar sx={{width: "10rem", height: "10rem", bgcolor: "#053075"}}
                    src=''
                />
            </div>
            <div className='ventana__datos'>
                <p>Nombre</p>
                <input 
                    className='ventana__input'
                    id='nombre'
                    name='nombre'
                    placeholder={auth.user?.nombre}
                    value={formik.values.nombre}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                />
                <p>Apellidos</p>
                <input
                    className='ventana__input'
                    id='apellidos'
                    name='apellidos'
                    placeholder={auth.user?.apellidos}
                    value={formik.values.apellidos}
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                />
                
            </div>
          </form>
        </Box>
      </Modal>
    </div>
  );
}