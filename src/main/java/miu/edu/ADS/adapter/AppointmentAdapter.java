package miu.edu.ADS.adapter;

import lombok.RequiredArgsConstructor;
import miu.edu.ADS.dto.appointment.AppointmentRequest;
import miu.edu.ADS.dto.appointment.AppointmentResponse;
import miu.edu.ADS.model.Appointment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentAdapter {

    private final ModelMapper modelMapper;

    public Appointment requestToAppointment(AppointmentRequest appointmentRequest) {
        return modelMapper.map(appointmentRequest, Appointment.class);
    }

    public AppointmentResponse appointmentToResponse(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentResponse.class);
    }
}
