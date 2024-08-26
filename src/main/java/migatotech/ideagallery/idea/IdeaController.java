package migatotech.ideagallery.idea;

import lombok.RequiredArgsConstructor;
import migatotech.ideagallery.session.Session;
import migatotech.ideagallery.session.SessionHelper;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/idea")
@RequiredArgsConstructor
public class IdeaController {
    private final IdeaService ideaService;
    private final Session session;

    @GetMapping("/{ideaId}")
    public Idea getIdea(@PathVariable long ideaId,
                        @RequestParam(name = "resolution", required = false) String resolution) {
        return ideaService.getIdea(ideaId, resolution, SessionHelper.retrieveMandatoryAccountId(session));
    }

    @GetMapping
    public List<Idea> getAllIdeas(@RequestParam(name = "categories", required = false) List<String> categories,
                                  @RequestParam(name = "resolution", required = false) String resolution,
                                  Pageable pageable) {
        return ideaService.getAllIdeas(categories, resolution, SessionHelper.retrieveMandatoryAccountId(session), pageable);
    }

    @GetMapping("/myprofile")
    public List<Idea> getMyIdeas(@RequestParam(name = "resolution", required = false) String resolution,
                                 Pageable pageable) {
        return ideaService.getMyIdeas(resolution, SessionHelper.retrieveMandatoryAccountId(session), pageable);
    }

    @PostMapping
    public void createIdea(NewIdea newIdea) {
        ideaService.createIdea(newIdea, SessionHelper.retrieveMandatoryAccountId(session));
    }
}
