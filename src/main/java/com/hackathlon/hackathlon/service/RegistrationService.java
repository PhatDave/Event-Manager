package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.User.*;

import java.util.*;

public interface RegistrationService {
    List<Registration> getAll();
    Registration getById(Long id);
}
