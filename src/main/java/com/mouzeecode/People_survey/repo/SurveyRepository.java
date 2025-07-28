package com.mouzeecode.People_survey.repo;

import com.mouzeecode.People_survey.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<UserDetails, Long> {

}
