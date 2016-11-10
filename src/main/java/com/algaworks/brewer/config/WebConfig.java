package com.algaworks.brewer.config;

import com.algaworks.brewer.controller.CervejasController;
import com.algaworks.brewer.controller.converter.CidadeConverter;
import com.algaworks.brewer.controller.converter.EstadoConverter;
import com.algaworks.brewer.controller.converter.EstiloConverter;
import com.algaworks.brewer.thymeleaf.BrewerDialect;
import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.math.BigDecimal;
import java.util.Locale;


/**
 * Created by evandro on 31/08/16.
 */

@Configuration // Classe de configuração do Spring
@ComponentScan(basePackageClasses = {CervejasController.class})
/*Indica o pacote que será procurado os controles, pode ser usado como a string do nome do pacote
* mas isso pode acarretar digitação errada.
* utilizando o basePackageClasses, vc indica uma classe e o Spring pega o pacote base dela como referência
* Pode ser qualquer classe do pacote base
* */
@EnableWebMvc // Habilita o projeto para desenvolvimento Web
@EnableSpringDataWebSupport
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{
    /** Classe responsável por ensinar o Spring a encontrar os controles
     * por ser uma classe de configuração, recebe a anotação do Spring
     * a classe WebMvcConfigurerAdapter é um adaptator para facilitar as configurações da aplicação*/

    private ApplicationContext appicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appicationContext = applicationContext;
    }

    @Bean
    public ViewResolver viewResolver(){
        // Função responsável por encontrar e processar as requisições
        ThymeleafViewResolver resolver =new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public TemplateEngine templateEngine(){
        // Responsavel por processar os arquivos HTML
        // Para estar disponível para a aplicação, necessita da anotação Bean
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setTemplateResolver(templateResolver());

        engine.addDialect(new LayoutDialect());
        engine.addDialect(new BrewerDialect());// Inclusão do Uso do Dialeto
        engine.addDialect(new DataAttributeDialect());
        return engine;
    }

    // Indica onde está o template HTML
    private ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(appicationContext);
        resolver.setPrefix("classpath:/templates/"); // onde estão os templates: resources\templates
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 19/09/2016 Aula 9.7
     * Método utilizado para registrar os Converter utilizados na camada de View com o Controller
     */
    @Bean
    public FormattingConversionService mvcConversionService(){
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(new EstiloConverter());
        conversionService.addConverter(new CidadeConverter());
        conversionService.addConverter(new EstadoConverter());

        NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter("#,##0.00");
        conversionService.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);

        NumberStyleFormatter integerFormatter = new NumberStyleFormatter("#,##0");
        conversionService.addFormatterForFieldType(Integer.class, integerFormatter);

        return conversionService;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new FixedLocaleResolver(new Locale("pt", "BR"));
    }

}
