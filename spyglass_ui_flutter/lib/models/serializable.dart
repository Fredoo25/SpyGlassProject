import 'package:spyglass_ui_flutter/constants/logger.dart';
import 'package:spyglass_ui_flutter/models/goal.dart';
import 'package:spyglass_ui_flutter/models/investmentAccount.dart';
import 'package:spyglass_ui_flutter/models/user.dart';

abstract class Serializable {
  Map<String, dynamic> get json;
  Type get type;
  String get uuid;



}

  Type getType<T>() => T;


  dynamic fromJson<T>(Map<String, dynamic> json) {
    Type t = getType<T>();
    logger.i("Get Type: $t");
    switch(getType<T>()) {
      case User: return User.fromJson(json);
      case Goal: return Goal.fromJson(json);
      case Investments : return Investments.fromJson(json);
      default: logger.e("Incompatible Type: ${getType<T>()} passed!");
    }
 }

