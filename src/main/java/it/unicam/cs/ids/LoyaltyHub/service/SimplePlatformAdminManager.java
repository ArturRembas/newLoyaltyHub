package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.PlatformAdmin;
import it.unicam.cs.ids.LoyaltyHub.repository.PlatformAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Service class for managing platform administrator entities within the loyalty platform.
 * Provides functionalities to create, retrieve, and delete platform administrators, along with basic validations.
 */
@Validated
@Service
public class SimplePlatformAdminManager implements PlatformAdminManager {

    @Autowired
    private PlatformAdminRepository adminRepository;

    /**
     * Retrieves a platform administrator by their ID.
     *
     * @param id The ID of the platform administrator.
     * @return The {@link PlatformAdmin} object.
     * @throws EntityNotFoundException if the platform administrator is not found.
     */
    @Override
    public PlatformAdmin getInstance(Long id) throws EntityNotFoundException {
        return adminRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Platform administrator with ID " + id + " not found."));
    }

    /**
     * Creates a new platform administrator after performing necessary validations.
     *
     * @param object The {@link PlatformAdmin} object to create.
     * @return The created {@link PlatformAdmin} object.
     * @throws IdConflictException if the platform administrator already exists.
     */
    @Override
    public PlatformAdmin create(PlatformAdmin object) throws IdConflictException {
        checkIfValuesAreNotNull(object);
        checkCreation(object);
        return adminRepository.save(object);
    }

    /**
     * Updates an existing platform administrator. This method is currently not implemented.
     *
     * @param object The {@link PlatformAdmin} object to update.
     * @return null as this operation is not supported.
     */
    @Override
    public PlatformAdmin update(PlatformAdmin object) {
        throw new UnsupportedOperationException("Platform administrator update not supported.");
    }

    /**
     * Deletes a platform administrator by their ID.
     *
     * @param id The ID of the platform administrator to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws EntityNotFoundException if the platform administrator does not exist.
     */
    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if (!adminRepository.existsById(id)) {
            throw new EntityNotFoundException("Platform administrator with ID " + id + " not found, cannot delete.");
        }
        adminRepository.deleteById(id);
        return !adminRepository.existsById(id);
    }

    /**
     * Checks if a platform administrator exists by their ID.
     *
     * @param id The ID of the platform administrator.
     * @return true if the platform administrator exists, false otherwise.
     */
    @Override
    public boolean exists(Long id) {
        return adminRepository.existsById(id);
    }

    /**
     * Validates the creation of a platform administrator, ensuring no duplicate entries.
     *
     * @param admin The {@link PlatformAdmin} to validate.
     * @throws IdConflictException if the platform administrator already exists.
     */
    private void checkCreation(PlatformAdmin admin) throws IdConflictException {
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new IdConflictException("Platform administrator already exists with email: " + admin.getEmail());
        }
    }

    /**
     * Ensures all required platform administrator information is provided.
     *
     * @param admin The {@link PlatformAdmin} to validate.
     * @throws NullPointerException if essential information is missing.
     */
    private void checkIfValuesAreNotNull(PlatformAdmin admin) {
        Objects.requireNonNull(admin.getEmail(), "Email is required.");
        Objects.requireNonNull(admin.getPhone(), "Phone number is required.");
    }
}
