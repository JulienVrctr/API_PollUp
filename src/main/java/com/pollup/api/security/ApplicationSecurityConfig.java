package com.pollup.api.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/add_reward", "/login/{mail}/{password}", "/add_comment", "/delete_poll/{idartist}/{date}", "/artist_avatar/{idartist}", "/update_artist/{idartist}", "/artist_by_featuring/{idmusic}", "/featurings/{idartist}", "/add_featuring", "/add_artist", "/update_my_listening/{idartist}/{idmusic}", "/music_data/{idmusic}", "/music/{idmusic}", "/polls/{startweek}/{endweek}/{idartist}/{style}", "/add_problem", "/add_poll/{idartist}", "/polls", "/music_styles","/projectsbytitle/**","/artistsbyname/**", "/most_polls/{startDate}/{endDate}", "/musicsbytitle/**","/delete_my_like/{idartist}/{idmusic}","/add_my_likes", "/project_cover/**", "/sign-in/**", "/artists", "/musics", "/add_music", "/projects", "/project/{projectId}", "/artist/{artistId}", "/musicOfWeek/{startWeek}/{endWeek}", "/login", "/login/**", "/my_likes_by_idartist/{idartist}", "/my_likes", "/add_project").permitAll()
                .antMatchers("/comments").hasAuthority("ARTIST")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .sessionManagement()
                .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
                .sessionFixation().none();
    }

    private SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.daoAuthenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(userService);

        return provider;
    }
}
