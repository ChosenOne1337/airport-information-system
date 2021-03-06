package app.gui.forms.input.impl;

import app.gui.controllers.EntityInputFormController;
import app.gui.controllers.interfaces.ChoiceItemSupplier;
import app.gui.custom.ChoiceItem;
import app.model.Chief;
import app.model.types.Sex;
import app.utils.RequestExecutor;
import app.utils.ServiceFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ChiefInputFormBuilder extends AbstractEntityInputFormBuilder<Chief> {

    public ChiefInputFormBuilder(RequestExecutor requestExecutor) {
        super(Chief::new, ServiceFactory.getChiefService(), requestExecutor);
    }

    @Override
    protected void fillInputForm(
            Chief chief,
            FormType formType,
            boolean isContextWindow,
            EntityInputFormController<Chief> controller
    ) {

        controller.addTextField(
                "ФИО начальника",
                chief.getName(),
                chief::setName
        );

        controller.addChoiceBox(
                "Пол",
                chief.getSex(),
                chief::setSex,
                Sex::getChoiceItems
        );

        controller.addDateField(
                "Дата рождения",
                chief.getBirthDate(),
                chief::setBirthDate
        );

    }

    @Override
    protected String getCreationFormWindowTitle() {
        return "Добавить нового начальника";
    }

    @Override
    protected String getEditFormWindowTitle(Chief chief) {
        return String.format("Начальник %s - изменить", chief.getName());
    }
    
}
