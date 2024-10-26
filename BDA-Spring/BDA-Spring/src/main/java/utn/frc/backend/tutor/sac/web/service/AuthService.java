package utn.frc.backend.tutor.sac.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.frc.backend.tutor.sac.auth.AuthManager;
import utn.frc.backend.tutor.sac.auth.AuthorizationException;
import utn.frc.backend.tutor.sac.web.api.dto.AlumnoDTO;
import utn.frc.backend.tutor.sac.web.api.dto.MateriaDTO;
import utn.frc.backend.tutor.sac.web.api.dto.PersonaDTO;

import java.util.List;
import java.util.Map;

@Service
public class AuthService {
    AuthManager authManager;
    private PersonasService personasService;
    private AlumnosService alumnosService;
    private MateriasService materiasService;

    @Autowired
    public AuthService(
            AuthManager authManager,
            PersonasService personasService, AlumnosService alumnosService, MateriasService materiasService
    ) {
        this.authManager = authManager;
        this.personasService = personasService;
        this.alumnosService = alumnosService;
        this.materiasService = materiasService;
    }

    public String login(String usr, String pwd) {
        return authManager.login(usr, pwd);
    }

    public String login(Map<String, String> header) {
        return authManager.login(header);
    }

    public List<PersonaDTO> findPersonas(Map<String, String> header) {
        if (!authManager.verifyPermission(header, AuthManager.Permission.ListarPersonas)) {
            throw new AuthorizationException("No permitido");
        }
        return personasService.findAll();
    }

    public List<AlumnoDTO> findAlumnos(Map<String, String> header) {
        Map<AuthManager.Permission, Boolean> permissions = authManager.getPermissions(header);
        if (!permissions.get(AuthManager.Permission.ListarAlumnos)) {
            throw new AuthorizationException("No permitido");
        }
        return alumnosService.findAll();
    }

    public List<MateriaDTO> findMaterias(Map<String, String> header) {
        Map<String, Object> claimsMap = authManager.getClaimsMap(header);
        String role = (String) claimsMap.get("role");
        Map<AuthManager.Permission, Boolean> permissions = authManager.getPermissions(role);
        if (!permissions.get(AuthManager.Permission.ListarMaterias)) {
            throw new AuthorizationException("No permitido");
        }

        return materiasService.findAll();
    }

}

