import 'package:flutter/material.dart';
import 'package:spyglass_frontend/views/login.dart';
import 'package:spyglass_frontend/views/newUser.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'SpyGlass 🔍',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primaryColorBrightness: Brightness.light,
        primaryColor: Color(0xFF96151d),
        primaryColorLight: Color(0xFFba2028),
        accentColor: Color(0xff1e7ab0),
       brightness: Brightness.light,
      ),
      home:LoginScreen(),
      routes: {
        "Registration" : (_) => NewUser()
      }
      
    );
  }
}
