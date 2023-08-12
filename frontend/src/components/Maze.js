import "../styles/Maze.css";
import { motion } from "framer-motion";
import spongebobImage from "../assets/spongebob.png";
import { useState, useEffect, useRef } from "react";
import axios from "axios";
function Maze({ setOpenModal, destinationReached, setDestinationReached, path, setPath, setListenToEvents, listenToEvents, setPlayerPos, playerPos, moveDistance, maze, x, y, setX, setY }) {
  const mazeRef = useRef(maze)
  useEffect(() => {
    mazeRef.current = maze;
  }, [maze]);
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
    if (playerPosRef.current[0] === mazeRef.current.length - 1 && playerPosRef.current[1] === mazeRef.current.length - 1){
      setDestinationReached(destinationReached => !destinationReached)
      setListenToEvents(listenToEvents => !listenToEvents)
      setOpenModal(true)
      axios.post("http://localhost:8080/api/character/traveled-path", {
        "visitedCells": path
      })
      .then(res => console.log(res))
      .catch(err => console.log(err))
    }
    // eslint-disable-next-line
  }, [playerPos]);
  const moveDelay = 125;
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
        if (playerPosRef.current[1] === 0 || (mazeRef.current[playerPosRef.current[1]-1][playerPosRef.current[0]])){
          break
        }
        setY((prevY) => prevY - moveDistanceRef.current);
        setPlayerPos((playerPos) => [playerPos[0], playerPos[1] - 1]);
        setPath(path => [...path, [playerPosRef.current[0], playerPosRef.current[1]-1]])
        break;
      case "KeyS":
      case "ArrowDown":
        if ((playerPosRef.current[1] === mazeRef.current.length-1) || (mazeRef.current[playerPosRef.current[1]+1][playerPosRef.current[0]])){
          break
        }
        setY((prevY) => prevY + moveDistanceRef.current);
        setPlayerPos((playerPos) => [playerPos[0], playerPos[1] + 1]);
        setPath(path => [...path, [playerPosRef.current[0], playerPosRef.current[1]+1]])
        break;
      case "KeyA":
      case "ArrowLeft":
        if (playerPosRef.current[0] === 0 || (mazeRef.current[playerPosRef.current[1]][playerPosRef.current[0]-1])){
          break
        }
        setX((prevX) => prevX - moveDistanceRef.current);
        setPlayerPos((playerPos) => [playerPos[0] - 1, playerPos[1]]);
        setPath(path => [...path, [playerPosRef.current[0]-1, playerPosRef.current[1]]])
        break;
      case "KeyD":
      case "ArrowRight":
        if (playerPosRef.current[0] === mazeRef.current.length-1 || (mazeRef.current[playerPosRef.current[1]][playerPosRef.current[0]+1])){
          break
        }
        setX((prevX) => prevX + moveDistanceRef.current);
        setPlayerPos((playerPos) => [playerPos[0] + 1, playerPos[1]]);
        setPath(path => [...path, [playerPosRef.current[0]+1, playerPosRef.current[1]]])
        break;
      default:
        break;
    }
  };
  // useEffect(() => {
  //   console.log(playerPos);
  // }, [playerPos]);
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
    <div className={`maze-container ${destinationReached ? '' : ''}`}>
      <div className="rows">
        {maze.map((row, rowIndex) => (
          <div key={rowIndex} className="row">
            {row.map((cell, cellIndex) => (
              <div
                style={{
                  width: `${700 / maze.length}px`,
                  height: `${700 / maze.length}px`,
                }}
                key={cellIndex}
                className={`cell ${cell === true ? "wall" : "path"} ${
                  rowIndex === 0 && cellIndex === 0 ? "start" : ""
                }`}
              >
                {rowIndex === 0 && cellIndex === 0 && (
                  <motion.img
                    style={{
                      width: `${350 / maze.length}px`,
                      height: `${350 / maze.length}px`,
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
