import "./styles/App.css"
import { useState } from "react";
import Maze from "./Maze";
import Logo from "./Logo"
import { Slider, Box, Typography, Tooltip, Button } from "@mui/material";
import InfoIcon from "@mui/icons-material/Info";
import axios from "axios";
function App() {
  const [playerPos, setPlayerPos] = useState([0, 0]);
  const [size, setSize] = useState(12);
  const [difficulty, setDifficulty] = useState(5);
  const [maze, setMaze] = useState()
  const handleSizeChange = (event, value) => setSize(value);
  const handleDifficultyChange = (event, value) => setDifficulty(value);
  const [x, setX] = useState(0);
  const [y, setY] = useState(0);
  const [moveDistance, setMoveDistance] = useState(0)
  const handleSubmit = () => {
    axios.post('http://localhost:8080/api/maze/generate', {
      difficulty: difficulty,
      size: size
    })
    .then(res => {setMaze(res.data)
    setX(0)
    setY(0)
    setMoveDistance(600/res.data.length)
    setPlayerPos([0, 0])
    })
    .catch(err => console.log(err))
  }
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
      label: "1",
    },
    {
      value: 10,
      label: "10",
    },
  ];


  return (
    <div className="App">
      <div className="app-container">
        {maze ? <Maze playerPos={playerPos} setPlayerPos={setPlayerPos} moveDistance={moveDistance} maze={maze} x={x} y={y} setX = {setX} setY = {setY} /> : <Logo/>}
        <div className="input-container">
          <Box sx={{ width: "25vw" }}>
            <Box sx={{ display: "flex" }}>
              <Typography>Choose the size of the maze:</Typography>
              <Tooltip title="The maze is generated as an n*n square, where n is the size of the maze.">
                <InfoIcon
                  sx={{
                    width: "15px",
                    height: "15px",
                    color: "#36454F",
                    ml: 0.5,
                    mt: 0.5,
                  }}
                />
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
            <Box sx={{ display: "flex" }}>
              <Typography>Choose the difficulty of the maze:</Typography>
              <Tooltip title="The higher the difficulty of the maze, the more complex will the generated maze be.">
                <InfoIcon
                  sx={{
                    width: "15px",
                    height: "15px",
                    color: "#36454F",
                    ml: 0.5,
                    mt: 0.5,
                  }}
                />
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
          <Button variant="contained" color="secondary" sx={{ ml: 15, mr: 15 }} onClick={handleSubmit}>
            Generate maze
          </Button>
        </div>
      </div>
    </div>
  );
}

export default App;
