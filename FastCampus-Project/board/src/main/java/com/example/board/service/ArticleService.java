package com.example.board.service;

import com.example.board.Dto.ArticleDto;
import com.example.board.Dto.ArticleWithCommentsDto;
import com.example.board.domain.Article;
import com.example.board.domain.type.SearchType;
import com.example.board.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String search_keyword, Pageable pageable) {
        if(search_keyword == null || search_keyword.isBlank()){
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        return switch (searchType){
            case TITLE -> articleRepository.findByTitleContaining(search_keyword,pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(search_keyword,pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(search_keyword,pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(search_keyword,pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#"+search_keyword,pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    public void saveArticle(ArticleDto Dto) {
        articleRepository.save(Dto.toEntity());
    }

    public void updateArticle(ArticleDto articleDto) {
        try{
            Article article = articleRepository.getReferenceById(articleDto.id());
            article.setHashtag(articleDto.hashtag());
            if (articleDto.title() != null ) { article.setTitle(articleDto.title()); }
            if ( articleDto.content() != null ) { article.setContent(articleDto.content()); }
        } catch (EntityNotFoundException e){
            log.warn("게시글 업데이트 실패 게시글을 찾을 수 없습니다 - dto : {}",articleDto);
        }
    }

    public void deleteArticle(long Id) {
        articleRepository.deleteById(Id);
    }

}
