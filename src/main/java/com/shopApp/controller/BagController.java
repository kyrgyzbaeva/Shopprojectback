package kg.edu.alatoo.online.shop.controller;

import kg.edu.alatoo.online.shop.model.User;
import kg.edu.alatoo.online.shop.dto.BagRequestDTO;
import kg.edu.alatoo.online.shop.service.BagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/items")
public class BagController {
    private final BagService bagService;

    public BagController(BagService bagService) {
        this.bagService = bagService;
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<?> create(@AuthenticationPrincipal User user,
                                                  @RequestBody @Valid BagRequestDTO bagRequestDTO) {
        return new ResponseEntity<>(bagService.create(bagRequestDTO, user), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,  @RequestBody @Valid BagRequestDTO bagRequestDTO,
                                                  @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(bagService.update(id, bagRequestDTO, user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('SELLER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return new ResponseEntity<>(bagService.get(id, user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('SELLER') or hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<?> getAll(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(bagService.getAll(user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SELLER') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        bagService.delete(id);
        return new ResponseEntity<>("Вещь успешно удалена", HttpStatus.OK);
    }
}
