package service;

import entity.Mobiles;
import repository.MobileRepository;
import libary.erros.DataNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MobileService {

    private final MobileRepository repository = new MobileRepository();

    private List<Mobiles> mobileList;

    public MobileService() {
        mobileList = repository.read();
    }

    // Tạo ID tự động
    private int generateId() {
        int maxId = 0;

        for (Mobiles mobile : mobileList) {
            if (mobile.getId() > maxId) {
                maxId = mobile.getId();
            }
        }

        return maxId + 1;
    }

    // Thêm điện thoại
    public void addMobile(Mobiles mobile) {
        mobile.setId(generateId());
        mobileList.add(mobile);
        repository.write(mobileList);
    }

    // Xóa điện thoại
    public void deleteMobile(int id) {
        boolean removed = mobileList.removeIf(mobile -> mobile.getId() == id);

        if (!removed) {
            throw new DataNotFoundException("Khong tim thay dien thoai co ID: " + id);
        }

        repository.write(mobileList);
    }

    // Lấy danh sách điện thoại
    public List<Mobiles> getAllMobiles() {
        return mobileList;
    }

    // Tìm kiếm điện thoại
    public List<Mobiles> searchMobile(String keyword) {
        List<Mobiles> result = new ArrayList<>();
        String searchValue = keyword.toLowerCase().trim();

        for (Mobiles mobile : mobileList) {
            String id = String.valueOf(mobile.getId());
            String name = mobile.getName().toLowerCase();

            if (id.contains(searchValue) || name.contains(searchValue)) {
                result.add(mobile);
            }
        }

        return result;
    }

    public List<Mobiles> searchMobileById(int id) {
        List<Mobiles> result = new ArrayList<>();

        for (Mobiles mobile : mobileList) {
            if (mobile.getId() == id) {
                result.add(mobile);
            }
        }

        return result;
    }

    public List<Mobiles> searchMobileByName(String name) {
        List<Mobiles> result = new ArrayList<>();
        String searchName = name.toLowerCase().trim();

        for (Mobiles mobile : mobileList) {
            if (mobile.getName().toLowerCase().contains(searchName)) {
                result.add(mobile);
            }
        }

        return result;
    }

}
