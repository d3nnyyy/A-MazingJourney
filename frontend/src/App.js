import "./styles/App.css";
import { useState } from "react";
import Maze from "./components/Maze";
import Logo from "./components/Logo";
import { motion } from "framer-motion";
import ModalWindow from "./components/ModalWindow";
import { Slider, Box, Typography, Tooltip, Button, Switch } from "@mui/material";
import InfoIcon from "@mui/icons-material/Info";
import axios from "axios"; 
import getStats from "./functions/stats";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import useWindowDimensions from "./functions/useWindowDimensions";
import Start from "./components/Start";
function App() {
  const theme = createTheme({
    palette: {
      primary: {
        main: "#e30b5a",
      },
      secondary: {
        main: "#0be394",
      },
    },
  });

  const { height, width } = useWindowDimensions()

  const [gameStarted, setGameStarted] = useState(false)
  const [showVisitedCells, setShowVisitedCells] = useState(true)
  const [showBestCells, setShowBestCells] = useState(true)
  const [shortestPath, setShortestPath] = useState()
  const [showStats, setShowStats] = useState(false);
  const [stats, setStats] = useState("");
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

  const handleSubmit = () => {
    const URL = "a-mazing-journey.eu-central-1.elasticbeanstalk.com";
    axios
      .post(`http://${URL}/api/maze/generate`, {
        difficulty: difficulty,
        size: size,
      })
      .then((res) => {
        setShortestPath(res.data.shortestPath)
        setMaze(res.data.maze);
        setStats("");
        setX(0);
        setY(0);
        setMoveDistance((height*0.8) / res.data.maze.length);
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

  return (
    <div className="App">
      <ThemeProvider theme={theme}>
        {destinationReached && (
          <ModalWindow
            setShowStats={setShowStats}
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
              showBestCells={showBestCells}
              showVisitedCells={showVisitedCells}
              shortestPath={shortestPath}
              showStats={showStats}
              setMazeStarted={setMazeStarted}
              mazeStarted={mazeStarted}
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
          {gameStarted ? <div className='input-container'>
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
                sx ={{'& .MuiSlider-markLabel'
                :{
                  color:"white",
                },
                '& .MuiSlider-markLabelActive':{
                  color:"white"
                }
              }}
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
                sx ={{'& .MuiSlider-markLabel'
                :{
                  color:"white",
                },
                '& .MuiSlider-markLabelActive':{
                  color:"white"
                }
              }}
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
                      sx={{ m: 3, color: "white" }}
                      onClick={handleSubmit}
                    >
                      Regenerate
                    </Button>
                  </motion.div>
                </div>
              ) : (
                <>
                <Box sx={{display:'flex'}}>
                <Typography>Show only your path
                  <Switch color="secondary"  onClick={() => {setShowBestCells(value => !value)}}/>
                </Typography>
                <Typography>Show only best path
                  <Switch color="secondary" onClick={() => {setShowVisitedCells(value => !value)}}/>
                </Typography>
                </Box>
                <motion.div
                  initial={{ opacity: 0.5, scale: 0.8 }}
                  animate={{ opacity: 1, scale: 1 }}
                  transition={{ duration: 0.5 }}
                >
                  <Button
                    variant="contained"
                    color="secondary"
                    sx={{ m: 3, color: "white" }}
                    onClick={() => {
                      setStats("");
                      setX(0);
                      setY(0);
                      setPlayerPos([0, 0]);
                      setPath([[0, 0]]);
                      setListenToEvents(true);
                      setDestinationReached(false);
                      setShowStats(false)
                    }}
                  >
                    Reset
                  </Button>
                </motion.div>
                </>
              )
            ) : (
              <div style={{display:"flex"}}>
                <motion.div
                  initial={{ opacity: 0.5, scale: 0.8 }}
                  animate={{ opacity: 1, scale: 1 }}
                  transition={{ duration: 0.25 }}
                >
                  <Button
                    variant="contained"
                    color="secondary"
                    sx={{ m: 3, color: "white" }}
                    onClick={handleSubmit}
                  >
                    Generate maze
                  </Button>
                </motion.div>
                <motion.div
                  initial={{ opacity: 0.5, scale: 0.8 }}
                  animate={{ opacity: 1, scale: 1 }}
                  transition={{ duration: 0.25 }}
                >
                  <Button
                    variant="contained"
                    color="secondary"
                    sx={{ m: 3, color: "white" }}
                    onClick={() => {
                      setGameStarted(gameStarted => !gameStarted)
                    }}
                  >
                    Home
                  </Button>
                </motion.div>
              </div>
            )}
            {stats && getStats(stats)}
          </div> : <Start setGameStarted={setGameStarted}/>}
        </div>
      </ThemeProvider>
    </div>
  );
}

export default App;
