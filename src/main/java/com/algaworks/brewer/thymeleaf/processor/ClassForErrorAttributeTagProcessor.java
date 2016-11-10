package com.algaworks.brewer.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.util.FieldUtils;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Created by evandro on 23/09/16.
 */
public class ClassForErrorAttributeTagProcessor extends AbstractAttributeTagProcessor{

    private static final String NOME_ATRIBUTO = "classforerror";
    // A precedencia é definida para ver a ordem de execução do atributo. No nosso caso não haveremos a necessidade
    // dessa validação
    private static final int PRECEDENCIA = 1000;

    public ClassForErrorAttributeTagProcessor(String dialectPrefix){
        super(TemplateMode.HTML, dialectPrefix, null, false, NOME_ATRIBUTO, true, PRECEDENCIA, true);
    }

    @Override
    protected void doProcess(ITemplateContext iTemplateContext, IProcessableElementTag iProcessableElementTag,
                             AttributeName attributeName, String s, IElementTagStructureHandler iElementTagStructureHandler) {

        // s é referencia ao valor que foi informado no elemento.
        // a variável field foi definida por fazermos o tratamento em cima de um th:field
        String field = s;


        //<!-- th:classappend="${#fields.hasErrors('sku')} ? has-error"-->
        boolean temErro = FieldUtils.hasErrors(iTemplateContext, s);

        if (temErro){
            String classesExistentes = iProcessableElementTag.getAttributeValue("class");
            //o setAttribute substitui o valor existente na class pelo que estou passando, preciso reposicionar as
            // classes que quero manter
            iElementTagStructureHandler.setAttribute("class", classesExistentes+ " has-error");
        }
    }
}
