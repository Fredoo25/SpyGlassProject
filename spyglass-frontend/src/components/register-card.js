import Colors from "../constants/colors";
import {Box, Card, Typography} from "@material-ui/core";
import {ThemedTextField, Types} from "./themed-text_field";
import PrimaryButton from "./primaryButton";
import SecundaryButton from "./secundaryButton";

const {makeStyles} = require("@material-ui/core");
const {useState} = require("react");

const useStyles = makeStyles((theme) => ({
    title: {
        color: Colors.primary,
        fontFamily: 'Helvetica',
        fontStyle: 'italic',
        fontSize: 26
    },
}))


const RegisterCard = (props) => {
    const [email, setEmail] = useState(props.email !== undefined ? props.email : '')
    const [name, setName] = useState('');
    const [password, setPassword] = useState(props.password !== undefined ? props.password : '');
    const [confirmPassword, setConfirmPassword] = useState('')

    const classes = useStyles();

    const handleUpdateName = (event) => setName(event.target.name)
    const handleUpdateEmail = (event) => setEmail(event.target.value)
    const handleUpdatePassword = (event) => setPassword(event.target.value)
    const handleUpdateConfirmPassword = (event) => setConfirmPassword(event.target.value);

    const handleRegister = () => {
       //Validate
    }

    const handleFlipToLogin = () => {
        props.flip()
    }

    const handleCancel = () => {
        props.onCancel()
    }
    return(<div>
        <Box width={400} height={700}>
            <Card elevation={20}>
                <Typography className={classes.title}>Welcome to SpyGlass</Typography>
                <ThemedTextField label={"Name"} hintText={"Enter your name"} type={Types.text} onChanged={handleUpdateName} />
                <ThemedTextField label={"Email"} hintText={"Enter your email"} type={Types.email} onChanged={handleUpdateEmail} />
                <ThemedTextField label={"Password"} hintText={"Enter your password"} type={Types.password} onChanged={handleUpdatePassword} />
                <ThemedTextField label={"Confirm Password"} hintText={"Confirm your password"} type={Types.password} onChanged={handleUpdateConfirmPassword} />
                <PrimaryButton title={"Register"} onClick={handleRegister} padding={2} />
                <SecundaryButton title={"Already have and account?"} onClick={() => {}} padding={2} />
                <SecundaryButton title={"Cancel"} onClick={() => {}} padding={2} />
            </Card>
        </Box>
    </div>)



}

export default RegisterCard;