import 'dart:math';
import 'package:intl/intl.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:percent_indicator/linear_percent_indicator.dart';
import 'package:spyglass_ui_flutter/components/goal_card.dart';
import 'package:spyglass_ui_flutter/components/primary_btn.dart';
import 'package:spyglass_ui_flutter/components/secundary_btn.dart';
import 'package:spyglass_ui_flutter/controllers/controller.dart';
import 'package:spyglass_ui_flutter/models/goal.dart';

class MainPage extends StatelessWidget {

  final List<String> greetings = [
    "Hello,", "Welcome,", "Hiya"
  ];

  String pickRandomGreeting() {
    return greetings[Random.secure().nextInt(greetings.length)];
  }


  Future<bool> signOutUserOnPop(context) async {
    return showDialog(context: context, builder: (builder) =>
        AlertDialog(
            title: Text("Logout?"),
            content: Text("Are you sure you want to log out?"),
            actions: [
              TextButton(
                child: Text("Yes"),
                onPressed: () => Navigator.of(context).pop(true),
              ),
              TextButton(
                child: Text("No"),
                onPressed: () => Navigator.of(context).pop(false),
              )
            ]
        )).then((value) => value);
  }

  ImageProvider getGoalImage(Goal goal) {
    if (goal.imageURL.isEmpty) {
      return AssetImage('images/default-goal-image.jpg');
    } else {
      return NetworkImage(goal.imageURL);
    }
  }

  Widget goalCard(Goal goal, BuildContext context, int index) {
    var formatter = NumberFormat('###,000');
    return Card(
          elevation: 20,
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(20)),
          margin: const EdgeInsets.all(10),
          child: Container(
            padding: const EdgeInsets.all(10),
            child: Row(
              children: [
                Column(
                    children: [
                      Text(goal.name, style: GoogleFonts.alexBrush(textStyle: TextStyle(
                        color: Theme.of(context).colorScheme.primary,
                        fontSize: 32,
                        fontWeight: FontWeight.w700,
                      )),),
                      SizedBox(height: 20,),
                      SizedBox(
                        width: 400,
                        child: Text(goal.description, maxLines: 4, style: TextStyle(
                            color: Colors.grey[700]!,
                            fontSize: 14
                        ),),
                      ),

                      SizedBox(height: 20,),

                      Row(
                        children: [
                          Text(
                            'Target: \$ ${formatter.format(goal.amount)}', style: TextStyle(color: Theme
                              .of(context)
                              .colorScheme
                              .primary),),
                          SizedBox(width: 20,),
                          Text('Current: \$ ${formatter.format(goal.current)}',
                            style: TextStyle(color: Colors.black),)
                        ],
                      ),
                      SizedBox(height: 20,),

                      Row(children: [
                        Text('Start Date: ${DateTime.fromMillisecondsSinceEpoch(
                            goal.startDate)}'),
                        SizedBox(width: 20,),
                        Text('Target Date: ${DateTime.fromMillisecondsSinceEpoch(
                            goal.projectedEndDate)}')
                      ],),
                      SizedBox(height: 20,),

                      Column(
                        children: [
                          Text("Current Progress"),
                          LinearPercentIndicator(
                            width: 300,
                            lineHeight: 14,
                            percent: (goal.current / goal.amount),
                            backgroundColor: Colors.grey[700],
                            progressColor: Theme.of(context).colorScheme.primaryVariant,
                          ),
                        ],
                      ),
                      SizedBox(height: 20,),
                      Row(children: [
                        SizedBox(
                          width: 150,
                          height: 100,
                          child: PrimaryButton(
                            title: "Edit",
                            onTap: () => {
                              appController.selectedGoal = index,
                              showAddGoalModal(context)
                            },
                            hoverElevation: 20,
                          ),
                        ),
                        SizedBox(width: 20,),
                        SizedBox(
                          width: 150,
                          height: 100,
                          child: SecundaryButton(
                            title: "Delete",
                            onTap: () => appController.removeGoal(index),
                            hoverElevation: 20,
                          ),
                        )
                      ],)
                    ]
                ),
                SizedBox(width: 20,),
                Expanded(
                child: Container(
                  child: goal.imageURL.isEmpty ? Image.asset('images/default-goal-image.jpg') : Image.network(goal.imageURL),
                  clipBehavior: Clip.antiAlias,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(20),
                  )
                )
                )
              ],
            ),
          ),
    );
  }

  void showAddGoalModal(context) {
    showDialog(context: context, builder: (context) =>
        GoalCard());
  }

  Widget build(BuildContext context) {
    if (appController.user == null) {
      Navigator.of(context).pushNamed('/');
      return Container();
    } else {
      return WillPopScope(
        onWillPop: () => signOutUserOnPop(context),
        child: Scaffold(
          appBar: AppBar(
              automaticallyImplyLeading: false,
              elevation: 0,
              backgroundColor: Colors.white,
              title: Text("${pickRandomGreeting()} ${appController.user!.name}!"
                  .capitalize!,
                  style: GoogleFonts.alexBrush(textStyle: TextStyle(
                      fontSize: 32,
                      fontWeight: FontWeight.w700,
                      color: Theme
                          .of(context)
                          .colorScheme
                          .primary
                  ))),
              actions: [
                IconButton(
                  color: Theme
                      .of(context)
                      .colorScheme
                      .primary,
                  icon: Icon(Icons.add),
                  onPressed: () => showAddGoalModal(context),
                )
              ]
          ),
          body: Center(
            child: Obx(() =>
                  SizedBox(
                    width: 1000,
                    child: ListView.builder(
                      scrollDirection: Axis.vertical,
                      itemCount: appController.goals.length,
                      itemBuilder: (context, index) => goalCard(appController.goals[index], context, index),
                ),
                  )),
          ),
        ),
      );
    }
  }
}