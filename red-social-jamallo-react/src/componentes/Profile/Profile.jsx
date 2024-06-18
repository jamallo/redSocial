import React from "react";
import { useParams } from "react-router-dom";
import "../../styles/Perfil.scss";
import { Avatar, Box, Button, Card, Tab, Tabs } from "@mui/material";
import PostCard from "../Post/PostCard";
import UserReelCard from "../Reels/UserReelCard";
import { useSelector } from "react-redux";
import Boton from "../Botones/Boton";
import ProfileModel from "./ProfileModel";

const tabs = [
  { value: "post", name: "Post" },
  { value: "reels", name: "Reels" },
  { value: "saved", name: "Saved" },
  { value: "repost", name: "Repost" },
];

const reels = [1, 1, 1, 1];
const savedPost = [1, 1, 1, 1];
const repost = [1, 1, 1, 1];

const Profile = () => {
  const{auth, post} = useSelector((store)=>store);
  const { id } = useParams();

  const [value, setValue] = React.useState("post");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const [open, setOpen] = React.useState(false);
  const handleOpenProfileModal = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const posts = post.posts.filter(p => p.userId === auth.user.id);

  return (
    <div className="Perfil">
      <Card
        sx={{
          bgcolor: "#b1cffbae",
          borderRadius: "20px",
          padding: "20px",
          marginTop: "20px",
        }}
      >
        <div className="Perfil__contenido">
          <div className="Perfil_contenido_central">
            <img
              className="Perfil_contenido_central__img"
              src=""
              alt="imagen perfil"
            />
          </div>
          <div className="Perfil__contenido__daatos">
            <Avatar
              className="Perfil__contenido__daatos__avatar"
              sx={{
                width: "8rem",
                height: "8rem",
                bgcolor: "#053075",
              }}
            />
            {true ? (
              <Boton
                text="Editar Perfil"
                className="Perfil__contenido__daatos__Boton"
                onClick={handleOpenProfileModal}
              />
            ) : (
              <Button
                variant="outlined"
                sx={{
                  borderRadius: "20px",
                  color: "#053075",
                  borderColor: "#053075",
                }}
                onClick={handleOpenProfileModal}
              >
                Seguir
              </Button>
            )}
          </div>
          <div className="Perfil__contenido__masDatos">
            <div>
              <h1 className="Perfil__contenido__masDatos__nombre">
              {auth.user?.nombre + " " + auth.user?.apellidos}
              </h1>
              <p>@{auth.user?.nombre.toLowerCase() + "_" + auth.user?.apellidos.toLowerCase()}</p>
            </div>
            <div className="Perfil__contenido__masDatos__perfil">
              <span>41 post</span>
              <span>32 follows</span>
              <span>22 followings</span>
            </div>
            <div>
              <p>
                Algo de texto que ponga el usuario para escribir algo sobre él y
                saber más.
              </p>
            </div>
            <section>
              <Box
                sx={{
                  width: "100%",
                  borderBottom: 1,
                  borderColor: "#053075",
                  color: "#053075",
                }}
              >
                <Tabs
                  value={value}
                  onChange={handleChange}
                  aria-label="wrapped label tabs example"
                >
                  {tabs.map((item) => (
                    <Tab value={item.value} label={item.name} wrapped />
                  ))}
                </Tabs>
              </Box>
              <div className="Perfil__contenido__masDatos__post">
                {value === "post" ? (
                  <div className="Perfil__contenido__masDatos__post__Card">
                    {posts.map((item) => (
                      <PostCard key={item.id} item={{...item, comments: item.comments || []}}/>
                    ))}
                  </div>
                ) : value === "reels" ? (
                  <div className="Perfil__contenido__masDatos_reels">
                    {reels.map((item, index) => (
                      <UserReelCard key={index}/>
                    ))}
                  </div>
                ) : value === "saved" ? (
                  <div className="Perfil__contenido__masDatos__post__Card">                    
                    {savedPost.map((item, index) => (
                      <PostCard key={index} item={item}/>
                    ))}
                  </div>
                ) : (
                  <div className="Perfil__contenido__masDatos__post__Card">                    
                    {repost.map((item, index) => (
                      <PostCard key={index} item={item}/>
                    ))}
                  </div>
                )}
              </div>
            </section>
          </div>
        </div>
        <section>
        <ProfileModel open={open} handleClose={handleClose}/>
        </section>
      </Card>
    </div>
  );
};

export default Profile;
