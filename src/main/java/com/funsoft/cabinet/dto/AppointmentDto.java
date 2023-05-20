package com.funsoft.cabinet.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class AppointmentDto implements Serializable {
    private long id;
    private long idp;
    private long idd;
    private Date rdvTime;
}
