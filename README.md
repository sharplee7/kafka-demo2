# kafka-demo
##시나리오
![](images/tag-v1.0-h2.png)
1. Publish
   1. http://localhost:8080/pub/swagger-ui/에 접속해 EmpDTO 내용 등록(empNo, empName, detpNo)  
   2. EmpService의 insertEmp 호출, EmpService는 EmpDTO 등록시, EmpRepository를 통해 H2DB에 EMP 내용 등록 및 EmpPublisher를 통해 Kafka로 EmpDTO 내용 publish  
   3. EmpRepository를 통해 H2DB에 EmpDTO 등록  
   4. EmpPublisher를 통해 Kafka에 EmpDTO Publish  
   5. Kafka로 EmpDTO 내용 Sirialize 시켜 전달  
2. Subscribe  
   6. Kafka에 EmpDTO 신규 등록 내용 캐치  
   7. EmpSubscriber가 Kafka에 신규 등록된 EmpDTO Subscribe  
   8. EmpSubscriber가 EmpService를 호출 해 insertEmp(EmpDTO) 실행  
   9. EmpService를 통해 EmpDTO를 H2DB에 등록  

###사용 환경
1. jdk 1.8
2. gradle
3. spring boot 2.2.6
4. mybatis
5. swagger 2.9
6. h2db (in-memory)
7. mongodb (in-memory)
8. kafka_2.12-2.1.1 (https://archive.apache.org/dist/kafka/2.1.1/kafka_2.12-2.1.1.tgz)
   단, h2db는 git checkout 1.0-h2 으로 체크아웃해서 사용
   mongodb는 git checkout 2.0-mongo로 체크아웃해서 사용

###개발환경 구성
1. git clone <url> 으로 개발소스 복제
2. kafka 다운로드
3. kafka 설정
   1. %KAFKA_HOME%\config\ 폴더에 있는 zookeeper.properties 파일을 열어 dataDir=/tmp/zookeeper 항목을 dataDir=C:/temp/zookeeper 로 변경

###프로그램 빌드
1. 전체 프로젝트 빌드
   gradlew build
2. 모듈별 빌드
   gradlew :<모듈명>:build

###프로그램 실행
1. zookeeper 실행
   1. 새로운 윈도우 코맨드 창을 열어 kafka가 설치된 디렉토리로 이동
   2. 윈도우라면 다음과 같이 bin\windows\zookeeper-server-start.bat를 실행
   3. 이때 입력 파라미터로 %KAFKA_HOME%\config\zookeeper.properties의 경로명과 파일명을 함께 넘길 것
   4. e.g. start/b C:\kafka_2.12-2.1.1\bin\windows\zookeeper-server-start.bat C:\kafka_2.12-2.1.1\config\zookeeper.properties
2. kafka 실행
   1. 새로운 윈도우 코맨드 창을 열어 kafka가 설치된 디렉토리로 이동 
   2. 윈도우라면 다음과 같이 bin\windows\kafka-server-start.bat를 실행
   3. 이떄 입력 파라미터로 %KAFKA_HOME%\config\server.properties를 넘길 것
   4. e.g. start/b C:\kafka_2.12-2.1.1\bin\windows\kafka-server-start.bat C:\kafka_2.12-2.1.1\config\server.properties
3. kafka-demo/kafka-pub/src/main/java/KafkaPubApplication을 찾아 실행
4. kafka-demo/kafka-sub/src/main/java/KafkaSubApplication을 찾아 실행

###프로그램 테스트
1. http://localhost:8080/pub/swagger-ui.html 호출
   1. [GET] /employee 를 클릭해 try it out >> Execute 를 실행해 데이터가 1건 등록되어 있는 것을 확인한다.(기본으로 한 건 등록되어 있다.)
   2. [POST] /employee 를 클릭해 try it out >> Execute 를 실행해 임의의 데이터를 넣고 저장한다.
   3. [GET] /employee 를 다시 실행해 데이터가 2건 등록되어 있는 것을 확인한다.
2. http://localhost:8080/sub/swagger-ui.html 호출
   1. [GET] /employee 를 클릭해 try it out >> Execute 를 실행해 pub서비스에서 방금 입력한 데이터 1건이 전송되어 온것을 확인한다.
