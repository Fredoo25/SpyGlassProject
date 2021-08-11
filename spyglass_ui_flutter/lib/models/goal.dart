import 'package:spyglass_ui_flutter/models/IncrementFrequency.dart';
import 'package:spyglass_ui_flutter/models/serializable.dart';

class Goal implements Serializable{
  late String uuid;
  late double amount;
  late double current;
  late String name;
  late String description;
  late String imageURL;
  late int startDate;
  late int projectedEndDate;
  late bool isInvested;
  late double amountPerInterval;
  late IncrementFrequency savingInterval;
  late bool onTrack;
  late String investmentUID;
  late String userUID;

  Goal({
      required this.uuid,
      required this.amount,
      required this.current,
      required this.name,
      required this.description,
      required this.imageURL,
      required this.startDate,
      required this.projectedEndDate,
      required this.isInvested,
      required this.amountPerInterval,
      required this.savingInterval,
      required this.onTrack,
      required this.investmentUID,
      required this.userUID});

  Goal.fromJson(Map<String, dynamic> json)  {
      uuid = json['uid'];
      amount = json['amount'];
      current = json['current'];
      name = json['name'];
      description = json['description'];
      imageURL = json['imageURL'];
      startDate = json['startDate'];
      projectedEndDate = json['projectedEndDate'];
      isInvested = json['isInvested'];
      savingInterval = fromString(json['savingInterval']);
      onTrack = json['onTrack'];
      investmentUID = json['investmentAccountUID'];
      userUID = json['userUID'];
      amountPerInterval = json['amountPerInterval'];
    }

  Map<String, dynamic> get json => {
    'uid' : uuid,
    'amount' : amount,
    'current': current,
    'name' : name,
    'description' : description,
    'imageURL' : imageURL,
    'startDate' : startDate,
    'projectedEndDate' : projectedEndDate,
    'isInvested' : isInvested,
    'amountPerInterval' : amountPerInterval,
    'savingInterval' : savingInterval.stringify(),
    'onTrack' : onTrack,
    'investmentAccountUID' : investmentUID,
    'userUID' : userUID
  };

  Type get type => this.runtimeType;
  


}