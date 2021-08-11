import 'package:flutter/cupertino.dart';
import 'package:spyglass_ui_flutter/models/IncrementFrequency.dart';
import 'package:spyglass_ui_flutter/models/serializable.dart';

class Investments implements Serializable{
  late double percentageYield;
  late IncrementFrequency incrementInterval;
  late double currentTotal;
  late double projectedTotal;
  late String goalUID;
  late String userUID;
  late String uid;

  Investments({
    required this.percentageYield,
    required this.incrementInterval,
    required this.currentTotal,
    required this.projectedTotal,
    required this.goalUID,
    required this.uid,
    required this.userUID});
  
  Investments.fromJson(Map<String, dynamic> json) {
    percentageYield = json['percentageYield'];
    incrementInterval = fromString(json['incrementInterval']);
    currentTotal = json['currentTotal'];
    projectedTotal = json['projectedTotal'];
    goalUID = json['goalUID'];
    uid = json['uid'];
    userUID = json['userUID'];
  }

  Map<String, dynamic> get json => {
    'uid' : uid,
    'userUID': userUID,
    'goalUID' : goalUID,
    'projectedTotal' : projectedTotal,
    'currentTotal' : currentTotal,
    'incrementInterval' : incrementInterval.stringify(),
    'percentageYield' : percentageYield,
  };

  Type get type => runtimeType;

  String get uuid => this.uid;

}