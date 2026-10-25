# CampusTodo 上下文映射图与聚合清单

> 提交：Lab3（事件风暴 / 上下文映射 / 聚合梳理）
> 维护：王（SM）+ 团队
> 说明：项目为单体演进式架构，本图是**当前实现 + 规划演进**的映射，用于识别核心域与聚合边界。

---

## 1. 上下文映射图

```mermaid
graph LR
    subgraph 前端层
        UI[Web UI 页面]
    end

    subgraph CampusTodo 核心上下文（Core Domain）
        API[REST API 控制器<br/>edu.hbuas.campustodo.controller]
        AppSvc[应用服务<br/>TaskService]
        TaskAgg[聚合：Task<br/>id / title / priority / completed]
    end

    subgraph 支撑上下文（Supporting）
        CI[GitHub Actions CI<br/>mvn verify + 质量门禁]
        Ops[部署与监控<br/>Sprint4: Docker Compose + Prometheus + Grafana]
    end

    UI -->|HTTP| API
    API --> AppSvc
    AppSvc --> TaskAgg

    TaskAgg -->|后续持久化| DB[(MySQL via Flyway)]
    AppSvc -.->|指标| Ops
    CI -.->|门禁反馈| API
```

## 2. 核心域识别

- **核心域（Core Domain）**：任务管理（Task Management）—— 产品价值所在。
- **支撑域（Supporting）**：CI/质量门禁、部署与监控 —— 支撑交付质量与可观测性。
- **通用域（Generic）**：Spring Boot 框架能力、日志、异常处理 —— 使用现成方案。

## 3. 聚合（Aggregates）清单

| 聚合 | 根实体 | 关键字段 | 业务规则（来自用户故事） |
|---|---|---|---|
| Task | Task | id, title, priority(HIGH/MEDIUM/LOW), completed | 标题不能为空白；按 id 完成，不存在或重复完成需明确处理；优先级仅三档 |
| （规划）TaskList | TaskList | id, name, taskIds | 支持按列表组织任务（后续迭代，当前由 Task 聚合的查询承担） |

> 聚合规则：Task 是不变式边界——对任务的所有修改（创建/完成/编辑/删除/筛选）都经由 Task 聚合根或聚合内的仓储完成，保证一致性与事务边界（单体阶段由 Service 方法保证）。

## 4. 事件风暴产出（关键业务事件）

| 事件 | 触发 | 消费方 |
|---|---|---|
| TaskCreated | 创建任务成功 | 列表/看板刷新 |
| TaskCompleted | 完成任务成功 | 完成进度统计 |
| TaskEdited | 编辑标题成功 | 列表/看板刷新 |
| TaskDeleted | 删除任务成功 | 列表/看板刷新 |
| TaskFiltered | 按优先级筛选 | 前端视图 |

## 5. 聚合与看板卡片对应

| 看板卡片（Backlog） | 聚合/用例 | Gherkin 文件 |
|---|---|---|
| Create a new todo task | Task 创建 | `docs/backlog/stories/001-create-task.feature` |
| Complete a task | Task 完成 | `docs/backlog/stories/002-complete-task.feature` |
| Filter tasks by priority | Task 筛选 | `docs/backlog/stories/003-filter-priority.feature` |
| View task list and details | Task 查询 | `docs/backlog/stories/004-view-task-list.feature` |
| Edit or delete a task | Task 编辑/删除 | `docs/backlog/stories/005-edit-delete-task.feature` |

---

## 变更记录

| 版本 | 日期 | 修改内容 | 修改人 |
|---|---|---|---|
| v1.0 | 2026-10-25 | 初始版本：上下文映射、聚合清单、事件清单 | 王（SM）整理，团队确认 |
