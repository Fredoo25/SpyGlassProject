import 'package:spyglass_frontend/models/IncrementFrequency.dart';

class InvestmentAccount {
  String uid;
  String goalUID;
  String userUID;
  double percentYield;
  double currentTotal;
  double projectedTotal;
  IncrementFrequency incrementFrequency;

  InvestmentAccount({
    this.uid = "", this.goalUID = "", this.userUID = "", this.percentYield = 0.0,
     this.currentTotal = 0, this.projectedTotal = 0, this.incrementFrequency = IncrementFrequency.Yearly
  });

  factory InvestmentAccount.fromJson(Map<String, dynamic> json) => InvestmentAccount(
    uid: json['uid'],
    goalUID: json['goalUID'],
    userUID: json['userUID'],
    percentYield: json['percentageYield'],
    currentTotal: json['currentTotal'],
    projectedTotal: json['projectedTotal'],
    incrementFrequency: fromString(IncrementFrequency.values,json['incrementInterval'])
  );

  Map<String, dynamic> toJson() => {
    'uid': uid,
    'goalUID': goalUID, 
    'userUID': userUID,
    'percentageYield': percentYield,
    'currentTotal': currentTotal,
    'projectedTotal': projectedTotal,
    'incrementInterval': incrementFrequency.asString()
  };
  

}