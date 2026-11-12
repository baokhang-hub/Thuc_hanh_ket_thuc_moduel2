
package repository;

import entity.Mobiles;
import entity.GenuineMobile;
import entity.HandCarriedMobile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class MobileRepository implements IRepository<Mobiles> {

    private static final String FILE_NAME = "data/mobiles.csv";

    @Override
    public void write(List<Mobiles> list) {

        try {
            Path path = Paths.get(FILE_NAME);

            // Tạo thư mục data nếu chưa tồn tại
            Files.createDirectories(path.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(
                    path, StandardCharsets.UTF_8)) {

                for (Mobiles mobile : list) {

                    String data;

                    // Điện thoại chính hãng
                    if (mobile instanceof GenuineMobile) {

                        GenuineMobile genuine =
                                (GenuineMobile) mobile;

                        data = genuine.getId() + ","
                                + "CHINH_HANG" + ","
                                + genuine.getName() + ","
                                + genuine.getPrice() + ","
                                + genuine.getQuantity() + ","
                                + genuine.getManufacturer() + ","
                                + genuine.getWarrantyPeriod() + ","
                                + genuine.getWarrantyScope() + ",,";

                    }

                    // Điện thoại xách tay
                    else if (mobile instanceof HandCarriedMobile) {

                        HandCarriedMobile handCarried =
                                (HandCarriedMobile) mobile;

                        data = handCarried.getId() + ","
                                + "XACH_TAY" + ","
                                + handCarried.getName() + ","
                                + handCarried.getPrice() + ","
                                + handCarried.getQuantity() + ","
                                + handCarried.getManufacturer() + ",,,"
                                + handCarried.getCountry() + ","
                                + handCarried.getStatus();

                    } else {
                        throw new IllegalArgumentException(
                                "Loai dien thoai khong hop le!"
                        );
                    }

                    writer.write(data);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Error writing to file: " + e.getMessage()
            );
        }
    }

    @Override
    public List<Mobiles> read() {

        List<Mobiles> mobileList = new ArrayList<>();

        Path path = Paths.get(FILE_NAME);

        if (!Files.exists(path)) {
            return mobileList;
        }

        try (BufferedReader reader = Files.newBufferedReader(
                path, StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",", -1);

                if (data.length < 10) {
                    System.out.println("Du lieu CSV khong hop le: " + line);
                    continue;
                }

                int id = Integer.parseInt(data[0]);
                String type = data[1];
                String name = data[2];
                double price = Double.parseDouble(data[3]);
                int quantity = Integer.parseInt(data[4]);
                String manufacturer = data[5];

                // Đọc điện thoại chính hãng
                if (type.equals("CHINH_HANG")) {

                    int warrantyPeriod =
                            Integer.parseInt(data[6]);

                    String warrantyScope = data[7];

                    GenuineMobile genuine = new GenuineMobile(
                            id,
                            name,
                            price,
                            quantity,
                            manufacturer,
                            warrantyPeriod,
                            warrantyScope
                    );

                    mobileList.add(genuine);

                }

                // Đọc điện thoại xách tay
                else if (type.equals("XACH_TAY")) {

                    String country = data[8];
                    String status = data[9];

                    HandCarriedMobile handCarried =
                            new HandCarriedMobile(
                                    id,
                                    name,
                                    price,
                                    quantity,
                                    manufacturer,
                                    country,
                                    status
                            );

                    mobileList.add(handCarried);
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Error reading from file: " + e.getMessage()
            );
        }

        return mobileList;
    }
}
