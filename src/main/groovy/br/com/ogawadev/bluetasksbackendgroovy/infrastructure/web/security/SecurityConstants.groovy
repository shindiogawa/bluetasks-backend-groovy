package br.com.ogawadev.bluetasksbackendgroovy.infrastructure.web.security

class SecurityConstants {

    static final long EXPIRATION_TIME = 86400000 // 1 dia
    static final String SECRET_KEY = "tHeSeCrEtKeY"
    static final String AUTHORIZATION_HEADER = "Authorization"
    static final String TOKEN_PREFIX = "Bearer "
}
