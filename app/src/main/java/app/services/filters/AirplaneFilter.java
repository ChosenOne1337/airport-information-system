package app.services.filters;

import app.model.Airplane;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class AirplaneFilter implements Filter<Airplane> {

    private String airplaneTypeName;

    private Date minCommissioningDate;
    private Date maxCommissioningDate;

    private Integer minRepairsNumber;
    private Integer maxRepairsNumber;

    private Date minRepairDate;
    private Date maxRepairDate;

    private Date minTechInspectionDate;
    private Date maxTechInspectionDate;

}
