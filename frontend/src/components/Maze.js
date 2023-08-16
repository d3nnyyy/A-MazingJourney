import "../styles/Maze.css";
import { motion } from "framer-motion";
import spongebobImage from "../assets/spongebob.png";
import { useState, useEffect, useRef } from "react";
import axios from "axios";
import { Button } from "@mui/material";
import useWindowDimensions from "../functions/useWindowDimensions";
function Maze({
  showVisitedCells,
  showBestCells,
  shortestPath,
  showStats,
  setMazeStarted,
  mazeStarted,
  setStats,
  setOpenModal,
  destinationReached,
  setDestinationReached,
  path,
  setPath,
  setListenToEvents,
  listenToEvents,
  setPlayerPos,
  playerPos,
  moveDistance,
  maze,
  x,
  y,
  setX,
  setY,
}) {
  const showStatsRef = useRef(showStats);

  function containsSubArray(mainArray, subArray) {
    return mainArray.some(
      (item) => JSON.stringify(item) === JSON.stringify(subArray)
    );
  }

  function cellStyleCheck(cell, row, userPath, bestPath, showVisited, showBest) {
    if (
      containsSubArray(userPath, [cell, row]) &&
      containsSubArray(bestPath, [row, cell])
    ) {
      return (showVisited && showBest) ? "visited-best-cell" 
      : (showVisited ? "visited-cell" : "best-cell");
    } else if (
      containsSubArray(userPath, [cell, row]) &&
      !containsSubArray(bestPath, [row, cell])
    ) {
      return showVisited ? "visited-cell" : '';
    } else if (
      !containsSubArray(userPath, [cell, row]) &&
      containsSubArray(bestPath, [row, cell])
    ) {
      return showBest ? "best-cell" : '';
    } else {
      return "";
    }
  }

  const { height, width } = useWindowDimensions();

  useEffect(() => {
    showStatsRef.current = showStats;
  }, [showStats]);

  const mazeRef = useRef(maze);

  useEffect(() => {
    mazeRef.current = maze;
  }, [maze]);

  const pathRef = useRef(path);

  useEffect(() => {
    pathRef.current = path;
  }, [path]);

  const moveDistanceRef = useRef(moveDistance);
  useEffect(() => {
    moveDistanceRef.current = moveDistance;
  }, [moveDistance]);

  useEffect(() => {
    if (listenToEvents) {
      window.addEventListener("keydown", handleKeyPress);
    }

    return () => {
      if (listenToEvents) {
        window.removeEventListener("keydown", handleKeyPress);
      }
    };
    // eslint-disable-next-line
  }, [listenToEvents]);

  const animationFrame = useRef(null);
  const lastKeyPressTime = useRef(0);
  const [isMoving, setIsMoving] = useState(false);
  const playerPosRef = useRef(playerPos);

  useEffect(() => {
    playerPosRef.current = playerPos;
    if (
      playerPosRef.current[0] === mazeRef.current.length - 1 &&
      playerPosRef.current[1] === mazeRef.current.length - 1
    ) {
      setDestinationReached((destinationReached) => !destinationReached);
      setListenToEvents((listenToEvents) => !listenToEvents);
      setOpenModal(true);
      const URL = "a-mazing-journey.eu-central-1.elasticbeanstalk.com";
      axios
        .post(`http://${URL}/api/maze/stats`, {
          visitedCells: pathRef.current,
        })
        .then((res) => {
          setStats(res.data);
        })
        .catch((err) => console.log(err));
    }
    // eslint-disable-next-line
  }, [playerPos]);

  const moveDelay = 25;

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
        event.preventDefault();
        if (
          playerPosRef.current[1] === 0 ||
          mazeRef.current[playerPosRef.current[1] - 1][playerPosRef.current[0]]
        ) {
          break;
        }
        setY((prevY) => prevY - moveDistanceRef.current);
        setPlayerPos((playerPos) => [playerPos[0], playerPos[1] - 1]);
        setPath((path) => [
          ...path,
          [playerPosRef.current[0], playerPosRef.current[1] - 1],
        ]);
        break;
      case "KeyS":
      case "ArrowDown":
        event.preventDefault();
        if (
          playerPosRef.current[1] === mazeRef.current.length - 1 ||
          mazeRef.current[playerPosRef.current[1] + 1][playerPosRef.current[0]]
        ) {
          break;
        }
        setY((prevY) => prevY + moveDistanceRef.current);
        setPlayerPos((playerPos) => [playerPos[0], playerPos[1] + 1]);
        setPath((path) => [
          ...path,
          [playerPosRef.current[0], playerPosRef.current[1] + 1],
        ]);
        break;
      case "KeyA":
      case "ArrowLeft":
        if (
          playerPosRef.current[0] === 0 ||
          mazeRef.current[playerPosRef.current[1]][playerPosRef.current[0] - 1]
        ) {
          break;
        }
        setX((prevX) => prevX - moveDistanceRef.current);
        setPlayerPos((playerPos) => [playerPos[0] - 1, playerPos[1]]);
        setPath((path) => [
          ...path,
          [playerPosRef.current[0] - 1, playerPosRef.current[1]],
        ]);
        break;
      case "KeyD":
      case "ArrowRight":
        if (
          playerPosRef.current[0] === mazeRef.current.length - 1 ||
          mazeRef.current[playerPosRef.current[1]][playerPosRef.current[0] + 1]
        ) {
          break;
        }
        setX((prevX) => prevX + moveDistanceRef.current);
        setPlayerPos((playerPos) => [playerPos[0] + 1, playerPos[1]]);
        setPath((path) => [
          ...path,
          [playerPosRef.current[0] + 1, playerPosRef.current[1]],
        ]);
        break;
      default:
        break;
    }
  };

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
    <div className={`maze-container ${destinationReached ? "" : ""}`}>
      <div className={` ${mazeStarted ? "hidden" : "absolute-position"}`}>
        <motion.div
          initial={{ opacity: 0.5, scale: 0.8 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ duration: 0.5 }}
        >
          <Button
            size="large"
            variant="contained"
            color="secondary"
            sx={{ m: 3, color: "white" }}
            onClick={() => {
              setListenToEvents((listenToEvents) => !listenToEvents);
              setMazeStarted((mazeStarted) => !mazeStarted);
            }}
          >
            Start
          </Button>
        </motion.div>
      </div>
      <div className="rows">
        {maze.map((row, rowIndex) => (
          <div key={rowIndex} className="row">
            {row.map((cell, cellIndex) => (
              <div
                style={{
                  width: `${(height * 0.8) / maze.length}px`,
                  height: `${(height * 0.8) / maze.length}px`,
                }}
                key={cellIndex}
                className={`cell ${cell === true ? "wall" : "path"} ${
                  mazeStarted ? "" : "blur"
                } ${
                  rowIndex === 0 && cellIndex === 0
                    ? "start rounded-top-left"
                    : ""
                } ${
                  rowIndex === maze.length - 1 && cellIndex === maze.length - 1
                    ? "end rounded-bottom-right"
                    : ""
                }
                ${
                  rowIndex === 0 && cellIndex === maze.length - 1
                    ? "rounded-top-right"
                    : ""
                }
                ${
                  rowIndex === maze.length - 1 && cellIndex === 0
                    ? "rounded-bottom-left"
                    : ""
                }
                ${
                  showStats
                    ? cellStyleCheck(cellIndex, rowIndex, path, shortestPath, showVisitedCells, showBestCells)
                    : ""
                }

                `}
              >
                {rowIndex === 0 && cellIndex === 0 && (
                  <motion.img
                    style={{
                      width: `${(height * 0.4) / maze.length}px`,
                      height: `${(height * 0.4) / maze.length}px`,
                    }}
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
  );
}

export default Maze;
