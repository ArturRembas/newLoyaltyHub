package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.ActivityAdmin;
import it.unicam.cs.ids.LoyaltyHub.service.ActivityAdminManager;
import it.unicam.cs.ids.LoyaltyHub.repository.ActivityAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/ActivityAdmin")
public class SimpleActivityAdminController implements ActivityAdminController{
    @Autowired
    private ActivityAdminManager activityAdminManager;
    @Autowired
    private ActivityAdminRepository activityAdminRepository;
    @Override
    @GetMapping("/{id}")
    public ActivityAdmin getInstance(@PathVariable Long id) throws EntityNotFoundException{
        return activityAdminManager.getInstance(id);
    }

    @Override
    @PostMapping("/createNew")
    public ActivityAdmin create(@RequestBody ActivityAdmin object) throws IdConflictException, EntityNotFoundException {
        return activityAdminManager.create(object);
    }

    @Override
    @PostMapping("/update")
    public ActivityAdmin update(@RequestBody ActivityAdmin object) throws IdConflictException, EntityNotFoundException {
    	return activityAdminManager.update(object);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return activityAdminManager.delete(id);
    }

    @Override
    @GetMapping("/exists/{id}")
    public boolean exists(@PathVariable Long id) {
    	return activityAdminManager.exists(id);
    }


}
