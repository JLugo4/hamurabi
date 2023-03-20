package hammurabi;               // package declaration
import java.util.InputMismatchException;
import java.util.Random;         // imports go here
import java.util.Scanner;

public class Hammurabi {
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {new Hammurabi().playGame();
    }
    public void playGame() {
        // inital values
        int year = 1;
        int population = 100;
        int bushels = 2800;
        int land = 1000;
        int landValue = 19;
        int plague = 0;
        int starved = 20;
        int immigrants = 0;
        int bushelsEaten = 0;
        int harvest =2000;
        System.out.println("Most highest Hammurabi, you have entered year 1 of your 10 year adventure.\n" +
                "Your responsibilities include managing the provision of food for your people, overseeing agricultural activities, \n" +
                "and conducting transactions involving the acquisition and disposition of property in order to sustain the well-being of your kingdom." +
                "Be cautious of rat infestations and the potential spread of the plague. In this context,\n grain serves as the predominant form of currency and is typically measured in bushels. \n" +
                "The following will help you in your decisions:\n" +
                "\n" +
                "Each citizen needs at least 20 bushels of grain per year to survive\n" +
                "Each person can farm at most 10 acres of land\n" +
                "It takes 2 bushels of grain to farm an acre of land\n" +
                "The market price for land fluctuates yearly\n" +
                "Rule wisely and you will be showered with appreciation at the end of your term.\n" +
                "Rule poorly and you will be kicked out of office!\n"+
                "\n" +
                "Press any key to proceed");
         while (true) {
             scanner.nextLine();
             break;
         }
    }
    for(year = 1; year <= 10; year++) {
        System.out.println(printSummary(year,plague, starved,immigrants,population,harvest,bushelsEaten,land,landValue));
        int bought = askHowManyAcresToBuy(landValue, bushels);
        land += bought;
        bushels -= bought * landValue;

        if(bought == 0){
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

        if(uprising(population, starved)){
            System.out.println("Yo bro, your population starved, you stink at this job");
            break;
        }
        if(starved == 0) {
            immigrants = immigrants(population, land, bushels);
            population += immigrants;
        }

        harvest = harvest(plant, plant);
        bushels += harvest;

        bushelsEaten = grainEatenByRats(bushels);
        bushels -= bushelsEaten;

        landValue = newCostOfLand();


    }


   /* public int askHowManyToBuy() {
        this.price = price;
        System.out.println("How many acres would you like to purchase?");
        int acres = scanner.nextInt();
        if (acres == 0) {
            System.out.println("You have declinded to purchase land.");
        } else if (acres * price <= bushels) {
            bushels -= acres * price;
            buySellAcres = false;
        } else if (acres * price > bushels) {
            System.out.println("Not enough bushels");
            buySellAcres = false;
        }
        return land;
    }
    public int askHowManyAcresToSell(int land) {
        if (buySellAcres = false) {
            buySellAcres = true;
            return land;
        }

        this.land = land;
        System.out.println("How many acres do you want to sell?");
        int acres = scanner.nextInt();
        if (land > acres){
            land -= acres;
            bushels += acres * price;
        } else if (land < acres) {
            System.out.println("you cannot sell land you occupy.");
        }
        return land;
    }
    public int askUserHowMuchGrainToFeed(int bushels){
        this.bushels = bushels;
        System.out.println("How many bushels do you want to feed people?");
        int feed = scanner.nextInt();
        if (feed > bushels){
            feed = bushels;
            bushels = 0;
        } else if (feed < bushels){
            System.out.println("You have fed your people " + feed + "bushels" );
            bushels -= feed;
        }
        if(feed / rations >= population){
            System.out.println("eEveryone Survives");
            numStarve = 0;
        } else if (feed/ rations < population){
            numStarve = population - feed / rations;
            population = (feed / rations);
            System.out.println();
            System.out.println();
        }
        return feed;
    }
    public int AskUserAMountOfAcresToPlant(int acresOwned, int population, int bushels){
        this.land = acresOwned;
        this.population = population;
        this.bushels = bushels;
        System.out.println("How many acres do you want to plant?");
        int acresToPlant = scanner.nextInt();
        if( land > acresToPlant && population > acresToPlant * workers && bushels > acresToPlant * capcity){
            bushels -= acresToPlant * capcity;
            bushelsPlanted = acresToPlant * capcity;
            return bushelsPlanted;
        } else if ( land > acresToPlant && population > acresToPlant * yield && bushels < acresToPlant * capcity){
            System.out.println("You do not have bushels for that");
            bushels = 0;
            return bushelsPlanted;
        }else {
            return 0;
        }
    }
    public int plagueDeaths(int population){
        rand.nextInt(20);

        if( 3> rand.nextInt(20)){
            System.out.println("Hammurabi, there is a plague!");
            population *= 0.5;
        } else {
            System.out.println("There is no plague this year!");
        }
        return population;
    }
    public int immigrants(int population, int acresOwned, int grainInStorage){
        int immigrants = 0;
        if(numStarve == 0){
            immigrants = (20 * land + bushels) / (100 * population) + 1;
        }
        return immigrants;
    }
    public int harvest( int acres){
        return 0;
    }
    public int grainEatenByRats(int bushels){
        this.bushels = bushels;
        if( 2 > rand.nextInt(5)){
            System.out.println("Our food has been ravaged!");
            bushels -= (bushels * (rand.nextInt(2)+1)/10);
        }
        return bushels;
    }
    public int newCostOfLand(){
        landValue = rand.nextInt(7)+17;
        return landValue;
    }
    public int starvationDeaths(int population, int bushelsFedToPeople){
        int bushelsNeeded = population * 20;
        int exessBushels = bushelsFedToPeople - bushelsNeeded;
        if (exessBushels < 0){
            return Math.abs(excessBushels) / 20;
        } else{
            return 0;
        }
    }
    public boolean uprising(int population, int howManyPeopleStarved){
        double starvationRate = (double) howManyPeopleStarved / population;
        return (starvationRate > 0.45);
    }*/
}


