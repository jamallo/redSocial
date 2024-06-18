import { Avatar, CardHeader, IconButton } from '@mui/material'
import React from 'react';
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';

const UserChatCartd = () => {
  return (
    <CardHeader avatar = {
            <Avatar 
                sx={{
                    width: "3.5rem", 
                    height: "3.5rem", 
                    fontSize: "1.5rem",
                    bgcolor: "#053075",
                    color: "#B1CEFB"
                    }} 
                src=""/>
        } action = {<IconButton>
            <MoreHorizIcon sx={{color: "#053075"}}/>
        </IconButton>}
        title="Persona"
        subheader={"nuevo mensaje"}
        sx={{backgroundColor: "#b1cffbae",
            borderRadius:"20px",
            color: "#053075",
            marginRight: "5px",
            boxShadow: "2px 2px 2px 1px rgba(0, 0, 0, 0.2)"}}
        >
         
    </CardHeader>
  )
}

export default UserChatCartd
