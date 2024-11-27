package utn.frc.backend.tutor.sac.web.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.backend.tutor.sac.auth.AuthenticationException;
import utn.frc.backend.tutor.sac.auth.AuthorizationException;
import utn.frc.backend.tutor.sac.web.api.dto.AlumnoDTO;
import utn.frc.backend.tutor.sac.web.api.dto.MateriaDTO;
import utn.frc.backend.tutor.sac.web.api.dto.PersonaDTO;
import utn.frc.backend.tutor.sac.web.api.exception.APIErrorEntity;
import utn.frc.backend.tutor.sac.web.service.AuthService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthApi {

    private static final String EXCEPTION_HANDLED_BY = "AuthAPI";

    private AuthService service;

    @Autowired
    public AuthApi(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader Map<String, String> header) {
        return ResponseEntity.ok(service.login(header));
    }

    @GetMapping("/personas")
    public ResponseEntity<List<PersonaDTO>> findPersonas(@RequestHeader Map<String, String> header) {
        List<PersonaDTO> personas = service.findPersonas(header);
        return personas.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(personas);
    }

    @GetMapping("/alumnos")
    public ResponseEntity<List<AlumnoDTO>> findAlumnos(@RequestHeader Map<String, String> header) {
        List<AlumnoDTO> alumnos = service.findAlumnos(header);
        return alumnos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(alumnos);
    }

    @GetMapping("/materias")
    public ResponseEntity<List<MateriaDTO>> findMaterias(@RequestHeader Map<String, String> header) {
        List<MateriaDTO> materias = service.findMaterias(header);

        return materias.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(materias);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handleAuthenticationException(
            AuthenticationException e,
            HttpServletRequest request
    ) {

        String error = e.getMessage();

        APIErrorEntity apiError = new APIErrorEntity(
                EXCEPTION_HANDLED_BY,
                "(Custom)AuthenticationException (@ExceptionHandler)",
                null,
                HttpStatus.UNAUTHORIZED,
                request.getRequestURI(),
                e.getLocalizedMessage(),
                error
        );

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity handleAuthorizationException(
            AuthorizationException e,
            HttpServletRequest request
    ) {

        String error = e.getMessage();

        APIErrorEntity apiError = new APIErrorEntity(
                EXCEPTION_HANDLED_BY,
                "(Custom)AuthorizationException (@ExceptionHandler)",
                null,
                HttpStatus.UNAUTHORIZED,
                request.getRequestURI(),
                e.getLocalizedMessage(),
                error
        );

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
