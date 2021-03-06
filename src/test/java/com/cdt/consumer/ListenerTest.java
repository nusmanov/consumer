package com.cdt.consumer;


import static org.assertj.core.api.Assertions.assertThat;

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

  /** Stub Runner will mimic the RabbitMQ broker message flow in memory, being guided by the Contract.
  * Explicitly enable the Stub Runner for AMQP protocol in applicaiton.yml
  *
  **/
  @Autowired
  StubTrigger stubTrigger;

  @Autowired
  UserListener userListener;

  @Test
  public void shouldReceiveNotification() {

    /** trigger method launches the required contract scenario.
    * We will refer to the particular part of the scenario via label that was specified way back in the YAML.
    * Within this integration test, the StubRunner will:
    *   - find the proper AMQP binding
    *   - configure in-memory RabbitMQ mock and create the necessary RabbitMQ client infrastructure behind the scenes.
    * Shortly, this will allow the UserPresenceListener to handle AMQP messages as if they were routed by the proper RabbitMQ broker.
    */
    stubTrigger.trigger("user-goes-online");

    /** To test the listener, we just trigger the message, and make sure that the number of the available users
    * is not empty, thus indicating a delivered message.*/

    assertThat(this.userListener.getAvailableUsers()).hasSize(1);
    assertThat(this.userListener.getAvailableUsers().get(0).getUser()).isEqualTo("joe");
    assertThat(this.userListener.getAvailableUsers().get(0).getId()).isEqualTo(987);


  }
}
