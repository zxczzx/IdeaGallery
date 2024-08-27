package migatotech.ideagallery.idea;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import migatotech.ideagallery.session.Session;
import migatotech.ideagallery.session.SessionHelper;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Operation(summary = "Get idea by id", security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping("/{ideaId}")
    public Idea getIdea(@PathVariable Long ideaId,
                        @RequestParam(name = "resolution", required = false) String resolution) {
        return ideaService.getIdea(ideaId, resolution, SessionHelper.retrieveOptionalAccountId(session));
    }

    @Operation(summary = "Get all ideas", security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping("/all")
    public List<Idea> getAllIdeas(@RequestParam(name = "categories", required = false) List<String> categories,
                                  @RequestParam(name = "resolution", required = false) String resolution,
                                  Pageable pageable) {
        return ideaService.getAllIdeas(categories, resolution, SessionHelper.retrieveOptionalAccountId(session), pageable);
    }

    @Operation(summary = "Get my profile ideas", security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping("/myprofile")
    public List<Idea> getMyIdeas(@RequestParam(name = "resolution", required = false) String resolution,
                                 Pageable pageable) {
        return ideaService.getMyIdeas(resolution, SessionHelper.retrieveMandatoryAccountId(session), pageable);
    }

    @Operation(summary = "Create a new idea", security = { @SecurityRequirement(name = "bearer-key") })
    @PostMapping
    public void createIdea(@RequestBody NewIdea newIdea) {
        ideaService.createIdea(newIdea, SessionHelper.retrieveMandatoryAccountId(session));
    }
}
