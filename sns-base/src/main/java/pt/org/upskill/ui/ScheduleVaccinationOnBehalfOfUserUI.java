package pt.org.upskill.ui;
import pt.org.upskill.controller.AppointmentController;
import pt.org.upskill.controller.FacilityController;
import pt.org.upskill.controller.VaccineTypeController;
import pt.org.upskill.dto.AppointmentDTO;
import pt.org.upskill.dto.FacilityDTO;
import pt.org.upskill.dto.SNSUserDTO;
import pt.org.upskill.dto.VaccineTypeDTO;
import pt.org.upskill.repository.FacilityRepository;
import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.SNSUserRepository;
import pt.org.upskill.repository.VaccineTypeRepository;
import pt.org.upskill.session.Context;

import java.time.LocalTime;
import java.util.Date;
import static pt.org.upskill.ui.utils.UITools.showKeyValueList;
import static pt.org.upskill.ui.utils.Utils.*;

public class ScheduleVaccinationOnBehalfOfUserUI extends UI {
    AppointmentController appointmentController = new AppointmentController();
    public void run() {
        System.out.println("");
        System.out.println("SCHEDULE VACCINATION");
        System.out.println("-----------");
        try {
            VaccineTypeRepository vaccineTypeRepository = Repositories.getInstance().vaccineTypeRepository();
            FacilityRepository facilityRepository = Repositories.getInstance().facilityRepository();
            SNSUserRepository snsUserRepository = Repositories.getInstance().snsUserRepository();

            showKeyValueList("Vaccines Types: ", new VaccineTypeController().keyValueDTOList());
            String vaccineTypeCode = readLineFromConsole("Select a vaccine type: ");

            String userNumber = readLineFromConsole("SNS user number: ");
            Date date = readDateFromConsole("Date: ");
            LocalTime hour = readHourFromConsole("Hour: ");

            showKeyValueList("Vacination Centers: ", new FacilityController().keyValueDTOList());
            Integer facilityId = readIntegerFromConsole("Select a Vaccination Center: ");


            AppointmentDTO dto = new AppointmentDTO.Builder()
                    .withFacilityDTO(facilityRepository.getById(facilityId).toDTO())
                    .withVaccineTypeDTO((vaccineTypeRepository.getByBusinessId(vaccineTypeCode)).toDTO())
                    .withSNSUserDTO(snsUserRepository.getByBusinessId(userNumber).toDTO())
                    .withDateDTO(date)
                    .withHourDTO(hour)
                    .build()
                    ;

            appointmentController.register(dto);

            appointmentController.save();

            appointmentController.print();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
