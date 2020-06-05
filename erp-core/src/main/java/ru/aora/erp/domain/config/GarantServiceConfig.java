package ru.aora.erp.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aora.erp.repository.jpa.JpaContractRepository;
import ru.aora.erp.repository.jpa.JpaCounteragentRepository;
import ru.aora.erp.repository.jpa.JpaKsRepository;
import ru.aora.erp.domain.service.ContractService;
import ru.aora.erp.domain.service.CounteragentService;
import ru.aora.erp.domain.service.KsService;
import ru.aora.erp.repository.gateway.DbContractGateway;
import ru.aora.erp.repository.gateway.DbCounteragentGateway;
import ru.aora.erp.repository.gateway.DbKsGateway;

@Configuration
public class GarantServiceConfig {

    @Bean
    public DbKsGateway dbKsGateway(JpaKsRepository gateway) {
        return new DbKsGateway(gateway);
    }

    @Bean
    public KsService ksService(DbKsGateway gateway) {
        return new KsService(gateway);
    }

    @Bean
    public DbContractGateway dbContractGateway(JpaContractRepository gateway) {
        return new DbContractGateway(gateway);
    }

    @Bean
    public ContractService contractService(DbContractGateway gateway) {
        return new ContractService(gateway);
    }

    @Bean
    public DbCounteragentGateway dbCounteragentGateway(JpaCounteragentRepository gateway) {
        return new DbCounteragentGateway(gateway);
    }

    @Bean
    public CounteragentService counteragentService(DbCounteragentGateway gateway) {
        return new CounteragentService(gateway);
    }

//    @Bean //todo: используем или нет???
//    public CommonsRequestLoggingFilter logFilter() {
//        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
//        filter.setIncludeQueryString(true);
//        filter.setIncludeQueryString(true);
//        filter.setIncludePayload(true);
//        filter.setMaxPayloadLength(10000);
//        filter.setIncludeHeaders(false);
//        filter.setAfterMessagePrefix("RQ Data : ");
//        return filter;
//    }
}
