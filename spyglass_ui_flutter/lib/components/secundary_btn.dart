import 'package:flutter/material.dart';
import 'package:spyglass_ui_flutter/constants/colors.dart';

class SecundaryButton extends StatelessWidget {
  final String title;
  final onTap;
  final double defaultElevation;
  final double hoverElevation;
  final Set<MaterialState> interactions = const {
      MaterialState.pressed, 
      MaterialState.hovered,
  };

  //Function will reaise or lower the button based on if it is
  //hovered or interacted with. 
  double getElevation(Set<MaterialState> states) {
    if(states.any(interactions.contains)) {
      return this.hoverElevation;
    } else {
      return this.defaultElevation;
    }
  }


  const SecundaryButton({required this.title, required this.onTap, this.defaultElevation = 0.0, this.hoverElevation = 5.0});

  Widget build(BuildContext context) {
    return  Padding(
      padding: const EdgeInsets.all(10.0),
      child: OutlinedButton(
          style: ButtonStyle(
            backgroundColor: MaterialStateProperty.all<Color>(Colors.white),
            elevation: MaterialStateProperty.resolveWith((getElevation)),
            shape: MaterialStateProperty.all<OutlinedBorder>(RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(20),
              side: BorderSide(color: customColors['primary']!, width: 10.0, style: BorderStyle.solid))),
              side: MaterialStateProperty.all(BorderSide(color: customColors['primary']!, width: 2))
          ),
          child: Padding(
            padding: const EdgeInsets.all(4.0),
            child: Text(title, style: TextStyle(
              color: customColors['primary']!, 
              fontWeight: FontWeight.bold, 
              fontSize: 16
            )),
          ),
          
          onPressed: onTap,
        ),
    );

  }

}