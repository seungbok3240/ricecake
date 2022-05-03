package toyproject.dcricecake;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import toyproject.dcricecake.interceptor.CustomerLoginInterceptor;
import toyproject.dcricecake.interceptor.SellerLoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // seller
        registry.addInterceptor(new SellerLoginInterceptor())
                .order(1)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/*.ico", "/error", "/admin/login", "/admin/logout", "/admin/new");

        // customer
        registry.addInterceptor(new CustomerLoginInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/**", "/*.ico", "/error", "/login", "/logout", "/new", "/");
    }
}
