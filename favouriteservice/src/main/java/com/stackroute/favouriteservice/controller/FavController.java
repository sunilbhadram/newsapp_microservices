package com.stackroute.favouriteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.model.Article;
import com.stackroute.favouriteservice.model.ArticleMessage;
import com.stackroute.favouriteservice.service.FavArticleService;
import com.stackroute.favouriteservice.service.MessageProducer;

@RestController
@RequestMapping("favservice/")
public class FavController {

	private ResponseEntity<?> responseEntity;
	private FavArticleService articleService;
	private MessageProducer messageProducer;

	@Autowired
	public FavController(FavArticleService articleService, MessageProducer messageProducer) {
		this.articleService = articleService;
		this.messageProducer = messageProducer;
	}

	@PostMapping("addtofav/{username}")
	public ResponseEntity<?> addToFav(@RequestBody Article article, @PathVariable("username") String username)
			throws Exception {

		ArticleMessage message = null;
		try {
			boolean favAdded = this.articleService.addToFavArticle(article, username);
			message = this.articleService.createArticleMessage(article, username,"ADD");
			this.messageProducer.sendMessage(message);
			if (favAdded) {
				responseEntity = new ResponseEntity<>(article, HttpStatus.CREATED);
			} else {
				responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("Error !! Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@GetMapping("listfav/{userid}")
	public ResponseEntity<?> listFavourites(@PathVariable("userid") String userId) throws Exception {
		try {
			System.out.println("in listFavourites" + userId);
			List<Article> articles = this.articleService.getAllArticleByUserId(userId);
			if (articles != null && articles.size() > 0) {
				responseEntity = new ResponseEntity<>(articles, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>("No Fav Items", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {

			responseEntity = new ResponseEntity<>("Error !! Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

	@PostMapping("delfav/{username}")
	public ResponseEntity<?> deleteFavourite(@RequestBody Article article,@PathVariable("username") String username) throws Exception {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ delete method");
		ArticleMessage message = null;
		try {
			this.articleService.deleteFavourite(username, article.getTitle());
			message = this.articleService.createArticleMessage(article, username,"DELETE");
			this.messageProducer.sendMessage(message);
			responseEntity = new ResponseEntity<>("Article with Id:" + article.getTitle() + " is successfully Deleted",
					HttpStatus.OK);

		} catch (Exception exception) {
			exception.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		return responseEntity;
	}
}
