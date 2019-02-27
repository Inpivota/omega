package com.inpivota.omega.FinaleImports;

import com.inpivota.omega.model.Location;
import com.inpivota.omega.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static com.inpivota.omega.FinaleImports.HelperMethods.FindLocationByName;

@Service
public class LocationImport {

    private LocationRepository locationRepository;

    @Autowired
    public LocationImport(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    public String ImportLocations(){
        String filePath = "";
        String productFileName = "Locations.csv";
        String line = "";
        List<Location> dbLocations = locationRepository.findAll();

        List<String> errors = new ArrayList<>();

        try {
            var br = new BufferedReader(new FileReader(filePath + productFileName));
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                String category = data[0];
                if(category != "Category" && category != "--"){
                    Location dbCategory = FindLocationByName(dbLocations, category);
                    if (dbCategory == null){
                        Location newLocation = new Location();
                        newLocation.setName(category);
                        locationRepository.save(newLocation);
                    }
                }

            }

        }catch (Exception e){
            errors.add(e.getMessage());
        }

        if(errors.isEmpty()){
            return "Had no errors importing Locations";
        }
        else {
            return String.join(" ", errors);
        }
    }
}
