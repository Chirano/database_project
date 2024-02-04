package pt.org.upskill.controller;
import pt.org.upskill.domain.Appointment;
import pt.org.upskill.dto.AppointmentDTO;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.repository.AppointmentRepository;
import pt.org.upskill.repository.Repositories;
import java.util.List;

public class AppointmentController implements UIable {
    AppointmentRepository appointmentRepository = Repositories.getInstance().appointmentRepository();


    private Appointment appointment;

    @Override
    public void register(DTO dto) {
        appointment = appointmentRepository.createAppointment(dto);
    }

    @Override
    public boolean save() {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<KeyValueDTO> keyValueDTOList() { return appointmentRepository.keyValueDTOList(); }

    public boolean print(){
       return appointmentRepository.print(appointment);
    }
}
