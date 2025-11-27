package org.centrale.hceres.controller;

import org.centrale.hceres.items.PostDoc;
import org.centrale.hceres.service.PostDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostDocController {

    @Autowired
    private PostDocService postDocService;

    @GetMapping("Api/PostDocs")
    public Iterable<PostDoc> getPostDocs() {
        return postDocService.getPostDocs();
    }


    @GetMapping("Api/PostDocs/{id}")
    public PostDoc getPostDoc(@PathVariable("id") final Integer id) {
        Optional<PostDoc> postDoc = postDocService.getPostDoc(id);
        if(postDoc.isPresent()) {
            return postDoc.get();
        } else {
            return null;
        }
    }

    @PostMapping(value = "Api/AddPostDoc")
    public PostDoc createPostDoc(@RequestBody Map<String, Object> request) {
        return postDocService.savePostDoc(request);
    }

    @DeleteMapping("Api/DeletPostDoc/{id}")
    public void deletePostDoc(@PathVariable("id") final Integer id) {
        postDocService.deletePostDoc(id);
    }

}
