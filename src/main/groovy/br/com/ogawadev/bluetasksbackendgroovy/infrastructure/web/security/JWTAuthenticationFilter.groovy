package br.com.ogawadev.bluetasksbackendgroovy.infrastructure.web.security

import br.com.ogawadev.bluetasksbackendgroovy.domain.user.AppUser
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private AuthenticationManager authenticationManager

    JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager
    }

    @Override
    Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            ObjectMapper mapper = new ObjectMapper()
            AppUser appUser = mapper.readValue(request.getInputStream(), AppUser)

            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(appUser.username, appUser.password)
            return authenticationManager.authenticate(upat)
        }catch(IOException e) {
            throw new RuntimeException(e)
        }

    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal()

        String jwtToken = Jwts.builder()
            .setSubject(userDetails.username)
            .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
            .claim("displayName", userDetails.displayName)
            .signWith(SignatureAlgorithm.HS512,SecurityConstants.SECRET_KEY)
            .compact()

        response.addHeader(SecurityConstants.AUTHORIZATION_HEADER, SecurityConstants.TOKEN_PREFIX + jwtToken )
        
    }
}
