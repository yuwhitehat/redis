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
### git的工作区和暂存区
工作区就是能够看到的目录，工作区有一个隐藏目录`.git`，这个不算工作区，而是Git的版本库。版本库里存放了一个名为`stage`的暂存区，
还有`git`为我们创建的分支`master`，以及指向指向`master`的指针`HEAD`
1. 在执行`git add`的命令时，就是将改动的文件放入暂存区
2. 执行`git commit`后，会将暂存区的文件添加到当前分支
