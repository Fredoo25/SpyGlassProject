import {AppBar, makeStyles, Toolbar, Typography, Container, Button, Modal} from "@material-ui/core";
import PrimaryButton from "./primaryButton";
import SecundaryButton from "./secundaryButton";
import Colors from "../constants/colors";
import {useState} from "react";
import {AuthCard, InitialFace} from "./auth-card";

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
    const [modalIsOpen, setModelIsOpen] = useState(false)
    const [modalFace, setModalFace] = useState(InitialFace.Login)

    const handleOpen = () => {
        setModelIsOpen(true)
    }

    const handleClose = () => {
        setModelIsOpen(false)
    }

    const handleRegister = () => {
        setModalFace(InitialFace.Register)
        setModelIsOpen(true)
    }

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

                <PrimaryButton title="Login" padding={2} onClick={handleOpen}/>
                <SecundaryButton title="Register" padding={2} onClick={handleRegister}/>

            </Toolbar>
        </AppBar>
        <Modal open={modalIsOpen} onClose={handleClose} children={<AuthCard face={modalFace} /> }/>
        </div>
    )
}

export default NavBar;