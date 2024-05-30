import { Avatar, Card, IconButton } from "@mui/material";
import React, { useState } from "react";
import "../../styles/MiddlePart.scss";
import AddIcon from "@mui/icons-material/Add";
import BotonMas from "../Botones/BotonPerfil";
import PersonIcon from "@mui/icons-material/Person";
import ImageIcon from '@mui/icons-material/Image';
import VideocamIcon from '@mui/icons-material/Videocam';
import ArticleIcon from '@mui/icons-material/Article';
import PostCard from "../Post/PostCard";
import CreatePostModelo from "../CreatePost/CreatePostModelo";

const story = [1, 1, 1, 1, 1, 1];
const posts = [1, 1, 1, 1, 1, 1];
const MiddlePart = () => {

  const [openCreateModelo, setOpenCreatePostModelo] = useState(false);

  const handleCloseCreatePostModelo = () => setOpenCreatePostModelo(false);


  const handleOpenCreatePostModal = () => {
    setOpenCreatePostModelo(true);
    console.log ("abriendo post model...")
  };

  return (
    <div className="MiddlePart">
      <section className="MiddlePart__perfiles">
        <div className="MiddlePart__perfiles">
          <BotonMas>
            <AddIcon/>
          </BotonMas>
          {story.map((item) => (
            <BotonMas>
              <PersonIcon />
            </BotonMas>
          ))}
        </div>
      </section>
      <Card className="contenido" sx={{ backgroundColor: "#b1cffbae", borderRadius: "20px"}}>
        <div className="MiddlePart__contenido">
          <Avatar sx={{bgcolor: "#053075", margin: "20px"}}/>
          <input 
            onClick={handleOpenCreatePostModal}
            readOnly
            className="Buscador" 
            type="text"/>
        </div>
        <div className="contenido__contenido">
          <div className="contenido__contenido__opciones">
            <IconButton onClick={handleOpenCreatePostModal} >
              <ImageIcon sx={{color: "#053075"}}/>
              <span>imagen</span>
            </IconButton>

          </div>

          <div className="contenido__contenido__opciones">
            <IconButton onClick={handleOpenCreatePostModal} >
              <VideocamIcon sx={{color: "#053075"}}/>
              <span>video</span>
            </IconButton>

            

          </div>

          <div className="contenido__contenido__opciones">
            <IconButton onClick={handleOpenCreatePostModal}>
              <ArticleIcon sx={{color: "#053075"}}/>
              <span>artículo</span>
            </IconButton>

            

          </div>

        </div>
      </Card>
      <div className="Post">
      {posts.map((item) => <PostCard/>)}
      

      </div>
      <div>
        <CreatePostModelo handleClose={handleCloseCreatePostModelo} open={openCreateModelo}/>
      </div>
    </div>
  );
};

export default MiddlePart;
