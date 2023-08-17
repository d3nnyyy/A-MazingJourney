import '../styles/Logo.css'
import logoImage from '../assets/logoNoBackground.png'
function Logo(){
    return (
        <div className="logo"><img src={logoImage} className='logo-image' alt="Logo of the maze with the letters spelling out Amazing Journey"/></div>
    )
}

export default Logo