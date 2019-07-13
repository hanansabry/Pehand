package com.pehand.app.backend.subservies;

import com.pehand.app.pojos.SubService;

import java.util.ArrayList;

public class SubServicesRepositoryImpTemp implements SubServiceRepository {
    @Override
    public ArrayList<SubService> getAllSubServices() {
        return null;
    }

    @Override
    public ArrayList<SubService> getSubServicesByServiceId(int id) {
        ArrayList<SubService> subServices1 = new ArrayList<>();
        SubService sub1 = new SubService(11, "تركيب تكييف 1.5 حصان", "250", "جنيه", "https://www.pehand.com/Files/SubService/9/air-conditioner-installation.jpg");
        SubService sub2 = new SubService(9, "تركيب تكييف 2.25 حصان", "250", "جنيه", "https://www.pehand.com/Files/SubService/9/air-conditioner-installation.jpg");
        subServices1.add(sub1);
        subServices1.add(sub2);

        ArrayList<SubService> subServices2 = new ArrayList<>();
        SubService sub11 = new SubService(22, "فلتر مياه 7 مراحل تايوانى بخزان 10 لتر ", "2100", "جنيه شامل التركيب", "https://www.pehand.com/Files/SubService/1/1 (1).jpg");
        SubService sub22 = new SubService(30, "فلتر مياه 5 مراحل تايوانى ", "500", "جنيه شامل التركيب", "https://www.pehand.com/Files/SubService/2/s520121314253.jpg");
        subServices2.add(sub11);
        subServices2.add(sub22);

        ArrayList<SubService> subServices3 = new ArrayList<>();
        SubService sub111 = new SubService(11, "أعمال الكهرباء", "25", "جنيه للمعاينة", "https://www.pehand.com/Files/SubService/22/electromechanical.png");
        subServices3.add(sub111);

        if (id == 1) {
            return subServices1;
        } else if (id == 2) {
            return subServices2;
        } else {
            return subServices3;
        }
    }
}
