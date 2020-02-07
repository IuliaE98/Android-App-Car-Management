package com.example.carbuddy;

public class ExpenseDetails  {
    private String type;
    private String reason;
    private String furtherObservations;

    public ExpenseDetails(String type, String reason, String furtherObservations) {
        this.type = type;
        this.reason = reason;
        this.furtherObservations = furtherObservations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFurtherObservations() {
        return furtherObservations;
    }

    public void setFurtherObservations(String furtherObservations) {
        this.furtherObservations = furtherObservations;
    }

    @Override
    public String toString() {
        return "ExpenseDetails{" +
                "type='" + type + '\'' +
                ", reason='" + reason + '\'' +
                ", furtherObservations='" + furtherObservations + '\'' +
                '}';
    }

}
