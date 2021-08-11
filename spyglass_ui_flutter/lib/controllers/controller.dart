import 'dart:async';
import 'dart:collection';
import 'package:get/get.dart';
import 'package:spyglass_ui_flutter/models/goal.dart';
import 'package:spyglass_ui_flutter/models/investmentAccount.dart';
import 'package:spyglass_ui_flutter/models/user.dart';
import 'package:spyglass_ui_flutter/backend/authentication.dart' as Authentication;
import 'package:spyglass_ui_flutter/backend/backend-conn.dart' as DB;

class UserController extends GetxController {
  User? _user;
  late Rx<User?> _userObserver = _user.obs;
  RxList<Goal> _goals = RxList<Goal>();
  RxList<Investments> _investments = RxList<Investments>();

 final RxInt _selectedGoal = RxInt(-1);

 int get selectedGoal => _selectedGoal.value;

 set selectedGoal(int newGoal) => _selectedGoal(newGoal);

  UserController() {
    this._userObserver = _user.obs;
  }

  User? get user => _userObserver.value;

  UnmodifiableListView<Goal> get goals => UnmodifiableListView(_goals);

  UnmodifiableListView<Investments> get investments =>
      UnmodifiableListView(_investments);

  Future<bool> login(String email, String password) async {
    var user = await Authentication.login(email, password);
    if (user != null) {
     _userObserver(user);
      await fetchDataFromBackEnd();
      this.update();
      return true;
    } else {
      return false;
    }
  }


  Future<bool> register(String email, String password, String name) async {
    var user = await Authentication.register(email, password, name);
    if (user != null) {
      _userObserver(user);
      await addNewUserToBackend();
      await fetchDataFromBackEnd();
      this.update();
      return true;
    } else {
      return false;
    }
  }

  void addNewGoal(Goal newGoal) {
    _goals.add(newGoal);
    DB.post(newGoal);
    this.update();
  }

  void addInvestment(Investments newInvestment) {
    _investments.add(newInvestment);
    DB.post(newInvestment);
    this.update();
  }

  void removeGoal(int goalIndex) async {
    Goal goalToRemove = _goals[goalIndex];
    await DB.delete(goalToRemove);
    fetchDataFromBackEnd();
    this.update();
  }

  Future<void> fetchDataFromBackEnd() async {
    var backendResults = await DB.getMultiple<Goal>();
    _goals.clear();
    _goals.addAll(backendResults?? []);
    this.update();

  }

  Future<void> addNewUserToBackend() async {
    DB.post(user!);
  }

  void updateGoal(Goal updatedGoal) async {
    await DB.update(updatedGoal);
    fetchDataFromBackEnd();
    this.update();
  }
}

final appController = Get.find<UserController>();