package utn.frc.bda.semana05.jdbc;

import utn.frc.bda.semana05.functional.OrdersStream;

import java.sql.ResultSet;
import java.util.Date;

public class OrdersDB {

    // Util
    private static void populate(DBManager db) throws Exception {
        String fPath = System.getProperty("user.dir") + "/src/main/java/utn/frc/bda/semana04/orders.csv";
        OrdersStream.loadCollections(fPath);

        // Cities
        db.prepareUpdate("insert into city(cid, cname) values (?, ?)");
        for (OrdersStream.City city : OrdersStream.cityMap.values()) {
            db.setInt(1, city.id());
            db.setString(2, city.name());
            db.executeUpdate();
        }

        // Customers
        db.prepareUpdate("insert into customer(cid, cname, city_id) values (?, ?, ?)");
        for (OrdersStream.Customer customer : OrdersStream.customerMap.values()) {
            db.setInt(1, customer.id());
            db.setString(2, customer.name());
            db.setInt(3, customer.city().id());
            db.executeUpdate();
        }

        // Products
        db.prepareUpdate("insert into product(pid, pname, price) values (?, ?, ?)");
        for (OrdersStream.Product product : OrdersStream.productMap.values()) {
            db.setInt(1, product.id());
            db.setString(2, product.name());
            db.setDouble(3, product.price());
            db.executeUpdate();
        }

        // Orders
        db.prepareUpdate("insert into orders(oid, cid, odate) values (?, ?, ?)");
        for (OrdersStream.Order order : OrdersStream.orderMap.values()) {
            db.setInt(1, order.id());
            db.setInt(2, order.customer().id());
            db.setDate(3, order.date());
            db.executeUpdate();
        }

        // Order details
        db.prepareUpdate("insert into order_details(oid, pid, cnt) values (?, ?, ?)");
        for (OrdersStream.OrderDetailItem item : OrdersStream.detailItemList) {
            db.setInt(1, item.order().id());
            db.setInt(2, item.product().id());
            db.setInt(3, item.count());
            db.executeUpdate();
        }
    }

    public static void main(String[] args) {
        DBManager db = null;

        try {
            db = new DBManager(DBManager.H2_DRIVER_NAME, "jdbc:h2:file:///tmp/ddbb/ordersdb");
            db.connect();

            // One time only !!
            //populate(db);

            // ---------------------------------------------------------------------
            // Orders of customer 15

            ResultSet rs = db.executeQuery("""
                select o.oid, o.odate, cust.cname as customer, cit.cname as city, sum(od.cnt * p.price) as order_total
                  from orders o
                    join customer cust on o.cid = cust.cid
                    join city cit on cust.city_id = cit.cid
                    join order_details od on o.oid = od.oid
                    join product p on od.pid = p.pid
                  where cust.cid = 15
                  group by o.oid, o.odate, cust.cname, cit.cname                      
                """);

            Double customerTotal = 0d;
            while (rs.next()) {
                Integer oid = rs.getInt("oid");
                Date oDate = rs.getDate("odate");
                String customer = rs.getString("customer");
                String city = rs.getString("city");
                Double orderTotal = rs.getDouble("order_total");

                customerTotal += orderTotal;

                System.out.printf(
                        "Order: %d, Date: %s, Customer: %s, City: %s, Order total: %f\n",
                        oid, oDate, customer, city, orderTotal);
            }

            System.out.println("Total: " + customerTotal);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

}
