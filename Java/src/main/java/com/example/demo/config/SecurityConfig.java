//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * @program: Java
// * @author: HarryGao
// * @create: 2022-08-09 01:15
// */
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").roles("admin").password("$2a$10$I59e.CgJwQphaDRHoCREVeZjPwfdomlpYW3iWPHJQ.rkhOTZEBfDm")
//                .and()
//                .withUser("user").roles("user").password("$2a$10$I59e.CgJwQphaDRHoCREVeZjPwfdomlpYW3iWPHJQ.rkhOTZEBfDm");
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    public static void main(String[] args) {
//        String gyr = new BCryptPasswordEncoder().encode("gyr");
//        System.out.println(gyr);
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception{
//        super.configure(web);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests() //开启登录认证
//                .antMatchers("http://localhost:8081/register").hasRole("admin") //访问接口需要admin的角色
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated() // 其他所有的请求 只需要登录即可
//                .and().formLogin()
//                .loginPage("/login") //自定义的登录页面
//                .loginProcessingUrl("/login") //登录处理接口
//                .usernameParameter("username") //定义登录时的用户名的key 默认为username
//                .passwordParameter("password") //定义登录时的密码key，默认是password
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = httpServletResponse.getWriter();
//                        out.write("success");
//                        out.flush();
//                    }
//                }) //登录成功处理器
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = httpServletResponse.getWriter();
//                        out.write("fail");
//                        out.flush();
//                    }
//                }) //登录失败处理器
//                .permitAll() //通过 不拦截，更加前面配的路径决定，这是指和登录表单相关的接口 都通过
//                .and().logout() //退出登录配置
//                .logoutUrl("/logout") //退出登录接口
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = httpServletResponse.getWriter();
//                        out.write("logout success");
//                        out.flush();
//                    }
//                }) //退出登录成功 处理器
//                .permitAll() //退出登录的接口放行
//                .and()
//                .httpBasic()
//                .and()
//                .csrf().disable(); //csrf关闭 如果自定义登录 需要关闭
//
//    }
//
//
//}
