import {AppBar, makeStyles, Toolbar, Typography, Container, Button, Modal} from "@material-ui/core";
import PrimaryButton from "./primaryButton";
import SecundaryButton from "./secundaryButton";
import Colors from "../constants/colors";

const useStyles = makeStyles((theme) => ({
    appBar: {
        color: 'white',
        backgroundColor: 'white',
        boxShadow: '0px'
    },
    title: {
        color: Colors.primary,
        fontFamily: 'Helvetica',
        fontStyle: 'italic',
        fontSize: 26
    },
    menu: {
        color: Colors.secundary,
        fontFamily: 'Helvetica',
        fontStyle: 'underline',
        fontSize: 14,
    },
    paper: {
        position: 'absolute',
        width: 400,
        backgroundColor: theme.palette.background.paper,
        border: '2px solid #000',
        boxShadow: theme.shadows[5],
        padding: theme.spacing(2, 4, 3),
    }}))

const NavBar = (props) => {
    const classes = useStyles();
    return (
        <div>
        <AppBar className={classes.appBar} elevation={10}>
            <Toolbar>
                <Typography className={classes.title} variant='h2'>{props.title}</Typography>
                <Container>
                    <Button onClick={() => {
                    }}>
                        <Typography className={classes.menu} variant='overline'>Product</Typography>
                    </Button>
                </Container>
                <Container>
                    <Button onClick={() => {
                    }}>
                        <Typography className={classes.menu} variant='overline'>Contact Us</Typography>
                    </Button>
                </Container>

                <PrimaryButton title="Login" padding={2} onClick={() => {}}/>
                <SecundaryButton title="Register" padding={2} onClick={() => {}}/>

            </Toolbar>
        </AppBar>

        </div>
    )
}

export default NavBar;