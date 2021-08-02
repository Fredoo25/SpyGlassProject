import 'package:flutter/material.dart';

class InputField extends StatefulWidget {
  final String hintText;
  final TextEditingController controller;
  final String label;
  final int maxLines;

  InputField({required this.controller, required this.hintText, required this.label, this.maxLines = 1});

  _InputField createState() => _InputField(controller: this.controller, hintText: this.hintText, label: this.label, maxLines: this.maxLines);
}

class _InputField extends State<InputField>  {
  final String hintText;
  final TextEditingController controller;
  final String label;
  final int maxLines;

  _InputField({required this.controller, required this.hintText, required this.label, required this.maxLines});

  @override
  Widget build(BuildContext context) {
    return TextField(
      controller: this.controller, 
      maxLines: this.maxLines,
      decoration: InputDecoration(
        hintText: this.hintText,
        labelText: this.label,
        fillColor: Theme.of(context).primaryColor,
      ),
    );
  }


}