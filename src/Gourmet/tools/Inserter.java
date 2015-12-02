/******************************************************************************
*  CS5200 Milestone 4 Hello World
*  Bee: Bolun Hu, Enting Wu, Yujiao Liu
******************************************************************************/

package Gourmet.tools;

import Gourmet.dal.*;
import Gourmet.model.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;



public class Inserter {

    public static void main(String[] args) throws SQLException {
        
        // get today's date
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                
        // Get Yesterday's date
        Calendar yd = Calendar.getInstance();
        yd.add(Calendar.DATE, -1);
        java.sql.Date yesterdayDate = new java.sql.Date(yd.getTimeInMillis());

        // DAO instances.
        UsersDao usersDao = UsersDao.getInstance();
        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        DeliveryRestaurantDao deliveryDao = DeliveryRestaurantDao.getInstance();
        TakeOutRestaurantDao takeOutDao = TakeOutRestaurantDao.getInstance();
        ReservationRestaurantDao reserveDao = ReservationRestaurantDao.getInstance();
        PromotionsDao promotionsDao = PromotionsDao.getInstance();
        ReviewsDao reviewsDao = ReviewsDao.getInstance();
        ReservationsDao reservationDao = ReservationsDao.getInstance();
        GoodForDao goodForDao = GoodForDao.getInstance();
        CategoriesDao categoriesDao = CategoriesDao.getInstance();
        CheckInDao checkInDao = CheckInDao.getInstance();
        
        // INSERT objects from our model.
        Users user0 = new Users("jeff","888111888", yesterdayDate, 0, true);
        user0 = usersDao.create(user0);
        
        @SuppressWarnings("deprecation")
        java.sql.Time openTime = new java.sql.Time(11, 00, 00);
        java.sql.Time closeTime = new java.sql.Time(21, 00, 00);
        java.sql.Time newCloseTime = new java.sql.Time(22, 00, 00);
        java.sql.Time newOpenTime = new java.sql.Time(12, 00, 00);
        java.util.Date newStartTime =new java.util.Date(114,11,26,11,00,00);
        java.util.Date newEndTime = new java.util.Date(114,12,26,11,00,00);
        
        
        Restaurants restaurant0 = new Restaurants("88888aaaaa", "Test-Restaurant", true, true, 4, openTime, closeTime, 2, 
                "['Downtown']", 4.8,48, "200 Summer St", "Boston", "MA", 2115);
        restaurant0 = restaurantsDao.create(restaurant0);
        
        DeliveryRestaurant dr1 = new DeliveryRestaurant ("88888bbbb", "Test-DeliveryRestaurant", true, true, 4, openTime, closeTime, 2, 
                "['Downtown']", 4.8,48, "200 Winter St", "Boston", "MA", 2115, 5);
        dr1 = deliveryDao.create(dr1);
        
        TakeOutRestaurant to0 = new TakeOutRestaurant ("88888cccc", "Test-TakeOutRestaurant", true, true, 4, openTime, closeTime, 2, 
                "['Downtown']", 4.8,48, "200 Fall St", "Boston", "MA", 2115, 1000);
        to0 = takeOutDao.create(to0);
        
        ReservationRestaurant rs0 = new ReservationRestaurant ("88888dddd", "Test-ReservationRestaurant", true, true, 4, openTime, closeTime, 2, 
                "['Downtown']", 4.8,48, "200 Spring St", "Boston", "MA", 2115, 10000);
        rs0 = reserveDao.create(rs0);
        
        
        Reviews rw0 = new Reviews(user0, restaurant0, "Good", currentDate, (float) 4.0);
        rw0 = reviewsDao.create(rw0);
        
        Reservations rsv0 = new Reservations(user0, rs0, currentDate, currentDate, 5);
        rsv0 = reservationDao.create(rsv0);
        
        Promotions p0 = new Promotions (restaurant0, newStartTime, newEndTime, 0.8);
        p0 = promotionsDao.create(p0);
        
        GoodFor gd0 = new GoodFor ("88888aaaaa", true, true, true, true, true, true, true, true);
        gd0 = goodForDao.create(gd0);
        
        Categories cg0 = new Categories("88888aaaaa", true, true, true, true, true, true, true, true,true
        		,true,true,true,true);
        cg0 = categoriesDao.create(cg0);
        
        CheckIn ck0 = new CheckIn("88888aaaaa", 100, 100, 100, 100, 100, 100, 100);
        ck0 = checkInDao.create(ck0);
        
        // READ.
        Users u0 = usersDao.getUserFromUserId(6);
        System.out.format("Reading user: u:%s n:%s p:%s cs:%s rc:%s g:%s \n",
                u0.getUserId(), u0.getName(), u0.getPassword(), u0.getCreatedSince(), u0.getReviewCount(), u0.getGender());
        
        Restaurants r1 = restaurantsDao.getRestaurantById("CshTEcwHhtL7uxs888cLzA");    
        System.out.format("Reading Restaurant: id:%s name:%s acceptcreditcard:%s wifi:%s pricerange:%s opentime:%s "
                + "closetime:%s noiseleve:%s neighborhood:%s star:%s parking:%s st:%s city:%s state:%s zip:%s \n",
                        r1.getRestaurantId(), r1.getName(), r1.getAcceptsCreditCard(), r1.getWIFI(), r1.getPriceRange(),
                        r1.getOpen(), r1.getClose(), r1.getNoiseLevel(), r1.getNeighborhood(), 
                        r1.getStar(), r1.getParking(), r1.getStreet(), r1.getCity(), r1.getState(), r1.getZipCode());
        
        DeliveryRestaurant dr0 = deliveryDao.getDeliveryRestaurantById("-0lOuL7RkZQnjAl96dXTvA");
        System.out.format("Reading Restaurant: id:%s name:%s acceptcreditcard:%s wifi:%s pricerange:%s opentime:%s "
                + "closetime:%s noiseleve:%s neighborhood:%s star:%s parking:%s st:%s city:%s state:%s zip:%s maxD:%s \n",
                        dr0.getRestaurantId(), dr0.getName(), dr0.getAcceptsCreditCard(), dr0.getWIFI(), dr0.getPriceRange(),
                        dr0.getOpen(), dr0.getClose(), dr0.getNoiseLevel(), dr0.getNeighborhood(), 
                        dr0.getStar(), dr0.getParking(), dr0.getStreet(), dr0.getCity(), dr0.getState(), dr0.getZipCode(),
                        dr0.getMaxDeliverTime());
        
        List<DeliveryRestaurant> deliverlist1 = deliveryDao.getDeliveryRestaurantsByMaxDeliveryTime(1);
        for(DeliveryRestaurant d: deliverlist1) {
            System.out.format("Looping Delivery Restaurant: id:%s md:%s \n",
                    d.getRestaurantId(), d.getMaxDeliverTime()); }
        
        TakeOutRestaurant to1 = takeOutDao.getTakeOutRestaurantById("-024YEtnIsPQCrMSHCKLQw");
        System.out.format("Reading Restaurant: id:%s name:%s acceptcreditcard:%s wifi:%s pricerange:%s opentime:%s "
                + "closetime:%s noiseleve:%s neighborhood:%s star:%s parking:%s st:%s city:%s state:%s zip:%s maxW:%s \n",
                        to1.getRestaurantId(), to1.getName(), to1.getAcceptsCreditCard(), to1.getWIFI(), to1.getPriceRange(),
                        to1.getOpen(), to1.getClose(), to1.getNoiseLevel(), to1.getNeighborhood(), 
                        to1.getStar(), to1.getParking(), to1.getStreet(), to1.getCity(), to1.getState(), to1.getZipCode(),
                        to1.getMaxWaitTime());
        
        List<TakeOutRestaurant> takeOutlist1 = takeOutDao.getTakeOutRestaurantsByMaxWaitTime(1);
        for(TakeOutRestaurant t: takeOutlist1) {
            System.out.format("Looping Take Out Restaurant: id:%s mw:%s \n",
                    t.getRestaurantId(), t.getMaxWaitTime()); }
        
        ReservationRestaurant rs1 = reserveDao.getReservationRestaurantById("62uhxauw6vxW30XyTVCbjw");
        System.out.format("Reading Restaurant: id:%s name:%s acceptcreditcard:%s wifi:%s pricerange:%s opentime:%s "
                + "closetime:%s noiseleve:%s neighborhood:%s star:%s parking:%s st:%s city:%s state:%s zip:%s maxR:%s \n",
                        rs1.getRestaurantId(), rs1.getName(), rs1.getAcceptsCreditCard(), rs1.getWIFI(), rs1.getPriceRange(),
                        rs1.getOpen(), rs1.getClose(), rs1.getNoiseLevel(), rs1.getNeighborhood(), 
                        rs1.getStar(), rs1.getParking(), rs1.getStreet(), rs1.getCity(), rs1.getState(), rs1.getZipCode(),
                        rs1.getMaxRoomSize());
        
        List<ReservationRestaurant> reservationRestaurantlist1 = reserveDao.getReservationRestaurantsByMaxRoomSize(100);
        for(ReservationRestaurant rs: reservationRestaurantlist1) {
            System.out.format("Looping Reservation Restaurant: id:%s mr:%s \n",
                    rs.getRestaurantId(), rs.getMaxRoomSize()); }
        
        List<Reviews> reviewlist1 = reviewsDao.getReviewsByUserId(49);
        for(Reviews rv: reviewlist1) {
            System.out.format("Looping Review: reid:%s uid:%s rstid:%s review:%s creat:%s ratin:%s \n",
                    rv.getReviewId(), rv.getUser().getUserId(), rv.getRestaurant().getRestaurantId(), 
                    rv.getReview(), rv.getCreated(), rv.getRating());}

        // UPDATE
        usersDao.updatePassword(u0,"666666");
        restaurantsDao.updateStar(restaurant0, 5.0);
        restaurantsDao.updatePriceRange(restaurant0, 5);
        restaurantsDao.updateNoiseLevel(restaurant0, 5);
        restaurantsDao.updateOpenTime(restaurant0,newOpenTime);
        restaurantsDao.updateCloseTime(restaurant0,newCloseTime);
        restaurantsDao.updateWifi(restaurant0,false);
        restaurantsDao.updateAcceptsCreditCard(restaurant0,false);
        deliveryDao.updateMaxDeliverTime(dr0, 10);
        takeOutDao.updateMaxWaitTime(to0, 10);
        reserveDao.updateRoomSize(rs0, 18);
        promotionsDao.updateDiscount(p0, 0.2);

       
        // DELETE
        // usersDao.delete(user0);
        // restaurantsDao.delete(restaurant0);
       //  restaurantsDao.delete(rs0);
        deliveryDao.delete(dr1);
        takeOutDao.delete(to0);
        

  


        
        
    }
}
        
        
    
        


