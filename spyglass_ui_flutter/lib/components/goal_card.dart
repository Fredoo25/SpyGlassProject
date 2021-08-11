import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:spyglass_ui_flutter/backend/storage.dart';
import 'package:spyglass_ui_flutter/components/custom-CheckBox.dart';
import 'package:spyglass_ui_flutter/components/custom_drop_down.dart';
import 'package:spyglass_ui_flutter/components/custom_text_field.dart';
import 'package:spyglass_ui_flutter/components/primary_btn.dart';
import 'package:spyglass_ui_flutter/components/secundary_btn.dart';
import 'package:spyglass_ui_flutter/controllers/controller.dart';
import 'package:spyglass_ui_flutter/models/IncrementFrequency.dart';
import 'package:spyglass_ui_flutter/models/goal.dart';
import 'package:uuid/uuid.dart';



class GoalCard extends StatelessWidget {
  final name = appController.selectedGoal == -1 ? '' : appController.goals[appController.selectedGoal].name;
  final desc = appController.selectedGoal == -1 ? '' : appController.goals[appController.selectedGoal].description;
  final target = appController.selectedGoal == -1 ? '' : appController.goals[appController.selectedGoal].amount;
  final start = appController.selectedGoal == -1 ? '' : appController.goals[appController.selectedGoal].current;
  final sDate = appController.selectedGoal == -1 ? '' : appController.goals[appController.selectedGoal].startDate;
  final eDate = appController.selectedGoal == -1 ? '' : appController.goals[appController.selectedGoal].projectedEndDate;
  final freq = appController.selectedGoal == -1 ? '' : appController.goals[appController.selectedGoal].savingInterval;
  final invested = appController.selectedGoal == -1 ? '' : appController.goals[appController.selectedGoal].isInvested;
  String imagePath = appController.selectedGoal == -1 ? '' : appController.goals[appController.selectedGoal].imageURL;
  final String goalUID = appController.selectedGoal == -1 ? Uuid().v4() : appController.goals[appController.selectedGoal].uuid;
  late final TextEditingController nameField;
  late final TextEditingController descField;
  late final TextEditingController targetAmount;
  late final TextEditingController startAmount ;
  late final TextEditingController startDate ;
  late final TextEditingController endDate ;
  final CustomDropDownController frequency = CustomDropDownController();
  final CustomCheckBoxController isInvested = CustomCheckBoxController();
  final verticalSeparation = 20.0;
  final horizontalSeparation = 20.0;

  GoalCard() {
    nameField = TextEditingController(text: name);
    descField = TextEditingController(text: desc);
    targetAmount = TextEditingController(text: target.toString());
    startAmount = TextEditingController(text: start.toString());
    startDate = TextEditingController(text: sDate.runtimeType == int ? DateTime.fromMillisecondsSinceEpoch(sDate as int).toString() : sDate as String);
    endDate = TextEditingController(text: eDate.runtimeType == int ? DateTime.fromMillisecondsSinceEpoch(eDate as int).toString() : eDate as String);
  }

  _selectDate(context, TextEditingController date) async {
    final DateTime? picked = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      lastDate: DateTime(2100),
      firstDate: DateTime(2000),
    );
    date.text = picked != null ? picked.toString() : '';
  }

  void saveNewGoal(context) async {
    Goal newGoal = new Goal(
        uuid: appController.selectedGoal != -1? appController.goals[appController.selectedGoal].uuid : Uuid().v4().toString(),
        amount: double.parse(targetAmount.value.text),
        current: double.parse(startAmount.value.text),
        name: nameField.value.text,
        description: descField.value.text,
        imageURL: imagePath,
        startDate: DateTime
            .parse(startDate.value.text)
            .millisecondsSinceEpoch,
        projectedEndDate: DateTime
            .parse(endDate.value.text)
            .millisecondsSinceEpoch,
        isInvested: isInvested.checked,
        amountPerInterval: 0.0,
        savingInterval: fromString(frequency.selectedElement),
        onTrack: true,
        investmentUID: isInvested.checked ? Uuid().v4().toString() : '',
        userUID: appController.user!.uuid);
    double percentage =  (newGoal.current / newGoal.amount) * 100;
    await showDialog(context: context, builder: (builder) => AlertDialog(
      title: Text("Great Work!"),
      content: Text("Congrats you made it to ${percentage.toStringAsFixed(0)}% of your goal! "),
      actions: [
        PrimaryButton(title: "Ok", onTap: () => Navigator.of(context).pop()),
      ]
    ));
    appController.selectedGoal != -1? appController.updateGoal(newGoal) : appController.addNewGoal(newGoal);
    appController.selectedGoal = -1;
    Navigator.of(context).pop();
  }

  Widget build(BuildContext context) {
    return Center(
      child: Container(
        height: 800,
        width: 600,
        child: Card(
            shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(20)
            ),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              mainAxisSize: MainAxisSize.min,
              children: [
                Text(appController.selectedGoal != -1? "Update a Goal!" : "Create a new Goal!",
                    style: GoogleFonts.alexBrush(textStyle: TextStyle(
                        color: Theme
                            .of(context)
                            .colorScheme
                            .primary,
                        fontSize: 36,
                        fontWeight: FontWeight.w700
                    ))),
                SizedBox(height: verticalSeparation,),
                SizedBox(
                  width: 500,
                  child: ThemedTextField(
                      isPassword: false,
                      label: "Goal Name",
                      hintText: "Enter goal name",
                      controller: nameField),
                ),
                SizedBox(height: verticalSeparation,),
                SizedBox(
                  width: 500,
                  child: ThemedTextField(
                    isPassword: false,
                    label: "Description",
                    hintText: "Enter goal Description Here",
                    controller: descField,
                    isMultiLine: true,
                    numOfLines: 4,),
                ),
                SizedBox(height: verticalSeparation,),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    SizedBox(
                      width: 245,
                      child: ThemedTextField(
                          isPassword: false,
                          label: "Target Amount",
                          hintText: "Goal Target",
                          controller: targetAmount),
                    ),
                    SizedBox(width: horizontalSeparation),
                    SizedBox(
                      width: 245,
                      child: ThemedTextField(
                          isPassword: false,
                          label: "Start Amount",
                          hintText: "Starting Balance",
                          controller: startAmount),
                    ),
                  ],
                ),
                SizedBox(height: verticalSeparation,),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    SizedBox(
                      width: 245,
                      child: ThemedTextField(
                        isPassword: false,
                        label: "Start Date",
                        controller: startDate,
                        hintText: 'Start Date',
                        onTap: () => _selectDate(context, startDate),
                      ),
                    ),
                    SizedBox(width: horizontalSeparation),
                    SizedBox(
                      width: 245,
                      child: ThemedTextField(
                        isPassword: false,
                        label: "End Date",
                        controller: endDate,
                        hintText: 'End Date',
                        onTap: () => _selectDate(context, endDate),
                      ),
                    ),
                  ],
                ),
                SizedBox(height: verticalSeparation,),
                CustomDropDown(controller: frequency,
                    elements: IncrementFrequency.values.map((value) =>
                        value.stringify()).toList()),
                SizedBox(height: verticalSeparation,),
                CustomCheckBox(
                    controller: isInvested, title: "Invest Savings?"),
                SizedBox(height: verticalSeparation,),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    SizedBox(
                      width: 150,
                      height: 100,
                      child: SecundaryButton(title: "Upload an Image",
                        onTap: () async {
                          FilePickerResult? result = await FilePicker.platform.pickFiles(
                            type: FileType.image,
                          );
                          if(result != null) {
                            PlatformFile file = result.files.first;
                            imagePath = await uploadImage(file, goalUID);
                          } else
                            imagePath = '';
                        },
                        hoverElevation: 20,),),
                    SizedBox(
                        width: 150,
                        height: 100,
                        child: PrimaryButton(title: "Save",
                          onTap: () => saveNewGoal(context),
                          hoverElevation: 20,)),
                    SizedBox(
                        width: 150,
                        height: 100,
                        child: SecundaryButton(title: "Cancel",
                          onTap: () => {
                          appController.selectedGoal = -1,
                          Navigator.of(context).pop()},
                          hoverElevation: 20,))
                  ],)
              ],
            )
        ),
      ),
    );
  }

}