package ru.aora.erp.repository.gateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class GatewayUtils {

    public static final Integer DEACTIVATED_FLAG = 1;

    static <K, V> Function<List<V>, Map<K, V>> groupFunctions(Function<V, K> keyExtractor) {
        return list -> {
            Map<K, V> keyValMap = new HashMap<>();
            for (V val : list) {
                keyValMap.put(keyExtractor.apply(val), val);
            }
            return keyValMap;
        };
    }
}
