package ru.aora.erp.presentation.controller.garant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.domain.service.KsService;
import ru.aora.erp.utils.common.CommonUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.StringJoiner;

import static java.util.Objects.requireNonNull;

@Controller
public final class GarantResultController {

    public static final String GARANT_RESULT_MAPPING = "/garantresult";
    public static final String GARANT_RESULT_TEMPLATE = "garantresult";
    private static final String DTO_MODEL = "garantResultDto";

    private final KsService ksService;

    public GarantResultController(KsService ksService) {
        this.ksService = ksService;
    }

    @RequestMapping(GARANT_RESULT_MAPPING)
    public String garantResult(Map<String, Object> model) {
        model.put(
                DTO_MODEL,
                garantResult(ksService.loadAll())
        );
        return GARANT_RESULT_TEMPLATE;
    }

    @SuppressWarnings("WeakerAccess")
    static class GarantResultDto {
        public BigDecimal notPaidKsTotalSum = BigDecimal.ZERO;
        public long minDaysToGarantDate = Long.MAX_VALUE;

        @Override
        public String toString() {
            return new StringJoiner(", ", GarantResultDto.class.getSimpleName() + "[", "]")
                    .add("notPaidKsTotalSum=" + notPaidKsTotalSum)
                    .add("minDaysToGarantDate=" + minDaysToGarantDate)
                    .toString();
        }
    }

    private static GarantResultDto garantResult(Collection<Ks> ksList) {
        final GarantResultDto garantResult = new GarantResultDto();
        for (Ks ks : requireNonNull(ksList)) {
            if (ks != null && !ks.getPaymentStatus()) {
                if (ks.getGarantSum() != null) {
                    garantResult.notPaidKsTotalSum = garantResult.notPaidKsTotalSum.add(ks.getGarantSum());
                }
                if (ks.getGarantDate() != null) {
                    garantResult.minDaysToGarantDate = minDaysToGarantDate(
                            garantResult.minDaysToGarantDate,
                            ks.getGarantDate()
                    );
                }
            }
        }
        return garantResult;
    }

    private static long minDaysToGarantDate(long minDays, LocalDate date) {
        if (date != null) {
            Long daysToCurrentDate = CommonUtils.daysToCurrentDate(date);
            if (daysToCurrentDate != null) {
                return Long.min(minDays, daysToCurrentDate);
            }
        }
        return minDays;
    }
}
