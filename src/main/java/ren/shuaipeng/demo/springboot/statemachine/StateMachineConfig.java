package ren.shuaipeng.demo.springboot.statemachine;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import javax.annotation.Resource;
import java.util.EnumSet;

/**
 *
 */
@Configuration
@EnableStateMachine
@Log4j2
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<IterationStates, IterationEvents> {

    /**
     * 配置状态机的监听 主要是
     * @param config
     * @throws Exception
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<IterationStates, IterationEvents> config) throws Exception {
        config.withConfiguration().autoStartup(false).listener(listener());
    }

    /**
     * 状态机 流程 配置
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<IterationStates, IterationEvents> states) throws Exception {
        states
            .withStates()
                .initial(IterationStates.Create)
                .states(EnumSet.allOf(IterationStates.class));
    }

    /**
     * 状态梳理
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<IterationStates, IterationEvents> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(IterationStates.Create).target(IterationStates.Create)
                    .event(IterationEvents.CREATE)
                    .and()
                .withExternal()
                    .source(IterationStates.Create).target(IterationStates.Test)
                    .event(IterationEvents.PUSH)
                    .and()
                .withExternal()
                    .source(IterationStates.Test).target(IterationStates.Uat)
                    .event(IterationEvents.PUSH)
                    .and()
                .withExternal()
                    .source(IterationStates.Uat).target(IterationStates.Prod)
                    .event(IterationEvents.PUSH)
                    .and()
                .withExternal()
                    .source(IterationStates.Prod).target(IterationStates.File)
                    .event(IterationEvents.PUSH);
    }

    @Resource
    IterationService service;

    @Bean
    public StateMachineListener<IterationStates, IterationEvents> listener() {
        return new StateMachineListenerAdapter<>() {
            @SneakyThrows
            @Override
            public void stateChanged(State<IterationStates, IterationEvents> from, State<IterationStates, IterationEvents> to) {
                log.info("State Change to " + to.getId());
                switch (to.getId()) {
                    case Create:
                        service.create();
                        break;
                }
            }
        };
    }

    /**
     * 创建迭代分支
     * @return
     */
    public Action<IterationStates, IterationEvents> iterationCreateAction() {
        log.info("创建迭代");
        return null;
    }

    public Action<IterationStates, IterationEvents> iterationPushTestAction() {
        log.info("迭代推进到测试环境");
        return context -> {

        };
    }

    public Action<IterationStates, IterationEvents> iterationPushUatAction() {
        log.info("迭代推进到UAT环境");
        return context -> {

        };
    }

    public Action<IterationStates, IterationEvents> iterationPushProdAction() {
        log.info("迭代推进生产环境");
        return context -> {

        };
    }

    public Action<IterationStates, IterationEvents> iterationPushFileAction() {
        System.out.println("迭代推进到归档环境");
        return context -> {

        };
    }
}
