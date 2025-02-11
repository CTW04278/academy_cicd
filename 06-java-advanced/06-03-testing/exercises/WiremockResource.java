package com.ctw.workstation.rack.test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

public class WiremockResource implements QuarkusTestResourceLifecycleManager {

    WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        if (wireMockServer == null) {
            wireMockServer = new WireMockServer();
            wireMockServer.start();
            WireMock.configureFor(new WireMock(wireMockServer));
        }
        return Map.of("external-api.url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        wireMockServer.stop();
    }
}
