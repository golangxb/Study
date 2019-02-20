

public enum Week {
    One(1,"星期一"),Two(2,"星期二"),Three(3,"星期三"),Four(4,"星期四"),Five(5,"星期五"),Six(6,"星期六"),Seven(7,"星期日");

    private Integer number;
    private String weeks;

    public Integer getNumber() {
        return number;
    }

    public String getWeeks() {
        return weeks;
    }

    Week(Integer number, String weeks) {
        this.number = number;
        this.weeks = weeks;
    }

    public static Week foreachWeek(int index){

        Week[] values = Week.values();
        for (Week value :values) {
            if(index==value.getNumber()){
                return value;
            }
        }
        return null;
    }

}

