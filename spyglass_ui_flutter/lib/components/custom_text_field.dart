import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:spyglass_ui_flutter/constants/colors.dart';

class ThemedTextField extends StatelessWidget {
  final bool isPassword;
  final bool? isMultiLine;
  final int? numOfLines;
  final String label;
  final String hintText;
  final TextEditingController controller;
  final GestureTapCallback? onTap;

  ThemedTextField({
    required this.isPassword,
    required this.label,
    required this.hintText,
    required this.controller,
    this.isMultiLine,
    this.numOfLines = 1,
    this.onTap
  });

  Widget build(BuildContext context) {
    return Theme(
      data: Theme.of(context).copyWith(
        splashColor: Colors.white
      ),
      child: TextField(
        onTap: onTap,
        controller: this.controller,
        obscureText: this.isPassword,
        maxLines: this.numOfLines,
        decoration: InputDecoration(
          hintText: this.hintText,
          labelText: this.label,
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(20),
            borderSide: BorderSide(color: customColors['primary']!,
            width: 5, 
            style: BorderStyle.solid
            )
          ),
        ),
      ),
    );
  }
}