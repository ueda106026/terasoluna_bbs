package jp.co.brightstar.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.brightstar.domain.model.Posts;
import jp.co.brightstar.domain.model.PostsForm;
import jp.co.brightstar.domain.repository.BbsRepository;
import jp.co.brightstar.domain.repository.PostsRepository;



@Controller
public class IndexController {
	@Autowired
	PostsRepository postsRepository;
	@Autowired
	BbsRepository bbsRepository;
	
	@GetMapping(value = "/")
	public String index(
	        Model model,
	        @ModelAttribute PostsForm form,
	        @RequestParam(name = "page", defaultValue = "0") Integer page
			) {
		try {
		    // リクエストパラメータを基にPageableを作成
		    Pageable pageable = Pageable.ofSize(10).withPage(page);
			// 現在の日付から3年目を取得
		    LocalDateTime threeYearsAgo = LocalDateTime.now().minusYears(3);
		    
		    Page<Posts> listPage = postsRepository.findByCreateDateAfterOrderByCreateDateDesc(threeYearsAgo, pageable);
		    List<Posts> posts = listPage.getContent();
		    
			// tbl_postsの総レコード数を取得して、最終ページを算出する
			long totalCount = Math.toIntExact(listPage.getTotalElements());
			int lastPage = (int) Math.floor((double) (totalCount -1) / 10);
			
			// 過去投稿が0件の場合、リンクを非表示にする
			boolean postHistoryBlank = false;
			List<Posts> historyPosts = postsRepository.findByCreateDateBeforeOrderByCreateDateDesc(threeYearsAgo); // 過去3年以降の投稿を取得
			int historyCount = historyPosts.size(); // 過去3年以降の投稿件数を取得
			if (historyCount == 0) {
				postHistoryBlank = true;
			}
			
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
			model.addAttribute("postHistoryBlank", postHistoryBlank); // 過去投稿へのリンクの非表示設定
			
			return "index";
		} catch (Exception e) {
			return "redirect:/error_exception";
		}
	}

	@GetMapping(value = "/post_history")
	public String postHistory(
	        Model model,
	        @ModelAttribute PostsForm form,
	        @RequestParam(name = "page", defaultValue = "0") Integer page
			) {
	    try {
			// リクエストパラメータを基にPageableを作成
		    Pageable pageable = Pageable.ofSize(10).withPage(page);
			// 現在の日付から3年目を取得
		    LocalDateTime threeYearsAgo = LocalDateTime.now().minusYears(3);
			
		    Page<Posts> listPage = postsRepository.findByCreateDateBeforeOrderByCreateDateDesc(threeYearsAgo, pageable);
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
			
			return "post_history";
		} catch (Exception e) {
			return "redirect:/error_exception";
		}
	}
	
	@GetMapping(value = "/error")
	public String error() {
		return "error";
	}
	
	@GetMapping(value = "/error_exception")
	public String errorException() {
		return "error_exception";
	}
}
