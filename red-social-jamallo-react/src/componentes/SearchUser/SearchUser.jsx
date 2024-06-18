import React, { useState } from 'react';
import "../../styles/SearchUser.scss";
import { Avatar, Card, CardHeader } from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';
import { searchUser } from '../../Redux/Auth/auth.action';
import { createChat } from '../../Redux/Mensaje/mensaje.action';

const SearchUser = () => {

  const [username, setUsername] = useState("");

  const dispatch = useDispatch();

  const {mensaje, auth} = useSelector(store=>store);


  const handleSearchUser = (e) => {
    setUsername(e.target.value)
    console.log("buscar usuario...", auth.searchUser);
    dispatch(searchUser(username))
  };

  const handleClick = (id) => {
    dispatch(createChat({userId:id}))
  };

  return (
    <div>
      <div className='buscador'>
        <input 
          type='text'
          className='buscador__input'
          placeholder='buscar usuario...'
          onChange={handleSearchUser}
        />
             {
        username && (
          auth.searchUser.map((item) =><Card key={item.id} sx={{bgcolor:"transparent", marginRight:"5px", position: "absolute", zIndex: "100", width: "100%"}}>
          <CardHeader
            sx={{bgcolor: "#053075", color:"#B1CEFB", borderRadius:"20px"}}
            onClick={() => {
            handleClick(item.id);
            setUsername("");
          }}
            avatar = {<Avatar sx={{bgcolor: "#B1CEFB", color: "#053075"}}/>}
            title = {item.nombre + " " + item.apellidos}
            subheader = {item.nombre.toLowerCase() + "" + item.apellidos.toLowerCase()}
            subheaderTypographyProps={{ style: { color: "#B1CEFB" }}}
          />
        </Card>)
        )
      } 
      </div>
      {/* <div className='Mensajes__menu__contenido__contactos_mensajes'> */}

      {/* </div> */}
    </div>
  )
}

export default SearchUser
