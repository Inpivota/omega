package com.inpivota.omega.FinaleImports;

import com.inpivota.omega.model.Location;
import com.inpivota.omega.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.inpivota.omega.FinaleImports.HelperMethods.FindLocationByName;

@Service
public class LocationImport {

    private LocationRepository locationRepository;

    @Autowired
    public LocationImport(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    public String ImportLocations(){
        String productFileName = "Locations.csv";
        String line = "";
        List<Location> dbLocations = locationRepository.findAll();

        List<String> errors = new ArrayList<>();

        try {
            var br = new BufferedReader(new FileReader(RunImport.PATH_TO_IMPORT_FILES + productFileName));
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                String locationName = data[0];
                if(!locationName.equals("Sublocation") && !locationName.equals("--")){
                    Optional<Location> dbCategory = FindLocationByName(dbLocations, locationName);
                    if (dbCategory.isEmpty()){
                        Location newLocation = new Location();
                        newLocation.setName(locationName);
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
