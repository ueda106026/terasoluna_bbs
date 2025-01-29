package jp.co.brightstar.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.brightstar.domain.model.Posts;



@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {
	// List<Posts> findAllByOrderByCreateDateDesc(); // 全件検索で登録日時の降順
	// List<Posts> findByCreateDateAfterOrderByCreateDateDesc(LocalDateTime createDate); // 過去3年以内の投稿で登録日時の降順
	List<Posts> findByCreateDateBeforeOrderByCreateDateDesc(LocalDateTime createDate); // 過去3年以降の投稿で登録日時の降順
	
	// ページネーションを有効
	Page<Posts> findByCreateDateAfterOrderByCreateDateDesc(LocalDateTime createDate, Pageable pageable);; // 過去3年以内の投稿で登録日時の降順
	Page<Posts> findByCreateDateBeforeOrderByCreateDateDesc(LocalDateTime createDate, Pageable pageable);; // 過去3年以降の投稿で登録日時の降順
}
