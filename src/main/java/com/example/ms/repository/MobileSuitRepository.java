package com.example.ms.repository;

import com.example.ms.entity.MobileSuit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MobileSuitRepository extends JpaRepository<MobileSuit, String> {
    // SELECT * FROM MOBILE_SUIT WHERE MS_TYPE = ?;
    List<MobileSuit> findByMsType(String msType); //양산형인지, 전용기인지 찾는다.

    // SELECT * FROM MOBILE_SUIT WHERE MS_STATUS = ?;
    List<MobileSuit> findByMsStatus(String msStatus);

    // SELECT * FROM MOBILE_SUIT WHERE FACTION_ID = (SELECT FACTION_ID FROM FACTION WHERE FACTION_NAME = ?);
    List<MobileSuit> findByFaction_FactionName(String factionName);

    // SELECT * FROM MOBILE_SUIT WHERE FACTION_ID = (SELECT COMPANY_ID FROM COMPANY WHERE COMPANY_NAME = ?);
    List<MobileSuit> findByCompany_CompanyName(String companyName);

    // SELECT * FROM MOBILE_SUIT ORDER BY MS_STATUS;
    @Query(name = "MobileSuit.findAllOrderByStatus")
    List<MobileSuit> findAllOrderByStatus();
}
