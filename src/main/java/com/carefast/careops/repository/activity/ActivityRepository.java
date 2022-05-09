package com.carefast.careops.repository.activity;

import com.carefast.careops.model.activity.ActivityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityModel, Integer> {
    @Query(value = "SELECT * FROM tab_master_activity " +
            "WHERE activity_id = :activityId", nativeQuery = true)
    List<ActivityModel> findActivityById(int activityId);
}
