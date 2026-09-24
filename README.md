# CampusTodo

CampusTodo 是《软件工程》课程中用于练习 Git 与 GitHub 团队协同开发的 Java 项目。项目采用 Java 17、Maven 和 JUnit 5，可直接使用 IntelliJ IDEA 打开。

当前版本：支持新增、列出和按优先级筛选任务。

> 重要：上面这一行是合并冲突实验的固定锚点。只有实验任务明确要求时才修改，且不要提前合并其他同学的文字。

## 1. 初始能力

- `Task`：包含 `id`、`title` 和 `completed` 三个字段。
- `TaskService.addTask(String title)`：新增任务。
- `TaskService.listAll()`：按加入顺序返回任务快照。
- 基线测试：覆盖正常新增任务与空白标题校验。

## 2. 环境要求

- JDK 17
- IntelliJ IDEA 2023.3 或更高版本
- Maven 3.9 或 IntelliJ IDEA 内置 Maven
- Git 2.x

## 3. 打开与验证

1. 解压后，在 IntelliJ IDEA 中选择 **File → Open**，打开本目录中的 `pom.xml`。
2. 在 Project SDK 中选择 JDK 17，等待 Maven 依赖加载完成。
3. 打开 Maven 工具窗口，执行 `Lifecycle → test`；也可在终端执行：

```bash
mvn test
```

初始版本应有 2 个测试通过。

## 4. 团队迭代任务

| Issue | 负责人 | 目标 | 建议分支 |
| --- | --- | --- | --- |
| #1 Priority filter | 开发者 A | 增加 HIGH/MEDIUM/LOW，并支持按优先级筛选 | `feature/1-priority-filter` |
| #2 Complete task | 开发者 B | 按编号完成任务，并处理异常与重复完成 | `feature/2-complete-task` |
| #3 CI and guide | 质量负责人 Q | 增加 Maven CI、PR 模板并完善协作说明 | `feature/3-ci-guide` |

详细验收标准以教师发放的《实验任务书》为准。建议合并顺序为 `#3 → #1 → #2`，以便在 #2 中完成预定的 README 冲突练习。

## 5. Git 起步

本压缩包不包含 `.git` 目录。仓库管理员首次解压后执行：

```bash
git init
git add .
git commit -m "chore: initialize CampusTodo baseline"
git branch -M main
git remote add origin <REPOSITORY_URL>
git push -u origin main
git tag -a v0.1.0 -m "CampusTodo starter baseline"
git push origin v0.1.0
```

## 6. 协作约束

- 一个功能分支只解决一个 Issue，禁止直接向 `main` 提交功能代码。
- 提交信息采用 `<type>: <说明>`，例如 `test: specify task completion rules`。
- Pull Request 必须关联 Issue，并提供测试证据和自检结果。
- 作者不能批准自己的 Pull Request；评审意见处理完毕且 CI 通过后再合并。
- 不提交 `.idea/`、`target/`、访问令牌、账号密码或个人隐私数据。
- 禁止使用 `git push --force` 修改共享的 `main` 分支。

## 7. 协作流程（GitHub Flow）

本仓库采用 GitHub Flow 工作流，一次迭代的完整流程如下：

```
Issue → 建分支 → 小步提交 → Push → Pull Request → CI → Review → 修改 → Approve → Merge → 同步 main → 删除分支
```

### 7.1 日常开发步骤

```bash
# 1. 从最新 main 创建功能分支（分支命名：feature/<issue号>-<简短主题>）
git switch main
git pull --ff-only origin main
git switch -c feature/3-ci-guide

# 2. 小步提交（先测试后实现，type 用 feat/test/docs/fix/ci/chore）
git add src/test && git commit -m "test: specify new behavior"
git add src/main && git commit -m "feat: implement the feature"

# 3. 推送并创建 Pull Request（正文写 Closes #编号）
git push -u origin feature/3-ci-guide
```

### 7.2 持续集成（CI）

仓库配置了 GitHub Actions（`.github/workflows/maven.yml`）：

- 触发条件：push 到 `main`，以及所有针对 `main` 的 Pull Request。
- 执行内容：`mvn -B verify`，即编译 + 全部单元测试。
- 通过标准：PR 的 Checks 页面显示 `build` 绿色，才允许合并。

如果 CI 失败，请打开 PR 页面的 Checks 详情定位失败原因，在同一功能分支上补充提交并推送，CI 会自动重新运行——**不要关闭 PR 重开**。

### 7.3 Pull Request 规范

创建 PR 时会自动加载 PR 模板（`.github/pull_request_template.md`），请完整填写：

1. **关联 Issue**：正文必须包含 `Closes #编号`。
2. **修改说明**：做了什么、为什么。
3. **测试证据**：新增测试数量与本地/CI 测试结果。
4. **自检清单**：逐项勾选确认。

### 7.4 代码评审

- 每名学生至少评审一个非本人 PR，评审意见必须**可验证、可执行**，不能只写 "LGTM"。
- 作者根据评审意见在同一分支上补充提交；所有意见解决、CI 通过、取得至少 1 人 Approve 后才能合并。
- 修改用 Squash and merge 合入 `main`；如遇 README 冲突，须保留双方功能描述后解决。

### 7.5 合并后清理

```bash
git switch main
git pull --ff-only origin main
git branch -d feature/3-ci-guide
git fetch --prune
```
