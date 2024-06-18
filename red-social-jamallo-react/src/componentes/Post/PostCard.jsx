import {
  Avatar,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  Divider,
  IconButton,
  Typography,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import { blue } from "@mui/material/colors";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import ShareIcon from "@mui/icons-material/Share";
import StarIcon from "@mui/icons-material/Star";
import ChatBubbleIcon from "@mui/icons-material/ChatBubble";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import "../../styles/PostCard.scss";
import { createCommentAction, likePostAction } from "../../Redux/Post/post.action";
import { useDispatch, useSelector } from "react-redux";
import { isLikedByReqUser } from "../../utils/isLikedByReqUser";

const PostCard = ({ item }) => {
  const [showComments, setShowComments] = useState(false);
  const [localComments, setLocalComments] = useState(item.comments || []);
  const dispatch = useDispatch();
  const { auth, post } = useSelector((store) => store);
  const currentPost = post.posts.find(p => p.id === item.id) || item;

  const handleShowComment = () => setShowComments(!showComments);

  const handleCreateComment = async (content) => {
    const reqData = {
      postId: item.id,
      data: {
        content
      },
    };

    const response = await dispatch(createCommentAction(reqData));
        if (response.id) {
            setLocalComments([...localComments, response]);
        }
    };

    const handleLikePost = async () => {
      dispatch(likePostAction(item.id));
    };
    

  useEffect(() => {
    setLocalComments(item.comments);
}, [item.comments, post.newComment]);

const liked = isLikedByReqUser(auth.user.id, item);

console.log("gusta: " + liked)

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
        title={currentPost.user.nombre + " " + currentPost.user.apellidos}
        subheader={`@${currentPost.user.nombre.toLowerCase()}_${currentPost.user.apellidos.toLowerCase()}`}
        
      />
      <img className="Card__imagenesPost" src={item.image} alt="imagen post"/>
      <CardContent>
        <Typography variant="body2" sx={{ color: "#053075" }}>
          {currentPost.titulo}
        </Typography>
      </CardContent>

      <CardActions className="Post__acciones" disableSpacing>
        <div>
          <IconButton onClick={handleLikePost}>
            {liked ? (
              <StarIcon sx={{ color: blue[900] }} />
             ) : (
              <StarBorderIcon sx={{ color: "#053075" }} />
            )}
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
            <BookmarkBorderIcon sx={{ color: "#053075" }} />
          </IconButton>
        </div>
      </CardActions>

      {showComments && (
        <section>
          <div className="postCard_ponerComentario">
            <Avatar sx={{ marginRight: "20px" }} />
            <input
              className="postCard_ponerComentario__caja"
              onKeyPress={(e) => {
                if (e.key === "Enter" && e.target.value.trim()) {
                  handleCreateComment(e.target.value.trim());
                  e.target.value = '';
                }
              }}
              type="text"
              placeholder="Escribe tu comentario..."
            />
          </div>
          <Divider />
          <div>
            {localComments.map((comment) => (
              <div className="postCard_comentarios" key={comment.id}>
                <Avatar>{comment.user.nombre.charAt(0)}</Avatar>
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
