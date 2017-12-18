package com.tobz.clean.domain.repository;

import io.reactivex.Observable;

/**
 * Repository related to user.
 *
 */

public interface UserRepository {

    /**
     * Test user login
     * @param username
     * @param password
     * @return boolean
     */
    Observable<Boolean> userLogin(String username, String password);
}
