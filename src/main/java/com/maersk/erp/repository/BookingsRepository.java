package com.maersk.erp.repository;

import com.maersk.erp.model.Bookings;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends CassandraRepository<Bookings, Integer> {
}
