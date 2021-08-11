import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:spyglass_ui_flutter/components/primary_btn.dart';
import 'package:spyglass_ui_flutter/components/secundary_btn.dart';
import 'package:spyglass_ui_flutter/constants/colors.dart';

import 'auth_card.dart';

class FloatingAppBar extends AppBar {

  FloatingAppBar({required BuildContext context}) : super(
    primary: false,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
        backgroundColor: Colors.white,
        automaticallyImplyLeading: false,
        elevation: 20,
        title: Text(
              "SpyGlass",
               style: GoogleFonts.alexBrush(
                 textStyle: TextStyle(
                   fontSize: 36,
                   color: customColors['primary']!,
                 ))),
        actions: [
          SizedBox(
            width: MediaQuery.of(context).size.width - 200,
            height: 200,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                SizedBox(width: 50,),
                Row(children: [
                  TextButton(
                  child: Text("Home", style: TextStyle(decoration: TextDecoration.underline),),
                  onPressed: () => Navigator.of(context).pushReplacementNamed('/'),
                ),
                SizedBox(width: 50,),
                  TextButton(
                  child: Text("Product", style: TextStyle(decoration: TextDecoration.underline),),
                  onPressed: () => {/* TODO: Handle Scroll To Product Page */},
                ),
                SizedBox(width: 50,),
                TextButton(
                  child: Text("Contact Us", style: TextStyle(decoration: TextDecoration.underline),),
                  onPressed: () => {Navigator.of(context).pushNamed('/contact-us')},
                ),
                ],),
                Row(children: [
                  SizedBox(width: 200, child: PrimaryButton(title: "Login", onTap: () => showDialog(context: context, builder: (context) =>  AuthCard(initialFace: Face.Login,)))),
                  SizedBox(width: 10,),
                  SizedBox(width: 200, child: SecundaryButton(title: "Register", onTap:  () => showDialog(context: context, builder: (context) => AuthCard(initialFace: Face.Register,)),))
              
                ],)
              ]
                
            ),
          ),
        ],
        );
  
}