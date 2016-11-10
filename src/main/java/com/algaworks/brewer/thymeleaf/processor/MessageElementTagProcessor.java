package com.algaworks.brewer.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Created by evandro on 24/09/16.
 */
public class MessageElementTagProcessor extends AbstractElementTagProcessor {

    private static final String NOME_TAG = "message";
    private static final int PRECEDENCIA = 1000;

    public MessageElementTagProcessor(String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, NOME_TAG, true, null, false, PRECEDENCIA);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
                             IElementTagStructureHandler structureHandler) {
        IModelFactory modelFactory = context.getModelFactory();
        IModel model = modelFactory.createModel();

        // No model adiciono o  elementos HTML que desejamos
        // parâmetros: tag th:block, atributo th:incluse e valor fragmentos/MensagemSucesso
//        model.add(modelFactory.createStandaloneElementTag("th:block", "th:include", "fragmentos/MensagemSucesso"));
//        model.add(modelFactory.createStandaloneElementTag("th:block", "th:include", "fragmentos/MensagemErroValidacao"));
        model.add(modelFactory.createStandaloneElementTag("div", "th:replace", "fragmentos/MensagemSucesso :: mensagemSucesso"));
        model.add(modelFactory.createStandaloneElementTag("div", "th:replace", "fragmentos/MensagemErroValidacao :: mensagemErro"));

//<div th:replace="estilo/CadastroRapidoEstilo :: modal"></div>

        // o segundo parâmetro indica se os elementos que estamos incluindo precisam de um processamento ou se são códigos
        // Nativos html. Como são elementos do Thymeleaf, precisamos passar como true
        structureHandler.replaceWith(model, true);
    }
}
