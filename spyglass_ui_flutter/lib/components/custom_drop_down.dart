import 'package:flutter/material.dart';

class CustomDropDown extends StatefulWidget {
  final CustomDropDownController controller;
  final List<dynamic> elements;

  CustomDropDown({required this.controller, required this.elements});

  _CustomDropDownState createState() => _CustomDropDownState(controller, elements);
}

class _CustomDropDownState extends State<CustomDropDown> {
  final CustomDropDownController controller;
  List<dynamic> elements;
  _CustomDropDownState(this.controller, this.elements) {
    controller.selectedElement = elements.first.toString();
  }

  Widget build(BuildContext context) {
    return DropdownButton<String>(
      value: controller.selectedElement,
      icon: const Icon(Icons.arrow_downward),
      iconSize: 24,
      elevation: 16,
      style: TextStyle(
          color: Theme.of(context).colorScheme.primaryVariant
      ),
      underline: Container(
        height: 2,
        color: Theme.of(context).colorScheme.primary,
      ),
      onChanged: (String? newValue) {
        setState(() {
          controller.selectedElement = newValue!;
        });
      },
      items: elements.map((value) => value.toString()).toList()
          .map<DropdownMenuItem<String>>((String value) {
        return DropdownMenuItem<String>(
          value: value,
          child: Text(value),
        );
      })
          .toList(),
    );
  }

}

class CustomDropDownController {
  String selectedElement = '';

  CustomDropDownController();
}