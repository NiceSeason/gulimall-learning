package io.niceseason.gulimall.product.saga;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.niceseason.common.utils.HttpUtils;
import io.niceseason.gulimall.product.vo.DispatchVo;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Log4j2
@Component
public class SagaCoordinator {


    @Autowired
    SagaInstanceRepository sagaInstanceRepository;

    @Autowired
    SagaStepRepository sagaStepRepository;

    public void executeCreateDispatchSaga(DispatchVo dispatchVo) throws Exception {


        //每一次执行当前saga步骤会创建一条saga数据
        SagaInstance sagaLocal = sagaInstanceRepository.create("DISPATCh_CREATE", dispatchVo.id);
        try{
            //1.调用B服务，并记录进步骤表
            sagaStepRepository.createStep(Long.valueOf(dispatchVo.id), "CALL_B");
            //模拟调用B派工服务器
            HttpResponse response = HttpUtils.doGet("http://localhost:7000/", "dispatch/test", "get", new HashMap<>(), null);
            String resEntity = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            JSONObject responseObject = JSON.parseObject(resEntity);
            //如果上述无错，则继续流程
            //记录状态
            sagaStepRepository.markSuccess(Long.valueOf(dispatchVo.id));

            //人员派出之后修改当前事业部的人员信息。
            log.info("远程派工完成之后，处理当前服务逻辑");

            sagaInstanceRepository.updateStatus(sagaLocal.getId(),SagaStatus.COMPLETED);

        }catch (Exception e){
            sagaInstanceRepository.updateStatus(sagaLocal.getId(),SagaStatus.COMPENSATING);
            throw e;
        }

    }
}
