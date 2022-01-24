package com.hackathlon.hackathlon.service;

import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.User.*;

import java.util.*;

public interface TeamService {
    List<Team> getAll();
    Team getById(Long id);
}
