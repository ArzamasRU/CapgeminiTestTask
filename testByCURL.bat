curl localhost:8080/api/projects
curl -i -d "name=prj1" localhost:8080/api/projects
curl localhost:8080/api/projects
curl localhost:8080/api/projects/1
curl -T "%cd%\params.txt" localhost:8080/api/projects/1
curl localhost:8080/api/projects
curl -X DELETE localhost:8080/api/projects/1
curl localhost:8080/api/projects
pause

