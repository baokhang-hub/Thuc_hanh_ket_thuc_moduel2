package Ui;

import service.MobileService;
import entity.Mobiles;
import entity.GenuineMobile;
import entity.HandCarriedMobile;
import libary.InputHelper;
import libary.erros.DataNotFoundException;

public class UiMobile {

    private static final MobileService mobileService = new MobileService();

    public static void showMobileMenu() {
        int choice;

        do {
            System.out.println("===== QUAN LY DIEN THOAI =====");
            System.out.println("1. Them dien thoai");
            System.out.println("2. Xoa dien thoai");
            System.out.println("3. Xem danh sach dien thoai");
            System.out.println("4. Tim kiem dien thoai");
            System.out.println("0. Tro ve menu quan ly");
            System.out.print("Chon chuc nang: ");

            choice = InputHelper.inputInt();

            switch (choice) {
                case 1:
                    addMobile();
                    break;
                case 2:
                    deleteMobile();
                    break;
                case 3:
                    getAllMobiles();
                    break;
                case 4:
                    searchMobile();
                    break;
                case 0:
                    System.out.println("Good bye!!!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }

        } while (choice != 0);
    }

    // 1. Them dien thoai
    public static void addMobile() {
        System.out.println("===== THEM DIEN THOAI =====");
        System.out.println("1. Dien thoai chinh hang");
        System.out.println("2. Dien thoai xach tay");
        System.out.print("Chon loai dien thoai: ");

        int type = InputHelper.inputInt();

        System.out.print("Nhap ten dien thoai: ");
        String name = InputHelper.inputString();

        System.out.print("Nhap gia ban: ");
        double price = InputHelper.inputDouble();

        System.out.print("Nhap so luong: ");
        int quantity = InputHelper.inputInt();

        System.out.print("Nhap nha san xuat: ");
        String manufacturer = InputHelper.inputString();

        Mobiles mobile;

        switch (type) {
            case 1:
                System.out.print("Nhap thoi gian bao hanh (ngay): ");
                int warrantyPeriod = InputHelper.inputInt();

                System.out.print("Nhap pham vi bao hanh (Toan Quoc/Quoc Te): ");
                String warrantyScope = InputHelper.inputString();

                mobile = new GenuineMobile(
                        0, name, price, quantity,
                        manufacturer, warrantyPeriod, warrantyScope
                );
                break;

            case 2:
                System.out.print("Nhap quoc gia xach tay: ");
                String importCountry = InputHelper.inputString();

                System.out.print("Nhap trang thai sua chua (Da sua chua/Chua sua chua): ");
                String repairStatus = InputHelper.inputString();

                mobile = new HandCarriedMobile(
                        0, name, price, quantity,
                        manufacturer, importCountry, repairStatus
                );
                break;

            default:
                System.out.println("Loai dien thoai khong hop le!");
                return;
        }

        mobileService.addMobile(mobile);
        System.out.println("Dien thoai da duoc them thanh cong!");
    }

    // 2. Xoa dien thoai
    public static void deleteMobile() {
        System.out.println("===== XOA DIEN THOAI =====");
        System.out.print("Nhap ID dien thoai can xoa: ");

        int id = InputHelper.inputInt();

        try {
            System.out.println("Ban co chac muon xoa dien thoai co ID: " + id + "? (Y/N)");
            String confirmation = InputHelper.inputString();

            if (confirmation.equalsIgnoreCase("Y")) {
                mobileService.deleteMobile(id);
                System.out.println("Dien thoai da duoc xoa thanh cong!");
            }

        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // 3. Xem danh sach dien thoai
    public static void getAllMobiles() {
        System.out.println("===== DANH SACH DIEN THOAI =====");

        int index = 1;

        for (Mobiles mobile : mobileService.getAllMobiles()) {
            System.out.println(index + ". " + mobile);
            index++;
        }

        if (index == 1) {
            System.out.println("Danh sach dien thoai trong!");
        }
    }

    // 4. Tim kiem dien thoai
    public static void searchMobile() {
        System.out.println("===== TIM KIEM DIEN THOAI =====");
        System.out.println("1. Tim theo ID");
        System.out.println("2. Tim theo ten");
        System.out.print("Chon cach tim kiem: ");

        int searchType = InputHelper.inputInt();
        var result = switch (searchType) {
            case 1 -> {
                System.out.print("Nhap ID dien thoai can tim: ");
                yield mobileService.searchMobileById(InputHelper.inputInt());
            }
            case 2 -> {
                System.out.print("Nhap ten dien thoai can tim: ");
                yield mobileService.searchMobileByName(InputHelper.inputString());
            }
            default -> {
                System.out.println("Lua chon khong hop le!");
                yield null;
            }
        };

        if (result == null) {
            return;
        }

        if (result.isEmpty()) {
            System.out.println("Khong tim thay dien thoai!");
        } else {
            System.out.println("===== KET QUA TIM KIEM =====");

            for (Mobiles mobile : result) {
                System.out.println(mobile);
            }
        }
    }
}
