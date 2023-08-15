import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import Button from "@mui/material/Button";
import getStats from "../functions/stats";
function ModalWindow({
  stats,
  setStats,
  setListenToEvents,
  setShowStats,
  setX,
  setY,
  setPlayerPos,
  setPath,
  setMazeStarted,
  setDestinationReached,
  open,
  setOpenModal,
}) {
  const handleClick = () => {
    setX(0);
    setY(0);
    setPlayerPos([0, 0]);
    setPath([[0, 0]]);
    setDestinationReached(false);
    setMazeStarted(false);
    setShowStats(false);
  };

  // const handleClose = () => setOpenModal(false);

  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 400,
    bgcolor: "background.paper",
    border: "2px solid #000",
    boxShadow: 24,
    p: 4,
    outline: 0,
  };

  return (
    <Modal
      open={open}
      // onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={style}>
        <Typography id="modal-modal-title" variant="h6" component="h2">
          Maze completed!
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          Congratulations, you completed the maze!
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          {stats && getStats(stats)}
        </Typography>

        <Box sx={{ display: "flex", justifyContent: "space-evenly", pt: 2 }}>
          <Button onClick={handleClick}>Try a different maze </Button>
          <Button
            onClick={() => {
              setOpenModal(false);
              setStats("");
              setX(0);
              setY(0);
              setPlayerPos([0, 0]);
              setPath([[0, 0]]);
              setListenToEvents(true);
              setDestinationReached(false);
              setShowStats(false);
            }}
          >
            Try again
          </Button>
          <Button
            onClick={() => {
              setOpenModal(false);
              setShowStats((showStats) => !showStats);
            }}
          >
            Stats
          </Button>
        </Box>
      </Box>
    </Modal>
  );
}

export default ModalWindow;
