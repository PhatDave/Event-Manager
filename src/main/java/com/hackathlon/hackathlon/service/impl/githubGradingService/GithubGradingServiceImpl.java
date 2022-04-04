package com.hackathlon.hackathlon.service.impl.githubGradingService;

import com.hackathlon.hackathlon.dto.githubRelatedDtos.GithubRepoDto;
import com.hackathlon.hackathlon.entity.user.User;
import com.hackathlon.hackathlon.enums.GithubLanguageGradingEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubGradingServiceImpl implements GithubGradingService {
    private final GithubRetrieveService githubRetrieveService;

    @Override
    public Integer grade(User user) {
        String repositoryUrl = user.getExperience().getRepositoryUrl();
        List<GithubRepoDto> repos = githubRetrieveService.getByUrl(repositoryUrl);

        Integer score = 0;
        score += repos.size();
        score += gradeByLanguage(repos);
        score += gradeByActivity(repos);

        return score;
    }

    private Integer gradeByActivity(List<GithubRepoDto> repos) {
        Integer score = 0;
        for (GithubRepoDto repo : repos) {
            if (isRecent(repo)) {
                // TODO: make this configurable
                score += 5;
            }
        }
        return score;
    }

    private Boolean isRecent(GithubRepoDto repo) {
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(repo.getUpdated_at());
        // TODO: make this configurable
        calendarNow.add(Calendar.DATE, 14);
        Date futureDate = calendarNow.getTime();
        Date now = new Date();
        return futureDate.after(now);
    }

    private Integer gradeByLanguage(List<GithubRepoDto> repos) {
        Integer score = 0;

        for (GithubRepoDto repo : repos) {
            String language = repo.getLanguage();
            try {
                if (language != null) {
                    GithubLanguageGradingEnum languageEnum = GithubLanguageGradingEnum.valueOf(language.toUpperCase());
                    score += languageEnum.getScore();
                }
            } catch (IllegalArgumentException e) {
                continue;
            }
        }

        return score;
    }
}
