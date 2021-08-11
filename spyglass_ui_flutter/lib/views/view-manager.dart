import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:spyglass_ui_flutter/views/mobile/greet-page.dart';
import 'package:spyglass_ui_flutter/views/web/landing_page.dart';

class ViewManager extends StatelessWidget {

  Widget build(BuildContext context) {
    if(kIsWeb) {
      return LandingPage();
    } else  if(Platform.isAndroid | Platform.isIOS) {
      return GreetPage();
    } else return Container();
  }
}