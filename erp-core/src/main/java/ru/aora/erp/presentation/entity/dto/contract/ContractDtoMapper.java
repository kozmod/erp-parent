package ru.aora.erp.presentation.entity.dto.contract;

import ru.aora.erp.presentation.entity.dto.utils.MapperUtils;
import ru.aora.erp.model.entity.business.Contract;

import java.util.List;

public final class ContractDtoMapper {

    public static ContractDto toContractDto(Contract contract) {
        return new ContractDto()
                .setContractDate(contract.getContractDate())
                .setContractNumber(contract.getContractNumber())
                .setContractSubject(contract.getContractSubject())
                .setContractType(contract.getContractType())
                .setCounteragentId(contract.getCounteragentId())
                .setId(contract.getId());
    }

    public static Contract toContract(ContractDto dto) {
        return new Contract()
                .setContractDate(dto.getContractDate())
                .setContractNumber(dto.getContractNumber())
                .setContractSubject(dto.getContractSubject())
                .setContractType(dto.getContractType())
                .setCounteragentId(dto.getCounteragentId())
                .setId(dto.getId());
    }

    public static ContractListDto toListDto(List<Contract> contracts) {
        return ContractListDto.of(MapperUtils.convert(contracts, ContractDtoMapper::toContractDto));
    }
}
