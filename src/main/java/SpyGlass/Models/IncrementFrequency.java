package SpyGlass.Models;

public enum IncrementFrequency {
    Daily(365), Weekly(52), BiWeekly(26), Monthly(12), Yearly(1);

    public int sections;
    IncrementFrequency(int sections) {
        this.sections = sections;
    }


}
