# KOSTA_Project: 그루빙 🛍

## 프로젝트 설명 
1인 가구를 위한 공동 구매 웹 플랫폼 

|개발 환경|개발 언어 및 프레임워크|템플릿 엔진|라이브러리|데이터베이스|빌드 도구|기타|
|---|---|---|---|---|---|---|
|![Spring](https://img.shields.io/badge/STS4-6DB33F?style=flat-square&logo=Spring&logoColor=black)|![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white) ![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=HTML5&logoColor=white) ![CSS](https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=CSS3&logoColor=white) ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=black)|![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=Thymeleaf&logoColor=white)|![Lombok](https://img.shields.io/badge/Lombok-red?style=flat-square&logo=Lombok&logoColor=white)|![Oracle](https://img.shields.io/badge/Oracle-F80000?style=flat-square&logo=Oracle&logoColor=white)|![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white)|
|![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ-yellow?style=flat-square&logo=IntelliJIDEA&logoColor=black)|![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=SpringBoot&logoColor=black)||![Spring Security](https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat-square&logo=SpringSecurity&logoColor=black) |||
||||![Spring Batch](https://img.shields.io/badge/SpringBatch-6DB33F?style=flat-square&logo=Spring&logoColor=black) ![Spring Quartz](https://img.shields.io/badge/SpringQuartz-6DB33F?style=flat-square&logo=Spring&logoColor=black)||
||||![Mahout](https://img.shields.io/badge/Mahout-66CCFF?style=flat-square&logo=apache&logoColor=black)||

## 프로젝트 목표(개요)
1) 대량의 물건을 원하는 만큼만 저렴하게 구매 가능한 공동구매 플랫폼 개발
2) 가까운 이웃들과 같은 배송지로 공동 구매를 통한 배송비 절약 & 소량 구매 

## 프로젝트 구조
- 메인 화면
  - 로그인/회원가입 페이지
  - 마이페이지
    - 내 정보 확인 및 수정
    - 완료된 나의 거래 내역
  - 찜 목록 페이지
  - 나의 거래 중인 내역
  - 메인 페이지
    - 게시글, 상품 검색
    - 게시글 목록 확인
  - 거래 제안서 작성 페이지
  - 거래 상세 내용 조회 페이지 (거래 게시글)
    - 거래 게시글 수정
    - 거래 게시글 삭제
    - 거래 참가자 모집 
    - 채팅 기능
    - 상품 찜 기반 추천 기능
    - 거래 참가자 간 중간 배송지 확인 및 추천 기능
    - 거래 참여하기 기능


## 개발과정
### 1. 설계
#### 사용자 프로세스
![그림1](https://user-images.githubusercontent.com/76260153/182779524-943f5d95-1c5b-4ed4-a2a6-4bfa8b35e7e1.png)

#### 유저플로우
![그림10](https://user-images.githubusercontent.com/76260153/183008681-67c9591e-ebff-4aba-b779-161a4e165187.png)
![그림00](https://user-images.githubusercontent.com/76260153/183008690-e5030dad-555b-4362-ab5b-5fa1267ecc02.png)
![그림13](https://user-images.githubusercontent.com/76260153/183011489-da2edca2-7a26-4cc9-85cd-eefc442fadeb.png)
![그림12](https://user-images.githubusercontent.com/76260153/183012287-0ee5a0a4-1bcc-48c9-a08f-13811b3ea550.png)

#### ERD
![그림2](https://user-images.githubusercontent.com/76260153/182779942-a8667e4d-da77-4ad4-ae62-e816791a7aea.png)
![그림3](https://user-images.githubusercontent.com/76260153/182780386-b7e6ee4a-1aaa-4365-b037-17ba52d9d223.png)

#### 프로토타입
![그림4](https://user-images.githubusercontent.com/76260153/182781026-05b9b066-6dee-4fff-8266-8f79a9c10b56.png)
![그림5](https://user-images.githubusercontent.com/76260153/182781033-b967142e-f0fb-41a6-97f3-665460ce9b90.png)
![그림6](https://user-images.githubusercontent.com/76260153/182781042-215b1d50-81eb-4075-a780-87f7ff43b477.png)
![그림7](https://user-images.githubusercontent.com/76260153/182781054-72d09be4-085f-454a-9780-7dca6c889fa1.png)
![그림8](https://user-images.githubusercontent.com/76260153/182781056-afb31cb1-a3e1-467e-aa49-67724be4e878.png)
![그림9](https://user-images.githubusercontent.com/76260153/182781073-5ad987e2-1e64-4378-94da-2ebf88e40904.png)


### 2. 구현

#### 회원가입 화면
![그림29](https://user-images.githubusercontent.com/76260153/182797113-25bca19c-8546-4119-b3cf-5ec30c243bb0.png)
- 아이디 중복 체크 기능
- Spring Security를 활용하여 비밀번호 암호화, 로그인 여부에 따라 페이지 접근 제어 구현
  - 로그인이 되어 있지 않은 상태라면 로그인, 회원가입 페이지 외에는 접근 불가능


#### (회원) 로그인 화면
![그림14](https://user-images.githubusercontent.com/76260153/182783109-4d239b89-5679-436d-aa61-363c66f0457b.png)


#### 로그인 후 메인 화면
![그림15](https://user-images.githubusercontent.com/76260153/182785828-09ca09e6-7101-474b-9b3e-e7bed8c5a0f5.png)
- 게시글을 최신순, 인기순으로 정렬 가능
- 상단의 카테고리와 제목 키워드를 통해 게시글 검색 가능, 음성 인식을 통해 게시글 검색 가능


#### 거래 제안 작성 페이지
![그림16](https://user-images.githubusercontent.com/76260153/182789070-08b37950-5d85-4110-a277-cfedc9c87cfc.png)
- 카테고리, 제목, 내용, 제품 링크, 상품 원가, 상품 수량, 거래 참가자 모집 마감 날짜, 최소 인원, 최대 인원, 상품 이미지 기입
  - 거래 참가자는 최소 2명으로 고정 (2명 이상일 때 거래가 활성화 되도록 함)


#### 거래 상세 페이지
1. 메인
![그림17](https://user-images.githubusercontent.com/76260153/182789609-7df7e4c2-18fc-4f81-a784-477a14de7c1a.png)
- 상단에 최대 구매가(최소 인원 수에 비례), 최소 구매가(최대 인원 수에 비례), 최소 인원, 최대 인원, 현재 인원 수(현재 참가하는 회원 수), 현재 예상 구매가, 마감일, 남은 시간이 뜸
- 참여하기 버튼을 누르면 거래 상세페이지에 참여 회원의 정보가 뜸
- 참여하는 회원의 수에 따라 상단의 현재 인원수, 현재 예상 구매가에 반영 됨
- 방장은 최소 인원이 채워지면 거래 완료하기 버튼이 활성화 됨 
  - 해당 버튼을 눌러 해당 거래를 만료 시킬 수 있음

![그림18](https://user-images.githubusercontent.com/76260153/182789617-e47741cf-2ed4-4b72-ae1e-23fa986dcfd6.png)
- 해당 거래에 최소 인원에 만족하지 않는다면 거래 완료하기 버튼이 활성화 되지 않음
- 거래 마감일까지의 시간을 타이머로 보여줌
- 해당 거래에 참여하는 회원 간의 중간 배송지를 모달창을 통해 보여 줌
- 해당 거래에 관심있는 회원 간 채팅(댓글)을 나눌 수 있음
  - 본인의 아이디에만 색상을 입혀서 회원 간의 분리를 하도록 구현 함


2. 추천 시스템 원리 및 구현
![그림20](https://user-images.githubusercontent.com/76260153/182793623-0f2b6cc3-482c-47a1-8b3b-ea11053aec82.png)
- 유저들이 선호하는 아이템(상품)과 유사한 상품을 추천해줌
- 사용자의 찜 목록 상품을 기반으로 상품 추천, Spring Quartz, Spring Batch를 사용하여 해당 추천 시스템 기능을 일정시간 동안 반복
  - 아파치 머하웃(Mahout) 라이브러리: 분산처리가 가능한 확장성을 가진 기계 학습 라이브러리
    - 아파치 머하웃 라이브러리의 아이템 기반(Item-Based) 추천 모델 사용
    
##### 구현 결과
![그림19](https://user-images.githubusercontent.com/76260153/182792666-145808b4-a18f-4d35-a31a-5a47bbf2e874.png)
- 유저들의 선호하는 아이템(찜)을 기반으로 추천하기 때문에 찜 해둔 아이템이 충분하지 않을 경우에는 분석하지 못했다는 메세지가 나오도록 구현


3. 중간 배송지 알고리즘 원리 및 구현
![그림24](https://user-images.githubusercontent.com/76260153/182795170-088fa3e4-6e09-4074-981f-4ac26bdda60b.png)
- 공동 구매 거래자들의 주소를 활용해 최적의 중간 지점과, 중간 지점과 가까운 편의점 찾기
- 다각형의 각 변의 이등분 선으로 점점 더 작은 다각형을 만들어 수렴하도록 하는 방법

![그림21](https://user-images.githubusercontent.com/76260153/182794962-27b15a48-7932-442d-922f-5923fdb7de95.png)
- 카카오 맵 API의 service.Geocoder 객체의 addressSearch(), coord2Address() 함수를 활용하여 지번주소를 위경도로 변환

![그림22](https://user-images.githubusercontent.com/76260153/182794978-905668a1-9c52-4802-85da-6e060c4ba38c.png)
- 유클리디안 거리를 활용

![그림23](https://user-images.githubusercontent.com/76260153/182794987-cbd6333f-5cb8-4bbd-b027-6234ee14b31d.png)

##### 구현 결과
<img width="963" alt="그림25" src="https://user-images.githubusercontent.com/76260153/182795907-95d0cd0f-ad77-446a-b6fa-606cd7a64da4.png">


#### 나의 거래 중인 내역
![그림26](https://user-images.githubusercontent.com/76260153/182796200-036f8d7e-6970-4a4d-9f03-674b9f007376.png)
- 회원이 참가하고 있으며 거래 상태가 완료되지 않은 거래들의 목록을 확인할 수 있음 


#### 마이페이지
![그림27](https://user-images.githubusercontent.com/76260153/182796800-3daada7f-c617-4824-9997-da0e8d81391b.png)
- 회원의 정보를 확인 할 수 있음
- 회원이 참가했으며 거래 상태가 완료된 거래들의 목록을 확인할 수 있음


#### 찜 목록 페이지
![그림28](https://user-images.githubusercontent.com/76260153/182796559-ca109ac2-0569-4e9d-8b15-c3b3cf93bf1d.png)
- 거래 메인 화면에서 하트 버튼을 누른 상품들을 한 눈에 볼 수 있음
- 상세보기 버튼을 누르면 해당 상품의 상세 페이지로 넘어감

## 프로젝트 결과물
웹 사이트: 
<br>
프로젝트 소개 영상: 
