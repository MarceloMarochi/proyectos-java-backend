package utn.frc.bda.semana05.functional;

import utn.frc.bda.util.DateUtil;
import utn.frc.bda.util.Mapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrdersStream {
    // Line record indices
    private static final int CUSTOMER_ID_INDEX = 0, CUSTOMER_NAME_INDEX = 1;
    private static final int CITY_ID_INDEX = 2, CITY_NAME_INDEX = 3;
    private static final int ORDER_ID_INDEX = 4, ORDER_DATE_INDEX = 5;
    private static final int PRODUCT_ID_INDEX = 6, PRODUCT_NAME_INDEX = 7, PRODUCT_COUNT_INDEX = 8, PRODUCT_PRICE_INDEX = 9;

    // Collections. Attention to variables NOT being used due to poor or not convenient design.
    private static List<OrderDetailRow> detailRowList;
    private static List<OrderDetailItem> detailItemList = new ArrayList<>();
    private static List<Order> orderList;
    private static Set<Order> orderSet;
    protected static Map<Integer, Order> orderMap = new HashMap<>();
    private static List<Product> productList;
    private static Set<Product> productSet;
    protected static Map<Integer, Product> productMap = new HashMap<>();
    private static List<Customer> customerList;
    private static Set<Customer> customerSet;
    protected static Map<Integer, Customer> customerMap = new HashMap<>();
    private static List<City> cityList;
    private static Set<City> citySet;
    protected static Map<Integer, City> cityMap = new HashMap<>();

    // Data structures -> Records (for showing purposes)
    public record City(Integer id, String name) {}
    public record Customer(Integer id, String name, City city) {}
    public record Product(Integer id, String name, Double price) {}
    public record Order(Integer id, Date date, Customer customer, List<OrderDetailItem> detailItemList) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Order order)) return false;

            return Objects.equals(id, order.id);
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }

        @Override
        // to avoid recursion
        public String toString() {
            return "Order{" +
                    "id=" + id +
                    ", date=" + date +
                    ", customer=" + customer +
                    '}';
        }
    }
    public record OrderDetailItem(Order order, Product product, Integer count, Double subTotal) {
        // for showing purpose
        public Double st() {
            return count * product.price;
        }
    }

    public record OrderDetailRow(
            Integer orderId, Date orderDate,
            Integer cityId, String cityName,
            Integer customerId, String customerName,
            Integer count, Integer productId, String productName, Double productPrice) {
        public double subTotal() {
            return count * productPrice;
        }
    }

    // Mappers.
    // Attention to different definition styles.
    // Attention to array based mappers: are they worth using?
    static Mapper<String[], City> cityArrMapper = new Mapper<String[], City>() {
        @Override
        public City map(String[] arr) {
            return new City(Integer.parseInt(arr[CITY_ID_INDEX]), arr[CITY_NAME_INDEX]);
        }
    };
    static Mapper<String[], Customer> customerArrMapper = (arr) -> {
        return new Customer(
                Integer.parseInt(arr[CUSTOMER_ID_INDEX]),
                arr[CUSTOMER_NAME_INDEX],
                cityArrMapper.map(arr));
    };
    static Mapper<String[], Product> productArrMapper = (arr) -> new Product(
            Integer.parseInt(arr[PRODUCT_ID_INDEX]),
            arr[PRODUCT_NAME_INDEX],
            Double.valueOf(arr[PRODUCT_PRICE_INDEX]));
    static Mapper<String[], Order> orderArrMapper = new Mapper<>() {
        @Override
        public Order map(String[] arr) {
            try {
                return new Order(
                        Integer.parseInt(arr[ORDER_ID_INDEX]),
                        DateUtil.parse(arr[ORDER_DATE_INDEX], "yyyy-MM-dd"),
                        customerArrMapper.map(arr),
                        new ArrayList<>());
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    };
    static Mapper<String[], OrderDetailItem> detailItemArrMapper = arr -> {
        Order order = orderArrMapper.map(arr);
        Product product = productArrMapper.map(arr);
        Integer count = Integer.parseInt(arr[PRODUCT_COUNT_INDEX]);
        Double subTotal = count * product.price;
        return new OrderDetailItem(order, product, count, subTotal);
    };

    // this is the only one actually being used!
    static Mapper<String[], OrderDetailRow> detailRowArrMapper = new Mapper<>() {
        @Override
        public OrderDetailRow map(String[] arr) {
            try {
                return new OrderDetailRow(
                        Integer.parseInt(arr[ORDER_ID_INDEX]),
                        DateUtil.parse(arr[ORDER_DATE_INDEX], "yyyy-MM-dd"),
                        Integer.parseInt(arr[CITY_ID_INDEX]),
                        arr[CITY_NAME_INDEX],
                        Integer.parseInt(arr[CUSTOMER_ID_INDEX]),
                        arr[CUSTOMER_NAME_INDEX],
                        Integer.parseInt(arr[PRODUCT_COUNT_INDEX]),
                        Integer.parseInt(arr[PRODUCT_ID_INDEX]),
                        arr[PRODUCT_NAME_INDEX],
                        Double.valueOf(arr[PRODUCT_PRICE_INDEX])
                );
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    };

    // Collections updaters: which ones to use?
    private static void updateCollections(OrderDetailItem detailItem) {
        Order order = detailItem.order;
        Customer customer = order.customer;
        City city = customer.city;
        Product product = detailItem.product;

        //...
        //...

        // mmm...
    }

    private static void updateCollections(OrderDetailRow detailRow) {
        City city = cityMap.get(detailRow.cityId);
        if (city == null) {
            city = new City(detailRow.cityId, detailRow.cityName);
            cityMap.put(city.id, city);
        }

        Customer customer = customerMap.get(detailRow.customerId);
        if (customer == null) {
            customer = new Customer(detailRow.customerId, detailRow.customerName, city);
            customerMap.put(customer.id, customer);
        }

        Product product = productMap.get(detailRow.productId);
        if (product == null) {
            product = new Product(detailRow.productId, detailRow.productName, detailRow.productPrice);
            productMap.put(product.id, product);
        }

        Order order = orderMap.get(detailRow.orderId);
        if (order == null) {
            order = new Order(detailRow.orderId, detailRow.orderDate, customer, new ArrayList<>());
            orderMap.put(order.id, order);
        }

        OrderDetailItem detailItem = new OrderDetailItem(order, product, detailRow.count, detailRow.subTotal());
        order.detailItemList.add(detailItem);
        detailItemList.add(detailItem);
    }

    // Util
    private static List<String> readTxtFileLinesWithFilesAndPaths(String txtFilePath) {
        try (Stream<String> s = Files.lines(Paths.get(txtFilePath))) {
            return s.toList();
        } catch (IOException ioe) {
            return new ArrayList<>();
        }
    }

    private static List<String> readTxtFileLinesWithBufferedReader(String txtFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(txtFilePath))) {
            return reader.lines().toList();
        } catch (FileNotFoundException fnfe) {
            return new ArrayList<>();
        } catch (IOException ioe) {
            return new ArrayList<>();
        }
    }

    private static List<String> readTxtFileLines(String txtFilePath) {
        return
                //readTxtFileLinesWithFilesAndPaths(txtFilePath);
                readTxtFileLinesWithBufferedReader(txtFilePath);
    }

    private static void loadDetailRowList(String csvFilePath) {
        String delim = "(?<!\\\\),";

        detailRowList = readTxtFileLines(csvFilePath)
                .stream()
                .skip(1)
                .map(l -> Arrays.stream(l.split(delim))
                        .map(el -> el
                                .replaceAll("\\\\,", ",")
                                .trim())
                        .toArray(String[]::new))
                .map(arr -> detailRowArrMapper.map(arr))
                .toList();
    }

    public static void loadCollections(String csvFilePath) {
        loadDetailRowList(csvFilePath);
        detailRowList.forEach(detailRow -> {
            updateCollections(detailRow);
        });
    }

    // Main
    public static void main(String[] args) {
        String fPath = System.getProperty("user.dir") + "/src/main/java/utn/frc/bda/semana04/orders.csv";
        loadCollections(fPath);

        // ---------------------------------------------------------------------
        // Orders of customer 15
        Map<Order, Double> m = orderMap.values().stream()
                .filter(order -> order.customer.id == 15)
                .collect(Collectors.toMap(
                        order -> order,
                        order -> order.detailItemList.stream().reduce(
                                0d,
                                (partial, element) -> partial + element.subTotal(),
                                Double::sum
                        )
                ));

        Double total = 0d;
        for (Order order : m.keySet()) {
            total += m.get(order);
            System.out.println(order + ": " + m.get(order));
        }

        System.out.println("Total: " + total);
    }

}
