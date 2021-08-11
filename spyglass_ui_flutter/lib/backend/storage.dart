import 'dart:io';
import 'package:file_picker/file_picker.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:spyglass_ui_flutter/constants/logger.dart';
import 'package:spyglass_ui_flutter/controllers/controller.dart';


FirebaseStorage storage = FirebaseStorage.instance;


Future<String> uploadImage(PlatformFile platformFile, String goalUID) async {
  logger.i('Uploading image ${platformFile.name} for goal: $goalUID');
  try{
    await storage.ref('${appController.user!.uuid}/$goalUID.${platformFile.name.split('.').last}')
        .putData(platformFile.bytes!);
    return await storage.ref('${appController.user!.uuid}/$goalUID.${platformFile.name.split('.').last}').getDownloadURL();
  } on FirebaseException catch(e) {
    logger.e(e.message);
    return '';
  }
}