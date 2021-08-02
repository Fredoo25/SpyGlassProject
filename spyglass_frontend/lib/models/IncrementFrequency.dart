enum IncrementFrequency {
  Daily, Weekly, BiWeekly, Monthly, Yearly 
}

extension MyIncrementFrequency on IncrementFrequency {
  int get value {
    switch(this) {
      case IncrementFrequency.Daily: return 365;
      case IncrementFrequency.Weekly: return 52;
      case IncrementFrequency.BiWeekly: return 26;
      case IncrementFrequency.Monthly: return 12;
      default: return 1;
    }
  }

  String asString() {
    return this.toString().split(".").last; 
  }
}

T fromString<T>(Iterable<T> values, String value) {
   return values.firstWhere((type) => type.toString().split(".").last == value,
     orElse: () => throw new Exception("Invalid value: " + value));
}