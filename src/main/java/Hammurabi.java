package hammurabi;               // package declaration
import java.util.Random;         // imports go here
import java.util.Scanner;

public class Hammurabi {
    private final static int MinimumGrainToSurivie = 20;

    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);
    int totalDeaths = 0;
    int percentDied = 0;
    int year = 0;
    int population = 95;
    int stores = 2800;
    int immigrants = 5;
    int deaths = 0;
    int harvest = 3000;
    int yeild = 3;
    int acres = harvest / yeild;
    int eaten = harvest - stores;
    int landprice;
    int fullPeople;
    int temp;


    public static void main(String[] args) {
        new Hammurabi().playGame();
    }



    void playGame() {
           private final static

        // ______________________________________
        // Ask player these questions
        private int askHowManyAcresToBuy(int price, int bushels);

        private int askHowManyAcresToSell(int acresOwned);

        private int askHowMuchGrainToFeedPeople(int bushels);

        private int askHowManyAcresToPlant(int acresOwned, int population, int bushels);
        // Methods for calculations
        int plagueDeaths(int population);
        int starvationDeaths(int population, int bushelsFedToPeople);
        boolean uprising(int population, int howManyPeopleStarved);
        int immigrants(int population, int acresOwned, int grainInStorage);
        // Calculation for people coming to city if well fed or not
        // (20 * _number of acres you have_ + _amount of grain you have in storage_) / (100 * _population_) + 1
        int harvest(int acres, int bushelsUsedAsSeed);
        int grainEatenByRats(int bushels);
        int newCostOfLand();
        // Need a 'printSummary' method
        // Need a 'finalSummary' method

    }



}
