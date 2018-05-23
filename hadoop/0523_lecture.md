## Hadoop
>###    1. 이클립스를 이용한 하둡 파일 시스템 접근
        * 우선 이클립스를 켜고 간단한 java 프로젝트를 생성
            - 코드는 SingleFileWrite.java 파일 확인(간단한 파일을 만드는 예제임)
            - 코드는 SingleFileRead.java 파일 확인(파일 읽어들임)
            - hadoop실습파일의 hadoop-1.2.1.tar.gz에서 hadoop-core-1.2.1.jar를 풀고 해당 자료를 build path에서 프로젝트의 add external library로 import한다.
                >> referenced library에 확인 가능
            - 해당 package를 jar export한다. : 패키지 우클릭 - export - java - jar
            - WinSCP를 이용해 하둡에 app folder를 만들고 jar파일을 업로드 한다.
        * putty로 접속하고 실행
            - WinSCP에서 app으로 만들었으므로 ll app을 명령하면 jar파일이 위치한 것을 확인 가능
            - 이에 따라, cd app으로 옮기고 다음의 명령을 실행
            - hadoop jar jar파일명 클래스명 sysarg[0] sysarg[1]
            - 만약, ClassNotFoundException이 발생한 경우, 패키지 이름도 정확히 넣었는지 확인
            - UnsupportedClassVersionError가 발생하면, 기존의 자바 버젼과 하둡의 환경에서 자바 버젼을 확인해야함.
                >> java -version 으로 확인하면 실행환경을 알 수 있다.
                >> 실행환경 자바버젼을 변경하거나, jar파일을 만든 eclipse 환경을 변경
                    ** 이는, JRE System Library를 우클릭 - properties - Execution environment를 변경하면 됨.                    
            - hadoop fs -cat fileName으로 파일의 내용을 확인할 수 있다.
            - 혹은 hadoop jar jar파일명 패키지명.클래스명 파일명으로 확인 가능
        * 가상머신 스냅샷 : 가상머신을 켜고 상단 바 - 머신 - 스냅샷 찍기로 찍음
            - 스냅샷을 찍으면 상단 바의 머신 도구에서 가상머신을 종료하고 해당 스냅샷 상태로 복원이 가능하다.
>###    2. map-reduce 실습(108페이지, ch04)
        * 32bit linux jdk를 WinSCP를 이용해 옮긴다.
        * tar명령어로 압축을 푼다.(root에서 품)
            - tar zxf 경로/파일명
        * ln명령어로 버젼이 바뀐 jdk로 링크를 변경한다.
            - rm 링크명
            - ln -s jdk파일/ 링크명
        * 맵리듀스 프로그래밍 요소
            - 하둡에서 특정 데이터 타입으로 네트워크 통신(분산환경에서)을 하고 싶은 경우 : WritableComparable interface를 구현하면 된다.
            - 데이터 타입(WritableComparable클래스)
                >> Writable 인터페이스와 Comparable 인터페이스를 다중 상속한 인터페이스이다.
                >> Comparable인터페이스는 java.lang 패키지의 인터페이스로 정렬을 처리하기 위해 compareTo 메서드를 제공함.
                ** Writable인터페이스(이를 구현시 write, readFields메서드를 구현해야 함.)
                    - write메서드는 데이터값을 직렬화함
                    - readFields는 직렬화된 데이터값을 해제하여 읽는 역할을 담당
                    - 데이터 포맷 클래스는 이 두 메서드를 구현하는 부분에서 데이터를 읽고 쓰는 기능 처리
                >> 개발자는 이 인터페이스를 구현하여 데이터 타입을 개발할 수 있다.(MyWritableComparable.java확인)
                >> 맵리듀스 API는 자주 쓰는 데이터 타입에 대한 WritableComparable 인터페이스를 구현한 Wrapper클래스를 구현함
                    - BooleanWritable
                    - ByteWritable
                    - ...
            - InputFormat - 추상클래스
                >> 맵리듀스는 입력 스플릿을 맵 메서드의 입력 파라미터로 사용토록 해당 추상 클래스를 제공
                >> 입력 스플릿을 맵 메서드가 사용할 수 있도록 getSplits 메서드 제공
                >> 사용자가 별도의 InputFormat을 설정하지 않을 시 TextInputFormat이 기본값
            - 매퍼
                >> 맵 메서드의 기능 수행. 키와 값으로 구성된 입력 데이터를 전달받아 이 데이터를 가공하고 분류해 새로운 데이터 목록을 생성
                >> 입력 스플릿마다 하나의 맵 태스크를 생성, 이 때 생성되는 맵 태스크가 매퍼 클래스
                >> 제네릭 파라미터 사용. 각각은 차례로 <입력키 유형, 입력값 유형, 출력키 유형, 출력값 유형>을 의미
                >> Context객체 선언. 이를 이용해 job에 대한 정보를 얻고 입력 스플릿을 레코드 단위로 읽을 수 있다.
                >> 맵 태스크의 출력 데이터가 어떤 리듀스 태스크로 전달될지 결정
                >> 기본으로 HashPartitioner 제공
            - 리듀서
                >> 맵 태스크의 출력 데이터를 입력 데이터로 전달받아 집계 연산 수행
                >> 제네릭 파라미터를 가지며, 각각은 차례로 <입력키 타입, 입력값 타입, 출력키 타입, 출력값 타입>
                >> Context객체 선언. 이는 ReduceContext를 상속받음. 이는 잡에 정보를 조회하고 입력값 목록을 확인함
            - 콤바이너 클래스
                >> 맵 태스크와 리듀스 태스크 사이의 데이터 전달 과정은 셔플.
                >> 콤바이너는 셔플할 데이터의 크기를 줄이는 데 도움을 줌
            - OutputFormat
                >> TextOutputFormat이 기본 포맷
        * WordCount 실습
            - 매퍼 구현
                >> 매핑작업을 위한 매퍼 클래스 구현
                >> 입력 파라미터의 값인 문장을 공백 단위로 구분해 글자 수 계산
                >> 구분 글자는 즉시 출력 파라미터에 추가. 글자는 하나이므로 글자 수를 1로 설정
                >> Mapper클래스를 상속받아 구현함. 제네릭으로 <LongWritable, Text, Text, IntWritable>을 선언
                ** 구현
                    - 이클립스에서 구현
                    - ctrl + n으로 패키지 내부에 클래스 생성가능
                    - WordCountMapper.java파일 확인
            - 리듀서 구현
                >> 입력 파라미터의 값에 담긴 글자 수 합산
                >> 합산 종료시 출력 파라미터에 레코드 추가
                ** 구현
                    - WordCountReducer.java파일 확인
            - 드라이버 클래스 구현
                >> 프로그램을 실행하기 위한 클래스를 드라이버 클래스 라고 한다.
                >> 잡 객체 생성, 잡 객체에 맵리듀스 잡의 실행 정보 설정
                >> 맵리듀스 잡 실행
                ** 구현
                    - WordCount.java파일 확인
            - 실행
                >> WordCount의 각 클래스가 들어있는 패키지를 export하고 해당 jar파일을 WinSCP로 이동시킴
                >> WordCount를 할 때 사용하기 위한 파일을 hadoop fs로 이동시킴. : hadoop fs -put 파일명 이동시킬 파일명
                >> 맵리듀스 실행 : hadoop jar 경로명 패키지명.클래스명 실행파일 결과경로
                >> 확인 : hadoop fs -cat 경로명/part-r-00000
                >> hadoop파일 시스템에서 해당 파일을 가져오고 싶은 경우 : hadoop fs -get 가져올파일 결과로만들파일
>###    3. 10장- 하둡 부가기능 활용
        * 10. 1 하둡 스트리밍
            - 하둡은 자바 외의 다른 언어로 맵리듀스 프로그래밍을 할 수 있게 하둡 스트리밍이라는 기능을 제공함
            - 유닉스 제공 기본 명령어, 파이썬, 루비와 같은 스크립트 언어를 이용해 간단히 맵리듀스 프로그램을 작성 가능
            - 동작 방식
                >> 유닉스 표준 입력을 통해 입력 데이터를 전달받고, 유닉스 표준출력을 통해 출력데이터 생성
                >> 매퍼 : 매퍼 옵션으로 설정한 소스. 입력 데이터를 분석해 키와 값으로 구성된 데이터 출력
                    :: 이 때, 키와 값은 탭을 구분자로 사용.(변경가능)
            - 실행옵션 : 다양한 옵션 가능(300페이지 참조)
            - 파이썬으로 동작시키고 싶은 경우 : vi 에디터를 열고 #!/usr/bin python을 타이핑 후 코드를 작성하면 된다.
>###    4. 리눅스 awk : http://zetawiki.com/wiki/%EB%A6%AC%EB%88%85%EC%8A%A4_awk참조
            - 패턴 검색 및 처리 언어
            - 주로 유닉스 계열에서 사용되는 스크립트 언어
            - 텍스트 형태로 되어있는 데이터를 필드로 구분하여 처리
>###    5. 리눅스 uniq : https://zetawiki.com/wiki/%EB%A6%AC%EB%88%85%EC%8A%A4_%EC%A4%91%EB%B3%B5%ED%96%89_%EC%A0%9C%EA%B1%B0_uniq참조
            - 중복된 내용의 행이 연속으로 있으면 중복 없이 하나의 행으로 만들어 주는 리눅스 유틸리티
            - 흔히 sort와 함께 사용함
            - -c 옵션을 붙이면 중복행의 개수를 출력함(-c는 count)
            * 이 기능과 awk를 사용해 map reduce작업이 가능(\는 다음줄 입력가능토록함)
                >> hadoop jar /opt/hadoop/hadoop/contrib/streaming/hadoop-streaming-1.2.1.jar -input input/2008.csv -output delay_filght_list \
                > -mapper "cut -f 9,10 -d , | awl 'NF'" \
                > -reducer "uniq"
