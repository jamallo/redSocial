import {
  Avatar,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  CardMedia,
  Divider,
  IconButton,
  Typography,
} from "@mui/material";
import React, { useState } from "react";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import { blue } from "@mui/material/colors";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import ShareIcon from "@mui/icons-material/Share";
import StarIcon from "@mui/icons-material/Star";
import ChatBubbleIcon from "@mui/icons-material/ChatBubble";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import BookmarkIcon from "@mui/icons-material/Bookmark";
import "../../styles/PostCard.scss";
import { createCommentAction, likePostAction } from "../../Redux/Post/post.action";
import { useDispatch, useSelector } from "react-redux";

const PostCard = ({ item }) => {
  const [showComments, setShowComments] = useState(false);
  const dispatch = useDispatch();
  const { post } = useSelector((store) => store);

  const handleShowComment = () => setShowComments(!showComments);

  const handleCreateComment = (content) => {
    const reqData = {
      postId: item.id,
      data: {
        content,
      },
    };
    dispatch(createCommentAction(reqData));
  };

  const handleLikePost = () => {
    dispatch(likePostAction(item.id));
  }

  return (
    <Card
      className=""
      sx={{ bgcolor: "#b1cffbae", marginTop: "20px", borderRadius: "20px" }}
    >
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: "#053075" }} aria-label="recipe"></Avatar>
        }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon sx={{ color: "#053075" }} />
          </IconButton>
        }
        sx={{ color: "#053075" }}
        title={item.user.nombre + " " + item.user.apellidos}
        subheader={
          "@" +
          item.user.nombre.toLowerCase() +
          "_" +
          item.user.apellidos.toLowerCase()
        }
      />
      <CardMedia
        sx={{ color: "#053075" }}
        component="img"
        height="194"
        image={item.imagen}
        alt="Imagen que se quiso poner"
      />
      <CardContent>
        <Typography variant="body2" sx={{ color: "#053075" }}>
          {item.titulo}
        </Typography>
      </CardContent>

      <CardActions className="Post__acciones" disableSpacing>
        <div>
          <IconButton onClick={handleLikePost}>
            {false ?
              <StarBorderIcon sx={{ color: "#053075" }} />
             : 
              <StarIcon sx={{ color: blue[900] }} />
            }
          </IconButton>

          <IconButton>
            <ShareIcon sx={{ color: "#053075" }} />
          </IconButton>

          <IconButton onClick={handleShowComment}>
            <ChatBubbleIcon sx={{ color: "#053075" }} />
          </IconButton>
        </div>
        <div>
          <IconButton>
            {true ? (
              <BookmarkIcon sx={{ color: "#053075" }} />
            ) : (
              <BookmarkBorderIcon sx={{ color: "#053075" }} />
            )}
          </IconButton>
        </div>
      </CardActions>

      {showComments && (
        <section>
          <div className="postCard_ponerComentario">
            <Avatar sx={{ marginRight: "20px" }} />
            <input
              // onKeyDown={(e)}
              className="postCard_ponerComentario__caja"
              onKeyPress={(e) => {
                if (e.key === "Enter") {
                  handleCreateComment(e.target.value);
                  console.log("pulsando entre.....", e.target.value);
                }
              }}
              type="text"
              placeholder="Escribe tu comentario..."
            />
          </div>
          <Divider />
          <div>
            {item.comments?.map((comment) => (
              <div className="postCard_comentarios" key={comment.id}>
                <Avatar>{comment.user.nombre}</Avatar>
                <p className="postCard_comentarios__texto">{comment.content}</p>
              </div>
            ))}
          </div>
        </section>
      )}
    </Card>
  );
};

export default PostCard;
