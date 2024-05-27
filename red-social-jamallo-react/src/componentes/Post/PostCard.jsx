import { Avatar, Card, CardActions, CardContent, CardHeader, CardMedia, IconButton, Typography } from '@mui/material';
import React from 'react';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { blue } from '@mui/material/colors';
import StarBorderIcon from '@mui/icons-material/StarBorder';
import ShareIcon from '@mui/icons-material/Share';
import StarIcon from '@mui/icons-material/Star';
import ChatBubbleIcon from '@mui/icons-material/ChatBubble';
import BookmarkBorderIcon from '@mui/icons-material/BookmarkBorder';
import BookmarkIcon from '@mui/icons-material/Bookmark';
import "../../styles/PostCard.scss"

const PostCard = () => {

    
  return (
    <Card className='' sx={{bgcolor: "#b1cffbae", marginTop: "20px", borderRadius: "20px"}}>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: "#053075" }} aria-label="recipe">
          </Avatar>
        }
        action={
          <IconButton aria-label="settings" >
            <MoreVertIcon sx={{color: "#053075"}}/>
          </IconButton>
        }
        sx={{color: "#053075"}}
        title="Persona"
        subheader="@Persona"
      />
      <CardMedia
        sx={{color: "#053075"}}
        component="img"
        height="194"
        image=""
        alt="Algo que se quiera poner"
      />
      <CardContent >
        <Typography variant="body2" sx={{color: "#053075"}}>
          Texto que se quiera poner.
        </Typography>
      </CardContent>

      <CardActions className='Post__acciones' disableSpacing>
        <div>
            <IconButton>
                {true ? <StarBorderIcon sx={{color: "#053075"}}/> : <StarIcon sx={{color: blue [900]}}/>}
            </IconButton>

            <IconButton>
                <ShareIcon sx={{color: "#053075"}}/>
            </IconButton>

            <IconButton>
                <ChatBubbleIcon sx={{color: "#053075"}}/>
            </IconButton>
        </div>
        <div>
            <IconButton>
                {true ? <BookmarkIcon sx={{color: "#053075"}}/> : <BookmarkBorderIcon sx={{color: "#053075"}}/>}
            </IconButton>
        </div>
      </CardActions>
    </Card>
  )
}

export default PostCard
