package org.sarthak.sample.Persistance;

import org.sarthak.sample.Entities.FetchLeadPersistance;
import org.sarthak.sample.Modals.FetchLead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FetchLeadDSC {

    @Autowired
    public FetchLeadDS fetchLeadDS;


    public Optional<FetchLeadPersistance> findLead(Integer id){
        return fetchLeadDS.findById(id);
    }


    public FetchLeadPersistance saveInDb(FetchLeadPersistance pers) {
        return  fetchLeadDS.save(pers);

    }

    public  void deleteEntityById(Integer id) {
        fetchLeadDS.delete(id);
    }

    public void updateEntityById(Integer id, FetchLead fetchLead) {

        fetchLeadDS.updateLeadById(id,fetchLead.getFirst_name(), fetchLead.getLast_name(), fetchLead.getMobile(), fetchLead.getEmail(),
                fetchLead.getLocation_type(), fetchLead.getLocation_string());

    }


    public void updateCommunication(Integer id, String comm){
        fetchLeadDS.updateCommunication(id, comm);
    }
}
