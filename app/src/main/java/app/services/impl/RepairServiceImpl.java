package app.services.impl;

import app.model.Repair;
import app.services.RepairService;
import app.services.impl.api.RepairServiceApi;

public class RepairServiceImpl
        extends AbstractCrudServiceImpl<Repair>
        implements RepairService {

    public RepairServiceImpl() {
        super(RepairServiceApi.class, Repair.class, "repairs");
    }


    private RepairServiceApi getServiceApi() {
        return (RepairServiceApi) getCrudServiceApi();
    }

}