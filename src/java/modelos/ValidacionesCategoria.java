/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationEventHandler;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author camilo
 */
public class ValidacionesCategoria implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Categoria.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Categoria categorias = (Categoria) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre",
                "required.nombre", "El campo Nombre es Obligatorio.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion",
                "required.descripcion", "El campo jkdsjskdjdskjdskd");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "influencia",
                "required.influencia", "El campo Correo electrónico es Obligatorio.");
    }

}
