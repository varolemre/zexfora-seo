package com.codingbytime.zexfora.control.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codingbytime.zexfora.entity.table.Website;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, UUID> {
}
