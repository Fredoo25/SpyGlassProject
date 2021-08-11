const {useState} = require("react");
const NavBar = require("../components/landing-navbar");
const LoginCard = require("../components/login-card");
const RegisterCard = require("../components/register-card");
const {Container} = require("@material-ui/core");

const LandingPage = () => {
    const [body, setBody] = useState(<Container fluid='true'/>)

    const handleOnLogin = () =>  setBody(<LoginCard/>)

    const handleRegister = () => setBody(<RegisterCard />)


    return (
        <div>
            <NavBar title={"SpyGlass"} onLogin={handleOnLogin} onRegister={handleRegister}/>
            {body}
        </div>
    )
}

export default LandingPage