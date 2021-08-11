import 'package:flip_card/flip_card_controller.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:spyglass_ui_flutter/components/custom_text_field.dart';
import 'package:spyglass_ui_flutter/components/primary_btn.dart';
import 'package:spyglass_ui_flutter/components/secundary_btn.dart';
import 'package:spyglass_ui_flutter/constants/colors.dart';
import 'package:spyglass_ui_flutter/controllers/controller.dart';

class LoginCard extends StatelessWidget {
  final TextEditingController emailFieldController = TextEditingController();
  final TextEditingController passwordFieldController = TextEditingController();
  final FlipCardController cardController;


  LoginCard(this.cardController) : super(key: new GlobalKey());

  void displayDialog(context, {String? title, String? content}) {
    showDialog(context: context, builder: (context) => AlertDialog(
        title: Text(title?? ""),
        content: Text(content?? ""),
        actions: [
          TextButton(
            child: Text("OK"),
            onPressed: () => Navigator.of(context).pop(),
          )
        ]
    ));
  }

  void validateEmail(context) {
    RegExp emailExp = RegExp(r"^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+\.[a-zA-Z]+");
    if(emailExp.hasMatch(emailFieldController.value.text)) {
      validatePassword(context);
    } else {
      displayDialog(context, title: "Invalid Email", content: "Please enter a valid email address."
          "\nExample: example@example.com");
    }
  }

  void validatePassword(context) {
    if(passwordFieldController.value.text.length < 6) {
      displayDialog(context, title:"Invalid password", content: "Passwords are 6+ characters");
    } else {
      appController.login(emailFieldController.value.text, passwordFieldController.value.text).then((result) => {
        if(result) {
          Navigator.of(context).pushNamed('/main')
        } else {
          displayDialog(context, title: "Invalid Login", content: "Provided email and password combination not found")
        }
      });
    }
  }



  Widget build(BuildContext context) {
    return  Center(
      child: Card(
            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
              margin: const EdgeInsets.all(10),
              elevation: 10,
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Text(
                        "Please Login.",
                         style: GoogleFonts.alexBrush(
                           textStyle: TextStyle(
                        color: customColors['primary']!,
                        fontSize: 48,
                        fontWeight: FontWeight.w300,
                      )
                         ),),
                    ),
                    SizedBox(height: 30,),
                    SizedBox(
                      width: 400,
                      child: Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: ThemedTextField(
                          controller: emailFieldController,
                          isPassword: false,
                          label: "Email",
                          hintText: "Please enter your email...",
                        ),
                      ),
                    ),
                    SizedBox(height: 20,),
                    SizedBox(
                      width: 400,
                      child: Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: ThemedTextField(
                          isPassword: true,
                          label: "Password",
                          hintText: "Please enter your password",
                          controller: passwordFieldController,
                        ),
                      ),
                    ),
                    SizedBox(height: 20,),
                    SizedBox(
                      width: 300,
                      height: 70,
                      child: PrimaryButton(
                        onTap: () => validateEmail(context),
                        title: "Login",
                        hoverElevation: 20,
                      ),
                    ),
                    SizedBox(height: 5,),
                    SizedBox(
                      width: 300,
                      height: 70,
                      child: SecundaryButton(title: "New to SpyGlass?", onTap: () => cardController.toggleCard(), hoverElevation: 20,)),
                      SizedBox(height: 5,),
                      SizedBox(
                        width: 300,
                        height: 70,
                        child: SecundaryButton(title: "Cancel", onTap: () => Navigator.of(context).pop(), hoverElevation: 20,),
                      )
                  ]
                ),
          ),
    );
  }

}