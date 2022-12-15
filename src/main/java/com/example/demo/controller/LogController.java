package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.LoginUserForm;


/**
 * ロギング処理のためのコントローラー.
 * 
 * @author kenjisuzuki
 *
 */
@Controller
@RequestMapping("/log")
public class LogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

	/**
	 * ロガーに含まれる各ログレベルのメソッドを呼ぶ.
	 * 
	 * @return ログ出力完了画面
	 */
	@GetMapping("/loglevel")
	public String logLevel(LoginUserForm loginUserForm) {
		LOGGER.error("エラーが発生しました");
		LOGGER.warn("警告です");
		LOGGER.info("情報です");
		LOGGER.debug("デバッグ情報です");
		LOGGER.trace("細かいトレース情報です");
		// どこに返していいか不明なため一旦ログインhtmlに返します。
		return "login";
	}
}
