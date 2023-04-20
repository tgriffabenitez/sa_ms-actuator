package com.sistemasactivos.msactuator.repository;

import com.sistemasactivos.msactuator.model.Metric;
import org.springframework.stereotype.Repository;

@Repository
public interface DiskRepository extends BaseRepository<Metric, Long>{
}
