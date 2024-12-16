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

        Company company = companyRepository.findByCompanyName(companyName)
                                           .orElseGet(() -> {
                                               Company c = new Company();
                                               c.setCompanyName(companyName);
                                               return companyRepository.save(c);
                                           });
        ms.setCompany(company);

        Faction faction = factionRepository.findByFactionName(factionName)
                                           .orElseGet(() -> {
                                               Faction f = new Faction();
                                               f.setFactionName(factionName);
                                               return factionRepository.save(f);
                                           });
        ms.setFaction(faction);

         return repository.save(ms);  // DB 저장
    }

    // 특정 유형의 MS 검색
    public List<MobileSuit> getByType(String msType) {
        return repository.findByMsType(msType);  // 자동 SQL 생성
    }

    public List<MobileSuit> getAllMobileSuits() {
        return repository.findAll(); // 모든 데이터 조회
    }

    public List<MobileSuit> getMobileSuits(String msType) {
        if (msType == null || msType.trim().isEmpty()) {
            return repository.findAll(); // 모든 리스트 반환
        } else {
            return repository.findByMsType(msType); // 조건에 맞는 리스트 반환
        }
    }

    public String getMobileSuitName(String msType) {
        System.out.println("Fetching Mobile Suit for type: " + msType);
        Optional<MobileSuit> ms = repository.findByMsType(msType).stream().findFirst();
        String result = ms.map(MobileSuit::getMsName).orElse("모바일 슈트 없음");
        System.out.println("Service result: " + result);
        return result;
    }


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
}
