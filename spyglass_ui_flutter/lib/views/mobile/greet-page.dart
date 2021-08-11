import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:spyglass_ui_flutter/components/auth_card.dart';
import 'package:spyglass_ui_flutter/components/primary_btn.dart';
import 'package:spyglass_ui_flutter/components/secundary_btn.dart';

class GreetPage extends StatelessWidget {

  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Text("Welcome to SpyGlass!",
                style: GoogleFonts.alexBrush(
                  textStyle: TextStyle(
                    color: Theme.of(context).colorScheme.primary,
                    fontSize: 46,
                    fontWeight: FontWeight.w700,
                  )
                )),
            SizedBox(height: 100,),
            Text("Your Personal Financial Goal Manager!\n"
                "Powered by Vanguard!", style: TextStyle(
              color: Colors.grey[700],
              fontStyle: FontStyle.italic,
              fontSize: 20
            )),
            SizedBox(height: 20,),
            SizedBox(
              width: 200,
              height: 100,
              child: PrimaryButton(
                title: "Already have an Account?",
                hoverElevation: 20,
                onTap: () => Navigator.of(context).push(MaterialPageRoute(builder: (builder) => AuthCard(
                  initialFace: Face.Login,
                ))),
              ),
            ),
            SizedBox(height: 20),
            SizedBox(
              width: 200,
              height: 100,
              child: SecundaryButton(
                title: "New to Spyglass?",
                hoverElevation: 20,
                onTap: () => Navigator.of(context).push(MaterialPageRoute(builder: (builder) => AuthCard(initialFace: Face.Register,))),
              ),
            )
          ]
        )
      )
    );
  }
}