package ru.aora.erp.presentation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.aora.erp.security.map.DashboardAuthorityUrlMap;
import ru.aora.erp.presentation.entity.dto.sidebar.SidebarPresenter;
import ru.aora.erp.presentation.presenter.AllSidebarPresenter;
import ru.aora.erp.presentation.presenter.GarantSidebarPresenter;
import ru.aora.erp.presentation.presenter.TestSidebarPresenter;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ComponentScan("ru.aora.erp.presentation.controller")
public class PresentationConfig {

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

    @Bean
    public GarantSidebarPresenter garantSidebarPresenter() {
        return new GarantSidebarPresenter();
    }

    @Bean
    public TestSidebarPresenter testSidebarPresenter() {
        return new TestSidebarPresenter();
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
