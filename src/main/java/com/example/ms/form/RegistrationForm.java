package com.example.ms.form;

import com.example.ms.entity.Company;
import com.example.ms.entity.Faction;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistrationForm {
    @NotEmpty(message = "MS의 형식번호를 입력하세요")
    private String msId;

    @NotEmpty(message = "MS의 명칭 입력하세요")
    private String msName;

    @NotEmpty(message = "양산기인지, 주력기인지, 전용기인지 입력하세요")
    private String msType;

    @NotEmpty(message = "현황을 입력하세요")
    private String msStatus;

    @NotNull(message = "생산 단가를 입력하세요")
    @Positive(message = "생산 단가는 0보다 커야 합니다")
    private double msCost;

    @NotEmpty(message = "어느 진영 소속인지 입력하세요")
    private String factionName;

    @NotEmpty(message = "생산 회사는 어디인가요 ")
    private String companyName;
}
