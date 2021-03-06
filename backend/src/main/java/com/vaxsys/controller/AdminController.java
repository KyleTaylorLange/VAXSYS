package com.vaxsys.controller;

import com.vaxsys.dto.AccountDto;
import com.vaxsys.dto.AccountCreationDto;
import com.vaxsys.dto.VaccineCenterCreationDto;
import com.vaxsys.dto.VaccineCenterDto;
import com.vaxsys.dto.VaccineCreationDto;
import com.vaxsys.dto.VaccineDto;
import com.vaxsys.dto.*;
import com.vaxsys.entity.*;
import com.vaxsys.mapper.*;
import com.vaxsys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vaxsys.constant.Role.ADMIN_CREATION_ROLES;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AccountService accountService;
    private final VaccineService vaccineService;
    private final DiseaseService diseaseService;
    private final VaccineCenterService vaccineCenterService;
    private final SlotService slotService;

    @Autowired
    private AppointmentService appointmentService;

    public AdminController(AccountService accountService, VaccineService vaccineService, DiseaseService diseaseService, VaccineCenterService vaccineCenterService, SlotService slotService) {
        this.accountService = accountService;
        this.vaccineService = vaccineService;
        this.diseaseService = diseaseService;
        this.vaccineCenterService = vaccineCenterService;
        this.slotService = slotService;
    }

    @PostMapping("/account")
    public AccountDto createAccount(@RequestBody AccountCreationDto accountCreationDto,
                                    HttpServletResponse response) throws IOException {
        Account account;
        if (!ADMIN_CREATION_ROLES.contains(accountCreationDto.getRole())) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Admin could only create nurse or vendor accounts.");
            return null;
        }
        try {
            account = accountService.createAccount(accountCreationDto, accountCreationDto.getRole().map());
        } catch (Exception e) {
            response.sendError(HttpStatus.FORBIDDEN.value(), e.getMessage());
            return null;
        }
        return AccountMapper.INSTANCE.map(account);
    }

    @GetMapping("/appointment/findAll")
    public Page<Appointment> findAllAppointments(Pageable pageable){
        Page<Appointment> appointmentPage = appointmentService.findAll(pageable);
        return new PageImpl<>(appointmentPage.getContent(),pageable,appointmentPage.getTotalElements());
    }
    @PostMapping("/vaccine")
    public VaccineDto createVaccine(@RequestBody VaccineCreationDto vaccineCreationDto, HttpServletResponse response) throws IOException {
        Vaccine vaccine;
        try {
            vaccine = vaccineService.createVaccine(vaccineCreationDto);
        } catch (Exception e) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Vaccine with name " + vaccineCreationDto.getName() + " already exists");
            return null;
        }

        return VaccineMapper.INSTANCE.map(vaccine);
    }

    @GetMapping("/vaccine/{name}")
    public VaccineDto findVaccineByName(@PathVariable String name) {
        return VaccineMapper.INSTANCE.map(vaccineService.findByName(name));
    }

    @GetMapping("/vaccine")
    public Page<VaccineDto> findAllVaccines(Pageable pageable) {
        Page<Vaccine> vaccinePage = vaccineService.findAll(pageable);
        return new PageImpl<>(VaccineMapper.INSTANCE.map(vaccinePage.getContent()), pageable, vaccinePage.getTotalElements());
    }

    @PostMapping("/disease")
    public DiseaseDto createDisease(@RequestBody DiseaseCreationDto diseaseCreationDto, HttpServletResponse response) throws IOException {
        Disease disease;
        try {
            disease = diseaseService.createDisease(diseaseCreationDto);
        } catch (Exception e) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Disease with name " + diseaseCreationDto.getName() + " already exists");
            return null;
        }
        return DiseaseMapper.INSTANCE.map(disease);
    }
    @GetMapping("/disease/{name}")
    public DiseaseDto findDiseaseByName(@PathVariable String name) {
        return DiseaseMapper.INSTANCE.map(diseaseService.findByName(name));
    }

    @GetMapping("/disease")
    public Page<DiseaseDto> findAllDiseases(Pageable pageable) {
        Page<Disease> diseasePage = diseaseService.findAll(pageable);
        return new PageImpl<>(DiseaseMapper.INSTANCE.map(diseasePage.getContent()), pageable, diseasePage.getTotalElements());
    }

    @PostMapping("/vaccineCenter")
    public VaccineCenterDto createVaccine(@RequestBody VaccineCenterCreationDto vaccineCenterCreationDto) {
        return VaccineCenterMapper.INSTANCE.map(vaccineCenterService.createVaccineCenter(vaccineCenterCreationDto));
    }

    @PostMapping("/slot")
    public SlotDto createSlot(@RequestBody SlotCreationDto slotCreationDto, HttpServletResponse response) throws IOException {
        Slot slot;
        try {
            slot = slotService.createSlot(slotCreationDto);
        } catch(Exception e) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "Unable to create slot: " + e.getMessage());
            return null;
        }
        return SlotMapper.INSTANCE.map(slot);
    }
}
