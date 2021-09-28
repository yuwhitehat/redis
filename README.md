## 练习git

### `git status`
```
git status 帮助我们掌握仓库当前的状态
```
### `git diff`
```
git diff 帮助我们查看具体的改动
```
### `git log`
```
git log 可以查看历史记录
```
### `git reset --hard HEAD^`
`HEAD`表示当前版本
```
git reset --hard HEAD^ 表示回退到上一个版本
git reset --hard HEAD^^ 表示回退到上上个版本
...
git reset --hard HEAD~100 表示回退到前100个版本
git reset --hard commit_id 表示到具体的某个版本（commit id表示版本号，是一个SHA1计算出来的一个非常大的数字，用十六进制表示）
```
### `git reflog`
```
git reflog 查看历史命令
```