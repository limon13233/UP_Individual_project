package com.example.UP_PR2.Controllers;

import com.example.UP_PR2.Models.Appliances;
import com.example.UP_PR2.Models.Office;
import com.example.UP_PR2.Models.Post;
import com.example.UP_PR2.Models.Provider;
import com.example.UP_PR2.Repositories.AppliancesRepository;
import com.example.UP_PR2.Repositories.PostRepository;
import com.example.UP_PR2.Repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/ADD/Post")
    public String PostAdd(Post post,Model model)
    {
        return ("post/PostADD");
    }

    @PostMapping("/ADD/Post")
    public String PostAdd(@Valid Post post,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            return ("post/PostADD");}
        postRepository.save(post);
        return ("redirect:/ADD");
    }
    @GetMapping("/Delete/post/{id}")
    public String PostDelete(@PathVariable long id) {

        postRepository.deleteById(id);
        return("redirect:/Delete/user");
    }
    @GetMapping("/Edit/post/{id}")
    public String IssueEdit(Model model, @PathVariable long id) {

        Post post = postRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return("post/post-edit");

    }
    @PostMapping("/Edit/post/{id}")
    public String IssueEdit(@Valid Post post,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ("post/post-edit");}
        postRepository.save(post);
        return ("redirect:/Edit/post");
    }
}
