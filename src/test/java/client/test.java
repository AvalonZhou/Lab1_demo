package client;

import com.magq.mq.client.Stage;
import com.magq.mq.client.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 测试MQ
 */
public class test {

    private static final Logger logger = LoggerFactory.getLogger(test.class);
    private Stage Stage_0 = new Stage("Stage_0");
    private Stage Stage_1 = new Stage("Stage_1");
    private User user_0 = new User("Alice");
    private User user_1 = new User("Tom");
    private User user_2 = new User("Jerry");

    @Test
    public void testStage() throws Exception{
        Stage_0.produce("计网作业提交提醒");
        Stage_0.produce("计网实验延期");
        Stage_1.produce("操作系统调课申请投票");
        Stage_1.produce("操作系统读书笔记作业发布");
        logger.info("SEND SUCCESS!");
    }

    @Test
    public void testUser() throws Exception{
        user_0.subscribe("Stage_0");
        user_1.subscribe("Stage_1");
        user_2.subscribe("Stage_0");
        user_2.subscribe("Stage_1");
        List<String> messageList_0=user_0.get();
        List<String> messageList_1=user_1.get();
        List<String> messageList_2=user_2.get();
        logger.info(user_0.getName()+"收到的信息列表为"+String.valueOf(messageList_0));
        logger.info(user_1.getName()+"收到的信息列表为"+String.valueOf(messageList_1));
        logger.info(user_2.getName()+"收到的信息列表为"+String.valueOf(messageList_2));
    }
}
