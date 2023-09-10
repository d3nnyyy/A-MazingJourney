import "./styles/App.css";
import { useState, useRef } from "react";
import Maze from "./components/Maze";
import Logo from "./components/Logo";
import { motion } from "framer-motion";
import ModalWindow from "./components/ModalWindow";
import {
  Slider,
  Box,
  Typography,
  Tooltip,
  Button,
  Switch,
} from "@mui/material";
import InfoIcon from "@mui/icons-material/Info";
import axios from "axios";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import useWindowDimensions from "./functions/useWindowDimensions";
import Start from "./components/Start";
import getStats from "./functions/stats";
import { useContainerDimensions } from "./functions/useContainerDimensions";
function App() {
  const theme = createTheme({
    palette: {
      primary: {
        main: "#e30b5a",
      },
      secondary: {
        main: "#e30b5a",
      },
    },
    typography: {
      fontSize: 18,
      fontFamily: [
        "Titillium Web",
        "Exo",
        "Orbitron",
        "-apple-system",
        "BlinkMacSystemFont",
        '"Segoe UI"',
        "Roboto",
      ].join(","),
    },
  });

  const { width, height } = useWindowDimensions();
  const divRef = useRef(null)
  const dimensions = useContainerDimensions(divRef)
  const [gameStarted, setGameStarted] = useState(false);
  const [showVisitedCells, setShowVisitedCells] = useState(true);
  const [showBestCells, setShowBestCells] = useState(true);
  const [shortestPath, setShortestPath] = useState();
  const [showStats, setShowStats] = useState(false);
  const [stats, setStats] = useState("");
  const [path, setPath] = useState([[0, 0]]);
  const [openModal, setOpenModal] = useState(false);
  const [mazeStarted, setMazeStarted] = useState(false);
  const [destinationReached, setDestinationReached] = useState(false);
  const [listenToEvents, setListenToEvents] = useState(false);
  const [playerPos, setPlayerPos] = useState([0, 0]);
  const [size, setSize] = useState(14);
  const [difficulty, setDifficulty] = useState(5);
  const [maze, setMaze] = useState();
  const handleSizeChange = (event, value) => setSize(value);
  const handleDifficultyChange = (event, value) => setDifficulty(value);
  const [x, setX] = useState(0);
  const [y, setY] = useState(0);
  const [moveDistance, setMoveDistance] = useState(0);

  const handleSubmit = () => {
    const URL = "a-mazing-journey-backend.azurewebsites.net";
    axios
      .post(`https://${URL}/api/maze/generate`, {
        difficulty: difficulty,
        size: size,
      })
      .then((res) => {
        setShortestPath(res.data.shortestPath);
        setMaze(res.data.maze);
        setStats("");
        setX(0);
        setY(0);
        setMoveDistance(changeMoveDistance(width) * 0.8 / res.data.maze.length);
        setPlayerPos([0, 0]);
        setShowBestCells(true);
        setShowVisitedCells(true)
      })
      .catch((err) => console.log(err));
  };

  const changeMoveDistance = (width) => {
    if (width > 1240) {
      return "700"
    }
    else if (1024 < width < 1240) {
      return "500"
    }
  }

  const sizeMarks = [
    {
      value: 8,
      label: "8",
    },
    {
      value: 20,
      label: "20",
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

  const shareToTelegram = () => {

    const userPathLength = path.length;
    const shortestPathLength = shortestPath.length;
    const percentage = (userPathLength / shortestPathLength) * 100;
    const percentageDifference = Math.round((percentage - 100));

    const subMessage = userPathLength === shortestPathLength ?
      "I've managed to solve the maze with the shortest path. Could you do it? " :
      `I've managed to solve the maze with the path which is ${percentageDifference}% longer than the shortest. I bet you could do it better! `

    const message = subMessage + "Try it out at http://a-mazing-journey.s3-website.eu-central-1.amazonaws.com/";
    const encodedMessage = encodeURIComponent(message);
    const telegramShareLink = `https://t.me/share/url?url=${encodedMessage}`;

    window.open(telegramShareLink, "_blank");
  };

  return (
    <div className="App">
      <ThemeProvider theme={theme}>
        {destinationReached && (
          <ModalWindow
            stats={stats}
            setStats={setStats}
            setListenToEvents={setListenToEvents}
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

        <div className="app-container" ref={divRef}>
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
          {gameStarted ? (
            <div className="input-container" >
              {!mazeStarted && <h1>Start the game by generating a maze</h1>}
              <Box
                sx={
                  mazeStarted
                    ? { display: "none" }
                    : { display: "block", width: width > 850 ? '25vw' : '60vw' }
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
                  defaultValue={14}
                  min={8}
                  max={20}
                  step={1}
                  marks={sizeMarks}
                  aria-label="Default"
                  valueLabelDisplay="auto"
                  value={size}
                  onChange={handleSizeChange}
                  color="secondary"
                  sx={{

                    "& .MuiSlider-markLabel": {
                      color: "white",
                    },
                    "& .MuiSlider-markLabelActive": {
                      color: "white",
                    },
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
                  sx={{
                    "& .MuiSlider-markLabel": {
                      color: "white",
                    },
                    "& .MuiSlider-markLabelActive": {
                      color: "white",
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
                  <div>
                    {!showStats ? (
                      <h1>A-Mazing Journey</h1>
                    ) : (
                      <h1>Your stats</h1>
                    )}
                    {!showStats ? (
                      <p style={{ fontSize: "18px" }}>
                        Try to get to the end of the maze in as few steps as
                        possible!
                      </p>
                    ) : (
                      <div style={{ fontSize: "18px" }}>
                        <p style={{ marginTop: "2rem" }}>
                          Your path length: {path.length} cells
                        </p>
                        <div
                          style={{
                            height: "4px",
                            width: `${dimensions.width / 2.5}px`,
                            backgroundColor: "#e3940b",
                          }}
                        />
                        <p style={{ marginTop: "2rem" }}>
                          Best/Shortest path length: {shortestPath.length} cells
                        </p>
                        <div
                          style={{
                            marginBottom: "1rem",
                            height: "4px",
                            width: `${dimensions.width / 2.5 * (shortestPath.length / path.length)}px`,
                            backgroundColor: "#5ae30b",
                          }}
                        />
                        <p style={{ marginTop: "2rem" }}>{getStats(stats)}</p>
                      </div>
                    )}
                    {showStats && (
                      <Box sx={{ display: "flex" }}>
                        <Typography>
                          Show best path
                          <Switch
                            defaultChecked={true}
                            disabled={!showVisitedCells}
                            color="secondary"
                            onClick={() => {
                              setShowBestCells((value) => !value);
                            }}
                          />
                        </Typography>
                        <Typography>
                          Show your path
                          <Switch
                            defaultChecked={true}
                            disabled={!showBestCells}
                            color="secondary"
                            onClick={() => {
                              setShowVisitedCells((value) => !value);
                            }}
                          />
                        </Typography>
                      </Box>
                    )}
                    <div
                      style={{
                        display: "flex",
                        flexDirection: "row-reverse",
                        justifyContent: "flex-end",
                      }}
                    >
                      <motion.div
                        initial={{ opacity: 0.5, scale: 0.8 }}
                        animate={{ opacity: 1, scale: 1 }}
                        transition={{ duration: 0.5 }}
                      >
                        {!showStats ? (
                          <Button
                            variant="contained"
                            color="secondary"
                            sx={{ m: 1, color: "white" }}
                            onClick={() => {
                              setStats("");
                              setX(0);
                              setY(0);
                              setPlayerPos([0, 0]);
                              setPath([[0, 0]]);
                              setListenToEvents(true);
                              setDestinationReached(false);
                              setShowStats(false);
                              setShowBestCells(true);
                              setShowVisitedCells(true)
                            }}
                          >
                            Reset
                          </Button>
                        ) : (
                          <Button
                            variant="contained"
                            color="secondary"
                            sx={{ m: 1, color: "white" }}
                            onClick={() => shareToTelegram()}
                          >
                            Share
                          </Button>
                        )}
                      </motion.div>
                      <motion.div
                        initial={{ opacity: 0.5, scale: 0.8 }}
                        animate={{ opacity: 1, scale: 1 }}
                        transition={{ duration: 0.5 }}
                      >
                        <Button
                          variant="contained"
                          color="secondary"
                          sx={{ mt: 1, mb: 1, mr: 1, color: "white", whiteSpace: "nowrap", minWidth: "auto" }}
                          onClick={() => {
                            setStats("");
                            setX(0);
                            setY(0);
                            setPlayerPos([0, 0]);
                            setPath([[0, 0]]);
                            setListenToEvents(false);
                            setDestinationReached(false);
                            setShowStats(false);
                            setMaze();
                            setMazeStarted(false);
                            setShowBestCells(true);
                            setShowVisitedCells(true);
                          }}
                        >
                          Try another
                        </Button>
                      </motion.div>
                      {showStats && (
                        <motion.div
                          initial={{ opacity: 0.5, scale: 0.8 }}
                          animate={{ opacity: 1, scale: 1 }}
                          transition={{ duration: 0.5 }}
                        >
                          <Button
                            variant="contained"
                            color="secondary"
                            sx={{ mt: 1, mb: 1, mr: 2, color: "white" }}
                            onClick={() => {
                              setStats("");
                              setX(0);
                              setY(0);
                              setPlayerPos([0, 0]);
                              setPath([[0, 0]]);
                              setListenToEvents(false);
                              setDestinationReached(false);
                              setShowStats(false);
                              setMaze();
                              setMazeStarted(false);
                              setGameStarted(false);
                              setShowBestCells(true);
                              setShowVisitedCells(true)
                            }}
                          >
                            Home
                          </Button>
                        </motion.div>
                      )}
                    </div>
                  </div>
                )
              ) : (
                <div style={{ display: "flex", flexDirection: "row-reverse" }}>
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
                      Generate
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
                        setGameStarted((gameStarted) => !gameStarted);
                      }}
                    >
                      Home
                    </Button>
                  </motion.div>
                </div>
              )}
            </div>
          ) : (
            <Start setGameStarted={setGameStarted} />
          )}
        </div>
      </ThemeProvider>
    </div>
  );
}

export default App;
