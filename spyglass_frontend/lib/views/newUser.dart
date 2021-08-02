import 'package:flutter/material.dart';
import 'package:spyglass_frontend/components/inputField.dart';

class NewUser extends StatelessWidget {
  final TextEditingController nameController = TextEditingController();

  @override
  Widget build(BuildContext context) {
   return Card(
     child: Hero(
       tag: "enroll",
       child: Column(
         children: [
           InputField(controller: nameController, hintText: "Enter your name", label: "Name")
         ]
       )
   
     ),
   );
  }

}