package com.itclub.demo.activiti.controller;

import com.itclub.common.core.domain.AjaxResult;
import com.itclub.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述
 *
 * @author: onePiece
 * @date: 2023-02-22
 */
@RestController
@RequestMapping("demo")
@Slf4j
public class ProcessController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    /**
     * 查询所有发布的流程列表
     *
     * @return
     */
    @GetMapping("/publishList")
    public AjaxResult publishList() {
        List<ProcessDefinition> list = repositoryService
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        for (ProcessDefinition item : list) {
            System.out.println("========");
            System.out.println(item.getDeploymentId());
            System.out.println(item.getId());
            System.out.println(item.getKey());
            System.out.println(item.getName());
        }
        return AjaxResult.success();
    }

    /**
     * 开启流程
     *
     * @return
     */
    @GetMapping("/startProcess")
    public AjaxResult startProcess(@RequestParam("key") String key) {
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(key);
        if (processInstance != null) {
            System.out.println("=============");
            System.out.println(processInstance.getName());
            System.out.println(processInstance.getProcessDefinitionKey());
            System.out.println(processInstance.getId());
        }
        return AjaxResult.success();
    }

    /**
     * 查询正在运行的流程列表
     *
     * @return
     */
    @GetMapping("/runningList")
    public AjaxResult runningList(@RequestParam("instanceId") String instanceId,
                                  @RequestParam("key") String key) {
        List<ProcessInstance> list = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(instanceId)
                .processDefinitionKey(key)
                .list();
        list.forEach(item -> {
            System.out.println("============");
            System.out.println(item.getBusinessKey());
            System.out.println(item.getDeploymentId());
            System.out.println(item.getStartTime());
            System.out.println(item.getProcessDefinitionKey());
            System.out.println(item.getName());
        });
        return AjaxResult.success();
    }

    /**
     * 查询任务并且完成
     *
     * @param instanceId
     * @param key
     * @param assignee
     * @return
     */
    @GetMapping("accomplishTask")
    public AjaxResult accomplishTask(@RequestParam("instanceId") String instanceId,
                                     @RequestParam("key") String key,
                                     @RequestParam("assignee") String assignee) {
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskAssignee(assignee)
                .processInstanceId(instanceId)
                .list();
//        设置任务id
        // taskService.setVariableLocal("862e5029-31e4-11ed-ab1a-005056c00008", "num", "4");
        // executionId 就当是processInstanceId
        //  runtimeService.setVariableLocal("862b1bd5-31e4-11ed-ab1a-005056c00008", "num", 3);
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
//            taskService.complete(task.getId());
            System.out.println("==========");
            System.out.println(task.getId());
            System.out.println(task.getName());
            System.out.println(task.getAssignee());
            System.out.println(task.getProcessDefinitionId());
            System.out.println(task.getProcessInstanceId());
            System.out.println(task.getTaskDefinitionKey());
            System.out.println(task.getExecutionId());
        }

        return AjaxResult.success();
    }

    /**
     * 判断进程是否结束
     *
     * @param instanceId
     * @return
     */
    @GetMapping("ProcessFinish")
    public AjaxResult ProcessFinish(@RequestParam String instanceId) {
        HistoricProcessInstance historicProcessInstance = historyService
                .createHistoricProcessInstanceQuery()
                .processInstanceId(instanceId)
                .singleResult();
        if (historicProcessInstance != null) {
            if (historicProcessInstance.getEndTime() != null) {
                System.out.println("流程已结束！");
            } else {
                System.out.println("流程未结束！");
            }
        } else {
            System.out.println("流程不存在！");
        }
        return AjaxResult.success();
    }

    /**
     * 查看审批进程
     *
     * @param instanceId
     * @return
     */
    @GetMapping("viewApproval")
    public AjaxResult viewApproval(@RequestParam String instanceId) {
        // 历史节点
        List<HistoricActivityInstance> list = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(instanceId)
//                .orderByActivityId()
//                .desc()
                .orderByHistoricActivityInstanceEndTime()
                .asc()
                .finished()
  //              .unfinished()
                .list()
                .stream()
                .filter(item -> !StringUtils.containsAny(item.getActivityType(), "inclusiveGateway", "parallelGateway"))
                .collect(Collectors.toList());
        // 历史变量
        for (int i = 0; i < list.size(); i++) {
            System.out.println("========");
            HistoricActivityInstance historicActivityInstance = list.get(i);
//            System.out.println(historicActivityInstance.getActivityId());  // _2
            System.out.println(historicActivityInstance.getActivityName());
            String taskId = historicActivityInstance.getTaskId();
            System.out.println(taskId);
            String result = "";
            if (StringUtils.isNotEmpty(taskId)) {
                HistoricVariableInstance historicVariableInstance = historyService.createHistoricVariableInstanceQuery().taskId(taskId).variableName("result").singleResult();
                result = (String) historicVariableInstance.getValue();
                // 已经经过的节点不适用该方法查询变量
//                result = (String) taskService.getVariableLocal(taskId, "result");
            }
            System.out.println(historicActivityInstance.getAssignee() + "----" + result);
            System.out.println(historicActivityInstance.getStartTime());
            System.out.println(historicActivityInstance.getEndTime());
//            System.out.println("ExecutionId:" + historicActivityInstance.getExecutionId());
//            System.out.println("ProcessInstanceId:" + historicActivityInstance.getProcessInstanceId());
//            System.out.println(historicActivityInstance.getActivityType()); // userTask
        }
        return AjaxResult.success();
    }

    /**
     * 申请任务
     * @param key
     * @param assignee
     * @return
     */
    @GetMapping("applyTask")
    public AjaxResult applyTask(@RequestParam("key") String key,
                                @RequestParam("assignee") String assignee){
        if (StringUtils.isBlank(key)) {
            throw new RuntimeException("key不能为空！");
        }
        if (StringUtils.isBlank(assignee)) {
            throw new RuntimeException("assignee不能为空！");
        }
        Task task = taskService.createTaskQuery()
                .taskCandidateUser(assignee)
//                .taskId(taskId)
                .processInstanceBusinessKey(key)
                .singleResult();
        if (task != null) {
            taskService.claim(task.getId(), assignee);
            log.info(assignee + "申领到了任务！");
        }
        return AjaxResult.success();
    }

    /**
     * 放弃任务
     * @param key
     * @param assignee
     * @return
     */
    @GetMapping("abandonTask")
    public AjaxResult abandonTask(@RequestParam("key") String key,
                                  @RequestParam("assignee") String assignee){
        if (StringUtils.isBlank(key)) {
            throw new RuntimeException("businessKey不能为空！");
        }
        if (StringUtils.isBlank(assignee)) {
            throw new RuntimeException("assignee不能为空！");

        }
        Task task = taskService.createTaskQuery()
                .taskAssignee(assignee)
//                .taskId(taskId)
                .processInstanceBusinessKey(key)
                .singleResult();
        if (task != null) {
            taskService.setAssignee(task.getId(), null);
            log.info(assignee + "放弃了任务！");
        }
        return AjaxResult.success();
    }
}
