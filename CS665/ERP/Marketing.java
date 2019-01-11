/**
* Marketing.java
* This is a Marketing class .
* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

import java.util.*;

public class Marketing {

        public static void main() {
            // Class Database object initiated from ERP
            Database DB = Database.getInstance();

            // Get main menu and display to user for input
            Menus menu = new Menus("marketing");
            String[] items = menu.getMenu();
            Prompt prompt = new Prompt( items );  // create Prompt instance with menu
            Integer choice = prompt.askChoice();  // ask user input from main menu Prompt

            // Delegate user action desired
            switch(choice) {
                case 1:  // Create campaign
                    Campaign.CampaignType type = askType();
                    // Create campaign object using abstract factory design
                    Campaign camp = CampaignFactory.createCampaign(type);
                    DB.CAMPAIGNSDB.add(camp);  // save campaign to Database.
                    break;
                case 2:  // Marketing items
                    System.out.println("Items include: " + Items.getItems(askList()) + "\n");
                    break;
                case 3:  // See all campaigns
                    DB.printCampaigns();
                    break;
                case 4:  // Modify campaign
                    Campaign campaign = DB.getCampaign();
                    campaign.updateAll();
                    System.out.println(campaign);
                    break;
                default:
                    prompt.askChoice();  // prompt again if choice not found
            }

        } // ----------------------------- END OF MAIN -----------------------------

        /** ----- CLASS METHODS ----- **/

        /**
        * This method prompts user for type of campaign to create.
        * @param null.
        * @return Campaign.CampaignType.
        */
        private static Campaign.CampaignType askType() {
            String[] types = Utils.convertTypes(Campaign.CampaignType.values());
            Prompt prompt = new Prompt( types );  // create Prompt instance with choices
            Integer choice = prompt.askChoice();  // ask user input from main menu Prompt
            return Campaign.CampaignType.values()[choice-1];
        }

        private static String askList() {
            System.out.println("\nAvailable campaign lists: PROMO1, PROMO2, PROMO3");
            Scanner input = new Scanner(System.in);
            String r = "";

            System.out.print("\n\nEnter list name: ");
            r = input.next().toUpperCase();

            if (r.equals("all")) {
                System.out.println("\nAvailable items for campaings are:\n");
                System.out.println("PROMO1: " + Items.getItems("PROMO1") + "\n");
                System.out.println("PROMO2: " + Items.getItems("PROMO2") + "\n");
                System.out.println("PROMO3: " + Items.getItems("PROMO3") + "\n");
                System.exit(0);
            }
            return r;
        }
}
