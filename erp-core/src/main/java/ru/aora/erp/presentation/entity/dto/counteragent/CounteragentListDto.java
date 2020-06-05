package ru.aora.erp.presentation.entity.dto.counteragent;

import java.util.List;
import java.util.StringJoiner;

public final class CounteragentListDto {
    private List<CounteragentDto> counteragents;

    private CounteragentListDto(List<CounteragentDto> counteragents) {
        this.counteragents = counteragents;
    }

    public static CounteragentListDto of(List<CounteragentDto> counteragents) {
        return new CounteragentListDto(counteragents);
    }

    public List<CounteragentDto> getCounteragents() {
        return counteragents;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CounteragentListDto.class.getSimpleName() + "[", "]")
                .add("counteragents=" + counteragents)
                .toString();
    }
}
