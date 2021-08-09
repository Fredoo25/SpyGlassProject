import { Button, Box } from '@material-ui/core';
import React from 'react';
import '../constants/colors'
import Colors from '../constants/colors';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
    secundaryButton: {
        background: 'white',
        border: `solid 2px ${Colors.secundary}`,
        borderRadius: 20,
        color: Colors.primary,
        fontWeight: 600,
        height: 40,
        padding: '0 30px',
        '&:hover' : {
            color: Colors.primary_light
        }
    }
})

const SecundaryButton = (props) => {
    const classes = useStyles();

    return(
        <Box p={props.padding} >
         <Button className={classes.secundaryButton} variant='outlined' onClick={props.onClick}>{props.title}</Button>
        </Box>
    )
}

export default SecundaryButton;