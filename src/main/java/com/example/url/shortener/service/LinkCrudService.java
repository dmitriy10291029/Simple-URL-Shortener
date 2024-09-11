package com.example.url.shortener.service;

import com.example.url.shortener.exception.LinkNotFoundException;
import com.example.url.shortener.exception.ValidationFailedException;
import com.example.url.shortener.model.Link;
import com.example.url.shortener.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LinkCrudService {
    private final LinkRepository linkRepository;
    private final CodeGenerator codeGenerator;
    private final UrlValidator urlValidator;

    @Autowired
    public LinkCrudService(LinkRepository linkRepository, CodeGenerator codeGenerator, UrlValidator urlValidator) {
        this.linkRepository = linkRepository;
        this.codeGenerator = codeGenerator;
        this.urlValidator = urlValidator;
    }

    public Link createLink(@NonNull String longUrl) {
        var link0 = linkRepository.findByLongUrl(longUrl);

        if (link0.isPresent()) {
            Link link = link0.get();

            try {
                urlValidator.validate(longUrl);
                link.setCreationDate(new Date());
                return updateLink(link);

            } catch (ValidationFailedException e) {
                deleteLinkById(link.getId());
                throw e;
            }
        }

        urlValidator.validate(longUrl);
        return linkRepository.save(Link.builder()
                .creationDate(new Date())
                .longUrl(longUrl)
                .code(codeGenerator.getCode())
                .usageCount(0)
                .build());
    }

    public Link getLink(@NonNull String code) {
        var link0 = linkRepository.findByCode(code);
        if (link0.isEmpty()) {
            throw new LinkNotFoundException(code);
        }

        return link0.get();
    }

    public Link updateLink(@NonNull Link updatedLink) {
        if (linkRepository.findById(updatedLink.getId()).isEmpty()) {
            throw new LinkNotFoundException(updatedLink.getCode());
        }

        return linkRepository.save(updatedLink);
    }

    public void deleteLinkById(@NonNull Long id) {
        linkRepository.deleteById(id);
    }
}
