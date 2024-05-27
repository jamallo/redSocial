import React from "react";
import { useParams } from "react-router-dom";
import "../../styles/Perfil.scss";
import { Avatar, Box, Button, Card, Tab, Tabs } from "@mui/material";
import PostCard from "../Post/PostCard";
import UserReelCard from "../Reels/UserReelCard";

const tabs = [
  { value: "post", name: "Post" },
  { value: "reels", name: "Reels" },
  { value: "saved", name: "Saved" },
  { value: "repost", name: "Repost" },
];

const posts = [1, 1, 1, 1];
const reels = [1, 1, 1, 1];
const savedPost = [1, 1, 1, 1];
const repost = [1, 1, 1, 1];

const Profile = () => {
  const { id } = useParams();

  const [value, setValue] = React.useState("post");

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

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
              <Button
                variant="outlined"
                sx={{
                  borderRadius: "20px",
                  color: "#053075",
                  borderColor: "#053075",
                }}
              >
                Editar Perfil
              </Button>
            ) : (
              <Button
                variant="outlined"
                sx={{
                  borderRadius: "20px",
                  color: "#053075",
                  borderColor: "#053075",
                }}
              >
                Seguir
              </Button>
            )}
          </div>
          <div className="Perfil__contenido__masDatos">
            <div>
              <h1 className="Perfil__contenido__masDatos__nombre">
                nombre y apellidos
              </h1>
              <p>@nombrePersona</p>
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
                      <PostCard />
                    ))}
                  </div>
                ) : value === "reels" ? (
                  <div className="Perfil__contenido__masDatos_reels">
                    {reels.map((item) => (
                      <UserReelCard />
                    ))}
                  </div>
                ) : value === "saved" ? (
                  <div className="Perfil__contenido__masDatos__post__Card">                    
                    {savedPost.map((item) => (
                      <PostCard />
                    ))}
                  </div>
                ) : (
                  <div className="Perfil__contenido__masDatos__post__Card">                    
                    {repost.map((item) => (
                      <PostCard />
                    ))}
                  </div>
                )}
              </div>
            </section>
          </div>
        </div>
      </Card>
    </div>
  );
};

export default Profile;
