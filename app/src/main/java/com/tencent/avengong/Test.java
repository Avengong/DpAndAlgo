package com.tencent.avengong;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Test {

    public Test() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.schedule();


        HashMap<String, Map<String, Long>> mapHashMap = new HashMap<>();

        Map<String, List<Double>> responseTimes = new HashMap<>();
        Map<String, Map<String, Double>> stats = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : responseTimes.entrySet()) {
            String apiName = entry.getKey();
            List<Double> apiRespTimes = entry.getValue();
            stats.putIfAbsent(apiName, new HashMap<String, Double>());
            stats.get(apiName).put("max", 22d);
            stats.get(apiName).put("avg", 44d);
        }

    }
}
