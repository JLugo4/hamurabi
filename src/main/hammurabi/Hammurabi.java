package hammurabi;               // package declaration
import java.util.InputMismatchException; // imports go here
import java.util.Random;
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Hammurabi().playGame();
    }
    public void playGame() {
        // initial values
        int year = 1;
        int population = 100;
        int bushels = 2800;
        int land = 1000;
        int landValue = 19;
        int plague = 0;
        int starved = 20;
        int immigrants = 0;
        int bushelsEaten = 0;
        int harvest = 2000;


        // Introduction to Game.
        System.out.println(
                "Most highest Hammurabi, you have entered year 1 of your 10 year adventure.\n" +
                "Your responsibilities include managing the provision of food for your people, overseeing agricultural activities, \n" +
                "and conducting transactions involving the acquisition and disposition of property in order to sustain the well-being of your kingdom." +
                "Be cautious of rat infestations and the potential spread of the plague. In this context,\n" +
                " grain serves as the predominant form of currency and is typically measured in bushels. \n" +
                "The following will help you in your decisions:\n" +
                "\n" +
                "Each citizen requires 20 bushels to survive each year.\n" +
                "Your people are limits to ten acres per person.\n" +
                "To farm one of these ten acres you need at least 2 bushels.\n" +
                "If you wish to buy land, note that the price of an acres changes annually.\n" +
                "If you do well your people will praise you...\n" +
                "else you may experience an uprising!\n" +
                "|_______________________________|\n" +
                "| Enter a key to start the game |\n" +
                "|_______________________________|");
        // While Loop
        while (true) {
            scanner.nextLine();
            break;
        }

        for (year = 1; year <= 10; year++) {
            System.out.println(printSummary(landValue, land, population, immigrants, harvest, bushelsEaten, starved, plague, year));

            int bought = askHowManyAcresToBuy(landValue, bushels);
            land = land + bought;
            bushels -= bought * landValue;

            if (bought == 0) {
                int sold = askHowManyAcresToSell(land);
                land -= sold;
                bushels += sold * landValue;
            }

            int feed = askHowMuchGrainToFeedPeople(bushels);
            bushels -= feed;

            int plant = askHowManyAcresToPlant(land, population, bushels);
            bushels -= plant * 2;

            plague = plagueDeaths(population);
            population -= plague;

            starved = starvationDeaths(population, feed);
            population -= starved;

            if (uprising(population, starved)) {
                System.out.println("Your people yearn for a new ruler...\n" +
                        "_________\n"+
                        "Game Over\n"+
                        "_________");
                break;
            }
            if (starved == 0) {
                immigrants = immigrants(population, land, bushels);
                population += immigrants;
            }

            harvest = harvest(plant);
            bushels += harvest;

            bushelsEaten = grainEatenByRats(bushels);
            bushels -= bushelsEaten;

            landValue = newCostOfLand();

        }
    }
        // New Year summary
        String printSummary ( int land, int landValue, int bushels, int bushelsEaten, int starved, int plague, int population, int immigrants, int year){

            String newYearSummary = "";

            newYearSummary += "Mi'lord, I bring great news!\n";
            newYearSummary += "This is the " + year + " of your reign. \n";
            newYearSummary += "Last year " + plague + " people died from the plague. \n";
            newYearSummary += "Last year " + starved + " people starved to death. \n";
            newYearSummary += "Last year " + immigrants + " people entered the kingdom. \n";
            newYearSummary += "That brings our population to " + population + "\n";
            newYearSummary += "Your people harvested " + bushels + " bushels. \n";
            newYearSummary += "Rats claimed " + bushelsEaten + " bushels which leaves us with " + bushels + " bushels in storage. \n";
            newYearSummary += "Our kingdom owns " + land + " acres of land. \n";
            newYearSummary += "This year's cost per acre is " + landValue + " bushels per acre.\n";

            return newYearSummary;
        }
        // Buying land method.
        public int askHowManyAcresToBuy ( int price, int bushels){
            int acres = getNumber("Enter amount of acres you would like to buy?");
            if (acres * price > bushels) {
                System.out.print("You do not have enough bushels for that transaction.");
                acres = 0;
            } else if (bushels > price * acres && acres > 0) {
                System.out.println("Transaction complete.\n" +
                        "You have purchased " + acres + " acres");
            } else {
                System.out.println("Transaction complete\n" +
                        "You have decided to not to buy land this year.");
                acres = 0;
            }

            return acres;
        }

        // sell land method
        public int askHowManyAcresToSell ( int acresOwned) {
            int acres = getNumber("Enter amount of acres you would like to sell?\n");
            if (acresOwned > acres && acresOwned >= 0) {
                System.out.println("Transaction complete... you sold " + acres + " acres of land\n");

            } else if (acresOwned < acres) {
                System.out.println("Transaction invalid... you can not sell land you do not own.\n");
                acres = 0;
            } else {
                System.out.println("No sale...\n"+"You have decided not to sell acres this year.");

            }
            return acres;
        }

        // Buying grain method
        public int askHowMuchGrainToFeedPeople ( int bushels){
            int feed = getNumber("The people want food... how much will you feed them?\n");
            if (bushels >= feed && feed > 0) {
                System.out.println("You have fed your people " + feed + " bushels\n");
            } else if (feed > bushels) {
                System.out.println("You have fed your people all the bushels!\n");
                feed = bushels;
            } else {
                System.out.println("You have decided not to feed your people.'(\n");
                feed = 0;
            }
            return feed;
        }
        // Planting acres method
        public int askHowManyAcresToPlant ( int acresOwned, int population, int bushels){
            int plant = 0;

            while (true) {
                plant = getNumber("You can plant this year... How much would you like to plant?\n");
                if (plant > acresOwned) {
                    System.out.println("We do not have enough land fo that!\n");
                }
                if (plant > population * 10) {
                    System.out.println("We do not have enough people to work plant that amount!\n");
                }
                if (plant > bushels * 2) {
                    System.out.println("We do not have enough bushels for that!\n");
                }
                if (plant < 0) {
                    System.out.println("You have an insufficient amount to plant this year.");
                }
                if (plant <= population * 10 && plant <= acresOwned && plant <= bushels * 2 && plant >= 0) {
                    System.out.println("Success! Your people have planted the amount of " + plant +" this year.");
                    return plant;
                }
            }

        }
        // plague deaths
        public int plagueDeaths ( int population){
            int deaths = 0;
            rand.nextInt(20);
            if (3 > rand.nextInt(20)) {
                deaths = (int) (population * 0.5);
            }
            return deaths;
        }
        // starvationDeaths
        public int starvationDeaths ( int population, int bushelsFedToPeople){
            int starve = 0;
            int survivors = bushelsFedToPeople / 20;
            if (population > bushelsFedToPeople / 20) {
                starve = population - survivors;
            }
            return starve;
        }
        // Uprising conditions method.
        public boolean uprising ( int population, int howManyPeopleStarved){
            if (howManyPeopleStarved > (int) (population * .45)) {
                return true;
            }
            return false;
        }
        // Immigrants method
        public int immigrants ( int population, int acresOwned, int grainInStorage){
            int immigrants = (int) ((double) (20 * acresOwned + grainInStorage) / (100 * population) + 1);
            return immigrants;
        }
        // harvest
        public int harvest ( int acres){
            int harvest = acres * (rand.nextInt(6) + 1);
            return harvest;
        }
        // Eaten by RATS
        public int grainEatenByRats ( int bushels){
            int bushelsEaten = 0;
            if (rand.nextInt(5)< 2) {
                bushelsEaten = (int) (bushels * (rand.nextInt(2) + 1) / 10 + 0.5);
            }
            return bushelsEaten;
        }
        // New costt of Land method, price ranging from 17 to 23.
        public int newCostOfLand () {
            int landValue = rand.nextInt(7) + 17;
            return landValue;
        }
        // Getting a number methhod
        public int getNumber (String message){
            while (true) {
                System.out.print(message);
                try {
                    return scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("\"" + scanner.next() + "\" You have entered an invalid response...\n" +
                            "please enter a number.");
                }
            }
        }
    }