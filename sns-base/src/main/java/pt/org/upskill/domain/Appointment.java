package pt.org.upskill.domain;
import pt.org.upskill.dto.AppointmentDTO;
import pt.org.upskill.dto.DTOable;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Appointment implements DTOable<AppointmentDTO> {
    private Integer id;
    private Facility facility;
    private VaccineType vaccineType;
    private SNSUser snsuser;
    private Date date;
    private LocalTime hour;

    public Appointment(){}
    public Appointment(final Appointment.Builder builder)
    {
        this.id = builder.id;
        this.facility = builder.facility;
        this.vaccineType = builder.vaccineType;
        this.snsuser = builder.snsUser;
        this.date = builder.date;
        this.hour = builder.hour;
    }

    public Integer id() {
        return this.id;
    }
    public Facility facility() {
        return this.facility;
    }
    public VaccineType vaccineType() {
        return this.vaccineType;
    }
    public SNSUser snsuser() {
        return this.snsuser;
    }
    public Date date() { return this.date; }
    public LocalTime hour() { return this.hour; }
    public String strHour(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        // Format the LocalTime variable as a string
        String formattedTime = this.hour.format(formatter);
        return formattedTime; }

    @Override
    public AppointmentDTO toDTO() {
        AppointmentDTO dto = new AppointmentDTO.Builder()
                .withId(id())
                .withFacilityDTO(facility().toDTO())
                .withVaccineTypeDTO(vaccineType().toDTO())
                .withSNSUserDTO(snsuser().toDTO())
                .withDateDTO(date())
                .withHourDTO(hour())
                .build();
        return dto;
    }

    public static class Builder {

        private Integer id;
        private Facility facility;
        private VaccineType vaccineType;
        private SNSUser snsUser;
        private Date date;
        private LocalTime hour;

        public Appointment.Builder withId(final Integer id) {
            this.id = id;
            return this;
        }
        public Appointment.Builder withFacility(final Facility facility) {
            this.facility = facility;
            return this;
        }
        public Appointment.Builder withVaccineType(final VaccineType vaccineType) {
            this.vaccineType = vaccineType;
            return this;
        }
        public Appointment.Builder withSNSUser(final SNSUser snsUser) {
            this.snsUser = snsUser;
            return this;
        }
        public Appointment.Builder withDate(final Date date) {
            this.date = date;
            return this;
        }
        public Appointment.Builder withHour(final LocalTime hour){
            this.hour = hour;
            return this;
        }
        public Appointment.Builder withHour (final String hour){
            String strHour = hour;
            String pattern = "HH:mm";
            this.hour = LocalTime.parse(strHour, DateTimeFormatter.ofPattern(pattern));
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }

    }

