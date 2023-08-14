import { Button } from "@mui/material";
import { motion } from "framer-motion";
import "../styles/Start.css"
function Start ({ setGameStarted }) {
  
  const handleClick = () => {
    setGameStarted(gameStarted => !gameStarted);
  };

  return (
    <div className="start-container">
      <h1>A-Mazing Journey</h1>
      <p>
        Lorem ipsum dolor sit, amet consectetur adipisicing elit. Minus fugit
        quaerat natus quibusdam id? Laboriosam dolore libero tempora laudantium,
        possimus at, porro veritatis nisi minus, illo itaque! Ipsum, natus
        atque? Soluta amet rem pariatur ea. Doloribus rem facere et officia,
        eaque deleniti temporibus laudantium quod vitae suscipit maiores
        pariatur ipsa explicabo similique porro repellendus odio, quo labore,
        laboriosam odit molestias. A, culpa officia asperiores reprehenderit
        quos aliquid fuga distinctio cumque praesentium eum fugit beatae
        consectetur ex omnis animi rerum dolorum totam maiores accusamus!
        Repellendus harum quo tempore neque, quasi modi. Neque deserunt eaque
        nostrum voluptatem illo adipisci, quae alias quis quibusdam, earum
        voluptas vero tempora quos laborum corrupti placeat praesentium
        doloribus aut impedit deleniti nobis accusantium officiis voluptate.
        Voluptate, numquam! Officia sapiente unde cumque porro fugit
        repudiandae. Inventore, ratione sequi. Maiores veritatis dolorum placeat
        animi incidunt excepturi accusantium molestias vitae corrupti, earum
        quidem fugiat distinctio praesentium accusamus illo voluptatem voluptas?
      </p>
      <motion.div
        className="button-container"
        initial={{ opacity: 0.5, scale: 0.8 }}
        animate={{ opacity: 1, scale: 1 }}
        transition={{ duration: 0.25 }}
      >
        <Button sx={{color:"white"}} variant="contained" color="secondary" onClick={handleClick}>
          Play
        </Button>
      </motion.div>
    </div>
  );
};

export default Start;