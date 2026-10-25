# CampusOverflow 团队章程 v1.0

> 仓库：`baga1234-q/campus-todo-team-four`
> 版本：v1.0　|　状态：生效中　|　适用：Sprint 0 - Sprint 4（软件工程课程实验）

---

## 1. 团队标识

| 项 | 内容 |
|---|---|
| 团队名称 | CampusOverflow |
| 课程 | 软件工程（团队协作 Git/GitHub 实验） |
| 项目名称 | CampusTodo（campus-todo-team-four） |
| 仓库地址 | https://github.com/baga1234-q/campus-todo-team-four |
| 项目看板 | https://github.com/users/xh314159261/projects/2（CampusTodo Team Board） |

## 2. 成员与角色

| 成员 | 角色 | 主要职责 |
|---|---|---|
| 小涵（xh314159261） | PO（Product Owner） | 维护 Backlog、定义用户故事与验收标准、代表客户做优先级决策 |
| 王 | SM（Scrum Master）+ Developer（兼职 QA） | 主持仪式（站会/Review/Retro）、保障流程、看板管理、质量检查（QA）、参与开发 |
| 龙 | Developer（后端 1） | 后端功能实现与测试（Task 领域相关） |
| 何 | Developer（后端 2） | 后端功能实现与测试（CI、基础设施相关） |
| 从 | Developer（前端） | 前端交互与联调 |

> 说明：角色为基线分工；每个人仍以 **Developer** 身份参与编码，禁止只写文档不碰代码。

## 3. 团队目标

1. 完成 CampusTodo 全部迭代需求（创建、完成、优先级筛选、查看、编辑/删除任务）。
2. 实践 GitHub Flow 协作：分支、PR、评审、CI 全流程真实落地。
3. 产出完整的软件工程过程文档（章程、用户故事、评审清单、回顾纪要等）。

## 4. 工作协议（团队约定）

### 4.1 完成定义（DoD）
一个 Backlog 项视为"完成"，必须同时满足：

- [ ] 功能代码已实现
- [ ] 单元测试已编写并通过
- [ ] 代码评审通过（至少 1 人 Approve，作者本人不能批准自己的 PR）
- [ ] PR 已合并到 `main`
- [ ] 相关文档已更新

### 4.2 Sprint 节奏

- 每个 Sprint 开始时做 Sprint Planning，确定目标与范围。
- 每个工作日（上课日）举行 **15 分钟站会**：昨天做了什么 / 今天做什么 / 有无阻塞。
- 每个 Sprint 结束举行 **Review（成果演示）与 Retro（回顾）**，回顾模板见 `docs/retro/`。
- 每个 Sprint 结束可**投票轮换 PO / SM**，票数优先者担任下一轮。

### 4.3 会议纪律

- 所有会议、头脑风暴、PR 评审要求**全员参加**。
- 阻塞问题**及时标记在看板上**，由 SM 组织沟通解决，不允许私下积压。
- 会议结论当天同步到群聊/文档，避免口头约定。

## 5. 协作规范

### 5.1 分支策略（GitHub Flow）

- 禁止直接向 `main` 提交功能代码。
- 一个功能分支只解决一个 Issue，命名：`feature/<issue号>-<简短主题>`，如 `feature/2-complete-task`。
- 合并后及时删除已合并的功能分支。

### 5.2 提交信息规范

- 格式：`<type>: <说明>`，type 取 `feat / fix / test / docs / ci / chore / refactor`。
- 示例：`feat: implement task completion`、`test: specify completion rules`、`docs: add team charter`。
- 不做无意义的提交（如 `update`、`asdf`、`fix bug`）。

### 5.3 Pull Request 规范

- PR 必须关联 Issue（正文含 `Closes #编号`）。
- PR 必须提供：修改说明、测试证据、自检清单。
- PR 模板：`.github/pull_request_template.md`（已配置）。
- 作者不能批准自己的 PR；所有评审意见解决 + CI 通过 + 至少 1 人 Approve 后才可合并。
- 合并方式：Squash and merge。

### 5.4 持续集成（CI）

- 触发：push 到 `main` 及针对 `main` 的所有 PR。
- 内容：`mvn -B verify`（编译 + 全部单元测试）。
- 通过标准：PR 的 Checks 显示 `build` 绿色才允许合并。

### 5.5 代码评审

- 每名成员至少评审一个**非本人**的 PR。
- 评审意见必须**可验证、可执行**，禁止只写 "LGTM"。
- 评审清单见 `docs/team/review-checklist.md`。

## 6. 安全与红线

- 不提交 `.idea/`、`target/`、访问令牌、账号密码、个人隐私数据。
- 禁止 `git push --force` 修改共享的 `main` 分支。
- 遇到合并冲突：保留双方功能描述后解决，不得覆盖他人成果。
- 实验任务书明确要求修改的内容（如 README 固定锚点）按任务执行，不提前改动。

## 7. 变更记录

| 版本 | 日期 | 修改内容 | 修改人 |
|---|---|---|---|
| v1.0 | 2026-10-25 | 初始版本：角色分工、工作协议、协作规范 | 王（SM）起草，全员确认 |
