/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Usuario
 */
public class ValidacionesEditorial implements Validator
{

    @Override
    public boolean supports(Class<?> type)
    {
        return Editorial.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) 
    {
        Editorial ed=(Editorial)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required.nombre", "El campo nombre es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correoContacto", "required.correoContacto", "El campo correoContacto es obligatorio");
    }
    
}
