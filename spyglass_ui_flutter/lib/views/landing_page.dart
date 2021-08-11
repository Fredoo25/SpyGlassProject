import 'package:flutter/material.dart';
import 'package:spyglass_ui_flutter/components/floating_app_bar.dart';



class LandingPage extends StatelessWidget {

 
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(10, 10, 10, 10),
      child: Scaffold(
        backgroundColor: Colors.white,
        appBar: PreferredSize(
          preferredSize: Size.fromHeight(50),
          child: FloatingAppBar(context: context)),
          body: Container() //TODO: Build landing Page Body UI. ,
          
      ),
    );
  }

}