package com.life.marketplace.BusinessLogicLayer;

import com.google.gson.Gson;
import com.life.marketplace.DataAccessLayer.DatabaseAccess;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import com.life.marketplace.model.*;

public class ProductManagement {

    DatabaseAccess db = new DatabaseAccess();

    public boolean addProduct(Double price, Date date, String type, String color, String condition, String sellerName, String productName) {
        return db.p_add_product(price, date, type, condition, color, sellerName, productName);
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            products = ObjectCreator.createProductList(db.f_get_all_products());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean addToCart(String username, UUID productID) {
        return db.p_add_to_cart(username, productID);
    }

    public boolean placeOrder(UUID orderID) {
        return db.p_user_place_order(orderID);
    }

    public boolean removeProduct(UUID productID) {
        return db.p_remove_product(productID);
    }

    public ArrayList<Product> productSearch(String type, String condition, double maxPrice, double minPrice) {

        ArrayList<Product> products = new ArrayList<>();

        try {

            products = ObjectCreator.createProductList(db.f_product_search(type, condition, maxPrice, minPrice));

        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
        }

        return products;
    }

    public Types getTypes() {
        Types types = new Types();
        try {
            ResultSet rs = db.f_get_types();

            while(rs.next()) {


                types.addType(rs.getString(1));



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    public Colors getColors() {
        Colors colors = new Colors();
        try {
            ResultSet rs = db.f_get_colors();

            while(rs.next()) {


                colors.addColors(rs.getString(1));



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colors;
    }

    public Conditions getConditions() {
        Conditions conditions = new Conditions();
        try {
            ResultSet rs = db.f_get_conditions();

            while(rs.next()) {


                conditions.addCondition(rs.getString(1));



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conditions;
    }

    public OrderAndProduct getCartForUser(String username) {
        OrderAndProduct orderAndProduct = new OrderAndProduct();
        ObjectCreator objectCreator = new ObjectCreator();
        try {
            ResultSet rs = db.f_get_cart_for_user(username);
            orderAndProduct = objectCreator.createCartList(rs);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return orderAndProduct;
    }


    
    public String getProductSellLists() {
        Gson gson = new Gson();

        String typeString = gson.toJson(getTypes());
        String conditionString = gson.toJson(getConditions());
        String colorString = gson.toJson(getColors());

        String str = "{"+ typeString +","+ conditionString +","+ colorString +"}";
        str = str.replaceAll("\\{", "");
        str = str.replaceAll("}", "");

        str = "{"+str+"}";

        return str;
    }

    public String getAllProductsJSON() {
        return new Gson().toJson(getAllProducts());
    }
    public String getCartForUserJSON(String username){
        return new Gson().toJson(getCartForUser(username));
    }

    public boolean userRemoveCart(String uuid) {
            return db.p_user_remove_cart(UUID.fromString(uuid));
        }
    public boolean userConfirmCart(String uuid) {
        return db.p_user_place_order(UUID.fromString(uuid));
    }

    public String showPurchaseHistory(String username, Date startDate, Date stopDate) {
        OrderAndProduct orderAndProduct = new OrderAndProduct();
        ArrayList<OrderAndProduct> ordersAndProducts = new ArrayList<>();
        try {
            ResultSet rs = db.f_show_purchase_history(username,startDate,stopDate);
            ObjectCreator oc = new ObjectCreator();
            ordersAndProducts = oc.createPurchaseHistoryList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Gson().toJson(ordersAndProducts);
    }

    public boolean sellProduct(String seller, String productName, double price, Date date, String type, String color, String condition) {
        return db.p_add_product(price,date,type,color,condition,seller,productName);
    }
}
