package com.cdt.consumer;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.cdt:producer:+"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ListenerTest {

  @Autowired
  StubTrigger stubTrigger;

  @Autowired
  UserPresenceListener userPresenceListener;

  @Test
  public void shouldReceiveNotification() {
    stubTrigger.trigger("user-goes-online");

    assertTrue(this.userPresenceListener.getAvailableUsers().size() > 0);
  }
}
