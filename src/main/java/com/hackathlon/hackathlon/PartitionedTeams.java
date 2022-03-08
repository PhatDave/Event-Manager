package com.hackathlon.hackathlon;

import com.hackathlon.hackathlon.entity.*;
import com.hackathlon.hackathlon.entity.user.*;
import com.hackathlon.hackathlon.repository.*;

import java.util.*;

public class PartitionedTeams {
    private ArrayList<PTeam> pTeams;
    private final List<Team> teams;
    private final List<User> users;

    private final TeamRepository teamRepository;

    public PartitionedTeams(List<Team> teams, List<User> users, TeamRepository teamRepository) {
        this.users = users;
        this.teams = teams;
        this.teamRepository = teamRepository;
        this.sortUsers();

        this.pTeams = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            this.pTeams.add(new PTeam());
        }

        this.doPartition();
        this.applyToTeams();
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
        PTeam minScoreTeam = this.pTeams.get(0);
        for (int i = 0; i < this.pTeams.size(); i++) {
            PTeam team = this.pTeams.get(i);
            if (team.getScore() < minScoreTeam.getScore()) {
                minScoreTeam = team;
            }
        }
        return minScoreTeam;
    }

    private void applyToTeams() {
        for (int i = 0; i < this.pTeams.size(); i++) {
            Team team = this.teams.get(i);
            PTeam pteam = this.pTeams.get(i);

            team.setUsers(pteam.getMembers());
            for (User user : team.getUsers()) {
                user.setTeam(team);
            }

        }
        teamRepository.saveAll(this.teams);
    }

    public List<Team> getTeams() {
        return this.teams;
    }
}
