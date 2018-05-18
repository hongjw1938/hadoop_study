##  하둡
>###    1. 설치
        * 가상머신(Oracle VM)
            - Host(Wm)
            - Guest(Linux)
            - 가상 머신의 네트워크카드는 호스트와 연결
            - 가상 컴퓨터는 논리적으로 네트워크 요청시 Host(NIC)를 통해서 요청
        * NAT : network address translation
        * 네트워크 카드 설정
            - Host
            - Bridge
            - 내부 네트워크
        * 완전분산모드(Fully distributed)
            - Host컴퓨터에 Guest를 여러개 만드는 방법(Ram이 많이 필요)
            - Host컴퓨터 여러개에 각각 Guest를 만드는 방법
                - 이 경우, NAT에 의해 주소가 같을 수도 있음. 하지만 서로 다른 Guest컴퓨터이기 때문에 서로 연결이 안됨.
                - 이 때, 포트포워딩을 해주면 Guest와 Host를 각각을 연결시킬 수 있다.
                - 포트포워딩을 사용하지 않는다면 Guest를 브릿지로 만들고 다른 Host컴퓨터들을 사용해 서로 연결 가능
>###    2. 실행 후 설정
        - 가상머신의 게스트 디스플레이 크기 조정으로 전반적 화면 조정 후 리부팅
        - ova파일을 불러오기 하여 가상머신 사용
        - Terminal을 켜면 명령어 입력해 사용가능
        - su - 명령어로 substitute user 가능. -뒤에 아무것도 입력 없을 시 root유저로 이동
        - ifconfig를 통해 네트워크 정보 확인 가능
            >> eht1 - inet addr의 내용을 이용해 putty로 windows환경에서 접속 가능
            >> Linux TUI환경이 불편한 부분 해소 가능
            * putty 색상 적용은 상단바 우클릭 후 colour에서 설정 변경
>###    3. Network 기본
        * 참조 링크 : http://shinb.tistory.com/20
        * 기본 개념
            - Internet에 통신하기 위해서는 NIC(Network Interface Card)가 필요함.
            - 각 Host컴퓨터는 고정Ip를 가지고 있어 이를 가지고 통신함
            - Guest컴퓨터의 Ip는 192.168.56.101과 같이 직접적으로 사용할 수는 없는 내부적으로 사용할 ip를 할당받음
            - 따라서 Guest컴퓨터가 internet에 통신하기 위해서는 Host를 통해서 통신을 시도함
            - 실제 통신을 할 때는, 정보를 요청할 서버(목적지 ip)와 보내는 ip의 정보를 다 같이 보내야함
            - 패킷 단위로 전송됨
            - 돌아올 때도 마찬가지임
            - Host, Guest설정을 하는 경우 4가지 설정이 가능
        * 설정
            - NAT
                * NAT 란 Network Address Translation 의 약자로써 네트워크 주소 변환을 의미합니다. 기본적으로 192.168.x.x 와 같은 사설(Private) IP 주소로는 외부와 통신(인터넷 연결 등) 할 수 없습니다. 외부와 통신할 수 있는 IP 주소는 오직 인터넷 IP 주소 관리 기관에서 공식적으로 발급한 공인(Public) IP 주소 뿐 입니다.
                >> NAT은 network address translation으로 네트워크 주소를 번역해서 보내는 것임
                >> 이 때, Guest는 직접 internet에 통신할 수 없기 때문에 Host를 통해서 고정ip로 변환하여 서버와 통신함
                >> Host는 정보를 요청한 후 되돌아올 때, 본인이 요청한 것인지, Guest가 요청한 것인지 확인하고 정보를 보냄
            - Bridged
                >> Bridged방식으로 설정하면 Host와 Guest가 같은 레벨에서 통신이 가능해짐
                >> 공유기와 같은 경우로 연결된 상황에서는 공유기 자체는 고정 ip를 가지는데 각각의 공유기에 연결된 Host들은 192.168.**.**으로 연결됨
                >> 이 때, Guest와 같은 경우도 192.168.**.**으로 지정되기 때문에 마지막 **을 제외한 3번째 **까지 네트워크 주소를 일치시킬 경우 공유기를 통해서 직접적으로 Internet에 통신할 수 있음
                >> 또한 Guest와 Host가 서로 통신할 수도 있음
                >> 이러한 설정방식이 브릿지
                >> 이 방식으로 마치 2개의 네트워크를 하나처럼 통신가능
            - Host only
                >> Host-only 네트워킹이란 호스트의 네트워크 아래에 사설망을 꾸며 이 사설망에 가상 머신들을 두는 구조를 가지고 있는 방식입니다. 단 이 사설망은 NAT 네트워킹과는 달리 NAT 라우터가 연결되어 있지 않기 때문에 게스트 컴퓨터에서는 호스트 컴퓨터를 제외한 외부로의 연결은 불가능합니다.
            - 내부 네트워크
                >> Guest끼리만 서로 연결하는 경우, Host와는 통신 불가
>###    4. putty로 연결 후 사용
        * 명령어
            - echo $SHELL : 우리가 사용하는 shell을 알 수 있음.(bash 사용중)
            - ls(list), ls -al(전체 리스트를 디렉토리까지), ls -al 파일명(해당 파일만)
            - more 파일명 : 파일 내용 확인 가능
            - groupadd name : 그룹 생성
            - useradd name -g gname : gname이름의 그룹에 user생성
            - ls /home : group들 확인 가능
            - passwd hadoop : hadoop group의 비밀번호 변경
            - alias : 별칭
            - cd : 디렉토리 변경, cd - : 이전 디렉토리로 이동
            - -R option : Recursive, 재귀적으로 명령 진행
            - chown : 소유주, 그룹 변경 --> chown -R 소유주명:group명 경로
            - su - username : user 변경
            - tar xvzf : tar는 파일을 묶는 것, x면 압축 풀기, c는 압축, v는 과정을 보여줌(verbose), z는 zip임을 알려줌
            - ln : 링크생성, ln -s : simbolic link --> 이 링크를 통해 가리키는 것만 변경하면 환경설정이 바뀌거나 버젼이 바뀌더라도 쉽게 사용 가능
            - vi : 편집기
                >> 편집모드 집입 : i, a, o, I, A, O
                >> 명령모드 : esc, 종료시에는 :wq -- 저장후 종료, :q는 그냥 종료, :q! 강제 종료
                >> ex모드
            - ps -ef | grep xxx : 각 라인 별로 xxx가 있는 것만, |는 파이프 명령어
        * 하둡 관련 환경변수 설정
            - [hadoop@edydr1p0 hadoop]$ vi ~/.bash_profile       
            - (제일 아랫쪽에 아래의 내용을 삽입)
            - export HADOOP_HOME=/opt/hadoop/hadoop
            - export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
            >> 환경변수 설정한 것을 사용하고 싶은 경우 $기호를 앞에 붙임
            >> :을 통해 path를 구분함, 우선순위가 있으면 앞에 쓸 것, 변수를 지정시에는 =사이에 공백 있으면 안됨.
            >> 소프트 심볼릭 링크를 path에 연결해두는 것이 버젼 변경에도 유지보수에 편리함.
        * 하둡 1.2.1다운로드
            - hadoop.apache.org에서 mirror사이트 접속해 hadoop-1.2.1.tar.gz다운로드
            - WinSCP프로그램을 다운받으면 window commander를 사용할 수 있다. hadoop계정을 만든다.(호스트에는 ip주소)
            - 해당 커맨더를 사용해 windows의 프로그램을 복사해 가상 머신으로 보낼 수 있다.
        * SSH설정(공개키 및 개인키 설정)
            - ps -ef | grep ssh명령어를 터미널에서 실행시 sshd(d는 데몬, 서버라고 부름, windows에서는 service라고 부름)항목이 있는지 확인 > 서버 작동 중인지 확인
            * PKI(public key infrastructure : 공개키 기반구조)
                - 공개 키 암호 방식을 바탕으로 한 디지털 인증서 활용하는 소프트웨어
                - 공개 키로 작성한 내용은 개인키로만 풀 수 있고, 개인키로 작성하면 공개키로만 풀 수 있다. 이를 통해 부인 부정을 구현
            - cd로 root로 돌아간 후, ssh-keygen -t rsa로 공개키, 개인키 설정을 함
            - 인가된 키 목록에 공유키 추가(처음 생성하는 것이므로 복사) : txt파일 참조
            - 로그인 테스트
        * 자바 설치
            - JDK를 다운 받고 설치
            - profile에 환경변수 설정
        * 의사(유사) 분산 모드 운용을 위한 하둡 환경 설정
            - 하둡 구성 파일이 있는 디렉터리로 이동함.(conf)
                1. hadoop-env.sh 파일을 편집
                    >> 위에서 9번째 줄의 주석을 수정
                        export JAVA_HOME=/usr/java/default
                        export HADOOP_HOME_WARN_SUPPRESS="TRUE"
                2. masters, slaves파일 확인 : 의사 분산 모드는 하나의 노드가 namenode, secondary namenode, datanode, jobtracker, tasktracker역할을 다 해야 함. localhost내용만 있으면 된다.
                    >> cat masters, cat slaves
                3. HDFS와 MapReduce 공통 설정
                    >> vi core-site.xml
                        <configuration>
                            <property>
                                <name>fs.default.name</name>
                                <value>hdfs://localhost:9000</value>
                            </property>
                            <property>
                                <name>hadoop.tmp.dir</name>
                                <value>/opt/hadoop/hadoop-tmp-dir/</value>
                            </property>
                        </configuration>
                4. HDFS만의 설정
                    >> vi hdfs-site.xml
                        <configuration>
                            <property>
                                <name>dfs.replication</name>
                                <value>1</value>
                            </property>
                            <property>
                                <name>dfs.http.address</name>
                                <value>localhost:50070</value>
                            </property>
                            <property>
                                <name>dfs.secondary.http.address</name>
                                <value>localhost:50090</value>
                            </property>
                            <property>
                                <name>dfs.name.dir</name>
                                <value>/opt/hadoop/hadoop-tmp-dir/dfs/name</value>
                            </property>
                            <property>
                                <name>dfs.name.edits.dir</name>
                                <value>${dfs.name.dir}</value>
                            </property>
                            <property>
                                <name>dfs.data.dir</name>
                                <value>/opt/hadoop/hadoop-tmp-dir/dfs/data</value>
                            </property>
                        </configuration>
                5. MapReduce만의 설정
                    >> vi mapred-site.xml
                        <configuration>
                            <property>
                                <name>mapred.job.tracker</name>
                                <value>localhost:9001</value>
                            </property>
                        </configuration>
                6. 네임노드 포맷. : user명 namenode -format
                7. 하둡관련 프로세스 실행 : jps
                8. 맵리듀스 모두 실행 : start-all.sh
                9. 다시 jps 했을 때, 6가지 나오면 문제 없음
                10. web browser로 확인 : localhost:50070(가상머신에서 할 때)
                    stop-all.sh        // HDFS & 맵리듀스 모두 종료
                    
                    start-dfs.sh       // HDFS만 실행
                    jps                // NameNode 및 DataNode는 HDFS의 구성요소
                    
                    25399 SecondaryNameNode
                    25127 NameNode
                    25468 Jps
                    25252 DataNode
                    
                    stop-dfs.sh        // HDFS만 종료
                     
                    start-mapred.sh    // 맵리듀스만 실행
                    jps                // JobTracker 및 TaskTracker는 맵리듀스의 구성요소
                    
                    25881 TaskTracker
                    25937 Jps
                    25755 JobTracker
                    
                    stop-mapred.sh     // 맵리듀스만 종료
                    
                    start-all.sh 
                11. WordCount 예제
                    - 하둡 파일 시스템 명령어는 hadoop fs로 시작함
                        >> 디렉토리 만들기  : hadoop fs -mkdir (상대, 절대 경로 모두 가능)
                        >> 파일 리스트 : hadoop fs -ls
                        >> 파일 옮기기 : hadoop fs -put 파일명 디렉토리명
                        >> 파일 리스트, 하위 리스트까지 : hadoop fs -lsr 경로(경로 없으면 현재)
                        >> 내용보기 : hadoop fs -cat 파일경로명(tab키 불가)
                        >> jar파일 실행 : hadoop jar 디렉토리명/파일명.jar 클래스명 실행파일명 '결과를 나타낼 파일명'
                            --> 워드카운트를 실행하고 나면, _SUCCESS : 성공했음을 알리는 파일, _logs : 진행한 내용을 저장한 파일을 확인 가능
                            --> part로 시작하는 파일이 있는데 결과물을 보여줌
                    - 하둡1은 이미 존재하는 파일을 덮어쓰지 못하도록 설계됨. 덮어쓰려는 시도를 하면 에러 발생, 2부터는 가능
                        >> 따라서, 재시도할 때, output경로 사용 불가. 지우고 재시도하거나 다르게 설정해야함
                        >> 지우기 : hadoop fs -rmr(recursive) output
                    * 네트워크
                        - 원래 web browser에서 주소창에 주소를 치면 ip주소로 변환되어 입력된다.
                        - 이것을 해주는 곳이 DNS서버인데, 그 전에 컴퓨터의 내부 hosts파일을 먼저 참조한다.
                        - 해당 호스트 파일은 C:\Windows\System32\drivers\etc에 존재함
                        - 따라서, 거기 없으면 DNS에서 찾음
                    * wordcount
                        - 만약 wordcount시에 jar파일을 실행해 wordcount클래스를 실행시, 실행파일을 여러 파일이 들어있는 폴더로 지정시 각 파일을 모두 받아서 수행함
                        - ex) hosts파일과 README.txt를 모두 input에 넣어놓고 실행해보기
                        >> input에 실행파일을 여러 개 넣고 hadoop jar로 실행하면 여러 파일에 대해 mapreduce실행
                    * 전체 진행 순서(process)
                        - input > split > mapping > shuffle > reduceing > final result
>###    5. 하둡 분산 파일 시스템
        * HDFS : hadoop distributed file system은 수십 테라~ 페타바이트 이상의 대용량 파일을 분산된 서버에 저장하고, 많은 클라이언트가 저장된 데이터를 빠르게 처리할 수 있게 설계된 파일시스템이다.
            - 기존에도 DAS, NAS, SAN과 같은 대용량 파일시스템은 존재했음. HDFS 또한 이와 유사한 점이 많음
            - 기존과의 가장 큰 차이점은 HDFS는 저사양 서버를 이용해 스토리지를 구성할 수 있다는 것.
            - 수십 혹은 수백 대의 웹 서버급 서버를 묶어 하나의 스토리지처럼 사용 가능
            - 이 때, HDFS에 저장하는 데이터는 물리적으로는 분산 서버의 로컬 디스크에 저장되어 있으나, 파일의 읽기 및 저장과 같은 제어는 HDFS에서 제공하는 API를 이용해 처리됨
            - 그러나, 기존을 완전히 대체할 수는 없음. DBMS와 같은 고성능과 고가용성이 필요시 SAN을, 안정적 파일 저장 시 NAS사용, 전자상거래처럼 트랜잭션이 중요시 적합하지 않음.
            - 데이터를 저장하거나(대규모), 배치 처리를 하는 경우에 HDFS를 사용
        ** HDFS 아키덱쳐
            * 블록 구조 파일 시스템
                - HDFS는 블록 구조의 파일 시스템, 저장 파일은 특정 크기의 블록으로 나뉘어 분산 서버에 저장. 크기는 기본적으로 64메가. 변경가능
                - 64MB인 이유?
                    1. 디스크 시크 타임 감소(2.0부터는 128)
                    2. 네임노드 유지하는 메타데이터 크기 감소
                    3. 클라이언트와 네임노드의 통신 감소
            * HDFS 클러스터 : hdfs-site.xml파일의 replication을 조정해 얼마나 복제할지 설정 가능(복제본의 수는 기본적으로 3개)
                - 노드를 여러개 만들어서 각 블록 저장.
                - 200MB이면 64, 64, 64, 8 로 저장(각 블록에)
                - 노드가 증가시 소프트웨어 관리 비용이 증가함. 이를 관리하는 것은 하둡이 해주는데 그 중, namenode, datanode가 함
                - 마스터 서버는 네임노드이며 슬레이브서버는 데이터노드임
            * 네임노드
                - 메타데이터 관리 : 파일 시스템 유지 위한 메타데이터 관리. 메타데이터는 파일 시스템 이미지와 파일에 대한 블록 매핑 정보로 구성
                - 데이터노드 모니터링 : 데이터노드는 네임노드에 3초마다 하트비트 메시지 전송. 네임노드는 이를 통해 데이터노드의 실행상태와 용량 모니터링
                - 블록 관리 : 장애 발생한 데이터 노드 발견시 해당 데이터 노드의 블록을 새로운 데이터노드로 복제함.
                - 클라이언트 요청 접수 : 클라이언트가 HDFS에 접근시 반드시 네임노드에 먼저 접속. 
            * 보조네임노드(SecondaryNameNode, SNN)
                - 네임노드는 메타데이터를 메모리에서 처리한다. 하지만 메모리에만 데이터 유지시 서버가 재부팅될 경우 데이터를 유실할 가능성이 있음
                - 이를 극복하기 위해, HDFS는 editslog와 fsimage라는 두 개의 파일을 생성한다.
                - editslog는 모든 변경 이력을 저장(HDFS)
                - fsimage에는 메모리에 저장된 메타데이터의 파일 시스템 이미지를 저장한 파일임
                - 1시간을 기본으로, editslog는 계속 쌓이면 용량이 커지니까 fsimage와 editslog를 합쳐서 새로운 image를 만들고 editslog를 만들어 새로 로그를 저장함
                - 이와 같이 fsimage를 갱신하는 역할을 주기적으로 하는데 이 작업을 checkpoint라고 한다.(따라서 보조네임노드를 체크포인팅 서버라고 표현)
                * 로그 롤링 : 보조네임노드는 네임노드에게 editslog를 롤링할 것을 요청(로그 롤링은 현재 로그 파일의 이름 변경 후, 원래 이름으로 새 로그 파일을 만드는 것.)
                    - 교재 79페이지 참조
            * 파일저장 방식
                1. 파일 저장 요청
                    - 클라이언트가 HDFS에 파일 저장시 파일을 저장을 위한 스트림 생성해야 함.
                    - 스트림을 요청하면 하둡은 FileSystem이라는 추상 클래스에 일반적 파일시스템을 관리하기 위한 메서드를 정의했기에 클라이언트는 DistributedFS의 create메서드를 호출해 스트림 객체를 생성함
                    - 그 스트림 객체로, FSDataOutputStream을 생성함. 이는 데이터노드와 네임노드의 통신 관리하는 DFSOutputStream을 래핑하는 클래스임.
                    - DFSOutputStream을 생성하기 위해 DFSClient의 create메서드 호출함.
                    - 스트림을 생성하면 네임노드는 요청이 유효한지 검사함. 문제가 있다면 오류 발생, 정상일시 파일 시스템 이미지에 해당 파일의 엔트리를 추가함
                    - 유효성 통과시 객체가 정상적으로 생성됨, FSDataOutputStream을 클라이언트에 반환
                2. 패킷 전송
                    - 클라이언트가 네임노드에게서 파일 제어권을 얻으면 파일 저장이 진행됨
                    - 각 데이터 노드에 클라이언트는 전송하게 되고 저장할 파일은 패킷 단위로 나누어 전송함
        * 각종 명령어 : 81~95페이지 참조
>###    6. 잡 스케쥴러
        * 하둡 잡 스케쥴
            - 기본적으로 FIFO임.
            - 그런데 만약 A, B작업이 있는데 A는 30초, B는 10분이 걸리나고 가정하자. 근데 B가 먼저 들어왔다고 해서 A가 진행되기 위해 10분을 기다려야 한다면 문제가 될 것
            - 그러므로, 페어 스케쥴러와 커패시티 스케쥴러를 제공함
            * 페어스케쥴러
                - 잡을 풀(pool)형태로 관리함
                - 모든 잡이 하둡 클러스터의 자원을 풀을 이용해 공평하게 사용하겠다는 것을 목표로 함.
                - 슬롯을 사용해 최소한으로 실행할 수 있는 맵 태스크와 리듀스 태스크의 개수를 관리하고, 슬롯으로 하둡 클러스터의 자원을 미리 할당받는다면 B도 10분 대기할 필요 없이 진행가능
                - 하둡 1에는 페어 스케쥴러가 소스만 포함되어 있으니 별도로 JAR파일을 빌드해야 함.
                - cd $HADOOP_HOME/src/contrib/fairscheduler에 있음
            * 커패스티 스케쥴러
                - 큐를 이용해 스케쥴 관리
                - 큐는 맵 태스크와 리듀스 태스크를 실행할 수 있는 슬롯을 가짐
                - 이런 큐를 여러 개 생성하여 하둡 클러스터의 자원을 공유하게 됨
                - 스케쥴러는 큐를 지속적으로 모니터링해서 자원을 재분배함
>###    7. 셔플
        * 셔플
            - 메모리에 저장돼 있는 매퍼의 출력 데이터를 파티셔닝 및 정렬하여 로컬 디스크에 저장 후, 네트워크를 통해 리듀서의 입력 데이터로 전달되는 과정을 의미
            - 기본적으로 파티션이 포함됨. 분류 및 정렬을 해줌.
            - 파티션에는 기본파티션이 있고, 별도의 파티션을 만들 수도 있다.
            - 셔플 속성은 mapred-site.xml에서 설정 가능함. 그러나 바람직하지 않고, 잡 드라이버 클래스의 Configuration객체의 setConf메서드로 설정 가능
        * 콤바이너
            - 매퍼의 출력 데이터가 네트워크를 통해 리듀서에 전달되기 전에 매퍼의 출력 데이터의 크기를 줄이는 기능 수행
            - 즉, 매퍼의 출력을 집계하여 리듀서에 보내기 전에 리소스를 줄이는 것. 리듀서는 콤바이너에서 줄어든 데이터를 가지고 다시 집계함
>###    8. 맵리듀스 기초(항공데이터)
        * 항공 데이터 : http://stat-computing.org/dataexpo/2009/1987.csv.bz2에서 다운로드
            - 칼럼정보는 콤마로 구분되며 압축 풀면 첫 째 줄에 칼럼정보가 있고 그 아래에 데이터가 있음
            - mkdir data로 하나 만들고 거기에 vi download.sh로 파일을 하나 만든다
            - 아래와 같이 내부에 넣고 chmod 755 download.sh
                #!/bin/sh
                
                for ((i=1987; i <= 2008; i++)) ; do
                  wget http://stat-computing.org/dataexpo/2009/$i.csv.bz2
                  bzip2 -d $i.csv.bz2
                  sed -e '1d' $i.csv > $i_temp.csv
                  mv $i_temp.csv $i.csv
                done
                * 코드
                    - wget으로 파일을 다운받을 수 있다.
                    - bzip으로 압축을 푼다
                    - sed로 컬럼을 1번째는 제거
                    - 해당 파일을 이동
                    - 한 번에 다운로드시 매우 느림. 나누어서 할 것.
            - ./download.sh 로 실행가능. --> 데이터를 받을 것.
>###    9. 튜닝
        * JVM 재사용
            - 태스크트래커는 맵 태스크와 리듀스 태스크를 실행할 때 각각 별도의 JVM을 실행함
            - 일반적으로 매우 빠르지만, 별도의 초기화 로직이 있거나, 매퍼, 리듀서의 초기화 시간이 오래 걸리면 전체 잡 실행에 영향을 줌
            - 이를 방지하기 위해 JVM을 재사용하는 옵션을 사용할 수 있음.
        * 투기적 잡 실행
            - 하둡은 하나의 잡에서 수행할 전체 태스크를 병렬로 수행함. 그런데, 하둡은 전체 맵 태스크가 완료돼야만 리듀스 태스크가 실행됨, 전체 리듀스 태스크 수행이 완료돼야 잡이 정상적으로 종료됐다고 인식
            - 하둡은 수행되는 태스크 가운데 일정 시간이 지났음에도 계속 실행하는 태스크가 있으면 해당 태스크가 수행하는 데이터노드와 다른 데이터노드에서 동일한 태스크를 병렬로 실행함
            - 기존 태스크가 먼저 완료시 병렬 수행하면 태스크를 강제 종료, 병렬로 수행되던 태스크가 먼저 완료시 기존 태스크 강제 종료
            - 즉, 160개의 작업이 있고 10개의 데이터 노드가 있다면, 각각 대략 16개씩 수행해야 함. 이 경우, 먼저 다 끝낸 데이터 노드가 다른 데이터 노드의 작업을 (현재 진행중인 것) 받아서 수행함. 
>###    10. 하둡 운영
        * 어드민 명령어
            - hadoop dfsadmin -report : 기본적인 정보 및 상태 출력