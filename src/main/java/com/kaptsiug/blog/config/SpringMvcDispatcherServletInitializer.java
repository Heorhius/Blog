package com.kaptsiug.blog.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{PersistenceJPAConfig.class, RedisConfiguration.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class, PersistenceJPAConfig.class, RedisConfiguration.class, SecurityConfig.class, JavaMailConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        // регистрируем конфигурацию созданую высше
//        ctx.register(SpringConfig.class);
//        // добавляем в контекст слушателя с нашей конфигурацией
//        servletContext.addListener(new ContextLoaderListener(ctx));
//
//        ctx.setServletContext(servletContext);
//
//        // настраиваем маппинг Dispatcher Servlet-а
//        ServletRegistration.Dynamic servlet =
//                servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctx));
//        servlet.addMapping("/");
//        servlet.setLoadOnStartup(1);
//    }
}
