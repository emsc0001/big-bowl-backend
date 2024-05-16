package bigbowl.security.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import bigbowl.security.error.CustomOAuth2AccessDeniedHandler;
import bigbowl.security.error.CustomOAuth2AuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)

@Configuration
public class SecurityConfig {

  @Value("${app.secret-key}")
  private String tokenSecret;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
    MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
    http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2ResourceServer((oauth2ResourceServer) ->
                    oauth2ResourceServer
                            .jwt((jwt) -> jwt.decoder(jwtDecoder())
                                    .jwtAuthenticationConverter(authenticationConverter())
                            )
                            .authenticationEntryPoint(new CustomOAuth2AuthenticationEntryPoint())
                            .accessDeniedHandler(new CustomOAuth2AccessDeniedHandler()));

    http.authorizeHttpRequests((authorize) -> authorize
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/api/auth/login")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/api/user-with-role")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/api/*")).permitAll()


            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/api/demo/anonymous")).permitAll()

            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/index.html")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/")).permitAll()

            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/swagger-ui/**")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/swagger-resources/**")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/v3/api-docs/**")).permitAll()

            .requestMatchers(mvcMatcherBuilder.pattern("/error")).permitAll()


            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/product")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/product/*")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/product")).hasAnyAuthority("ADMIN")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.PUT, "/product/*")).hasAnyAuthority("ADMIN")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/product/*")).hasAnyAuthority("ADMIN")



            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/BowlingLanes")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/BowlingLanes/*")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/BowlingLanes")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.PUT, "/BowlingLanes/*")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/BowlingLanes/*")).permitAll()


            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/DinnerTable")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/DinnerTable/*")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/DinnerTable")).hasAnyAuthority("ADMIN")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.PUT, "/DinnerTable/*")).hasAnyAuthority("ADMIN")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.PATCH, "/DinnerTable/*")).hasAnyAuthority("ADMIN")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/DinnerTable/*")).hasAnyAuthority("ADMIN")

            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/AirHockeyTables")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/AirHockeyTables/*")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/AirHockeyTables")).hasAnyAuthority("ADMIN")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.PUT, "/AirHockeyTables/*")).hasAnyAuthority("ADMIN")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/AirHockeyTables/*")).hasAnyAuthority("ADMIN")


            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/booking-activities")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/booking-activities/*")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/booking-activities")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.PUT, "/booking-activities/*")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/booking-activities/*")).hasAnyAuthority("ADMIN")

            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/bestilling")).permitAll()



            .requestMatchers(mvcMatcherBuilder.pattern("/**")).permitAll());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtAuthenticationConverter authenticationConverter() {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
    jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    return jwtAuthenticationConverter;
  }

  public SecretKey secretKey() {
    return new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256");
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withSecretKey(secretKey()).build();
  }

  @Bean
  public JwtEncoder jwtEncoder() {
    return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey()));
  }

}
