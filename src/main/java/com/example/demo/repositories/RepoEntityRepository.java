package com.example.demo.repositories;

import com.example.demo.model.RepoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoEntityRepository extends JpaRepository<RepoEntity, Long>{

    RepoEntity findByRepoName(String repoName);
}
