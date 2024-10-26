package utn.frc.backend.tutor.sac.web.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.tutor.sac.web.api.dto.PersonaDTO;
import utn.frc.backend.tutor.sac.web.api.exception.APIErrorEntity;
import utn.frc.backend.tutor.sac.web.api.exception.ResourceNotFoundException;
import utn.frc.backend.tutor.sac.web.service.PersonasService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Validated // ver post all
@RestController
@RequestMapping("/personas")
public class PersonasAPI {

    private static final String EXCEPTION_HANDLED_BY = "PersonasAPI";

    private PersonasService service;

    @Autowired
    public PersonasAPI(PersonasService service) {
        this.service = service;
    }

    @GetMapping
    //public ResponseEntity<List<PersonaDTO>> findPersonas(JwtAuthenticationToken auth) {
    public ResponseEntity<List<PersonaDTO>> findPersonas() {
        //var tkn = auth.getToken();

        List<PersonaDTO> personas = service.findAll();

        return personas.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(personas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> findPersona(@PathVariable Integer id) {
        Optional<PersonaDTO> personaDTO = service.findById(id);

        //if (personaDTO.isEmpty()) {
        //    throw new ResourceNotFoundException("Not foundddd");
        //}

        return personaDTO.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(personaDTO.get());
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> addPersona(@RequestBody @Valid PersonaDTO persona) {
        return new ResponseEntity<>(service.add(persona), HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<List<PersonaDTO>> addPersonas(
            // ver @Validated
            @NotNull(message = "Lista NO deber ser nula")
            @NotEmpty(message = "Lista NO deber ser vacía")
            @RequestBody
            List<@Valid PersonaDTO> personas
    ) {
        return new ResponseEntity<>(service.addAll(personas), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> updatePersona(
            @PathVariable Integer id,
            @RequestBody @Valid PersonaDTO persona
    ) {
        return new ResponseEntity<>(service.update(id, persona), HttpStatus.ACCEPTED);
    }

    // todo: implementamos patch (actualización parcial)?
    //@PatchMapping
    //...

    @DeleteMapping("/{id}")
    public ResponseEntity deletePersona(@PathVariable Integer id) {
        return service.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    //@ExceptionHandler(ResourceNotFoundException.class)
    //public ResponseEntity handleResourceNotFound(
    //        ResourceNotFoundException e,
    //        HttpServletRequest request
    //) {
    //
    //    String error = e.getMessage();
    //
    //    APIErrorEntity apiError = new APIErrorEntity(
    //            EXCEPTION_HANDLED_BY,
    //            "(Custom)ResourceNotFoundException (@ExceptionHandler)",
    //            null,
    //            HttpStatus.NOT_FOUND,
    //            request.getRequestURI(),
    //            e.getLocalizedMessage(),
    //            error
    //    );
    //
    //    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    //}
    //
    //@ExceptionHandler(BadRequestException.class)
    //public ResponseEntity handleBadRequestException(
    //        BadRequestException e,
    //        HttpServletRequest request
    //) {
    //
    //    String error = e.getMessage();
    //
    //    APIErrorEntity apiError = new APIErrorEntity(
    //            EXCEPTION_HANDLED_BY,
    //            "(Custom)BadRequestException (@ExceptionHandler)",
    //            null,
    //            HttpStatus.BAD_REQUEST,
    //            request.getRequestURI(),
    //            e.getLocalizedMessage(),
    //            error
    //    );
    //
    //    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    //}
    //
    //@ExceptionHandler(ConstraintViolationException.class)
    //public ResponseEntity<Object> handleConstraintViolation(
    //        ConstraintViolationException ex,
    //        HttpServletRequest request
    //) {
    //
    //    Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
    //    List<String> errors = new ArrayList<String>();
    //
    //    // for simple
    //    for (ConstraintViolation<?> violation : violations) {
    //        errors.add(
    //                violation.getRootBeanClass().getName() + " " +
    //                        violation.getPropertyPath() + ": " + violation.getMessage()
    //        );
    //    }
    //
    //    APIErrorEntity apiError = new APIErrorEntity(
    //            EXCEPTION_HANDLED_BY,
    //            "ConstraintViolationException (@ExceptionHandler)",
    //            null,
    //            HttpStatus.BAD_REQUEST,
    //            request.getRequestURI(),
    //            ex.getLocalizedMessage(),
    //            errors
    //    );
    //
    //    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    //}
    //
    //@ExceptionHandler({ HttpMessageNotReadableException.class })
    //public ResponseEntity<Object> handleHttpMessageNotReadable(
    //        HttpMessageNotReadableException e,
    //        HttpServletRequest request
    //) {
    //    String error = "Request body inexistente o mal formado";
    //
    //    APIErrorEntity apiError = new APIErrorEntity(
    //            EXCEPTION_HANDLED_BY,
    //            "HttpMessageNotReadableException (@ExceptionHandler)",
    //            null,
    //            HttpStatus.BAD_REQUEST,
    //            request.getRequestURI(),
    //            e.getLocalizedMessage(),
    //            error
    //    );
    //
    //    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    //}
}
