package poc.springboot.multipleds.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poc.springboot.multipleds.post.Post;
import poc.springboot.multipleds.post.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController
{
	private final PostService postService;

	public PostController(PostService postService)
	{
		this.postService = postService;
	}

	@GetMapping("/all")
	public List<Post> findAllPosts()
	{
		return postService.findAll();
	}

	@GetMapping("/{id}")
	public Post findPostById(@PathVariable String id)
	{
		return postService.findById(id).orElseThrow(() -> new RuntimeException("post id not valid"));
	}

	@PostMapping()
	public Post addPost(@RequestBody Post post)
	{
		return postService.create(post) == 1 ? post : null;
	}

	@PutMapping("/{id}")
	public Post updatePost(@RequestBody Post post, @PathVariable String id)
	{
		return postService.update(post, id) == 1 ? post : null;
	}

	@DeleteMapping("/{id}")
	public String deletePost(@PathVariable String id)
	{
		return postService.delete(id) == 1 ? "post deeleted successfully" : "deletion failed, check the id once again";
	}

}
