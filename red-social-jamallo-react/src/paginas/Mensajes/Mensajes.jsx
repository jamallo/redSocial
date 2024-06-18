import { Avatar, Grid, IconButton } from '@mui/material';
import React, { useEffect } from 'react';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import '../../styles/Mensajes.scss'
import AddIcCallIcon from '@mui/icons-material/AddIcCall';
import VideoCallIcon from '@mui/icons-material/VideoCall';
import AddPhotoAlternateIcon from '@mui/icons-material/AddPhotoAlternate';
import SearchUser from '../../componentes/SearchUser/SearchUser';
import UserChatCartd from './UserChatCartd';
import ChatMensajes from './ChatMensajes';
import { useDispatch, useSelector } from 'react-redux';
import { getAllChats } from '../../Redux/Mensaje/mensaje.action';

const Mensajes = () => {

  const dispatch = useDispatch();

  const {mensaje, auth} = useSelector(store=>store)


  useEffect(() => {
    dispatch(getAllChats())
  },[])

  const handleSelectImage = () => {
    console.log("cargar imagen seleccionada")
  }

  return (
    <div>
      <Grid container className='hideScrollBar Mensajes' >
        <Grid item xs={3} className='Mensajes__menu'>
          <div className='Mensajes__menu__contenido'>
            <div className='Mensajes__menu__contenido__volver'>
              <IconButton>
                <ArrowBackIcon sx={{color: "#B1CEFB", border: "solid 2px"}}/>
                <h1 className='Mensajes__menu__contenido__volver_inicio'>Inicio</h1>
              </IconButton>
            </div>
            <div className='Mensajes__menu__contenido__contactos'>
              {/* <input 
                placeholder='Buscar usuario'
                className='Mensajes__menu__contenido__contactos__buscador'>

              </input> */}
                <SearchUser/>

              
              <div className='Mensajes__menu__contenido__contactos_mensajes'>

                {
                  mensaje.chats.map((item) => <UserChatCartd/>)
                }
                
              </div>
            </div>
          </div>
        </Grid>
        <Grid item xs={9} className='Mensajes__mensajes'>
          <div>
            <div className='Mensajes__mensajes__contenedor'>
              <div className='Mensajes__mensajes__contenedor__usuario'>
                <Avatar sx={{bgcolor: "#053075", color: "#B1CEFB"}}/>
                <p>Alguien</p>
              </div>
              <div className='Mensajes__mensajes__contenedor__llamada'>
                <IconButton>
                  <AddIcCallIcon sx={{color: "#053075"}}/>
                </IconButton>
                <IconButton>
                  <VideoCallIcon sx={{color: "#053075"}}/>
                </IconButton>
              </div>

            </div>
            <div className='Mensajes__mensajes__chat'>
              <ChatMensajes/>
            </div>
          </div>
          <div className='Mensajes__mensajes__escribir'>
            <div className='Mensajes__mensajes__escribir__chat'>
              <input
                type='text'
                className='Mensajes__mensajes__escribir__chat__chat1'
                placeholder='Escribe texto para el chat'/>
              <input
                type='file'
                accept='image/*'
                onChange={handleSelectImage}
                className='Mensajes__mensajes__escribir__chat__adjuntar'
                id= "image-input"
              />
              <label htmlFor='image-input'>
                <AddPhotoAlternateIcon 
                  sx={{color:"#053075", bgcolor:"#b1cffbae", height:"40px", width:"40px", borderRadius:"10px", cursor:"pointer"}}
                  className='Mensajes__mensajes__escribir__chat__iconoImagen'
                  />
              </label>
            </div>


          </div>
          
        </Grid>
      </Grid>
    </div>
  )
}

export default Mensajes
