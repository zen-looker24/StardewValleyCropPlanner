import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
/*The idea is to be able to include/exclude certain crops, considering plantable tiles,
season, max return, etc */
//excludes trellis crops
//rice grows in 8 days unirrigated, 6 irrigated, the CSV says 6
//sunflowers drop 0-2 seeds upon harvest
//season = "All" indicates the crop grows in spring, summer, and fall
//A string from an object doesn't match input unless the input is in double quotes??
//There are currently no regrowing crops that grow in spring
//TODO: Make class variables private and functions public, possibly split into multiple files
public class StardewCropPlanner {
    static abstract class crops {
        String name;
        int sellPriceNormal;
        int sellPriceSilver;
        int sellPriceGold;
        int sellPriceIridium;
        boolean isAvailableYear1;
        int growTime;
        double yieldPerPlant;
        String season1;
        String season2; //if none then only grows in season1
        double calculatedSellPrice;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getSellPriceNormal() {
            return sellPriceNormal;
        }
        public void setSellPriceNormal(int sellPriceNormal) {
            this.sellPriceNormal = sellPriceNormal;
        }
        public int getSellPriceSilver() {
            return sellPriceSilver;
        }
        public void setSellPriceSilver(int sellPriceSilver) {
            this.sellPriceSilver = sellPriceSilver;
        }
        public int getSellPriceGold() {
            return sellPriceGold;
        }
        public void setSellPriceGold(int sellPriceGold) {
            this.sellPriceGold = sellPriceGold;
        }
        public int getSellPriceIridium() {
            return sellPriceIridium;
        }
        public void setSellPriceIridium(int sellPriceIridium) {
            this.sellPriceIridium = sellPriceIridium;
        }
        public boolean isAvailableYear1() {
            return isAvailableYear1;
        }
        public void setAvailableYear1(boolean availableYear1) {
            isAvailableYear1 = availableYear1;
        }
        public int getGrowTime() {
            return growTime;
        }
        public void setGrowTime(int growTime) {
            this.growTime = growTime;
        }
        public double getYieldPerPlant() {
            return yieldPerPlant;
        }
        public void setYieldPerPlant(double yieldPerPlant) {
            this.yieldPerPlant = yieldPerPlant;
        }
        public String getSeason1() {
            return season1;
        }
        public void setSeason1(String season1) {
            this.season1 = season1;
        }
        public String getSeason2() {
            return season2;
        }
        public void setSeason2(String season2) {
            this.season2 = season2;
        }
        public double getCalculatedSellPrice() {
            return calculatedSellPrice;
        }
        public void setCalculatedSellPrice(double calculatedSellPrice) {
            this.calculatedSellPrice = calculatedSellPrice;
        }
    }
    public static class regular extends crops {
        int seedCostPierre;
        int seedCostJoja; //if 0 then not sold

        public int getSeedCostPierre() {
            return seedCostPierre;
        }
        public void setSeedCostPierre(int seedCostPierre) {
            this.seedCostPierre = seedCostPierre;
        }
        public int getSeedCostJoja() {
            return seedCostJoja;
        }
        public void setSeedCostJoja(int seedCostJoja) {
            this.seedCostJoja = seedCostJoja;
        }

        public String toString() {
            String str = "  Name: " + name + "\n" +
                    "  Grow time: " + growTime + "\n" +
                    "  Sell price: " + sellPriceNormal + "\n" +
                    "  Seed cost: " + seedCostPierre;
            return str;
        }

        public regular() {}//attributes can be assigned through regular.set(attribute)
    }
    public static class regrowing extends regular {
        int regrowTime;

        public int getRegrowTime() {
            return regrowTime;
        }
        public void setRegrowTime(int regrowTime) {
            this.regrowTime = regrowTime;
        }

        public String rToString() {
            String str = "  Name: " + name + "\n" +
            "  Grow time: " + growTime + "\n" +
            "  Regrow time: " + regrowTime + "\n" +
            "  Sell price: " + sellPriceNormal + "\n" +
            "  Seed cost: " + seedCostPierre;
            return str;
        }

        public regrowing() {}
    }
    /*class oddObtain extends crops {
        int seedCost;
        String obtainmentMethod;
        boolean isRegrowing;
        int regrowTime;
        //rhubarb, beet, and starfruit can only be purchased in the oasis, strawberry can only be purchased at egg fest
        //coffee drops from dust sprites and can be purchased from the traveling merchant
        //carrot, squash, broccoli, ancient, cactus, pineapple, taro, sweet gem
    }Come back to this after others are confirmed working*/
    public static class farms {
        String name;
        int totalArableTiles;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getTotalArableTiles() {
            return totalArableTiles;
        }
        public void setTotalArableTiles(int totalArableTiles) {
            this.totalArableTiles = totalArableTiles;
        }

        public farms() {}
    }
    public static class regularCropStats {
        String name;
        double moneyPerTile;
        double moneyPerTilePerDay;
        double noOfHarvestsSeason;
        double moneyPerTilePerSeason;

        public String getName() {
            return name;
        }
        public double getMoneyPerTile() {
            return moneyPerTile;
        }
        public double getMoneyPerTilePerDay() {
            return moneyPerTilePerDay;
        }
        public double getNoOfHarvestsSeason() {
            return noOfHarvestsSeason;
        }
        public double getMoneyPerTilePerSeason() {
            return moneyPerTilePerSeason;
        }

        public String toString() {
            String str = "  Money per tile per day: " + moneyPerTilePerDay + "\n" +
                    "  Money per tile per season: " + moneyPerTilePerSeason;
            return str;
        }

        public regularCropStats(String name, double moneyPerTile, double moneyPerTilePerDay, double noOfHarvestsSeason, double moneyPerTilePerSeason) {
            this.name = name;
            this.moneyPerTile = moneyPerTile;
            this.moneyPerTilePerDay = moneyPerTilePerDay;
            this.noOfHarvestsSeason = noOfHarvestsSeason;
            this.moneyPerTilePerSeason = moneyPerTilePerSeason;
        }
    }
    public static class regrowingCropStats {
        String name;
        double noOfHarvests;
        double moneyPerTilePerSeason;
        double moneyPerTilePerDay;

        public String getName() {
            return name;
        }
        public double getNoOfHarvests() {
            return noOfHarvests;
        }
        public double getMoneyPerTilePerSeason() {
            return moneyPerTilePerSeason;
        }
        public double getMoneyPerTilePerDay() {
            return moneyPerTilePerDay;
        }

        public String toString() {
            String str = "  Money per tile per day left in growing season(s): " + moneyPerTilePerDay + "\n" +
            "  Money per tile until end of growing season(s): " + moneyPerTilePerSeason;
            return str;
        }

        public regrowingCropStats(String name, double noOfHarvests, double moneyPerTilePerSeason, double moneyPerTilePerDay) {
            this.name = name;
            this.noOfHarvests = noOfHarvests;
            this.moneyPerTilePerSeason = moneyPerTilePerSeason;
            this.moneyPerTilePerDay = moneyPerTilePerDay;
        }
    }
    public static class qualityProportions {
        int farmingLevel;
        double percentNormal;
        double percentSilver;
        double percentGold;
        double percentIridium;

        public int getFarmingLevel() {
            return farmingLevel;
        }
        public double getPercentNormal() {
            return percentNormal;
        }
        public double getPercentSilver() {
            return percentSilver;
        }
        public double getPercentGold() {
            return percentGold;
        }
        public double getPercentIridium() {
            return percentIridium;
        }

        public qualityProportions(int farmingLevel, double percentNormal, double percentSilver, double percentGold, double percentIridium) {
            this.farmingLevel = farmingLevel;
            this.percentNormal = percentNormal;
            this.percentSilver = percentSilver;
            this.percentGold = percentGold;
            this.percentIridium = percentIridium;
        }
    }

    /*public static ArrayList<Object> setUpFarmList(String filepath) throws FileNotFoundException {
        File farmCSV = new File(filepath);
        ArrayList<Object> farmList = new ArrayList<>();
        Scanner listReader = new Scanner(farmCSV);
        listReader.nextLine();
        while (listReader.hasNext()) {
            String[] line = listReader.nextLine().split(",\\s*");
            String name = line[0];
            int totalArableTiles = Integer.parseInt(line[1]);
            farms farm = new farms();
            farm.setName(name);
            farm.setTotalArableTiles(totalArableTiles);
            farmList.add(farm);
        }
        return farmList;
    }*/
    public static ArrayList<Object> setUpRegularList(String filepath) throws FileNotFoundException {
        File regularCSV = new File(filepath);
        ArrayList<Object> regularList = new ArrayList<>();
        Scanner listReader = new Scanner(regularCSV);
        listReader.nextLine();
        while (listReader.hasNext()) {
            String[] line = listReader.nextLine().split(",\\s*");
            String name = line[0];
            int seedCostPierre = Integer.parseInt(line[1]);
            int seedCostJoja = Integer.parseInt(line[2]);
            int sellPriceNormal = Integer.parseInt(line[3]);
            int sellPriceSilver = Integer.parseInt(line[4]);
            int sellPriceGold = Integer.parseInt(line[5]);
            int sellPriceIridium = Integer.parseInt(line[6]);
            boolean isAvailableYear1 = Boolean.parseBoolean(line[7]);
            int growTime = Integer.parseInt(line[8]);
            double yieldPerPlant = Double.parseDouble(line[9]);
            String season1 = line[10];
            String season2 = line[11];
            regular crop = new regular();
            crop.setName(name);
            crop.setSeedCostPierre(seedCostPierre);
            crop.setSeedCostJoja(seedCostJoja);
            crop.setSellPriceNormal(sellPriceNormal);
            crop.setSellPriceSilver(sellPriceSilver);
            crop.setSellPriceGold(sellPriceGold);
            crop.setSellPriceIridium(sellPriceIridium);
            crop.setAvailableYear1(isAvailableYear1);
            crop.setGrowTime(growTime);
            crop.setYieldPerPlant(yieldPerPlant);
            crop.setSeason1(season1);
            crop.setSeason2(season2);
            regularList.add(crop);
        }
        return regularList;
    }
    public static ArrayList<Object> setUpRegularStatList(ArrayList<Object> chosenRegularCrops, int currentDay, String season) {
        ArrayList<Object> regularStatList = new ArrayList<>();
        for (int i = 0; i < chosenRegularCrops.size(); i++) {
            regular currentCrop = (regular) chosenRegularCrops.get(i);
            String name = currentCrop.getName();
            double totalDays = 28.0;
            if ((season.equals("\"Spring\"") && currentCrop.getSeason2().equals("\"Summer\"")) ||
                    (season.equals("\"Summer\"") && currentCrop.getSeason2().equals("\"Fall\""))) {
                totalDays *= 2;
            }
            double moneyPerTile = (currentCrop.getCalculatedSellPrice() * currentCrop.getYieldPerPlant()) - currentCrop.getSeedCostPierre();
            double moneyPerTilePerDay = moneyPerTile / currentCrop.getGrowTime();
            double noOfHarvestsSeason = Math.floor((totalDays - currentDay) / currentCrop.getGrowTime());
            double moneyPerTilePerSeason = noOfHarvestsSeason * moneyPerTile;
            moneyPerTile = Math.floor(moneyPerTile);
            moneyPerTilePerDay = Math.floor(moneyPerTilePerDay);
            moneyPerTile = Math.floor(moneyPerTile);
            moneyPerTilePerSeason = Math.floor(moneyPerTilePerSeason);
            regularCropStats newCrop = new regularCropStats(name, moneyPerTile, moneyPerTilePerDay, noOfHarvestsSeason, moneyPerTilePerSeason);
            regularStatList.add(newCrop);
        }
        return regularStatList;
    }
    public static ArrayList<Object> setUpRegrowingList(String filepath) throws FileNotFoundException{
        File regrowingCSV = new File(filepath);
        ArrayList<Object> regrowingList = new ArrayList<>();
        Scanner listReader = new Scanner(regrowingCSV);
        listReader.nextLine();
        while (listReader.hasNext()) {
            String[] line = listReader.nextLine().split(",\\s*");
            String name = line[0];
            int seedCostPierre = Integer.parseInt(line[1]);
            int seedCostJoja = Integer.parseInt(line[2]);
            int sellPriceNormal = Integer.parseInt(line[3]);
            int sellPriceSilver = Integer.parseInt(line[4]);
            int sellPriceGold = Integer.parseInt(line[5]);
            int sellPriceIridium = Integer.parseInt(line[6]);
            boolean isAvailableYear1 = Boolean.parseBoolean(line[7]);
            int growTime = Integer.parseInt(line[8]);
            double yieldPerPlant = Double.parseDouble(line[9]);
            String season1 = line[10];
            String season2 = line[11];
            int regrowTime = Integer.parseInt(line[12]);
            regrowing crop = new regrowing();
            crop.setName(name);
            crop.setSeedCostPierre(seedCostPierre);
            crop.setSeedCostJoja(seedCostJoja);
            crop.setSellPriceNormal(sellPriceNormal);
            crop.setSellPriceSilver(sellPriceSilver);
            crop.setSellPriceGold(sellPriceGold);
            crop.setSellPriceIridium(sellPriceIridium);
            crop.setAvailableYear1(isAvailableYear1);
            crop.setGrowTime(growTime);
            crop.setYieldPerPlant(yieldPerPlant);
            crop.setSeason1(season1);
            crop.setSeason2(season2);
            crop.setRegrowTime(regrowTime);
            regrowingList.add(crop);
        }
        return regrowingList;
    }
    public static ArrayList<Object> setUpRegrowingStatList(ArrayList<Object> chosenRegrowingCrops, int currentDate, String season) {
        ArrayList<Object> regrowingStatList = new ArrayList<>();
        for (int i = 0; i < chosenRegrowingCrops.size(); i++) {
            regrowing currentCrop = (regrowing) chosenRegrowingCrops.get(i);
            String name = currentCrop.getName();
            double totalDays = 28.0;
            if ((season.equals("\"Spring\"") && currentCrop.getSeason2().equals("\"Summer\"")) ||
                    (season.equals("\"Summer\"") && currentCrop.getSeason2().equals("\"Fall\""))) {
                totalDays *= 2;
            }
            double noOfHarvests = Math.floor((totalDays - currentDate - currentCrop.getGrowTime()) / currentCrop.getRegrowTime()) + 1;
            double moneyPerTilePerSeason = (currentCrop.getCalculatedSellPrice() * currentCrop.getYieldPerPlant() * noOfHarvests) - currentCrop.getSeedCostPierre();
            double moneyPerTilePerDay = moneyPerTilePerSeason / (totalDays - 1);
            moneyPerTilePerSeason = Math.floor(moneyPerTilePerSeason);
            moneyPerTilePerDay = Math.floor(moneyPerTilePerDay);
            regrowingCropStats newCrop = new regrowingCropStats(name, noOfHarvests, moneyPerTilePerSeason, moneyPerTilePerDay);
            regrowingStatList.add(newCrop);
        }
        return regrowingStatList;
    }
    public static ArrayList<Object> setUpCropQualityPropList(int fertilizer) throws FileNotFoundException {
        ArrayList<Object> cropQuality = new ArrayList<>();
        String filePath = "C:\\Users\\no1sp\\IdeaProjects\\StardewCropPlanner\\src\\";
        switch (fertilizer) {
            case 0: filePath += "StardewNoFertilizer.txt"; break;
            case 1: filePath += "StardewBasicFertilizer.txt"; break;
            case 2: filePath += "StardewQualityFertilizer.txt"; break;
            case 3: filePath += "StardewDeluxeFertilizer.txt"; break;
        }
        File qualityPropList = new File(filePath);
        Scanner listReader = new Scanner(qualityPropList);
        listReader.nextLine();
        double percentNormal = 0;
        double percentSilver = 0;
        double percentGold = 0;
        double percentIridium = 0;
        while (listReader.hasNext()) {
            String[] line = listReader.nextLine().split(",\\s*");
            int farmingLevel = Integer.parseInt(line[0]);
            if (fertilizer == 3) {
                percentSilver = Double.parseDouble(line[1]);
                percentGold = Double.parseDouble(line[2]);
                percentIridium = Double.parseDouble(line[3]);
            } else {
                percentNormal = Double.parseDouble(line[1]);
                percentSilver = Double.parseDouble(line[2]);
                percentGold = Double.parseDouble(line[3]);
            }
            qualityProportions currentLine = new qualityProportions(farmingLevel, percentNormal, percentSilver, percentGold, percentIridium);
            cropQuality.add(currentLine);
        }
        return cropQuality;
    }

    public static int farmCheck(Scanner scanner, ArrayList<Object> farmList) {
        Boolean farmFound = false;
        System.out.println("What farm are you on?");
        System.out.println("Here are the options, be sure to type them as you see them:");
        //listing out the possible farms
        for (int j = 0; j < farmList.size(); j++) {
            farms farmListItem = (farms) farmList.get(j);
            if (j == (farmList.size() - 1)) {
                System.out.print(farmListItem.getName() + "\n");
            } else {
                System.out.print(farmListItem.getName() + ", ");
            }
        }
        int farmIndex = -1;
        //check for farm of matching name in farmList
        while (!farmFound) {
            System.out.println("Make sure to spell it correctly!");
            String farm = scanner.nextLine();//get user farm
            farm = "\"" + farm + "\"";//the code recognizes a match when the entry is surrounded in double quotes
            for (int i = 0; i < farmList.size(); i++) {
                farms farmListItem = (farms) farmList.get(i);
                if (farm.equals(farmListItem.getName())) {
                    farmIndex = i;
                    farmFound = true;
                }
            }
        }
        return farmIndex;
    }
    public static int fertilizerCheck(Scanner scanner) {
        System.out.println("Which type of fertilizer will you use?");
        System.out.println("[1] None \n[2] Basic\n[3] Quality\n[4] Deluxe");
        boolean properChoice = false;
        int choice = 0;
        while (!properChoice) {
            System.out.println("Type 1, 2, 3, or 4");
            choice = scanner.nextInt();
            if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
                properChoice = true;
            }
        }
        return choice;
    }
    public static int farmLevelCheck(Scanner scanner) {
        System.out.println("What is your farming level?");
        boolean properLevel = false;
        int farmingLevel = -1;
        while (!properLevel) {
            System.out.println("Type a number 0-10");
            farmingLevel = scanner.nextInt();
            if (farmingLevel >= 0 && farmingLevel <= 10) {
                properLevel = true;
            }
        }
        return farmingLevel;
    }
    public static String seasonCheck(Scanner scanner) { //use toUppercase on the input's first letter, and toLowercase on all other letters, to remove the input proper capitalization requirement
        System.out.println("Which season is it? Type Spring, Summer, or Fall");
        Boolean properSeason = false;
        String season = "";
        while (!properSeason) {
            System.out.println("Make sure to spell and capitalize correctly!");
            season = scanner.next();
            if (season.equals("Spring") || season.equals("Summer") || season.equals("Fall")) {
                season = "\"" + season + "\"";
                properSeason = true;
            }
        }
        return season;
    }
    public static int cropChoiceCheck(Scanner scanner) {
        System.out.println("Which types of crops would you like to use?");
        System.out.println("[1] Purchasable at Pierre's/Joja Mart, not including regrowing crops");
        System.out.println("[2] Regrowing crops");
        System.out.println("[3] Both");
        int cropChoice = 0;
        while (cropChoice != 1 && cropChoice != 2 && cropChoice != 3) {
            System.out.println("Type 1, 2, or 3");
            cropChoice = scanner.nextInt();
        }
        return cropChoice;
    }
    public static Boolean year1Check(Scanner scanner) {
        System.out.println("Are you currently in year 1?");
        System.out.println("[1] Yes \n[2] No");
        int year1answer = 0;
        while (year1answer != 1 && year1answer != 2) {
            System.out.println("Type 1 or 2");
            year1answer = scanner.nextInt();
        }
        if (year1answer == 1) {
            return true;
        } else if (year1answer == 2) {
            return false;
        }
        return null;
    }
    public static int dateCheck(Scanner scanner) {
        System.out.println("What numerical day of the season is it?");
        int currentDate = 0;
        while (currentDate < 1 || currentDate > 28) {
            System.out.println("Enter a number between 1 and 28");
            currentDate = scanner.nextInt();
        }
        return currentDate;
    }

    public static void calculateSellPriceRegular(ArrayList<Object> chosenCrops, ArrayList<Object> qualityProps, int fertilizer, int farmingLevel) {
        double calcSellPrice = 0;
        qualityProportions correctProps = (qualityProportions) qualityProps.get(farmingLevel);
        for (int i = 0; i < chosenCrops.size(); i++) {
            regular currentCrop = (regular) chosenCrops.get(i);
            if (fertilizer == 3) {
                calcSellPrice = (currentCrop.getSellPriceSilver() * correctProps.getPercentSilver())
                                + (currentCrop.getSellPriceGold() * correctProps.getPercentGold())
                                + (currentCrop.getSellPriceIridium() * correctProps.getPercentIridium());
            } else {
                calcSellPrice = (currentCrop.getSellPriceNormal() * correctProps.getPercentNormal())
                                + (currentCrop.getSellPriceSilver() * correctProps.getPercentSilver())
                                + (currentCrop.getSellPriceGold() * correctProps.getPercentGold());
            }
            currentCrop.setCalculatedSellPrice(calcSellPrice);
        }
    }
    public static void calculateSellPriceRegrowing(ArrayList<Object> chosenCrops, ArrayList<Object> qualityProps, int fertilizer, int farmingLevel) {
        double calcSellPrice = 0;
        qualityProportions correctProps = (qualityProportions) qualityProps.get(farmingLevel);
        for (int i = 0; i < chosenCrops.size(); i++) {
            regrowing currentCrop = (regrowing) chosenCrops.get(i);
            if (fertilizer == 3) {
                calcSellPrice = (currentCrop.getSellPriceSilver() * correctProps.getPercentSilver())
                        + (currentCrop.getSellPriceGold() * correctProps.getPercentGold())
                        + (currentCrop.getSellPriceIridium() * correctProps.getPercentIridium());
            } else {
                calcSellPrice = (currentCrop.getSellPriceNormal() * correctProps.getPercentNormal())
                        + (currentCrop.getSellPriceSilver() * correctProps.getPercentSilver())
                        + (currentCrop.getSellPriceGold() * correctProps.getPercentGold());
            }
            currentCrop.setCalculatedSellPrice(calcSellPrice);
        }
    }

    public static void regularCropPlan(ArrayList<Object> chosenCrops, ArrayList<Object> statList) {
        ArrayList<Double> moneyPerTilePerDayList = new ArrayList<Double>();
        ArrayList<Double> moneyPerTilePerSeasonList = new ArrayList<Double>();
        for (int i = 0; i < statList.size(); i++) {
            regularCropStats currentCropStats = (regularCropStats) statList.get(i);
            double moneyPerTilePerDay = currentCropStats.getMoneyPerTilePerDay();
            moneyPerTilePerDayList.add(moneyPerTilePerDay);
            double moneyPerTilePerSeason = currentCropStats.getMoneyPerTilePerSeason();
            moneyPerTilePerSeasonList.add(moneyPerTilePerSeason);
        }
        int greatestPerDayIndex = greatestIndex(moneyPerTilePerDayList);
        int greatestPerSeasonIndex = greatestIndex(moneyPerTilePerSeasonList);
        if (greatestPerDayIndex == greatestPerSeasonIndex) {
            regular bestCrop = (regular) chosenCrops.get(greatestPerDayIndex);
            regularCropStats bestCropStats = (regularCropStats) statList.get(greatestPerDayIndex);
            System.out.println("Highest paying regular crop: ");
            System.out.println(bestCrop.toString());
            System.out.println(bestCropStats.toString());
        } else {
            System.out.println("Highest paying regular crops: \n----------");
            regular bestPerDayCrop = (regular) chosenCrops.get(greatestPerDayIndex);
            regularCropStats bestPerDayCropStats = (regularCropStats) statList.get(greatestPerDayIndex);
            regular bestPerSeasonCrop = (regular) chosenCrops.get(greatestPerSeasonIndex);
            regularCropStats bestPerSeasonCropStats = (regularCropStats) statList.get(greatestPerSeasonIndex);
            System.out.println("Highest pay per day: ");
            System.out.println(bestPerDayCrop.toString());
            System.out.println(bestPerDayCropStats.toString());
            System.out.println("Highest pay per season: ");
            System.out.println(bestPerSeasonCrop.toString());
            System.out.println(bestPerSeasonCropStats.toString());
        }
        return;
    }
    public static void regrowingCropPlan(ArrayList<Object> chosenCrops, ArrayList<Object> statList) {
        ArrayList<Double> moneyPerTilePerSeasonList = new ArrayList<Double>();
        ArrayList<Double> moneyPerTilePerDayList = new ArrayList<Double>();
        for (int i = 0; i < statList.size(); i++) {
            regrowingCropStats currentCropStats = (regrowingCropStats) statList.get(i);
            double moneyPerTilePerSeason = currentCropStats.getMoneyPerTilePerSeason();
            moneyPerTilePerSeasonList.add(moneyPerTilePerSeason);
            double moneyPerTilePerDay = currentCropStats.getMoneyPerTilePerDay();
            moneyPerTilePerDayList.add(moneyPerTilePerDay);
        }
        int greatestMoneyPerSeasonIndex = greatestIndex(moneyPerTilePerSeasonList);
        int greatestMoneyPerDayIndex = greatestIndex(moneyPerTilePerDayList);
        if (greatestMoneyPerSeasonIndex == greatestMoneyPerDayIndex) {
            regrowing bestCrop = (regrowing) chosenCrops.get(greatestMoneyPerDayIndex);
            regrowingCropStats bestCropStats = (regrowingCropStats) statList.get(greatestMoneyPerDayIndex);
            System.out.println("Highest paying regrowing crop:");
            System.out.println(bestCrop.rToString());
            System.out.println(bestCropStats.toString());
        } else {
            regrowing bestSeasonCrop = (regrowing) chosenCrops.get(greatestMoneyPerSeasonIndex);
            regrowingCropStats bestSeasonCropStats = (regrowingCropStats) statList.get(greatestMoneyPerSeasonIndex);
            regrowing bestDayCrop = (regrowing) chosenCrops.get(greatestMoneyPerDayIndex);
            regrowingCropStats bestDayCropStats = (regrowingCropStats) statList.get(greatestMoneyPerDayIndex);
            System.out.println("Highest season pay crop: ");
            System.out.println(bestSeasonCrop.rToString());
            System.out.println(bestSeasonCropStats.toString());
            System.out.println("Highest day pay crop: ");
            System.out.println(bestDayCrop.rToString());
            System.out.println(bestDayCropStats.toString());
        }
    }

    public static int greatestIndex(ArrayList<Double> list) {
        int highestIndex = 0;
        double highestValue = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > highestValue) {
                highestValue = list.get(i);
                highestIndex = i;
            }
        }
        return highestIndex;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Object> regularList = setUpRegularList("C:\\Users\\no1sp\\IdeaProjects\\StardewCropPlanner\\src\\stardewCropsRegular.txt");
        ArrayList<Object> regrowingList = setUpRegrowingList("C:\\Users\\no1sp\\IdeaProjects\\StardewCropPlanner\\src\\stardewCropsRegrowing.txt");
        //ArrayList<Object> farmList = setUpFarmList("C:\\Users\\no1sp\\IdeaProjects\\StardewCropPlanner\\src\\stardewFarms.txt");
        ArrayList<Object> chosenRegularCrops = new ArrayList<>();
        ArrayList<Object> chosenRegrowingCrops = new ArrayList<>();
        System.out.println("Hello! Welcome to the Stardew Valley Crop Planner!");
        Scanner scanner = new Scanner(System.in);
        //int farmIndex = farmCheck(scanner, farmList);
        //sprinklers?
        int fertilizer = fertilizerCheck(scanner); //0=none, 1=basic, 2=quality, 3=deluxe
        int farmingLevel = farmLevelCheck(scanner);
        int cropChoice = cropChoiceCheck(scanner);
        String season = seasonCheck(scanner);
        int currentDate = dateCheck(scanner);
        Boolean isYear1 = year1Check(scanner);
        if (cropChoice == 1 || cropChoice == 3) {
            for (int i = 0; i < regularList.size(); i++) {
                regular currentCrop = (regular) regularList.get(i);
                //adds crop to list if seasons match or the crop's season is "all"
                if (currentCrop.getSeason1().equals(season) || currentCrop.getSeason2().equals(season) || currentCrop.getSeason1().equals("All")) {
                    chosenRegularCrops.add(currentCrop);
                }
            }
        }
        if (cropChoice == 2 || cropChoice == 3) {
            for (int i = 0; i < regrowingList.size(); i++) {
                regrowing currentCrop = (regrowing) regrowingList.get(i);
                if (currentCrop.getSeason1().equals(season) || currentCrop.getSeason2().equals(season) || currentCrop.getSeason1().equals("All")) {
                    chosenRegrowingCrops.add(currentCrop);
                }
            }

        }
        if (isYear1) { //removes crops that aren't available yr1 if it is yr1
            if (!chosenRegularCrops.isEmpty()) {
                for (int i = 0; i < chosenRegularCrops.size(); i++) {
                    regular currentCrop = (regular) chosenRegularCrops.get(i);
                    if (!currentCrop.isAvailableYear1()) {
                        chosenRegularCrops.remove(i);
                        i--;
                    }
                }
            }
            if (!chosenRegrowingCrops.isEmpty()) {
                for (int i = 0; i < chosenRegrowingCrops.size(); i++) {
                    regrowing currentCrop = (regrowing) chosenRegrowingCrops.get(i);
                    if (!currentCrop.isAvailableYear1()) {
                        chosenRegrowingCrops.remove(i);
                        i--;
                    }
                }
            }
        }
        if (!chosenRegularCrops.isEmpty()) {
            ArrayList<Object> propList = setUpCropQualityPropList(fertilizer);
            calculateSellPriceRegular(chosenRegularCrops, propList, fertilizer, farmingLevel);
            ArrayList<Object> regularStatList = setUpRegularStatList(chosenRegularCrops, currentDate, season);
            regularCropPlan(chosenRegularCrops, regularStatList);
        }
        if (cropChoice == 3) {
            System.out.println("-------------------------");
        }
        if (!chosenRegrowingCrops.isEmpty()) {
            ArrayList<Object> propList = setUpCropQualityPropList(fertilizer);
            calculateSellPriceRegrowing(chosenRegrowingCrops, propList, fertilizer, farmingLevel);
            ArrayList<Object> regrowingStatList = setUpRegrowingStatList(chosenRegrowingCrops, currentDate, season);
            regrowingCropPlan(chosenRegrowingCrops, regrowingStatList);
        }
    }
}