import 'package:flutter/material.dart';
import 'package:spyglass_ui_flutter/components/floating_app_bar.dart';



class LandingPage extends StatelessWidget {

 
  Widget build(BuildContext context) {
    return  Scaffold(
          appBar: FloatingAppBar(context: context),
          body: Container(
              decoration: BoxDecoration(
                  image: DecorationImage(
                    fit: BoxFit.cover,
                      image: AssetImage(
                          'images/landing-page.png'
                      )
                  )
              ),
            ),
   //TODO: Build landing Page Body UI. ,
    );
  }

}