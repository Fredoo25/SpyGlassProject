import 'dart:io';
import 'package:file_picker/file_picker.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/foundation.dart';
import 'package:spyglass_ui_flutter/constants/logger.dart';
import 'package:spyglass_ui_flutter/controllers/controller.dart';
import 'package:spyglass_ui_flutter/models/goal.dart';


FirebaseStorage storage = FirebaseStorage.instance;


Future<String> uploadImage(PlatformFile platformFile, String goalUID, String imageUrl) async {
  logger.i('Uploading image ${platformFile.name} for goal: $goalUID');
  if(imageUrl.isNotEmpty) {
    deleteRemoteImage(imageUrl);
  }
  try{
    logger.i("Uploading new image!");
    if(kIsWeb) {
      await storage.ref(
          '${appController.user!.uuid}/$goalUID.${platformFile.name
              .split('.')
              .last}')
          .putData(platformFile.bytes!);
    } else {
      await storage.ref('${appController.user!.uuid}/$goalUID.${platformFile.name
          .split('.')
          .last}').putFile(File(platformFile.path!));
    }
    return await storage.ref('${appController.user!.uuid}/$goalUID.${platformFile.name.split('.').last}').getDownloadURL();
  } on FirebaseException catch(e) {
    logger.e(e.message);
    return '';
  }
}

void deleteRemoteImage(String imageURL) async {
  logger.i("Deleting current image in Bucket!");
  await storage.refFromURL(imageURL).delete();
}