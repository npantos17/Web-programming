package com.example.backend.services;

import com.example.backend.entities.tag.Tag;
import com.example.backend.repository.TagRepository;

import javax.inject.Inject;
import java.util.List;

public class TagService {

    @Inject
    private TagRepository tagRepository;

    public List<Tag> allTags() {
        return tagRepository.allTags();
    }

    public Tag addTag(Tag tag) {
        return tagRepository.addTag(tag);
    }

    public void addTagsArticles(Integer tagId, Integer articleId){
        tagRepository.addTagsArticles(tagId, articleId);
    }

    public List<Tag> tagsFromArticle(Integer id) {
        return tagRepository.tagsFromArticle(id);
    }

}
