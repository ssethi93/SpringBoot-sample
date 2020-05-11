package org.sarthak.sample.controller;

import io.swagger.annotations.ApiOperation;
import org.sarthak.sample.Entities.FetchLeadPersistance;
import org.sarthak.sample.Modals.Communication;
import org.sarthak.sample.Modals.ErrorResponse;
import org.sarthak.sample.Modals.FetchLead;
import static org.sarthak.sample.Modals.Utilities.STATUS;

import org.sarthak.sample.Modals.SuccessResponse;
import org.sarthak.sample.Persistance.FetchLeadDSC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping
public class AppController {


    @Autowired
    private FetchLeadDSC fetchLeadDSC;

    @ApiOperation("This is the hello world api")
    @GetMapping("/")
    public String hello() {
        return "Hello World!!";
    }


    @ApiOperation("Service for fetching leads")
    @GetMapping(value = "/api/leads/{leadId}", produces = APPLICATION_JSON_VALUE)
        public ResponseEntity getLeads(@PathVariable Integer leadId){
        Optional<FetchLeadPersistance> optPersistance= null;
        try {
            optPersistance = fetchLeadDSC.findLead(leadId);
            boolean isPresent = optPersistance.isPresent();
            if(isPresent){
                FetchLead fl = this.convertPersistanceToModel(optPersistance.get());
                fl.setStatus(STATUS.Contacted);
                return ResponseEntity.status(HttpStatus.OK).body(fl);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
            }
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(STATUS.failure, e.getMessage()));
        }
    }

    @ApiOperation("Create API to modify the lead data")
    @PostMapping(value="/api/leads/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postLeads(@RequestBody FetchLead requestBody){


        try {
            FetchLeadPersistance perObject = this.convertModelToPersistance(requestBody);
            perObject.setStatus(STATUS.Created);
            FetchLead fl = this.convertPersistanceToModel(fetchLeadDSC.saveInDb(perObject));
            return ResponseEntity.status(HttpStatus.CREATED).body(fl);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(STATUS.failure, "Failed to delete into db"));
        }
    }

    @ApiOperation("Delete API to modify the lead data")
    @DeleteMapping(value="/api/leads/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteLeads(@PathVariable Integer id){
        try {
            fetchLeadDSC.deleteEntityById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(STATUS.success));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(STATUS.failure, "Failed to insert into db"));
        }
    }

    @ApiOperation("Update API to modify the lead data")
    @PutMapping(value="/api/leads/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateLeads(@PathVariable Integer id, @RequestBody FetchLead fetchLead){
        try {
            fetchLeadDSC.updateEntityById(id, fetchLead);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new SuccessResponse(STATUS.success));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(STATUS.failure, "Failed to insert into db" + e.getMessage()));
        }
    }

    @ApiOperation("Update API to modify the lead data")
    @PutMapping(value="/api/mark_lead/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity communication(@PathVariable Integer id, @RequestBody Communication comm){
        try {
            fetchLeadDSC.updateCommunication(id, comm.getCommunication());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Communication(comm.getCommunication(), STATUS.Contacted));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(STATUS.failure, "Failed to insert into db" + e.getMessage()));
        }
    }


    // converting the persistance to modal
    public FetchLead convertPersistanceToModel(FetchLeadPersistance perObj) {
        FetchLead fetchLead = new FetchLead();
        fetchLead.setEmail(perObj.getEmail());
        fetchLead.setFirst_name(perObj.getFirstName());
        fetchLead.setLast_name(perObj.getLastName());
        fetchLead.setLocation_string(perObj.getLocationString());
        fetchLead.setLocation_type(perObj.getLocationType());
        fetchLead.setMobile(perObj.getMobile());
        fetchLead.setStatus(perObj.getStatus());
        fetchLead.setId(perObj.getId());
        return fetchLead;
    }

    public FetchLeadPersistance convertModelToPersistance(FetchLead perObj) {
        FetchLeadPersistance fetchLead = new FetchLeadPersistance();
        if(perObj.getEmail() != null){
            fetchLead.setEmail(perObj.getEmail());
        }
        if(perObj.getFirst_name() != null){
            fetchLead.setFirstName(perObj.getFirst_name());
        }
        if(perObj.getLocation_type() != null){
            fetchLead.setLocationType(perObj.getLocation_type());
        }
        if(perObj.getLocation_string() != null){
            fetchLead.setLocationString(perObj.getLocation_string());
        }
        if(perObj.getMobile() != null){
            fetchLead.setMobile(perObj.getMobile());
        }
        if(perObj.getStatus() != null){
            fetchLead.setStatus(perObj.getStatus());
        }
        if(perObj.getLast_name() != null){
            fetchLead.setLastName(perObj.getLast_name());
        }
         return fetchLead;
    }




}
