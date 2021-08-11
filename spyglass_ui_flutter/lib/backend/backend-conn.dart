
import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:get/get.dart';
import 'package:get/get_connect/http/src/status/http_status.dart';
import 'package:spyglass_ui_flutter/constants/logger.dart';
import 'package:spyglass_ui_flutter/controllers/controller.dart';
import 'package:spyglass_ui_flutter/models/serializable.dart';
import 'package:spyglass_ui_flutter/models/goal.dart';
import 'package:spyglass_ui_flutter/models/investmentAccount.dart';
import 'package:spyglass_ui_flutter/models/user.dart';
import 'package:http/http.dart' as http;


final _appController = Get.find<UserController>();
final dbURI = kIsWeb? "http://localhost:8080" : "http://10.0.2.2:8080";
final Map<Type, String> _endPoints = {
  User : '/users',
  Goal : '/goals',
  Investments : '/investments'
};
final Map<String, String> _headers = {
  'Accept' : 'application/json',
  'Content-Type' : 'application/json'
};


Future<bool> post( Serializable newObject ) async {
  logger.i("Attempting to add new ${newObject.type} to DB with UID: ${newObject.uuid}");
  try {
    var response = await http.post(Uri.parse(dbURI + _endPoints[newObject.type]!),
    headers: _headers,
    body: json.encode(newObject.json));
    if(response.statusCode == HttpStatus.ok) {
      logger.i("${newObject.type} added Successfully!");
      return true;
    } else {
      logger.e("Got Error Code: ${response.statusCode} from DB with Message. \n"
          "${response.body}");
      return false;
    }
  } on Error catch(err) {
    logger.e(err);
    return false;
  }
}

Future<bool> update(Serializable updateObject) async {
  logger.i("Attempting to update ${updateObject.type} in DB with UID: ${updateObject.uuid}");
  try {
    var response = await http.put(Uri.parse(dbURI + _endPoints[updateObject.type]! + '/${updateObject.uuid}'),
    headers: _headers,
    body: json.encode(updateObject.json));
    if(response.statusCode == HttpStatus.ok) {
      logger.i("Resource ${updateObject.uuid} updated Successfully");
      return true;
    } else {
      logger.e("Unable to update resource."
          "\nServer Returned Status Code: ${response.statusCode} "
          "\nServer Returned Response Body:  ${response.body}");
      return false;
    }
  } on Error catch (err) {
    logger.e(err.toString());
    return false;
  }
}

Future<bool> delete(Serializable deleteObject) async {
  logger.i("Attempting to delete ${deleteObject.type} from DB with UUID: ${deleteObject.uuid}");
  try{
    var response = await http.delete(Uri.parse(dbURI + _endPoints[deleteObject.type]! + '/${deleteObject.uuid}'),
        headers: _headers,
        body: json.encode(deleteObject.uuid));
    if(response.statusCode == HttpStatus.ok) {
      logger.i("Resource ${deleteObject.uuid} deleted successfully!");
      return true;
    } else {
      logger.e("Could not delete resource."
          "\nServer Response Code: ${response.statusCode}. "
          "\nServer Response Body: ${response.body}");
      return false;
    }
  } on Error catch(ex) {
    logger.e(ex.toString());
    return false;
  }
}

/// Function is used to retrieve a single resource from the backend
/// using the resources unique uid.
Future<T?> getSingle<T extends Serializable>(String resourceUID) async {
  List<T> parsedObjects = [];
  //Get the type of data we are trying to get from backend
  Type type = getType<T>();
  logger.i("Attempting to get $type with UID: $resourceUID");
  try{
    //make the call the backend to the endpoint for the provided data type
    var response = await http.get(Uri.parse(dbURI + _endPoints[type]!),
    headers: _headers);
    //Check the status of the response
    if(response.statusCode == HttpStatus.ok) {
      //Start parsing the response body
      List<dynamic> jsonObjects = await json.decode(response.body);
      jsonObjects.forEach((element) => parsedObjects.add(fromJson<T>(element)));
      if(parsedObjects.isEmpty) {
        return null;
      } else {
        return parsedObjects.first;
      }
    } else {
      logger.e("Could not get resource $type."
      "\nServer Response Code: ${response.statusCode}. "
      "\nServer Response Body: ${response.body}");
    }
  } on Error catch(ex) {
    logger.e(ex.toString());
    return null;
  }
}

Future<List<T>?> getMultiple<T extends Serializable>() async {
  List<T> parsedObjects = [];
  Type type = getType<T>();
  logger.i("Attempting to get all ${getType<T>()} for User: ${_appController.user!.uuid}");
  try{
    var response = await http.get(Uri.parse(dbURI + _endPoints[type]! + "/${_appController.user!.uuid}"),
        headers: _headers);
    if(response.statusCode == HttpStatus.ok) {
      List<dynamic> jsonObjects = await json.decode(response.body);
      jsonObjects.forEach((element) => parsedObjects.add(fromJson<T>(element)));
      logger.i("Loaded ${parsedObjects.length} $type for User ${_appController.user!.uuid}");
      return parsedObjects;
    } else {
      logger.e("Could not get resource $type."
          "\nServer Response Code: ${response.statusCode}. "
          "\nServer Response Body: ${response.body}");
    }
  } on Error catch(ex) {
    logger.e(ex.toString());
    return null;
  }

}
