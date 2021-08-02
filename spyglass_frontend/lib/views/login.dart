import 'package:flutter/material.dart';
import 'package:flutter_login/flutter_login.dart';

class LoginScreen extends StatelessWidget {

  Future<String?>? onLogin(LoginData loginData, BuildContext context) {
    //Todo: Implement login logic. 
    //Todo: move to main screen. 
  }

  Future<String?>? onSignup(LoginData registerData, BuildContext context) {
    //TODO: Implement register logic
    Navigator.of(context).pushNamed("Registration");
    return null;
  }

  @override
  Widget build(BuildContext context) {
    return FlutterLogin(
      title: "SpyGlass",
      loginAfterSignUp: true,
      onLogin: (data) => onLogin(data, context),
      hideForgotPasswordButton: true,
      onRecoverPassword: (_) {},
      onSignup: (data) => onSignup(data, context),
      titleTag: "enroll",  
    );
  }

}