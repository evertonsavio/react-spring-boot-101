### Verificar quantos branch vcs tem:  
```
 git branch  
```
* Master branch é onde fica a aplicação estavel. No mundo real Master é tudo que foi testado, "stable" regression test, integration test, unity test etc.


### Os desenvolvedores trabalham nos branches, para criar um novo branch:
```
git branch nomedobranch
```  
### Para trocar de branch: 
```git checkout nomedobranch```   

### Para adicionar as mudancas   
```git add . ```   
### Para Commit   
```git commit -m 'Mensagem'   
### Para Push   
```git push```   
### Para voltar pro master e dar merge nos branch  
```git checkout master```  
```git pull```     
```git merge nomedobranch```   
```git push```   