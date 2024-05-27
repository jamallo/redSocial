import { Avatar, Button, CardHeader, IconButton } from '@mui/material'
import { blue, red } from '@mui/material/colors'
import React from 'react';
import MoreVertIcon from '@mui/icons-material/MoreVert';


const UsuarioPopulares = () => {
  return (
    <div>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: "#053075" }} aria-label="recipe">
            
          </Avatar>
        }
        action={
          <Button size='small' sx={{ color: "#053075"}}>
            Seguir
          </Button>
        }
        title="Persona"
        subheader="@Persona"
      />
    </div>
  )
}

export default UsuarioPopulares
