package main.models;

public class SecondQueryModel {
    private String MuseumName;
    private Integer Sum;
    private Integer Count;
    public SecondQueryModel(String name, Integer sum, Integer count) {
        MuseumName = name;
        Sum = sum;
        Count = count;
    }

    public String getMuseumName() {
        return MuseumName;
    }

    public Integer getSum() {
        return Sum;
    }

    public Integer getCount() {
        return Count;
    }
}
