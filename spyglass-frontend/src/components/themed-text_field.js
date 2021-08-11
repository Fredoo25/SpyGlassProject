import React from 'react';
import {makeStyles, TextField} from "@material-ui/core";
import Colors from "../constants/colors";

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
            width: '25ch',
            border: `${Colors.primary}`,
        },

    }
}))
const Types = {
    password: 'password',
    number: 'number',
    text: 'text',
    date: 'date',
    email: 'email',
}

const ThemedTextField = (props) => {

    const classes = useStyles();

    return(
        <form className={classes.root} noValidate autoComplete='off'>
         <TextField id="field" label={props.label} hintText={props.hintText} onChange={props.onChange} variant='outlined' type={props.type}/>
        </form>
    )

}

export {ThemedTextField, Types}