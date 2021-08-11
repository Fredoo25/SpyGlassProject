import 'package:firebase_auth/firebase_auth.dart';
import 'package:spyglass_ui_flutter/models/user.dart' as Model;

final FirebaseAuth _auth = FirebaseAuth.instance;

Future<Model.User?> register(String email, String password, String name) async {
  try{
    UserCredential registeredUser = await _auth.createUserWithEmailAndPassword(
        email: email, password: password);
    await registeredUser.user!.updateDisplayName(name);
    return Model.User(registeredUser.user!.uid, name: name);
  } on FirebaseAuthException catch (exception) {
    print("Function Register: ${exception.message}");
    return null;
  }
}

Future<Model.User?> login(String email, String password) async  {
  try{
    UserCredential loginUser = await _auth.signInWithEmailAndPassword(email: email, password: password);
    return Model.User(loginUser.user!.uid, name: loginUser.user!.displayName);
  }on FirebaseAuthException catch (exception) {
    print('Function Login: ${exception.message}');
    return null;
  }
}
