import React, { Component } from 'react';
import { Container } from '@material-ui/core';
import NavBar from "./landing-navbar.js";
import Colors from "../constants/colors";
import Box from '@material-ui/core/Box';



const style = {
    margin: 0,
    top: 'auto',
    right: 1000,
    bottom: 750,
    left: 'auto',
    position: 'fixed',
};

const style2 = {
        margin: 'auto',
        top: 'auto',
        right: 300,
        bottom: 100,
//        left: 50,
        position: 'fixed',
        height: 600,
        width: 1400,
        borderRadius: '25px',
        border: '2px solid black',
        padding: '20px'
}

class ContactForm extends Component {
    state = {
        text: 'We love questions and feedback - and we are always happy to help!',
        text2: 'Here are some ways to contact us.',
        text3: 'Send us a message and we will respond within 24 hours.'

    };
    render() {
            return (
                <div id="parent">
                <Container style = {style}>
                    <h1> Contact Us </h1>
                    {this.state.text}
                    <br />
                    {this.state.text2}
                </Container>
                   <NavBar title = 'SpyGlass'/>
                   <Box display="flex" p={1} bgcolor={Colors.contact_form} style =  {style2} borderColor={Colors.primary} >
                       <h2> Send us a message </h2>
                   </Box>
                 </div>
               );
    }
}
export default ContactForm;