import "./App.css";
import { useState, useEffect, useRef } from "react";
import { motion } from "framer-motion";
import spongebobImage from "./assets/spongebob.png";
function App() {
  const maze = [
    [1, 1, 1, 1, 1, 1],
    [1, 0, 0, 0, 0, 1],
    [1, 1, 1, 1, 0, 1],
    [1, 0, 0, 1, 0, 1],
    [1, 0, 1, 1, 0, 1],
    [1, 1, 1, 1, 1, 1],
  ];

  const [x, setX] = useState(0);
  const [y, setY] = useState(0);
  const [isMoving, setIsMoving] = useState(false);
  const [playerPos, setPlayerPos] = useState([0, 0])
  const moveDistance = 101.6;
  const moveDelay = 125;

  const animationFrame = useRef(null);
  const lastKeyPressTime = useRef(0);

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
        setPlayerPos(playerPos => [playerPos[0],(playerPos[1]+1)])
        break;
      case "KeyS":
      case "ArrowDown":
        setY((prevY) => prevY + moveDistance);
        setPlayerPos(playerPos => [playerPos[0],(playerPos[1]-1)])
        break;
      case "KeyA":
      case "ArrowLeft":
        setX((prevX) => prevX - moveDistance);
        setPlayerPos(playerPos => [(playerPos[0]-1), playerPos[1]])
        break;
      case "KeyD":
      case "ArrowRight":
        setX((prevX) => prevX + moveDistance);
        setPlayerPos(playerPos => [(playerPos[0]+1), playerPos[1]])
        break;
      default:
        break;
    }
  };

  useEffect(() => {
    console.log(playerPos)
  }, [playerPos])

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
      <div className="maze">
        <div class="rows">
          {maze.map((row, rowIndex) => (
            <div key={rowIndex} className="row">
              {row.map((cell, cellIndex) => (
                <div
                  key={cellIndex}
                  className={`cell ${cell === 0 ? "wall" : "path"} ${
                    rowIndex === 0 && cellIndex === 0 ? "start" : ""
                  }`}>
                
                  {rowIndex === 0 && cellIndex === 0 && (
                      <motion.img
                      initial={{ opacity: 0, scale: 0.5}}
                      animate={{ opacity: 1, scale: 1, x:x, y:y}}
                      transition={{ type: "tween"}}
                      src={spongebobImage}
                      alt="spongebob"
                    />
                    
                      // <img src={spongebobImage} alt="spongebob" />
                  )}
                </div>
              ))}
            </div>
          ))}
        </div>
      </div>
      {/* <motion.div
          className="box"
          animate={{ x, y }}
          transition={{ type: "tween" }}
        /> */}
    </div>
  );
}

export default App;
