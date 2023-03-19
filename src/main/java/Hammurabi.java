package java;               // package declaration
import org.w3c.dom.ls.LSOutput;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Random;         // imports go here
import java.util.Scanner;

public class Hammurabi {
    public int population, bushels, land, landValue, numEnter, price, rations, numStarve, yield, workers, capcity,
    bushelsPlanted;
    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {new Hammurabi().playGame();
    }
    public void playGame() {
        //years for loop
        for (int i = 0; i < 10; i++) {
        }
        // Objects
        population = 95;
        bushels = 2800;
        land = 1000;
        price = 19;
        landValue = land * price;
        numEnter = 0;
        rations = 20;
        yield = 3;
        workers = 0;
        capcity = 25;
    }
public void printGame(){
    System.out.println("O great Hammurabi!\n"); // Welcome
    System.out.println("You are in year" + numYears + "of your ten year rule\n"); //
    System.out.println("In the previous year "+ numStarve + " people starved to death\n");
    System.out.println("In the previous year" + numEnter + "people entered the kingdom.\n");
    System.out.println("The population is now" + population);
    System.out.println("We harvested" + land * 3 + "at 3 bushels per acre.\n");
    System.out.println("Rats destroyed" + ratsDestoryed + "leaving" + grain + "bushels in storage.\n");
    System.out.println("The city owns" + land + "acres of land.\n");
    System.out.println("Land is currently worth 19 bushels per acre.\n");
}
    boolean buySellAcres= true;

    public int askUserToBuyOrSellAcres() {
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
    int plagueDeaths(int population){
        rand.nextInt(20);

        if( 3> rand.nextInt(20)){
            System.out.println("Hammurabi, there is a plague!");
            population *= 0.5;
        } else {
            System.out.println("There is no plague this year!");
        }
        return population;
    }
    int immigrants(int population, int acresOwned, int graainInStorage){
        int immigrants = 0;
        if(numStarve == 0){
            immigrants = (20 * land + bushels) / (100 * population) + 1;
        }
        return immigrants;
    }
    int harvest( int acres, int bushelsUsedForHarvest){
        return 0;
    }
    int ravagedGrainByRats(int bushels){
        this.bushels = bushels;
        if( 2 > rand.nextInt(5)){
            System.out.println("Our food has been ravaged!");
            bushels -= (bushels * (rand.nextInt(2)+1)/10);
        }
        return bushels;
    }
    int newAcresCost(){
        landValue = rand.nextInt(7)+17;
        return landValue;
    }
}


