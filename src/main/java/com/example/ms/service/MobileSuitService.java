package com.example.ms.service;

import com.example.ms.entity.Company;
import com.example.ms.entity.Faction;
import com.example.ms.entity.MobileSuit;
import com.example.ms.repository.CompanyRepository;
import com.example.ms.repository.FactionRepository;
import com.example.ms.repository.MobileSuitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MobileSuitService {
    private final MobileSuitRepository repository;
    private final CompanyRepository companyRepository;
    private final FactionRepository factionRepository;


    // 신규 MS 추가
    public MobileSuit addMobileSuit(String msId,String msName, String msType,String msStatus, double msCost, String companyName, String factionName) {
        MobileSuit ms = new MobileSuit();
        ms.setMsId(msId);
        ms.setMsName(msName);
        ms.setMsType(msType);
        ms.setMsStatus(msStatus);
        ms.setMsCost(msCost);

        // 제작사 명을 받아와서 COMPANY 엔티티 값과 비교. 값이 있으면 그거 이용, 없으면 신규 등록
        Company company = companyRepository.findByCompanyName(companyName)
                                           .orElseGet(() -> {
                                               Company c = new Company();
                                               c.setCompanyName(companyName);
                                               return companyRepository.save(c);
                                           });
        ms.setCompany(company);

        // ms의 소속 세력 명을 받아와서 GACTION 엔티티 값과 비교. 값이 있으면 그거 이용, 없으면 신규 등록
        Faction faction = factionRepository.findByFactionName(factionName)
                                           .orElseGet(() -> {
                                               Faction f = new Faction();
                                               f.setFactionName(factionName);
                                               return factionRepository.save(f);
                                           });
        ms.setFaction(faction);

         return repository.save(ms);  // DB 저장
    }

    public List<MobileSuit> getAllMobileSuits() {
        return repository.findAll(); // 모든 데이터 조회
    }

    //  list 화면에서 필터링 거는 기능, 필터링은 ms의 타입 (양산기, 전용기 등), 상태 (운용중, 수주(개발중), 단종 등)
    //  세력(지구연방, 지온 등), 제조사 (AE, 지오닉사 등)
    public List<MobileSuit> getFilteredMobileSuits(String filterType, String filterValue) {
        switch (filterType) {
            case "type":
                return repository.findByMsType(filterValue); // 타입 기준
            case "status":
                return repository.findByMsStatus(filterValue); // 상태 기준
            case "faction":
                return repository.findByFaction_FactionName(filterValue); // 소속 세력 기준
            case "company":
                return repository.findByCompany_CompanyName(filterValue); // 제조사 기준
            default:
                return getAllMobileSuits(); // 전체 리스트 반환
        }
    }

    // SELECT * FROM MOBILE_SUITE
    public MobileSuit getAllMobileSuitsById(String msId) {
        return repository.findById(msId)
                         .orElseThrow(() -> new IllegalArgumentException("No MS found with id: " + msId));
    }

    // 현재 MS의 상태 갱신 기능
    public void updateMobileSuit(String msId, String msStatus) {
        MobileSuit ms = repository.findById(msId)
                                   .orElseThrow(() -> new IllegalArgumentException("No MS found with id: " + msId));
        ms.setMsStatus(msStatus);

        repository.save(ms);
    }

    public Optional<MobileSuit> getMobileSuitById(String msId) {
        return repository.findById(msId);
    }
}
