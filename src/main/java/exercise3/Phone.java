package exercise3;

public class Phone {
    private String number;
    private int duration;
    private int amount;

    public Phone(String number, int duration, int amount) {
        this.number = number;
        this.duration = duration;
        this.amount = amount;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number='" + number + '\'' +
                ", duration='" + duration + '\'' +
                ", amount=" + amount +
                '}';
    }
}
