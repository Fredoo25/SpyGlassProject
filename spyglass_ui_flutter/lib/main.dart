import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:logger/logger.dart';
import 'package:spyglass_ui_flutter/constants/colors.dart';
import 'package:spyglass_ui_flutter/controllers/controller.dart';
import 'package:spyglass_ui_flutter/views/MainPage.dart';
import 'package:spyglass_ui_flutter/views/contact-us.dart';
import 'package:spyglass_ui_flutter/views/landing_page.dart';

final _logger = Logger();

void main() {
  FlutterError.onError = (FlutterErrorDetails details) {
    _logger.e(details.exceptionAsString());
  };
  Get.put(UserController());
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'SpyGlass',
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
        colorScheme: ColorScheme(
          brightness: Brightness.light,
          primary: customColors['primary']!,
          primaryVariant: customColors['primary_light']!,
          secondaryVariant: Colors.blue[400]!,
          secondary: Colors.blue,
          background: Colors.white,
          onBackground: customColors['secondary']!,
          onPrimary: Colors.white,
          onSecondary: Colors.white,
          error: Colors.red,
          onError: Colors.red,
          surface: Colors.white,
          onSurface: customColors['secondary']!
        )
      ),
      initialRoute: '/',
      routes: {
        '/'  : (builder) => LandingPage(),
        '/register': (builder) => LandingPage(),
        '/login': (builder) => LandingPage(),
        '/contact-us': (builder) => ContactUs(),
        '/main' : (builder) => MainPage(),
      }
    );
  }
}

