import '../styles/Logo.css'
import logoImage from '../assets/logoNoBackground.png'
import useWindowDimensions from '../functions/useWindowDimensions'
function Logo(){
    const {height, width} = useWindowDimensions()
    return (
        <div className="logo"><img style={{width:height*0.8, height:height*0.8}} src={logoImage} alt="Logo of the maze with the letters spelling out Amazing Journey"/></div>
    )
}

export default Logo