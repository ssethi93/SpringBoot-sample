package org.sarthak.sample.Persistance;


import org.sarthak.sample.Entities.FetchLeadPersistance;
import org.sarthak.sample.Modals.Utilities.LOCATION;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface FetchLeadDS extends CrudRepository<FetchLeadPersistance, Integer> {

    Optional<FetchLeadPersistance> findById(Integer id);

    void delete(Integer id);

    @Transactional
    @Modifying
    @Query("update FetchLeadPersistance l set l.firstName = :fn, l.lastName = :ln, l.mobile = :mob, l.email = :email, l.locationType = :loctype, l.locationString = :locstr where l.id = :id ")
    void updateLeadById(@Param("id") Integer id, @Param("fn") String firstName, @Param("ln") String lastName,
                                                  @Param("mob") String mobile, @Param("email") String email, @Param("loctype") LOCATION loctype,
                                                  @Param("locstr") String locStr);

    @Transactional
    @Modifying
    @Query("update FetchLeadPersistance l set l.communication = :cm where l.id = :id ")
    void updateCommunication(@Param("id") Integer id, @Param("cm") String Communication);
}
