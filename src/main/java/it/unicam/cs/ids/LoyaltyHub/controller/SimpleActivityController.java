package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.model.Activity;
import it.unicam.cs.ids.LoyaltyHub.service.ActivityManager;
import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Activity")
public class SimpleActivityController implements ActivityController{
    @Autowired
    private ActivityManager activityManager;
    @Override
    @GetMapping("/{id}")
    public Activity getInstance(@PathVariable Long id) throws EntityNotFoundException {
        return activityManager.getInstance(id);
    }

    @Override
    @PostMapping("/createNew")
    public Activity create(@RequestBody Activity object) throws IdConflictException, EntityNotFoundException {
        return activityManager.create(object);
    }

    @Override
    @PostMapping("/update")
    public Activity update(@RequestBody Activity object) throws IdConflictException, EntityNotFoundException {
    	return activityManager.update(object);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
        return activityManager.delete(id);
    }

    @Override
    @GetMapping("/exists/{id}")
    public boolean exists(@PathVariable Long id) {
    	return activityManager.exists(id);
    }

    @Override
    @PostMapping("/createNewWithAdmin")
    public Activity createActivityWithAdmin(@RequestBody Activity activity) throws IdConflictException, EntityNotFoundException {
        return activityManager.createActivityWithAdminEmail(activity);
    }
}
