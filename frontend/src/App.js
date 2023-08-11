import "./App.css";
import { useState, useEffect, useRef } from "react";
import { motion } from "framer-motion";
import spongebobImage from "./assets/spongebob.png";
import { Slider, Box, Typography, Tooltip, Button } from "@mui/material";
import InfoIcon from "@mui/icons-material/Info";
function App() {
  const maze = [
    [1, 1, 1, 1, 1, 1],
    [1, 0, 0, 0, 0, 1],
    [1, 1, 1, 1, 0, 1],
    [1, 0, 0, 1, 0, 1],
    [1, 0, 1, 1, 0, 1],
    [1, 1, 1, 1, 1, 1],
  ];

  const sizeMarks = [
    {
      value: 10,
      label: "10",
    },
    {
      value: 15,
      label: "15",
    },
  ];

  const difficultyMarks = [
    {
      value: 1, 
      label: "1"
    }, 
    {
      value: 10,
      label: '10'
    }
  ]

  const [x, setX] = useState(0);
  const [y, setY] = useState(0);
  const [isMoving, setIsMoving] = useState(false);
  const [playerPos, setPlayerPos] = useState([0, 0]);
  const [size, setSize] = useState(12);
  const [difficulty, setDifficulty] = useState(5);
  const moveDistance = 101.6;
  const moveDelay = 125;

  const animationFrame = useRef(null);
  const lastKeyPressTime = useRef(0);

  const handleSizeChange = (event, value) => setSize(value);
  const handleDifficultyChange = (event, value) => setDifficulty(value);

  const handleKeyPress = (event) => {
    const currentTime = Date.now();
    if (currentTime - lastKeyPressTime.current < moveDelay) {
      return;
    }

    lastKeyPressTime.current = currentTime;

    setIsMoving(true);

    switch (event.code) {
      case "KeyW":
      case "ArrowUp":
        setY((prevY) => prevY - moveDistance);
        setPlayerPos((playerPos) => [playerPos[0], playerPos[1] + 1]);
        break;
      case "KeyS":
      case "ArrowDown":
        setY((prevY) => prevY + moveDistance);
        setPlayerPos((playerPos) => [playerPos[0], playerPos[1] - 1]);
        break;
      case "KeyA":
      case "ArrowLeft":
        setX((prevX) => prevX - moveDistance);
        setPlayerPos((playerPos) => [playerPos[0] - 1, playerPos[1]]);
        break;
      case "KeyD":
      case "ArrowRight":
        setX((prevX) => prevX + moveDistance);
        setPlayerPos((playerPos) => [playerPos[0] + 1, playerPos[1]]);
        break;
      default:
        break;
    }
  };

  useEffect(() => {
    console.log(playerPos);
  }, [playerPos]);

  useEffect(() => {
    window.addEventListener("keydown", handleKeyPress);

    return () => {
      window.removeEventListener("keydown", handleKeyPress);
    };
  }, []);

  useEffect(() => {
    if (isMoving) {
      cancelAnimationFrame(animationFrame.current);

      const animate = () => {
        animationFrame.current = requestAnimationFrame(animate);
      };

      animate();
    }
  }, [isMoving]);

  return (
    <div className="App">
      <div className="app-container">
        <div className="maze-container">
          <div class="rows">
            {maze.map((row, rowIndex) => (
              <div key={rowIndex} className="row">
                {row.map((cell, cellIndex) => (
                  <div
                    key={cellIndex}
                    className={`cell ${cell === 0 ? "wall" : "path"} ${
                      rowIndex === 0 && cellIndex === 0 ? "start" : ""
                    }`}
                  >
                    {rowIndex === 0 && cellIndex === 0 && (
                      <motion.img
                        className="img-spongebob"
                        initial={{ opacity: 0, scale: 0.5 }}
                        animate={{ opacity: 1, scale: 1, x: x, y: y }}
                        transition={{ type: "tween" }}
                        src={spongebobImage}
                        alt="spongebob"
                      />
                    )}
                  </div>
                ))}
              </div>
            ))}
          </div>
        </div>
        <div className="input-container">
          <Box sx={{ width: "25vw" }}>
            <Box sx={{display:'flex'}}>
              <Typography>
                Choose the size of the maze:
              </Typography>
              <Tooltip title="The maze is generated as an n*n square, where n is the size of the maze.">
                  <InfoIcon sx={{width:'15px', height:'15px', color:'#36454F', ml: 0.5, mt: 0.5}} />
                </Tooltip>
            </Box>

            <Slider
              defaultValue={12}
              min={10}
              max={15}
              step={1}
              marks={sizeMarks}
              aria-label="Default"
              valueLabelDisplay="auto"
              value={size}
              onChange={handleSizeChange}
              color="secondary"
            />
             <Box sx={{display:'flex'}}>
              <Typography>
                Choose the difficulty of the maze:
              </Typography>
              <Tooltip title="The higher the difficulty of the maze, the more complex will the generated maze be.">
                  <InfoIcon sx={{width:'15px', height:'15px', color:'#36454F', ml: 0.5, mt: 0.5}} />
                </Tooltip>
            </Box>
            <Slider
              defaultValue={5}
              min={1}
              max={10}
              step={1}
              marks={difficultyMarks}
              aria-label="Default"
              valueLabelDisplay="auto"
              value={difficulty}
              onChange={handleDifficultyChange}
              color="secondary"
            />
          </Box>
          <Button variant="contained" color="secondary" sx={{ml: 15, mr: 15}}>Generate maze</Button>
        </div>
      </div>
      {/* <div className="vertical-line"></div>
      <div className="horizontal-line"></div> */}
    </div>
  );
}

export default App;
