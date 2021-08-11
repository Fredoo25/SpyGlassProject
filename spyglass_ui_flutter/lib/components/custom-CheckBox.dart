import 'package:flutter/material.dart';

class CustomCheckBox extends StatefulWidget {
  final CustomCheckBoxController controller;
  final String title;

  CustomCheckBox({required this.controller, required this.title});

  _CustomCheckBoxState createState() => _CustomCheckBoxState(title, controller);
}

class _CustomCheckBoxState extends State<CustomCheckBox> {
  final CustomCheckBoxController controller;
  final String title;

  _CustomCheckBoxState(this.title, this.controller);

  Widget build(BuildContext context ){
    return Row(
      mainAxisSize: MainAxisSize.min,
      children: [
        Theme(
          data: ThemeData(unselectedWidgetColor: Theme.of(context).colorScheme.primary),
          child: Checkbox(
            activeColor: Theme.of(context).colorScheme.primaryVariant,
            shape: RoundedRectangleBorder(side: BorderSide(color: Theme.of(context).primaryColor), borderRadius: BorderRadius.circular(20)),
            checkColor: Theme.of(context).colorScheme.primary,
              value: controller.checked, onChanged: (value) => setState(() {
            this.controller.checked = value!;
          })),
        ),
        SizedBox(width: 5,),
        Text(title),
      ],
    );
  }

}

class CustomCheckBoxController  {
  bool checked;

  CustomCheckBoxController([this.checked=false]);
}