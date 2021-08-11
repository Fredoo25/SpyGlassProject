import 'package:flip_card/flip_card_controller.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:spyglass_ui_flutter/components/custom_text_field.dart';
import 'package:spyglass_ui_flutter/components/primary_btn.dart';
import 'package:spyglass_ui_flutter/components/secundary_btn.dart';
import 'package:spyglass_ui_flutter/constants/colors.dart';
import 'package:spyglass_ui_flutter/controllers/controller.dart';

class RegisterCard extends StatelessWidget {
  final TextEditingController nameField = TextEditingController();
  final TextEditingController emailField = TextEditingController();
  final TextEditingController passField = TextEditingController();
  final TextEditingController passConfirmField = TextEditingController();
  final FlipCardController cardController;

  RegisterCard(this.cardController) : super(key: new GlobalKey());

  void displayDialog(context, {String? title, String? content}) {
    showDialog(context: context, builder: (context) =>
        AlertDialog(
            title: Text(title ?? ""),
            content: Text(content ?? ""),
            actions: [
              TextButton(
                child: Text("OK"),
                onPressed: () => Navigator.of(context).pop(),
              )
            ]
        ));
  }

  void validateEmail(context) {
    RegExp emailExp = RegExp(
        r"^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+\.[a-zA-Z]+");
    if (emailExp.hasMatch(emailField.value.text)) {
      validatePasswords(context);
    } else {
      displayDialog(context, title: "Invalid Email",
          content: "Please enter a valid email address."
              "\nExample: example@example.com");
    }
  }

  void validatePasswords(context) {
    if (passField.value.text.length < 6) {
      displayDialog(context, title: "Invalid Password",
          content: "Password must be 6+ characters");
    }
    else if (passField.value.text != passConfirmField.value.text) {
      displayDialog(context, title: "Invalid Password",
          content: "Password do not match, please enter again");
      passField.text = "";
      passConfirmField.text = "";
    } else {
      appController.register(
          emailField.value.text, passField.value.text, nameField.value.text)
          .then((result) =>
      {
        if(result) {
          Navigator.of(context).pushNamed('/main')
        } else
          {
            displayDialog(context, title: "Account Already Exists",
                content: "Account with provided email already exists!")
          }
      });
    }
  }

  Widget build(BuildContext context) {
    return Center(
      child: SizedBox(
        width: 700,
        child: Card(
            margin: const EdgeInsets.all(10),
              shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                mainAxisSize: MainAxisSize.min,
                children: [
                  Text(
                    "Welcome To SpyGlass!",
                    style: GoogleFonts.alexBrush(
                        textStyle: TextStyle(
                          color: customColors['primary']!,
                          fontSize: 36,
                          fontWeight: FontWeight.w300,
                        )
                    ),
                  ),
                  SizedBox(height: 30,),
                  SizedBox(
                    width: 400,
                    child: ThemedTextField(
                      controller: nameField,
                      label: "Name",
                      hintText: "Enter your name",
                      isMultiLine: false,
                      isPassword: false,
                      numOfLines: 1,),
                  ),
                  SizedBox(height: 20,),
                  SizedBox(
                    width: 400,
                    child: ThemedTextField(
                      controller: emailField,
                      label: "Email",
                      hintText: "Enter your email",
                      isPassword: false,
                    ),
                  ),
                  SizedBox(height: 20,),
                  SizedBox(
                    width: 400,
                    child: ThemedTextField(
                      controller: passField,
                      isPassword: true,
                      label: "Password",
                      hintText: "Create a new Password (Must be 6 characters+)",
                    ),
                  ),
                  SizedBox(height: 20,),
                  SizedBox(
                    width: 400,
                    child: ThemedTextField(
                      controller: passConfirmField,
                      isPassword: true,
                      label: "Confirm Password",
                      hintText: "Confirm Password entered above.",
                    ),
                  ),
                  SizedBox(height: 20,),
                  SizedBox(
                    width: 300,
                    height: 70,
                    child: PrimaryButton(
                      onTap: () => validateEmail(context),
                      title: "Register",
                      hoverElevation: 20,
                    ),
                  ),
                  SizedBox(
                      width: 300,
                      height: 70,
                      child: SecundaryButton(title: "Already Have an Account?",
                        onTap: () => this.cardController.toggleCard(),
                        hoverElevation: 20,)),
                  SizedBox(
                    width: 300,
                    height: 70,
                    child: SecundaryButton(title: "Cancel",
                      onTap: () => Navigator.of(context).pop(),
                      hoverElevation: 20,),
                  )
                ],
              )
          ),
        ),
    );
  }
}