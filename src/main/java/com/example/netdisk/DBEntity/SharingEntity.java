package com.example.netdisk.DBEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharingEntity {
    public int Id;
    public String SharingCode;
    public String UserName;
    public  int FileId;
    public Date GivenPeriod;
}
