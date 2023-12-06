package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.ActivityAdmin;
import it.unicam.cs.ids.LoyaltyHub.service.ActivityAdminManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/ActivityAdmin")
public class SimpleActivityAdminController implements ActivityAdminController{
    @Autowired
    private ActivityAdminManager activityAdminManager;
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
    public ActivityAdmin update(ActivityAdmin object) {
        return null;
    }

    @Override
    @DeleteMapping("/deleteAdmin/{id}")
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return activityAdminManager.delete(id);
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }


}
