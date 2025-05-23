package com.example.ms_reports.repository;

import com.example.ms_reports.entity.EntityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoReport extends JpaRepository<EntityReport, Long> {
    @Query("SELECT b FROM EntityReport b WHERE b.bookingStatus = ?1 AND MONTH(b.bookingDate) = ?2 AND (b.lapsOrMaxTimeAllowed = ?3)")
    List<EntityReport> findByStatusAndDayAndLapsOrMaxTime(String status, String month, Integer maxTimeAllowed);

    @Query("SELECT b FROM EntityReport b WHERE b.bookingStatus = ?1 AND MONTH(b.bookingDate) = ?2 AND (b.numOfPeople = 1 OR b.numOfPeople = 2)")
    List<EntityReport> findByStatusAndDayAndNumOfPeople1or2(String status, String month, Integer numOfPeople);

    @Query("SELECT b FROM EntityReport b WHERE b.bookingStatus = ?1 AND MONTH(b.bookingDate) = ?2 AND (3 <= b.numOfPeople AND b.numOfPeople <= 5)")
    List<EntityReport> findByStatusAndDayAndNumOfPeople3to5(String status, String month, Integer numOfPeople);

    @Query("SELECT b FROM EntityReport b WHERE b.bookingStatus = ?1 AND MONTH(b.bookingDate) = ?2 AND (6 <= b.numOfPeople AND b.numOfPeople <= 10)")
    List<EntityReport> findByStatusAndDayAndNumOfPeople6to10(String status, String month, Integer numOfPeople);

    @Query("SELECT b FROM EntityReport b WHERE b.bookingStatus = ?1 AND MONTH(b.bookingDate) = ?2 AND (11 <= b.numOfPeople AND b.numOfPeople <= 15)")
    List<EntityReport> findByStatusAndDayAndNumOfPeople11to15(String status, String month, Integer numOfPeople);
}
