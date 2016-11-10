package com.algaworks.brewer.validation;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by evandro on 19/09/16.
 */

// Anotação pode ser usada em propriedades, métodos ou outras anotações
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
// Indica que é uma validação de Tempo de Execução
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})

// pattern de validação
@Pattern(regexp = "([a-zA-Z]{2}\\d{4})?")
public @interface SKU {
    //[a-zA-Z]{2} Cadeia de caracter entre a e z Maiuscula e minuscula limitado em 2 dígitos
    // \\d{4} Cadeia de dígitos numéricos limitado a 4 posições
    // ? somente fará essa validação se existir informação

    // Indica a sobrescrita do atributo message da classe de validação Pattern
    @OverridesAttribute(constraint = Pattern.class, name = "message")
    String message() default "Informe o SKU no formato XX9999";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
