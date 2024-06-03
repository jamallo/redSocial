import {
  Avatar,
  Backdrop,
  Box,
  CircularProgress,
  IconButton,
  Modal,
} from "@mui/material";
import { Formik, useFormik } from "formik";
import React, { useState } from "react";
import ImageIcon from "@mui/icons-material/Image";
import VideoCallIcon from "@mui/icons-material/VideoCall";
import { cargarANube, uploadToCloudinary } from "../../utils/cargarANube";
import Boton from "../Botones/Boton";
import "../../styles/CreatePostModelo.scss"
import { useDispatch } from "react-redux";
import { createPostAction } from "../../Redux/Post/post.action";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 500,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
  borderRadius: ".6rem",
  outline: "none",
};

const CreatePostModelo = ({ handleClose, open }) => {
  
  const [selectedImage, setSelectedImage] = useState();
  const [selectedVideo, setSelectedVideo] = useState();
  const [isLoading, setIsLoading] = useState(false);
  const dispatch=useDispatch();

  const handleSelectImagen = async(even) => {
    setIsLoading(true);
    const imageUrl = await cargarANube(even.target.files[0]);
    setSelectedImage(imageUrl);
    setIsLoading(false);
    formik.setFieldValue("image", imageUrl);
  };

  const handleSelectVideo = async(even) => {
    setIsLoading(true);
    const videoUrl = await cargarANube(even.target.files[0]);
    setSelectedVideo(videoUrl);
    setIsLoading(false);
    formik.setFieldValue("video", videoUrl);
  };

  const formik = useFormik({
    initialValues: {
      caption:"",
      imagen: "",
      video: ""
    },
    onSubmit:(values)=> {
      console.log("valores formik", values);
      dispatch(createPostAction(values));
    }
  });
  return (
    <>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <form onSubmit={formik.handleSubmit}>
            <div>
              <div className="ventanaArticulo">
                <Avatar />
                <div>
                  <p className="ventanaArticulo__nombre">Nombre Persona</p>
                  <p className="ventanaArticulo__nick">@llamada</p>
                </div>
              </div>

              <textarea
                className="ventanaArticulo_comentario"
                placeholder="Escribe lo que pienses ..."
                name="caption"
                id=""
                onChange={formik.handleChange}
                value={formik.values.caption}
                rows="4"
              ></textarea>
              <div className="ventana">
                <div>
                  <input
                    type="file"
                    accept="image/*"
                    onChange={handleSelectImagen}
                    id="image-input"
                  />
                  <label htmlFor="image-input">
                    <IconButton component="span">
                      <ImageIcon/>
                    </IconButton>
                  </label>
                  <span>Imagen</span>
                </div>
                <div>
                  <input
                    type="file"
                    accept="video/*"
                    onChange={handleSelectVideo}
                    id="video-input"
                  />
                  <label htmlFor="video-input">
                    <IconButton component="span">
                      <VideoCallIcon />
                    </IconButton>
                  </label>
                  <span>Video</span>
                </div>
              </div>
              {selectedImage && (
                <div>
                  <img className="image" src={selectedImage} alt="Seleccionada" />
                </div>
              )}

              <div>
                <Boton type= "submit" text = "Post"/>
              </div>
            </div>
          </form>
          <Backdrop
            sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
            open={isLoading}
            onClick={handleClose}
          >
            <CircularProgress color="inherit" />
          </Backdrop>
        </Box>
      </Modal>
    </>
  );
};

export default CreatePostModelo;
