package utn.frc.backend.tutor.sac.cfg;

import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import utn.frc.backend.tutor.sac.auth.AuthManager;
import utn.frc.backend.tutor.sac.auth.RsaTokenManager;
import utn.frc.backend.tutor.sac.auth.TokenManager;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//@EnableWebFluxSecurity
public class Cfg {

    // Jose ====================================================================
    @Bean
    public TokenManager tokenManager(
            @Value("${token.kid:keyId}") String kid,
            @Value("${token.ttl:3600}") long ttl
    ) throws JoseException {
        return new RsaTokenManager(kid, ttl);
    }

    @Bean
    public AuthManager authManager(@Autowired TokenManager tokenManager) {
        return new AuthManager(tokenManager);
    }


    // Spring Security =========================================================
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/cursos/**")
                .permitAll()

                .requestMatchers("/personas/**")
                //.hasRole("admin")
                .permitAll()

                .requestMatchers("/alumnos/**")
                .hasAnyAuthority("admin", "docente")
                //.hasAnyRole("admin", "docente")

                .requestMatchers("/docentes/**")
                .hasAnyRole("admin", "docente", "alumno")

                .requestMatchers(HttpMethod.GET, "/materias/**")
                .hasAnyRole("admin", "docente", "alumno")

                .requestMatchers(HttpMethod.POST, "/materias/**")
                .hasAnyRole("admin")

                .anyRequest()
                .authenticated()

        ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
        .csrf(csrf -> csrf.disable());
        return http.build();
    }


    interface AuthoritiesConverter extends Converter<Map<String, Object>, Collection<GrantedAuthority>> {}

    @Bean
    AuthoritiesConverter realmRolesAuthoritiesConverter() {
        return claims -> {
            var realmAccess = Optional.ofNullable((Map<String, Object>) claims.get("realm_access"));
            var roles = realmAccess.flatMap(map -> Optional.ofNullable((List<String>) map.get("roles")));
            return roles.map(List::stream)
                    .orElse(Stream.empty())
                    .map(SimpleGrantedAuthority::new)
                    .map(GrantedAuthority.class::cast)
                    .toList();
        };
    }

    @Bean
    JwtAuthenticationConverter authenticationConverter(
            Converter<Map<String, Object>, Collection<GrantedAuthority>> authoritiesConverter) {
        var authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            return authoritiesConverter.convert(jwt.getClaims());
        });
        return authenticationConverter;
    }

    //@Bean
    //public JwtAuthenticationConverter jwtAuthenticationConverter() {
    //    var jwtAuthenticationConverter = new JwtAuthenticationConverter();
    //    var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    //
    //    // Se especifica el nombre del claim a analizar
    //    grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
    //    // Se agrega este prefijo en la conversión por una convención de Spring
    //    grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
    //
    //    // Se asocia el conversor de Authorities al Bean que convierte el token JWT a un objeto Authorization
    //    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
    //    // También se puede cambiar el claim que corresponde al nombre que luego se utilizará en el objeto
    //    // Authorization
    //    // jwtAuthenticationConverter.setPrincipalClaimName("user_name");
    //
    //    return jwtAuthenticationConverter;
    //}

}
