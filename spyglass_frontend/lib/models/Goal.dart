import 'package:spyglass_frontend/models/IncrementFrequency.dart';

class Goal {
  String uid;
  String name;
  String description;
  String imageURL;
  String investmentUID;
  String userUID;
  double amount;
  double currentAmount;
  double amountPerInterval;
  bool isInvested;
  bool onTrack;
  DateTime? startDate;
  DateTime? projectedEndDate;
  IncrementFrequency incrementFrequency;

  

  Goal({this.uid = "", this.name = "", this.description = "",this.imageURL = "", this.investmentUID = "", this.userUID = "",
   this.amount = 0, this.currentAmount = 0, this.amountPerInterval = 0, this.isInvested = false, this.onTrack = false, 
   this.startDate, this.projectedEndDate, this.incrementFrequency = IncrementFrequency.Yearly });

   factory Goal.fromJson(Map<String, dynamic> json) =>
     Goal(
       uid: json['uid'],
       name: json['name'],
       description: json['description'],
       imageURL: json['imageURL'],
       investmentUID: json['investmentAccountUID'],
       userUID: json['userUID'],
       amount: json['amount'],
       currentAmount: json['current'],
       amountPerInterval: json['amountPerInterval'],
       isInvested: json['isInvested'],
       onTrack: json['onTrack'],
       startDate: json['startDate'],
       projectedEndDate: json['projectedEndDate'],
       incrementFrequency: fromString(IncrementFrequency.values, (json['savingInterval']))
     );

     Map<String, dynamic> toJson() {
       return {
         'uid' : this.uid,
         'name': this.name,
         'description': this.description, 
         'imageURL': this.imageURL, 
         'investmentAccountUID': this.investmentUID,
         'userUID': this.userUID,
         'amount': this.amount, 
         'current': this.currentAmount, 
         'amountPerInterval': this.amountPerInterval,
         'isInvested': this.isInvested, 
         'onTrack': this.onTrack, 
         'startDate': this.startDate, 
         'projectedEndDate': this.projectedEndDate, 
         'savingInterval': incrementFrequency.asString()
       };
     }
   
}