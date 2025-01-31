package jp.co.brightstar.app.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.brightstar.domain.model.Posts;
import jp.co.brightstar.domain.model.PostsForm;
import jp.co.brightstar.domain.repository.BbsRepository;
import jp.co.brightstar.domain.repository.PostsRepository;

@Controller
public class ResistrationController {
	@Autowired
	PostsRepository postsRepository;
	@Autowired
	BbsRepository bbsRepository;

	@PostMapping(value = "/registration")
	public String registration(@Validated PostsForm form, BindingResult result) {
		try {
			if (result.hasErrors()) {
				System.out.println("不正なリクエストです。");
				return "redirect:/error";
				
			} else {
				Posts posts = new Posts();
				BeanUtils.copyProperties(form, posts, "id");
				posts.setBbsId(1); // 掲示板IDを1としてセットする
				// 本日の日付をセットする
				java.util.Date utilDate = new java.util.Date();
				java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
				posts.setCreateDate(sqlDate);
				// DBへ登録処理
				posts = postsRepository.save(posts);
				
				return "redirect:/";
			}
		} catch (Exception e) {
			return "redirect:/error_exception";
		}
	}
}
