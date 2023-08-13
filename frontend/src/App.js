import "./styles/App.css";
import { useState, useRef } from "react";
import Maze from "./components/Maze";
import Logo from "./components/Logo";
import { motion } from "framer-motion";
import ModalWindow from "./components/ModalWindow";
import { Slider, Box, Typography, Tooltip, Button } from "@mui/material";
import InfoIcon from "@mui/icons-material/Info";
import axios from "axios";
function App() {
  const [stats, setStats] = useState()
  const [path, setPath] = useState([[0, 0]]);
  const [openModal, setOpenModal] = useState(false);
  const [mazeStarted, setMazeStarted] = useState(false);
  const [destinationReached, setDestinationReached] = useState(false);
  const [listenToEvents, setListenToEvents] = useState(false);
  const [playerPos, setPlayerPos] = useState([0, 0]);
  const [size, setSize] = useState(10);
  const [difficulty, setDifficulty] = useState(5);
  const [maze, setMaze] = useState();
  const handleSizeChange = (event, value) => setSize(value);
  const handleDifficultyChange = (event, value) => setDifficulty(value);
  const [x, setX] = useState(0);
  const [y, setY] = useState(0);
  const [moveDistance, setMoveDistance] = useState(0);
  const handleClose = () => setOpenModal(false);

  const handleSubmit = () => {
    const URL = "a-mazing-journey.eu-central-1.elasticbeanstalk.com"
    axios
      .post(`http://${URL}/api/maze/generate`, {
        difficulty: difficulty,
        size: size,
      })
      .then((res) => {
        setMaze(res.data.maze);
        setX(0);
        setY(0);
        setMoveDistance(700 / res.data.maze.length);
        setPlayerPos([0, 0]);
      })
      .catch((err) => console.log(err));
  };
  const sizeMarks = [
    {
      value: 5,
      label: "5",
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

  // useEffect(() => {
  //   pathRef.current = path
  //   console.log(pathRef.current)
  // }, [path])

  return (
    <div className="App">
      {destinationReached && (
        <ModalWindow
          setMazeStarted={setMazeStarted}
          setDestinationReached={setDestinationReached}
          setPath={setPath}
          setPlayerPos={setPlayerPos}
          setX={setX}
          setY={setY}
          open={openModal}
          setOpenModal={setOpenModal}
        />
      )}
      <div className="app-container">
        {maze ? (
          <Maze
            setMazeStarted = {setMazeStarted}
            mazeStarted = {mazeStarted}
            setStats={setStats}
            destinationReached={destinationReached}
            setOpenModal={setOpenModal}
            setDestinationReached={setDestinationReached}
            path={path}
            setPath={setPath}
            setListenToEvents={setListenToEvents}
            listenToEvents={listenToEvents}
            playerPos={playerPos}
            setPlayerPos={setPlayerPos}
            moveDistance={moveDistance}
            maze={maze}
            x={x}
            y={y}
            setX={setX}
            setY={setY}
          />
        ) : (
          <Logo />
        )}
        <div className="input-container">
          <Box
            sx={
              mazeStarted
                ? { display: "none" }
                : { display: "block", width: "25vw" }
            }
          >
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
              defaultValue={5}
              min={5}
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
          {maze ? (
            !mazeStarted ? (
              <div className="regenerate-start-container">
                <motion.div
                  initial={{ opacity: 0.5, scale: 0.8 }}
                  animate={{ opacity: 1, scale: 1 }}
                  transition={{ duration: 0.5 }}
                >
                  <Button
                    variant="contained"
                    color="secondary"
                    sx={{ m: 3 }}
                    onClick={handleSubmit}
                  >
                    Regenerate
                  </Button>
                </motion.div>
              </div>
            ) : (
              <motion.div
              initial={{ opacity: 0.5, scale: 0.8 }}
              animate={{ opacity: 1, scale: 1 }}
              transition={{ duration: 0.5 }}
              >
                <Button
                  variant="contained"
                  color="secondary"
                  sx={{ m: 3 }}
                  onClick={() => {
                    setX(0);
                    setY(0);
                    setPlayerPos([0, 0]);
                    setPath([[0,0]]);
                    setListenToEvents(true);
                    setDestinationReached(false);
                  }}
                >
                  Reset
                </Button>
              </motion.div>
            )
          ) : (
            <motion.div
            initial={{ opacity: 0.5, scale: 0.8 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ duration: 0.25 }}>
              <Button
                variant="contained"
                color="secondary"
                sx={{ m: 3 }}
                onClick={handleSubmit}
              >
                Generate maze
              </Button>
            </motion.div>
          )}
        </div>
      </div>
    </div>
  );
}

export default App;
