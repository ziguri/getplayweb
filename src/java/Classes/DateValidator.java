/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Zueb LDA
 */
@FacesValidator("dateValidator")
public class DateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        GregorianCalendar gc = new GregorianCalendar();
        int todayYear = gc.get(Calendar.YEAR);

        int yearInserted = (int) value;

        if (yearInserted > todayYear) {
            FacesMessage msg = new FacesMessage("Year from the future",
                    "please insert the correct year.");

        }

    }

}
