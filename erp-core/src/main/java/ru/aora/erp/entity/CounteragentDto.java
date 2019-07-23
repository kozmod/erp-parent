package ru.aora.erp.entity;

import ru.aora.erp.model.entity.counteragent.Counteragent;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public final class CounteragentDto {
    private List<Counteragent> counteragents;

    public CounteragentDto() {
    }

    private CounteragentDto(List<Counteragent> counteragents) { this.counteragents = counteragents; }

    public static CounteragentDto of(List<Counteragent> counteragents){
        return new CounteragentDto(counteragents);
    }

    public static CounteragentDto of(Counteragent ... counteragents){
        return new CounteragentDto(Arrays.asList(counteragents));
    }

    public List<Counteragent> getCounteragents() {
        return counteragents;
    }

    public void addCounteragents(Counteragent counteragent) {
        this.counteragents.add(counteragent);
    }

    public void setCounteragents(List<Counteragent> counteragents) {
        this.counteragents = counteragents;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CounteragentDto.class.getSimpleName() + "[", "]")
                .add("counteragents=" + counteragents)
                .toString();
    }
}
