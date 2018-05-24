##  2. AWS 기본과 계정 등록
>###    2.1 Amazon Web Service에서 제공하는 서비스
        * 카테고리로 구분됨
            - 컴퓨팅 : 가상 서버 기능 및 컨테이너 실행 환경에 대한 관리 서비스 제공
                >> Amazon EC2 : Amazon Elastic Computing Cloud. 종량제 형태로 과금되는 가상 서버 기능
                >> Amazon EC2 Container Service : Docker를 운용하는 서비스
                >> Amazon EC2 Container Registry
                ...
            - 스토리지 및 콘텐츠 배포
                >> Amazon S3
            - 데이터베이스
                >> Amazon RDS
            - 네트워크
                >> Amazon VPC : 사설 네트워크 구축을 위한 서비스
            - 개발자용 도구
            - 관리 도구
            - 보안 및 ID
            - 분석
            - IoT
            - 모바일 서비스
            - 애플리케이션 서비스
            - 엔터프라이즈 애플리케이션
>###    2.2 AWS 클라우드 디자인 패턴
        * Multi-Server 패턴(서버의 다양화) : 가상 서버 기능을 하는 EC2 인스턴스 여러 개를 늘어 놓고 로드 밸런싱 기능인 ELB가 부하에 대한 처리를 분산시키는 구성
        * Scale Up 패턴(서버의 확장/축소) : 리퀘스트의 많고 적음에 따라 서버의 스펙을 자유롭게 확장/축소하는 구성
        * DB Replication 패턴(데이터베이스 복제) : 시스템 장애나 재해 등으로부터 중요한 데이터가 소멸되지 않도록 데이터베이스 내용 복제
        * Functional Firewall패턴(계층별 접속)
>###    2.3 AWS 데이터 센터
        * 리전(Region)
        * AZ : Availability Zone
>###    2.4 계정 등록 및 사용(교재참고 62p)
        