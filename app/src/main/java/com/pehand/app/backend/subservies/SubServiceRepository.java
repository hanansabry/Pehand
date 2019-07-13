package com.pehand.app.backend.subservies;

import com.pehand.app.pojos.SubService;

import java.util.ArrayList;

public interface SubServiceRepository {

    ArrayList<SubService> getAllSubServices();

    ArrayList<SubService> getSubServicesByServiceId(int id);
}
