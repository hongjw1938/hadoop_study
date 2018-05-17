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