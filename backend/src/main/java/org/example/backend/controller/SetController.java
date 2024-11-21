package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.Set;
import org.example.backend.service.SetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/set")
@RequiredArgsConstructor
public class SetController {
    private final SetService setService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{setId}")
    public Set getSetById(@PathVariable String setId) {
    return setService.getSetById(setId);
}
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Set> getSet() {
        return setService.getSet();
    }
@ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Set createSet(@RequestBody Set set) {
        return setService.createSet(set);
}

}
