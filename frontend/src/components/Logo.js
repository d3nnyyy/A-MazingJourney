import '../styles/Logo.css'
import logoImage from '../assets/logoNoBackground.png'
function Logo(){
    return (
        <div className="logo"><img className='logo-image' src={logoImage} alt="Logo of the maze with the letters spelling out Amazing Journey"/></div>
    )
}

export default Logo