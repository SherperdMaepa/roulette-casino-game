package com.playsafe.roulettecasinogame;

public class GamingData {

    private String name;
    private String betOption;
    private double betAmount;
    private String status;
    private double winAmount;
    private double totalAvgAmount;

    public GamingData() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBetOption() {
        return betOption;
    }

    public void setBetOption(String betOption) {
        this.betOption = betOption;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(double winAmount) {
        this.winAmount = winAmount;
    }

    public double getTotalAvgAmount() {
        return totalAvgAmount;
    }

    public void setTotalAvgAmount(double totalAvgAmount) {
        this.totalAvgAmount = totalAvgAmount;
    }

    @Override
    public String toString() {
        return name + betOption + betAmount;
    }
}
