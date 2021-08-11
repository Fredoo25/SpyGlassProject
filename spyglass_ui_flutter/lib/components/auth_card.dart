import 'package:flip_card/flip_card.dart';
import 'package:flip_card/flip_card_controller.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:spyglass_ui_flutter/components/login_card.dart';
import 'package:spyglass_ui_flutter/components/register_card.dart';

enum Face {
  Login, Register
}
class AuthCard extends StatelessWidget {
  final Face initialFace;
  final FlipCardController controller = FlipCardController();
  AuthCard({required this.initialFace});

  Widget build(BuildContext context) {
    return Center(
      child: Container(
        width: 500,
        height: 600,
        child: FlipCard(
          controller: controller,
          front: initialFace == Face.Login? LoginCard(controller) : RegisterCard(controller),
          back: initialFace == Face.Login? RegisterCard(controller) : LoginCard(controller),
          flipOnTouch: false,
        ),
      ),
    );
  }
}