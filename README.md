## Schedule

---
### 📜 개요

내용인데요.

---
### 🛠️ 개발 프로세스

#### 개발 환경
- 이것은 개발환경.

#### 요구사항
- 이것은 요구사항.

#### 설계

- API 명세서
<details style="margin-left: 30px;">
  <summary>일정 생성 (Create)</summary>

| **기능**       | **Method** | **URL**           | **request** | **response** | **상태 코드**   |
  |----------------|------------|-------------------|-------------|--------------|-----------------|
| **일정 생성**    | `POST`     | `/schedule`       | `{  }`      | `{  }`       | `201 Created`   |
</details>

<details style="margin-left: 30px;">
  <summary>일정 조회 (Read All)</summary>

| **기능**       | **Method** | **URL**           | **request** | **response** | **상태 코드**   |
  |--------------------|------------|-------------------|-------------|--------------|-----------------|
| **일정 조회**      | `GET`      | `/schedule`       | -           | `{  }`       | `200 OK`        |
</details>

<details style="margin-left: 30px;">
  <summary>단건 일정 조회 (Read One)</summary>

| **기능**       | **Method** | **URL**            | **request** | **response** | **상태 코드**   |
  |--------------------|------------|--------------------|-------------|--------------|-----------------|
| **단건 일정 조회** | `GET`      | `/schedule/{id}`   | -           | `{ }`        | `200 OK`        |
</details>

<details style="margin-left: 30px;">
  <summary>일정 수정 (Update)</summary>

| **기능**       | **Method** | **URL**            | **request** | **response** | **상태 코드**   |
  |--------------------|------------|--------------------|-------------|--------------|-----------------|
| **일정 수정**      | `PUT`      | `/schedule/{id}`   | `{  }`      | `200 OK`     |
</details>

<details style="margin-left: 30px;">
  <summary>일정 삭제 (Delete)</summary>

| **기능**       | **Method** | **URL**            | **request** | **response**             | **상태 코드**   |
  |----------------|------------|--------------------|-------------|--------------------------|-----------------|
| **일정 삭제**    | `DELETE`   | `/schedule/{id}`   | -           | `{ "message": "삭제 완료" }` | `200 OK`        |
</details>

- ERD

---
### 🌱 필수기능

- lv1. 일정 생성 및 조회
- lv2. 일정 수정 및 삭제

---
### 트러블 슈팅

- TIL 링크

---