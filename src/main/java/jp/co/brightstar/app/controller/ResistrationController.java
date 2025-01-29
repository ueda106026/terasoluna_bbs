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
			/*
			@Validated @ModelAttribute PostsForm form, 
			BindingResult result, 
			Model model, 
			@RequestParam(name = "page", defaultValue = "0") Integer page
			) {
			*/
		try {
			if (result.hasErrors()) {
				System.out.println("不正なリクエストです。");
				return "redirect:/error";
				
				/*
			    // リクエストパラメータを基にPageableを作成
			    Pageable pageable = Pageable.ofSize(10).withPage(page);
				// 現在の日付から3年目を取得
			    LocalDateTime threeYearsAgo = LocalDateTime.now().minusYears(3);
			    
			    Page<Posts> listPage = postsRepository.findByCreateDateAfterOrderByCreateDateDesc(threeYearsAgo, pageable);
			    List<Posts> posts = listPage.getContent();
			    
				// tbl_postsの総レコード数を取得して、最終ページを算出する
				long totalCount = Math.toIntExact(listPage.getTotalElements());
				int lastPage = (int) Math.floor((double) (totalCount -1) / 10);
				
				// 前ページへ戻る
				boolean pageBackBlank = false; // 非表示設定のための変数
				int pageBack = page;
				
				if (page > 0) {
					pageBack -= 1;
				} else if (page <= 0) {
					pageBack = 0;
					pageBackBlank = true;
				} else {
					System.out.println("処理中にエラーが発生しました。");
				}
				
				// 次ページへ進む
				boolean pageNextBlank = false; // 非表示設定のための変数
				int pageNext = page;
				if (page < lastPage) {
					pageNext += 1;
				} else if (page >= lastPage) {
					pageNext = lastPage;
					pageNextBlank = true;
				} else {
					System.out.println("処理中にエラーが発生しました。");
				}
			    
				model.addAttribute("posts", posts); // 一覧に表示するレコード情報
				model.addAttribute("bbs", bbsRepository.getReferenceById(1)); // 掲示板名 & 管理人名
				model.addAttribute("currentPage", page + 1); // 現在のページ
				model.addAttribute("lastPage", lastPage); // 最終ページ
				model.addAttribute("pageBack", pageBack); // 前ページへ
				model.addAttribute("pageNext", pageNext); // 次ページへ
				model.addAttribute("pageBackBlank", pageBackBlank); // 前への非表示設定
				model.addAttribute("pageNextBlank", pageNextBlank); // 次への非表示設定
				
				return "index";
				*/
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
