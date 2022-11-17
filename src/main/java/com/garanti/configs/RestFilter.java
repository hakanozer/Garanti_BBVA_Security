package com.garanti.configs;

import com.garanti.entities.Info;
import com.garanti.repositories.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class RestFilter implements Filter {

    final InfoRepository iRepo;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String roles = parseRoles(auth.getAuthorities());

        if ( !url.contains("h2-console") ) {
            Info i = new Info();
            i.setUserName(auth.getName());
            i.setUrl(url);
            i.setRoles(roles);
            i.setDate(new Date());
            i.setSessionID(req.getSession().getId());
            iRepo.save(i);
        }


        filterChain.doFilter(req, res);
    }

    public String parseRoles(Collection<? extends GrantedAuthority> authorities) {
        StringBuilder builder = new StringBuilder();
        authorities.forEach( item -> {
            builder.append(item.getAuthority());
            builder.append(",");
        } );
        return builder.toString();
    }

}
