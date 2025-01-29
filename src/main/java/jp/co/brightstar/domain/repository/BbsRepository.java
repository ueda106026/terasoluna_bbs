package jp.co.brightstar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.brightstar.domain.model.Bbs;

public interface BbsRepository extends JpaRepository<Bbs, Integer> {
	
}
