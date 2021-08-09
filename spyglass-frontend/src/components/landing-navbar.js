import { AppBar, makeStyles, Toolbar, Typography, Container, Button } from "@material-ui/core";
import PrimaryButton from "./primaryButton";
import SecundaryButton from "./secundaryButton";
import Colors from "../constants/colors";

const useStyles = makeStyles({
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
    }

})

const NavBar = (props) => {
    const classes = useStyles();

    return (
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

                <PrimaryButton title="Login" padding={2} onClick={() => {
                }}/>
                <SecundaryButton title="Register" padding={2} onClick={() => {
                }}/>

            </Toolbar>
        </AppBar>
    )
}

export default NavBar;