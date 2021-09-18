package ren.shuaipeng.demo.springboot.statemachine;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Transactional(rollbackFor = Exception.class)
@Service
@Log4j2
public class IterationService {

    public void create() throws InterruptedException {
        log.info("迭代创建");
        TimeUnit.SECONDS.sleep(10);
    }
}
