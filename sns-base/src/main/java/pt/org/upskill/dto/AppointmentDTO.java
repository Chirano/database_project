package pt.org.upskill.dto;
import pt.org.upskill.domain.Appointment;
import pt.org.upskill.domain.Facility;
import pt.org.upskill.domain.SNSUser;
import pt.org.upskill.domain.VaccineType;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AppointmentDTO implements DTO{
    private Integer id;
    private FacilityDTO facilityDTO;
    private VaccineTypeDTO vaccineTypeDTO;
    private SNSUserDTO snsuserDTO;
    private Date date;
    private LocalTime hour;
    public Integer id() {
        return this.id;
    }
    public FacilityDTO facilityDTO() {
        return facilityDTO;
    }
    public VaccineTypeDTO vaccineTypeDTO() {
        return vaccineTypeDTO;
    }
    public SNSUserDTO snsuserDTO() {
        return this.snsuserDTO;
    }
    public Date date() {return this.date; }
    public LocalTime hour() {return this.hour; }
    private AppointmentDTO(final AppointmentDTO.Builder builder)
    {
        this.id = builder.id;
        this.facilityDTO = builder.facilityDTO;
        this.vaccineTypeDTO = builder.vaccineTypeDTO;
        this.snsuserDTO = builder.snsUserDTO;
        this.date = builder.date;
        this.hour = builder.hour;
    }
    @Override
    public String toString(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = df.format(date);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String strHour = hour.format(formatter);

        return String.format("\n*** AGENDAMENTO REALIZADO ***%nLocal: %s%nData: %s%nHora: %s",
                facilityDTO.name(), strDate, strHour);
    }

    public static class Builder {
        private Integer id;
        private FacilityDTO facilityDTO;
        private VaccineTypeDTO vaccineTypeDTO;
        private SNSUserDTO snsUserDTO;
        private Date date;
        private LocalTime hour;

        public AppointmentDTO.Builder withId(final Integer id) {
            this.id = id;
            return this;
        }
        public AppointmentDTO.Builder withFacilityDTO(final FacilityDTO facilityDTO) {
            this.facilityDTO = facilityDTO;
            return this;
        }
        public AppointmentDTO.Builder withVaccineTypeDTO(final VaccineTypeDTO vaccineTypeDTO) {
            this.vaccineTypeDTO = vaccineTypeDTO;
            return this;
        }
        public AppointmentDTO.Builder withSNSUserDTO(final SNSUserDTO snsUserDTO) {
            this.snsUserDTO = snsUserDTO;
            return this;
        }
        public AppointmentDTO.Builder withDateDTO(final Date date) {
            this.date = date;
            return this;
        }
        public AppointmentDTO.Builder withHourDTO(final LocalTime hour){
            this.hour = hour;
            return this;
        }

        public AppointmentDTO build() {
            return new AppointmentDTO(this);
        }
    }

}
