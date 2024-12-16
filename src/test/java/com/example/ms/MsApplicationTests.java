package com.example.ms;

import com.example.ms.service.MobileSuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@Transactional
class MsApplicationTests {

    @Autowired
    MobileSuitService service;

 /*   @Test
    @DisplayName("엔티티 생성")
    void t1() {
        Faction faction = new Faction();
        Meterial meterial = new Meterial();
        MobileSuit mobileSuit = new MobileSuit();
        OrderCompany orderCompany = new OrderCompany();
    }

    @Test
    @DisplayName("데이터 추가 테스트")
    void t2() {
        service.addMobileSuit("RGM-79","GM","양산기",3500);
    }*/

}
