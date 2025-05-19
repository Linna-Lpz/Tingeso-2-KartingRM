package com.example.ms_rates.repository;

import com.example.ms_rates.entity.EntityRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRates extends JpaRepository<EntityRates, Integer> {
    public Integer findBasePriceByLapsOrMaxTime(Integer lapsOrMaxTime);
    public Integer findDurationByLapsOrMaxTime(Integer lapsOrMaxTime);
}
