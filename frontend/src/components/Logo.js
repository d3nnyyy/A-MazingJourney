import '../styles/Logo.css'
import logoImage from '../assets/logoNoBackground.png'
import useWindowDimensions from '../functions/useWindowDimensions'
function Logo(){
    const {height, width} = useWindowDimensions()
    return (
        <div className="logo" style={{width:width/2.5, height:width/2.5}}><img style={{width:width/3, height:width/3}} src={logoImage} alt="Logo of the maze with the letters spelling out Amazing Journey"/></div>
    )
}

export default Logo