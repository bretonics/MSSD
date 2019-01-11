/**
* Campaign.java
* This is a Campaign class .
* @author  Andres Breton
* @version 1.0
* Postcondition: .
*/

import java.util.*;

/** ----- ABSTRACT FACTORY----- **/
// Abstract Campaign object factory interface
abstract class Campaign {
    // Class Database object initiated from ERP
    Database DB = Database.getInstance();

    enum CampaignType { SALE, OVERSTOCK, XMAS };

    /** ----- INSTANCE ----- **/

    // Instance variables
    CampaignType type = null;
    List<String> items = null;
    String name = "Campaign " + DB.CAMPAIGNSDB.size();
    String purpose;
    String location;
    Integer duration;

    // Default class constructor
    Campaign(CampaignType type, List<String> items) {
        this.type = type;
        this.items = items;
    }

    abstract void construct();

    /** ----- CLASS METHODS ----- **/

    /**
    * This method returns the CampaignType.
    * @param null.
    * @return CampaignType.
    */
    protected CampaignType getCampaignType() { return type; }

    /**
    * This method returns the items for sale in the campaign type.
    * @param null.
    * @return CampaignType.
    */
    protected List<String> getCampaignItems() { return items; }

    /**
    * This method returns the campaign's name.
    * @param null.
    * @return String.
    */
    protected String getName() { return name; }

    /**
    * This method returns the campaign's purpose.
    * @param null.
    * @return String.
    */
    protected String getPurpose() { return purpose; }

    /**
    * This method returns the campaign's location.
    * @param null.
    * @return Employee.Location.
    */
    protected String getLocation() { return location; }

    /**
    * This method returns the campaign's duration.
    * @param null.
    * @return Integer.
    */
    protected Integer getDuration() { return duration; }

    /**
    * This method updates all Campaign object attributes.
    * @param
    * @return .
    */
    protected void updateAll() {
        Scanner input = new Scanner(System.in);

        System.out.print("New campaign name: ");
        setName(input.nextLine());

        System.out.print("New campaign purpose: ");
        setPurpose(input.nextLine());

        System.out.print("New campaign location: ");
        setLocation(input.nextLine());

        System.out.print("New campaign duration (Integer): ");
        setDuration(input.nextInt());
    }

    /**
    * This method sets the CampaignType.
    * @param CampaignType.
    * @return void.
    */
    protected void setCampaignType(CampaignType type) { this.type = type; }

    /**
    * This method sets the items in the campaing.
    * @param List<String>.
    * @return void.
    */
    protected void setCampaignItems(List<String> items) { this.items = items; }

    /**
    * This method sets the name of the campaign.
    * @param  String name for campaign.
    * @return void.
    */
    protected void setName(String name) { this.name = name; }

    /**
    * This method sets the purpose of the campaign.
    * @param  String purpose for campaign.
    * @return void.
    */
    protected void setPurpose(String purpose) { this.purpose = purpose; }

    /**
    * This method sets the location of the campaign.
    * @param  Employee.Location of campaign.
    * @return void.
    */
    protected void setLocation(String location) { this.location = location; }

    /**
    * This method sets the duration in days of the campaign.
    * @param  Integer duration of days for campaign.
    * @return void.
    */
    protected void setDuration(Integer duration) { this.duration = duration; }

    /**
    * This method replaces default Campaign object string representation.
    * @param null
    * @return String representation of object.
    */
    public String toString() {
        String out = "\nName: " + name + "" ;
        out += "\nType: " + type;
        out += "\nItems: "+  items;
        out += "\nPurpose: " + purpose;
        out += "\nDuration: " + duration;
        out += "\nLocation: " + location;
        out += "\n\n";
        return out;
    }
}

/** ----- CONCRETE FACTORIES ----- **/

/**
* This method is a concrete factory for PROMO1 items.
* @param Campaign.CampaignType to be created.
* @return Campaign object.
*/
class PROMO1Factory {

    public static Campaign createCampaign(Campaign.CampaignType type) {

        List<String> items = Items.getItems("PROMO1");
        Campaign campaign = null;

        // Create campaing object accordingly
        switch(type) {
            case SALE:
                campaign = new SALECampaign(items);
                break;
            case OVERSTOCK:
                campaign = new OVERSTOCKCampaign(items);
                break;
            case XMAS:
                campaign = new XMASCampaign(items);
                break;
        }

        // Return campaign object created
        return campaign;
    }

}

/**
* This method is a concrete factory for PROMO2 items.
* @param Campaign.CampaignType to be created.
* @return Campaign object.
*/
class PROMO2Factory {

    public static Campaign createCampaign(Campaign.CampaignType type) {

        List<String> items = Items.getItems("PROMO2");
        Campaign campaign = null;

        // Create campaing object accordingly
        switch(type) {
            case SALE:
                campaign = new SALECampaign(items);
                break;
            case OVERSTOCK:
                campaign = new OVERSTOCKCampaign(items);
                break;
            case XMAS:
                campaign = new XMASCampaign(items);
                break;
        }

        // Return campaign object created
        return campaign;
    }

}

/**
* This method is a concrete factory for PROMO3 items.
* @param Campaign.CampaignType to be created.
* @return Campaign object.
*/
class PROMO3Factory {

    public static Campaign createCampaign(Campaign.CampaignType type) {

        List<String> items = Items.getItems("PROMO3");
        Campaign campaign = null;

        // Create campaing object accordingly
        switch(type) {
            case SALE:
                campaign = new SALECampaign(items);
                break;
            case OVERSTOCK:
                campaign = new OVERSTOCKCampaign(items);
                break;
            case XMAS:
                campaign = new XMASCampaign(items);
                break;
        }

        // Return campaign object created
        return campaign;
    }

}


// Define objects to be created by corresponding concrete factory
class SALECampaign extends Campaign {

    SALECampaign(List<String> items) {
        super(CampaignType.SALE, items);
        construct();
    }

    @Override
    protected void construct() {
        System.out.print("\nCreating sale campaign with the following items: " + items + "\n\n");
    }
}

class OVERSTOCKCampaign extends Campaign {

    OVERSTOCKCampaign(List<String> items) {
        super(CampaignType.OVERSTOCK, items);
        construct();
    }

    @Override
    protected void construct() {
        System.out.print("\nCreating overtock campaign with the following items: " + items + "\n\n");
    }
}

class XMASCampaign extends Campaign {

    XMASCampaign(List<String> items) {
        super(CampaignType.XMAS, items);
        construct();
    }

    @Override
    protected void construct() {
        System.out.print("\nCreating XMAS campaign with the following items: " + items + "\n\n");
    }
}
