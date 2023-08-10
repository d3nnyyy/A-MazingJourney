import "./App.css";
import { useState, useEffect, useRef } from "react";
import { motion } from "framer-motion";

function App() {
  const [x, setX] = useState(0);
  const [y, setY] = useState(0);
  const [isMoving, setIsMoving] = useState(false);

  const moveDistance = 100;
  const moveDelay = 100;

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
        break;
      case "KeyS":
      case "ArrowDown":
        setY((prevY) => prevY + moveDistance);
        break;
      case "KeyA":
      case "ArrowLeft":
        setX((prevX) => prevX - moveDistance);
        break;
      case "KeyD":
      case "ArrowRight":
        setX((prevX) => prevX + moveDistance);
        break;
      default:
        break;
    }
  };

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
      <motion.div className="box" animate={{ x, y }} transition={{type:'tween'}}/>
    </div>
  );
}

export default App;