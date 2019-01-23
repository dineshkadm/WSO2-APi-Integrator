package com.hmrc.dk.wiremock;


import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import java.io.IOException;
import java.nio.charset.Charset;

import com.google.common.io.Resources;
import org.junit.Test;


public class BackEndSoapServiceStubTest {

    @Test
    public void configureMockTest() throws Exception {

        String happyPathresponseXml;
        String errorPathresponseXml;
        try {
            happyPathresponseXml = Resources.toString(Resources.getResource("xml/mockFiles/HappyPathXMLSOAPResponse.xml"),
                    Charset.defaultCharset());
            errorPathresponseXml = Resources.toString(Resources.getResource("xml/mockFiles/ErrorXMLSOAPResponse.xml"),
                    Charset.defaultCharset());

        } catch (IOException e) {
            throw e;
        }


        stubFor(post(urlMatching("/mock1")).atPriority(3)
                .withRequestBody(containing("<company>SKY</company>"))
                .   willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody(happyPathresponseXml)));


        stubFor(post(urlMatching("/mock1")).atPriority(3)
                .withRequestBody(containing("<company>BT</company>"))
                .   willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "text/xml")
                        .withBody(errorPathresponseXml)));

    }

}
