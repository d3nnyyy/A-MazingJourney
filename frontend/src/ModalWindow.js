import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import Button from '@mui/material/Button'
function ModalWindow ({open, setOpenModal}) {
  const handleClose = () => setOpenModal(false);
  const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
    outline: 0
  };
    return (
        <Modal
        open={open}
        onClose={handleClose}
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
          <Box sx={{display: 'flex', justifyContent:'space-evenly', pt: 2}}>
            <Button>Try again</Button>
            <Button>Stats</Button>
          </Box>
        </Box>  
      </Modal>
    )
}

export default ModalWindow;