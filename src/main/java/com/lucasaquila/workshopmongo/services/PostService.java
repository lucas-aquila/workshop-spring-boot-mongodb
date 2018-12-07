package com.lucasaquila.workshopmongo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasaquila.workshopmongo.domain.Post;
import com.lucasaquila.workshopmongo.repository.PostRepository;
import com.lucasaquila.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	
	public Post findById(String id) {
		Post post = postRepository.findById(id).get();
		if(post == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return post;
	}
	
	public List<Post> findByTitle(String title) {
		return postRepository.findByTitle(title);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		//Para ele pegar o próximo dia, porque a data vem a meia noite então não pega os registros daquele dia
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return postRepository.fullSearch(text, minDate, maxDate);
	}

}
