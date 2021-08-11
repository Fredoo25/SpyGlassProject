import 'package:logger/logger.dart';

Logger logger = Logger(
  printer: PrettyPrinter(
    methodCount: 1,
    lineLength: 80,
    colors: true,
    printEmojis: true,
    printTime: false,
    errorMethodCount: 2
  )
);