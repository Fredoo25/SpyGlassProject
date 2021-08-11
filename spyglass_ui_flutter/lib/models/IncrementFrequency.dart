enum IncrementFrequency {
  Daily, Weekly, BiWeekly, Monthly, Yearly
}

extension MyIncrementFrequency on IncrementFrequency {
  String stringify() {
    return this.toString().split(".").last;
  }
  int section() {
    switch(this) {
      case IncrementFrequency.Daily : return 30;
      case IncrementFrequency.Weekly: return 4;
      case IncrementFrequency.Monthly: return 12;
      default: return 1;
    }
  }

}
IncrementFrequency fromString(String string) =>
    IncrementFrequency.values.firstWhere((element) => element.stringify() == string);