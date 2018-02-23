package br.com.newgaragem.domain.carro;

import br.com.newgaragem.core.controller.ResponseAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carro")
public class CarroController extends ResponseAbstractController {

    @Autowired
    CarroService carroService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return jsonResponse(carroService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        return jsonResponse(carroService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Validated @RequestBody Carro estado) {
        return jsonResponse(carroService.save(estado));
    }

    @PutMapping
    public ResponseEntity<?> update(@Validated @RequestBody Carro estado) {
        return jsonResponse(carroService.save(estado));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        carroService.delete(id);
        return jsonResponse(null);
    }

}
