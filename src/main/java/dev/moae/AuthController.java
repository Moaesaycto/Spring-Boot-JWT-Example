package dev.moae;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final JwtEncoder encoder;
  private final PasswordEncoder pe;
  private final long expiryMinutes;
  private final String demoHash; // bcrypt of "pass"

  public record LoginRequest(String username, String password) {}

  public AuthController(
      JwtEncoder enc, PasswordEncoder pe, @Value("${app.jwt.expiry-minutes}") long exp) {
    this.encoder = enc;
    this.pe = pe;
    this.expiryMinutes = exp;
    this.demoHash = pe.encode("pass");
  }

  @PostMapping("/login")
  public Map<String, String> login(@RequestBody LoginRequest req) {
    String demoUser = "moae";
    if (!demoUser.equals(req.username()) || !pe.matches(req.password(), demoHash))
      throw new org.springframework.web.server.ResponseStatusException(
          org.springframework.http.HttpStatus.UNAUTHORIZED, "Bad credentials");

    var now = Instant.now();
    var claims =
        JwtClaimsSet.builder()
            .issuer("hello-web")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiryMinutes * 60))
            .subject(req.username()) // <= This is where the user identifier is stored
            .claim("scope", "api.read api.write")
            .build();

    var header = JwsHeader.with(MacAlgorithm.HS256).build();
    String token = encoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    return Map.of("access_token", token, "token_type", "Bearer");
  }

  @GetMapping("/me")
  public Map<String, Object> me(@AuthenticationPrincipal Jwt jwt) {
    String userId = jwt.getSubject(); // <= Retrieving who is calling
    return Map.of("userId", userId, "scopes", jwt.getClaimAsString("scope"));
  }
}
