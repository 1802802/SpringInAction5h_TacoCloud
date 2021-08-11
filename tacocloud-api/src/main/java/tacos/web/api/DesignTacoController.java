package tacos.web.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Taco;
import tacos.data.TacoRepository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins="*")
public class DesignTacoController {
  private final TacoRepository tacoRepo;

  @Autowired
  EntityLinks entityLinks;

  public DesignTacoController(TacoRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
  }

  @GetMapping("/recent")
  public Iterable<Taco> recentTacos() {
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    return tacoRepo.findAll(page).getContent();
  }

  @GetMapping("/recenth")
  public Resources<TacoResource> recentTacosH() {
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    List<Taco> tacos = tacoRepo.findAll(page).getContent();

    List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
    Resources<TacoResource> recentResources = new Resources<>(tacoResources);
    recentResources.add(linkTo(methodOn(DesignTacoController.class).recentTacosH()).withRel("recents"));
    return recentResources;
  }

  @GetMapping("/recenth2")
  public Resources<Resource<Taco>> recentTacosH2() {
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    List<Taco> tacos = tacoRepo.findAll(page).getContent();

    Resources<Resource<Taco>> recentResources = Resources.wrap(tacos);
    recentResources.add(linkTo(methodOn(DesignTacoController.class).recentTacosH2()).withRel("recents"));
    return recentResources;
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepo.save(taco);
  }

  @GetMapping("/{id}")
  public Taco tacoById(@PathVariable("id") Long id) {
    Optional<Taco> optTaco = tacoRepo.findById(id);
    return optTaco.orElse(null);
  }

  @GetMapping("/2_{id}")
  public ResponseEntity<Taco> tacoById2(@PathVariable("id") Long id) {
    Optional<Taco> optTaco = tacoRepo.findById(id);
    return optTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
  }

}

