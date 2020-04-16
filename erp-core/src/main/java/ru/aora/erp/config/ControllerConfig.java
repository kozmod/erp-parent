package ru.aora.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.aora.erp.domain.service.CounteragentService;
import ru.aora.erp.presentation.controller.CounteragentController;
import ru.aora.erp.presentation.controller.DashboardController;
import ru.aora.erp.presentation.entity.dto.sidebar.SidebarPresenter;
import ru.aora.erp.presentation.presenter.AllSidebarPresenter;
import ru.aora.erp.presentation.presenter.GarantSidebarPresenter;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ComponentScan("ru.aora.erp.presentation.controller")
public class ControllerConfig {

    @Bean
    public GarantSidebarPresenter garantSidebarPresenter() {
        return new GarantSidebarPresenter();
    }

    @Bean
    public AllSidebarPresenter allSidebarPresenter(List<SidebarPresenter> presenters) {
        return presenters.stream()
                .map(SidebarPresenter::rootElement)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                AllSidebarPresenter::new
                        )
                );
    }

//    @Bean
//    public CounteragentController counteragentController(CounteragentService counteragentService){
//        return new CounteragentController(counteragentService);
//    }
//
//    @Bean
//    public DashboardController dashboardController(AllSidebarPresenter allSidebarPresenter){
//        return new DashboardController(allSidebarPresenter);
//    }
}
