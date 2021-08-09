import {Box, Card, makeStyles, Typography} from "@material-ui/core";
import {ThemedTextField, Types} from "./themed-text_field";
import PrimaryButton from "./primaryButton";
import SecundaryButton from "./secundaryButton";
import Colors from "../constants/colors";

const {useState} = require("react");

const useStyles = makeStyles((theme) => ({
    title: {
        color: Colors.primary,
        fontFamily: 'Helvetica',
        fontStyle: 'italic',
        fontSize: 26
    },
}))

const LoginCard = (props) => {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const classes = useStyles()

    const handleChangeEmail = (event) => {
        setEmail(event.target.value);
    }

    const handleChangePassword = (event) => {
        setPassword(event.target.value);
    }

    const handleOnLogin = () => {
        props.onLogin(email, password);
    }

    const handleOnRegister = () => {
        props.onRegister(email, password);
    }

    const handleOnCancel = () => {
        props.onCancel()
    }

    return (
        <div>
            <Box width={400} height={700}>
                <Card elevation={20}>
                    <Typography className={classes.title} variant='h3'>Please Login</Typography>
                    <ThemedTextField label={"Email"} hintText={"Enter Email"} onChange={handleChangeEmail}
                                     type={Types.email}/>
                    <ThemedTextField label={"Password"} hintText={"Enter your password"} onChange={handleChangePassword}
                                     type={Types.password}/>
                    <PrimaryButton title={"Login"} onClick={handleOnLogin} padding={2}/>
                    <SecundaryButton title={"Register"} onClick={handleOnRegister} padding={2}/>
                    <SecundaryButton title={"Cancel"} onClick={handleOnCancel} padding={2}/>
                </Card>
            </Box>
        </div>
    )

}

export default LoginCard