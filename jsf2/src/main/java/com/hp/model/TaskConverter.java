package com.hp.model;

import com.hp.model.dao.TaskDao;
import com.hp.model.domain.Task;
import lombok.extern.log4j.Log4j;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Log4j
@Named
@RequestScoped
public class TaskConverter implements Converter {

    @Inject
    TaskDao taskDao;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        int id;
        try {
            id = Integer.valueOf(s);
        } catch (NumberFormatException e) {
            log.error(e);
            return null;
        }
        return taskDao.findById(id);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null) {
            return Integer.toString(((Task)o).getId());
        } else
            return null;
    }
}
