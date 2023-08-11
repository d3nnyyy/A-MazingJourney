import "./styles/Maze.css"
import { motion } from "framer-motion";
import spongebobImage from "./assets/spongebob.png";
import { useState, useEffect, useRef } from "react";
function Maze ({ maze }) {
      useEffect(() => {
        window.addEventListener("keydown", handleKeyPress);
    
        return () => {
          window.removeEventListener("keydown", handleKeyPress);
        };
      }, []);
    
    const animationFrame = useRef(null);
    const lastKeyPressTime = useRef(0);
    const [x, setX] = useState(0);
    const [y, setY] = useState(0);
    const [isMoving, setIsMoving] = useState(false);
    const [playerPos, setPlayerPos] = useState([0, 0]);
        const handleKeyPress = (event) => {
        const moveDistance = 600/(maze.length);
        const moveDelay = 125;
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
        if (isMoving) {
          cancelAnimationFrame(animationFrame.current);
    
          const animate = () => {
            animationFrame.current = requestAnimationFrame(animate);
          };
    
          animate();
        }
      }, [isMoving]);
    return (
        <div className="maze-container">
          <div class="rows">
            {maze.map((row, rowIndex) => (
              <div key={rowIndex} className="row">
                {row.map((cell, cellIndex) => (
                  <div
                    style={{width:`${600/maze.length}px`, height:`${600/maze.length}px`}}
                    key={cellIndex}
                    className={`cell ${cell === true ? "wall" : "path"} ${
                      rowIndex === 0 && cellIndex === 0 ? "start" : ""
                    }`}
                  >
                    {rowIndex === 0 && cellIndex === 0 && (
                      <motion.img
                        style={{width:`${300/maze.length}px`, height:`${300/maze.length}px`}}
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
    )
}

export default Maze