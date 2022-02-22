package com.hackathlon.hackathlon;

import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.user.*;

import java.util.*;

public class PartitionedTeams {
    private ArrayList<PTeam> teams;
    private final List<User> users;

    public PartitionedTeams(int size, List<User> users) {
        this.users = users;
        this.sortUsers();

        this.teams = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.teams.add(new PTeam());
        }

        this.doPartition();
    }

    private void sortUsers() {
        this.users.sort((User userA, User userB) -> userA.getRegistration().getScore().compareTo(userB.getRegistration().getScore()));
    }

    public class PTeam {
        private Integer score;
        private List<User> members;

        public PTeam() {
            this.score = 0;
            this.members = new ArrayList<>();
        }

        public Integer getScore() {
            return this.score;
        }

        public void addMember(User member) {
            members.add(member);
            this.score += member.getRegistration().getScore();
        }

        public List<User> getMembers() {
            return this.members;
        }
    }

    private void doPartition() {
        for (User user : users) {
            this.addUserToTeams(user);
        }
    }

    private void addUserToTeams(User user) {
        this.getWeakestTeam().addMember(user);
    }

    private PTeam getWeakestTeam() {
        PTeam minScoreTeam = this.teams.get(0);
        for (int i = 0; i < this.teams.size(); i++) {
            PTeam team = this.teams.get(i);
            if (team.getScore() < minScoreTeam.getScore()) {
                minScoreTeam = team;
            }
        }
        return minScoreTeam;
    }

    public List<PTeam> getTeams() {
//        TODO: Maybe convert PTeam to Team?
        return this.teams;
    }
}