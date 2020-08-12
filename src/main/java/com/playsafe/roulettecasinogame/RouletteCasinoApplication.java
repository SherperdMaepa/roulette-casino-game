package com.playsafe.roulettecasinogame;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class RouletteCasinoApplication {


    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new GamingSession(), 0, 30000);
    }

    /*
     * this function generate a random wining number
     *
     * */
    public static int getRanWinNumber() {
        int min = 0;
        int max = 37;
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    /*
     * this class generate a random wining number in every 30 seconds
     *
     * */
    public static class GamingSession extends TimerTask {
        public void run() {
            // declare variables
            DecimalFormat df2 = new DecimalFormat("#.##");
            Scanner sc = new Scanner(System.in);
            String[] gamingDataArr = new String[4];
            String status = "";
            double winAmount = 0.0;
            String playerName = "";
            String betOpt = "";
            double betAmount = 0.0;
            int rndNumber = getRanWinNumber();

            // Adding input values to array
            for (int i = 0; i < gamingDataArr.length; i++){
                System.out.print("enter Name, bet option(1-36) OR (EVEN/ODD) and Bet amount: ");
                gamingDataArr[i] = sc.nextLine().trim();
            }

            // Writing input value to a file
            try {
                PrintWriter scanFile = new PrintWriter("data.txt");
                for (String s : gamingDataArr) {
                    scanFile.println(s);
                }
                System.out.println();
                scanFile.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // print winning number
            System.out.println("Number: " + rndNumber);
            System.out.println("- - - ");

            // print table header
            System.out.printf("%10s %10s %10s %10s %n","Player", "Bet","Outcome","Winnings");

            // process the data and cacluations
            for (int i = 0; i < gamingDataArr.length; i++) {
                String[] betDetails = gamingDataArr[i].split(" ");
                 playerName = betDetails[0].trim();
                 betOpt = betDetails[1].trim();
                 betAmount = Double.parseDouble(betDetails[2].trim());


                if (betOpt.matches("[0-9]+")) {
                    int temp = Integer.parseInt(betOpt);
                    if (temp == rndNumber && rndNumber % 2 == 0) {
                        status = "WIN";
                    } else if (temp == rndNumber) {
                        status = "WIN";
                    } else {
                        status = "LOST";
                    }
                    winAmount = 36 * betAmount;
                }else if (rndNumber % 2 == 0 && betOpt.contains("EVEN")) {
                    status = "WIN";
                    winAmount = (2 * betAmount);
                } else if (rndNumber % 2 != 0 && betOpt.contains("ODD")) {
                    status = "WIN";
                    winAmount = (2 * betAmount);
                } else {
                    status = "LOST";
                    winAmount = 0.0;
                }
                // print results
                System.out.printf("%10s %10s %10s %10s %n", playerName,betOpt,status,df2.format(winAmount));

                // show players who won the bet
                try {
                    File file = new File("data.txt");
                    Scanner scn = new Scanner(file);
                    while(scn.hasNext()){
                        String[] betWon = gamingDataArr[i].split(",");
                        String name = scn.next();
                        String betOption = scn.next();
                        double betAmt = Double.parseDouble(scn.next());
                        double totAmtWin = 0;
                        int totalBetWin = 0;

                        if (betOption.contains("WIN")) {
                            totalBetWin++;
                            totAmtWin =  betAmt + (betAmt * totalBetWin );
                            System.out.printf(name, totalBetWin, totAmtWin);
                        }

                    }
                    scn.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
