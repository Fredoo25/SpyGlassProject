import { Button, Box } from '@material-ui/core';
import { spacing } from '@material-ui/system';
import React from 'react';
import '../constants/colors'
import Colors from '../constants/colors';
import { makeStyles } from '@material-ui/core/styles';


const useStyles = makeStyles({
    primaryButton: {
        background: Colors.primary,
        border: 2,
        borderRadius: 20,
        color: 'white',
        fontWeight: 600,
        height: 40, 
        padding: '0 30px',
        '&:hover' : {
            backgroundColor: Colors.primary_light,
        }
    }
})

const PrimaryButton = (props) => {
    const classes = useStyles();
    return(
        <Box p={props.padding}>
         <Button className={classes.primaryButton} onClick={props.onClick}>{props.title}</Button>
        </Box>
    )
}

export default PrimaryButton