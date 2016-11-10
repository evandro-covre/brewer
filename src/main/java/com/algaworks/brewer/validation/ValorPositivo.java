package com.algaworks.brewer.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by evandro on 20/09/16.
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})

@DecimalMin(value = "0.01", message = "Valor da Cerveja deve ser maior que R$ 0,01")
@DecimalMax(value = "9999999.99", message = "Valor da Cerveja deve ser menor que R$ 9.999.999,99")
public @interface ValorPositivo {

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
