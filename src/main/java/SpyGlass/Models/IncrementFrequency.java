package SpyGlass.Models;

public enum IncrementFrequency {
    Daily(30), Weekly(4), BiWeekly(2), Monthly(12), Yearly(1);

    public int sections;
    IncrementFrequency(int sections) {
        this.sections = sections;
    }


}
