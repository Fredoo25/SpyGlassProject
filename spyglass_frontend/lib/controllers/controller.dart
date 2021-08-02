

import 'dart:collection';

import 'package:get/get_rx/src/rx_types/rx_types.dart';
import 'package:get/get_state_manager/get_state_manager.dart';
import 'package:get/get.dart';
import 'package:spyglass_frontend/models/Goal.dart';
import 'package:spyglass_frontend/models/InvestmentAccount.dart';
import 'package:spyglass_frontend/models/User.dart';

class Controller extends GetxController {
  final Rx<User?> _user = Rx<User?>(null);
  final RxList<Goal> _goals = RxList<Goal>([]);
  final RxList<InvestmentAccount> _investments = RxList<InvestmentAccount>([]);
  final RxInt _selectedGoal = RxInt(-1);
  final RxInt _selectedInvestment = RxInt(-1);

  int get selectedGoal => _selectedGoal.value;
  set selectedGoal(int newGoal) => _selectedGoal(newGoal);

  int get selectedInvestment => _selectedInvestment.value;
  set selectedInvestment(int newInvestment) => _selectedInvestment(newInvestment);

  set user(User? newUser) => _user(newUser);
  User? get user => _user.value;

  set goals(List<Goal> newGoals) => _goals.addAll(newGoals);
  // ignore: invalid_use_of_protected_member
  UnmodifiableListView<Goal> get goals =>  UnmodifiableListView(_goals.value);

  set investments(List<InvestmentAccount> investments) => _investments.addAll(investments);
  // ignore: invalid_use_of_protected_member
  UnmodifiableListView<InvestmentAccount> get invesetments => UnmodifiableListView(_investments.value);

  
  static Controller get to => Get.find<Controller>();
}