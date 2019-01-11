/**
* CampaignFactory.java
* This is a CampaignFactory class .
* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

class CampaignFactory {

    // Default class constructor
    private CampaignFactory() {};

    public static Campaign createCampaign(Campaign.CampaignType type) {
        Campaign campaign = null;

        String[] items = {"PROMO1", "PROMO2", "PROMO3"};
        Prompt prompt = new Prompt( items );  // create Prompt instance with menu
        System.out.println("\nItem categories for " + type + " campaign:");
        Integer promo = prompt.askChoice();  // ask user input from main menu Prompt

        // Create object from concrete factories accordingly
        switch(promo) {
            case 1:
                campaign = PROMO1Factory.createCampaign(type);
                break;
            case 2:
                campaign = PROMO2Factory.createCampaign(type);
                break;
            case 3:
                campaign = PROMO3Factory.createCampaign(type);
                break;
        }

        // Return campaign object created
        return campaign;
    }

}
