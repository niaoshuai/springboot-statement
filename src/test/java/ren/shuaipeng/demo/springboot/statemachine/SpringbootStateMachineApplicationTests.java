package ren.shuaipeng.demo.springboot.statemachine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import reactor.core.publisher.Mono;

/**
 * https://www.freesion.com/article/14061074929/
 * https://www.jianshu.com/p/9ee887e045dd
 */
@SpringBootTest
class SpringbootStateMachineApplicationTests {


	@Autowired
	StateMachine<IterationStates,IterationEvents> stateMachine;

	@Test
	void iterationPush() {
		stateMachine.startReactively().subscribe();
		// 推进一次 到 提测
		MessageBuilder<IterationEvents> messageBuilder = MessageBuilder
				.withPayload(IterationEvents.PUSH)
				.setHeader("BusinessId", 9999);
		stateMachine.sendEvent(Mono.just(messageBuilder.build())).subscribe();
		// 推进一次 到 UAT
		messageBuilder = MessageBuilder
				.withPayload(IterationEvents.PUSH)
				.setHeader("BusinessId", 9999);
		stateMachine.sendEvent(Mono.just(messageBuilder.build())).subscribe();
		// 推进一次 到 生产
		messageBuilder = MessageBuilder
				.withPayload(IterationEvents.PUSH)
				.setHeader("BusinessId", 9999);
		stateMachine.sendEvent(Mono.just(messageBuilder.build())).subscribe();
		// 推进一次 到 归档
		messageBuilder = MessageBuilder
				.withPayload(IterationEvents.PUSH)
				.setHeader("BusinessId", 9999);
		stateMachine.sendEvent(Mono.just(messageBuilder.build())).subscribe();
	}
}
