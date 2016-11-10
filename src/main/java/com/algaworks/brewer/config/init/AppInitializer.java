package com.algaworks.brewer.config.init;

import com.algaworks.brewer.config.JPAConfig;
import com.algaworks.brewer.config.ServiceConfig;
import com.algaworks.brewer.config.WebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;


/**
 * Created by evandro on 31/08/16.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    /**
     * A classe de inicialização deve ser extendida de AbstractAnnotationConfigDispatcherServletInitializer
     *
     */

    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class<?>[]{JPAConfig.class, ServiceConfig.class};
    }

    /**
     * */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }

    /* Método responsável por dizer o padrão que será delegado ao Dispatcher.
     * Quando há apenas uma / indica que toda a aplicação será repassada
     * Mesmo uso da URLMapping do Servlet */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        /** Método responsável por forçar o uso do UTF-8 no sistema
         * */
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[]{characterEncodingFilter};
    }

    @Override
    protected void customizeRegistration(Dynamic registration){

        // No parâmetro de configuração podemos passar o caminho base de gravação das imagens
        registration.setMultipartConfig(new MultipartConfigElement(""));
    }
}
