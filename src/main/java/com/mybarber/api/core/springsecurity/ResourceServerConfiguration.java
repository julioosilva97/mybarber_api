package com.mybarber.api.core.springsecurity;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@EnableResourceServer
@EnableConfigurationProperties(SecurityProperties.class)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String ROOT_PATTERN = "/**";

    private final SecurityProperties securityProperties;

    private TokenStore tokenStore;

    public ResourceServerConfiguration(final SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	 http
	        .authorizeRequests()
	        .antMatchers("/css/**", "/js/**", "/imagens/**","/pluginsAdd/**","/compiler/**","/img/**","/resources/**", "/webjars/**",
	        		"/api/usuarios/verificarUsuario/{login}",
	        		"/api/usuarios/verificarEmail/{email}",
	        		"/resetar-senha",
	        		"/registro",
	        		"/resources/template/AdminLTE**",
	        		"/funcionarios/verificarEmail/{email}",
	        		"/api/clientes/verificarEmail/{email}",
	        		"/api/usuarios/esqueceu-senha/{email}",
	        		"/api/usuarios/verificarUsuario/{login}",
	        		"/api/usuarios/buscar-token/{token}",
	        		"/api/usuarios/alterar-senha",
	        		"/login",
	        		"email/redefinir-senha**",
	        		"email/ativar-conta**",
	        		"/funcionarios/salvar-primeiro-funcionario",
	        		"/servicos",
                    "/funcionarios",
                    "/clientes",
                    "/agenda",
                    "/usuarios/reset",
                    "/",
                    "/api/agendamentos/listarFullCalendar/{idBarbeiro}").permitAll()
	        .antMatchers("/thymeleaf/").hasRole("LISTAR_SERVICO")
		    /*.antMatchers("/funcionarios/cadastrar").hasRole("ADMINISTRADOR")
	        .antMatchers("/servicos/listar").hasAnyRole("COMUM","ADMINISTRADOR")
	        .antMatchers("/funcionarios/listar").hasAnyRole("COMUM","ADMINISTRADOR")
	        .antMatchers("/servicos/editar/{id}").hasRole("ADMINISTRADOR")
	        .antMatchers("/funcionarios/editar/{id}").hasRole("ADMINISTRADOR")
	        .antMatchers("/servicos/editar").hasRole("ADMINISTRADOR")
	        .antMatchers("/funcionarios/editar").hasRole("ADMINISTRADOR")
	        .antMatchers("/funcionarios/excluir/{id}").hasRole("ADMINISTRADOR")*/
	        .anyRequest().authenticated(); // tira o token do json, depois ver isso
	        
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public DefaultTokenServices tokenServices(final TokenStore tokenStore) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        return tokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
        if (tokenStore == null) {
            tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        }
        return tokenStore;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(getPublicKeyAsString());
        return converter;
    }

    private String getPublicKeyAsString() {
        try {
            return IOUtils.toString(securityProperties.getJwt().getPublicKey().getInputStream(), UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
