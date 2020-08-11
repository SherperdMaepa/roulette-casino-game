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
            DecimalFormat df2 = new DecimalFormat("#.##");
            Scanner sc = new Scanner(System.in);

            int rndNumber = getRanWinNumber();
            GamingData gamingData = new GamingData();
            ArrayList<GamingData> gamingDataList = new ArrayList<>();

            try {
                Scanner scan = new Scanner(new File("data.txt"));
                while(scan.hasNextLine()){
                    System.out.println(scan.nextLine());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // Get details from the player
            System.out.print("enter play name: ");
            String name = sc.nextLine();
            System.out.print("enter bet option(1-36) OR (EVEN/ODD): ");
            String betOpt = sc.nextLine();
            System.out.print("enter bet amount: ");
            double betAmount = sc.nextDouble();

            // Get set player details
            gamingData.setName(name);
            gamingData.setBetOption(betOpt);
            gamingData.setBetAmount(betAmount);

            // String[] attributes = line.split(",");

            if (betOpt.matches("[0-9]+")) {
                int temp = Integer.parseInt(betOpt);
                if (temp == rndNumber && rndNumber % 2 == 0) {
                    gamingData.setStatus("WIN");
                } else if (temp == rndNumber) {
                    gamingData.setStatus("WIN");
                } else {
                    gamingData.setWinAmount(0);
                    gamingData.setStatus("LOST");
                }
                gamingData.setWinAmount(36 * betAmount);
            } else if (rndNumber % 2 == 0 && betOpt.contains("EVEN")) {
                gamingData.setStatus("WIN");
                gamingData.setWinAmount(2 * betAmount);
            } else if (rndNumber % 2 != 0 && betOpt.contains("ODD")) {
                gamingData.setStatus("WIN");
                gamingData.setWinAmount(2 * betAmount);
            } else {
                gamingData.setStatus("LOST");
                gamingData.setWinAmount(0);
                System.out.println("LOST");
            }
            // Add player inputs to a list
            gamingDataList.add(gamingData);

            try {
                FileWriter file = new FileWriter("data.txt");
                for (GamingData gData : gamingDataList) {
                    file.write(gData.toString());
                    file.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Number: " + rndNumber);
            System.out.println("-----");
            System.out.println("Player      Bet     Outcome     Winnings");
            for (GamingData i : gamingDataList) {
                System.out.println(i.getName() + "        " + i.getBetOption() + "      " +
                        gamingData.getStatus() + "        " + df2.format(gamingData.getWinAmount()));
            }
        }
    }
}
