import 'package:flutter/material.dart';
import 'package:spyglass_ui_flutter/constants/colors.dart';

class PrimaryButton extends StatelessWidget {
  final String title;
  final onTap;
  final Set<MaterialState> interactions = const {
      MaterialState.pressed, 
      MaterialState.hovered,
    };
      final double defaultElevation;
  final double hoverElevation;

  const PrimaryButton({required this.title, required this.onTap, this.hoverElevation = 5.0, this.defaultElevation = 0.0});

  //Function will provide the color for the button based on its state.
  //When button is hovered or pressed, the lighter shade will be used,
  //Otherwise the darker shade will be used. 
  Color getColor(Set<MaterialState> states) {
    if(states.any(interactions.contains)) {
      return customColors['primary_light']!;
    } else {
      return customColors['primary']!;
    }

  }

  //Function will reaise or lower the button based on if it is
  //hovered or interacted with. 
  double getElevation(Set<MaterialState> states) {
    if(states.any(interactions.contains)) {
      return hoverElevation;
    } else {
      return defaultElevation;
    }
  }
 
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(10),
      child: OutlinedButton(
        style: ButtonStyle(
          backgroundColor: MaterialStateProperty.resolveWith(getColor),
          shape: MaterialStateProperty.all(RoundedRectangleBorder(borderRadius: BorderRadius.circular(20))),
          elevation: MaterialStateProperty.resolveWith(getElevation)
        ),
        child: Padding(
          padding: const EdgeInsets.all(4.0),
          child: Text(title, style: TextStyle(
            color: Colors.white, 
            fontWeight: FontWeight.bold,
            fontSize: 16
          )),
        ),
        onPressed: onTap,
      ));
  }
} 