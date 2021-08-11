import 'serializable.dart';

class User extends Serializable {
  late String _name;
  late List<String> goals;
  late List<String> accounts;
  late String _uuid;

  String get uuid => _uuid;
  String get name => _name;


  set name(String name) => this._name = name;

  User(String uuid, {String? name, List<String>? goals, List<String>? accounts}) {
    this._name = name?? " ";
    this._uuid = uuid;
    this.goals = goals?? [];
    this.accounts = accounts?? [];
  }

  factory User.fromJson(Map<String, dynamic> json) =>
    User(json['uid'], goals: json['goals'], accounts: json['investmentAccounts']);


  Map<String, dynamic> get json => {
    'uid': uuid,
    'goals': goals,
    'investmentAccounts' : accounts
  };

  Type get type => runtimeType;



}