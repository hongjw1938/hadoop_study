##  실습
>###    ** 실습진행방식
        1. 컴퓨터 3대에 각각 1대씩 Virtual Box로 가상머신을 생성
        2. 각각의 컴퓨터는 Host, Guest가 NAT방식으로 통신
        3. Port Forwarding 기술을 이용해 통신을 각각의 Guest에게 전달
        4. Port Forwarding을 설정
            - 가상 머신의 IP 주소 확인 ( 10.*.*.* 네트워크의 호스트 주소 확인)
                >> 가상 머신에 root 유저로 접속 후 ifconfig eth1을 타이핑
            - 호스트 컴퓨터의 IP 주소 확인
                >> CMD 실행 -> ipconfig로 확인 -> ipv4주소 확인
            - Virtual Box에서 가상 머신 선택 후 [설정] -> [네트워크] -> [어댑터1] - [고급] -> [포트포워딩]
                >> +로 추가. 호스트IP, 호스트 포트(22), 게스트IP(10.*.*.*), 게스트포트(22) 추가
            - putty에서 확인
                >> touch xx 로 파일을 만들고 ll로 확인. 
            - 노드 설정
                >> [putty] root 계정으로 로그인
                >> vi /etc/hosts >> 파일 설정 변경
                    >> 각각의 서버는 각 컴퓨터의 IP로 변경(어차피 포트포워딩 되어 접속되니 문제 없음)
                >> vi /etc/sysconfig/network >> hostname 도 변경
            - 재부팅 : reboot. putty는 restart session으로 가능
            - 중지 후 설정에서 메모리 5GB(5120) 으로 변경
        5. 클라우데라 매니저 설치
            >> 교재 76부터 참조
            >> 중간에 호스트 파일 변경 후, 7180포트번호에 맞추어 포트 포워딩 규칙 생성
            >> 그 다음 URL접속
            * CDH : Cloudera Distributed Hadoop