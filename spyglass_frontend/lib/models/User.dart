class User {
  bool isNewUser;
  String name;
  String? uid;
  List<String> goals = [];
  List<String> investments  = [];


  User({this.isNewUser = true, this.name = "", required this.uid, this.goals = const [], this.investments = const []});


}