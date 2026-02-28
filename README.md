# Student-Manager

## Thông tin nhóm

- Trần Quang Huy – 2211288

## Giới thiệu dự án
- Tên project: Student Management System
- Render URL:
https://student-management.onrender.com
- Mục đích: Quản lý danh sách sinh viên
- Công nghệ sử dụng:
- Java 17
- Spring Boot
- Maven
- SQLite 
- PostgreSQL 
- Thymeleaf
- Docker


## Cách hoạt động

### Yêu cầu:
- JDK 17+
- Maven

### Chạy local:
- Vào Run/Debug Configurations, add file .env
- Vào Terminal gõ lệnh: ./mvnw spring-boot:run

### Truy cập:
http://localhost:8080

## Deployed

Render URL:
https://student-management.onrender.com

## Trả lời câu hỏi
### Lab 1
#### Câu 2. Ràng buộc Khóa Chính (Primary Key)

**Câu hỏi:**  
Cố tình insert một sinh viên có `id` trùng với một người đã có sẵn.  
Quan sát thông báo lỗi `UNIQUE constraint failed`.  
Tại sao Database lại chặn thao tác này?

**Trả lời:**  

Cột `id` được khai báo:

```sql
id INTEGER PRIMARY KEY
```

`PRIMARY KEY` có hai đặc tính bắt buộc:

- `NOT NULL`
- `UNIQUE`

Vì vậy:

- Không thể tồn tại hai bản ghi có cùng giá trị `id`.
- Khi insert trùng `id`, Database phát hiện vi phạm tính duy nhất và từ chối thao tác.

Cơ chế này đảm bảo **tính toàn vẹn dữ liệu (Data Integrity)** và giúp xác định duy nhất mỗi bản ghi trong hệ thống.

---

#### Câu 3. Toàn vẹn dữ liệu (Constraints)

**Câu hỏi:**  
Thử insert một sinh viên nhưng bỏ trống cột `name` (để `NULL`).  
Database có báo lỗi không?  
Sự thiếu chặt chẽ này ảnh hưởng gì khi Java đọc dữ liệu?

**Trả lời:**  

Database **không báo lỗi** vì bảng được định nghĩa như sau:

```sql
CREATE TABLE students (
    id INTEGER PRIMARY KEY,
    name TEXT,
    email TEXT,
    age INTEGER
);
```

Cột `name` không có ràng buộc `NOT NULL`, nên được phép chứa giá trị `NULL`.

Khi Java đọc dữ liệu:

```java
private String name;
```

Nếu giá trị từ Database là `NULL`:

- Biến `name` trong object sẽ bằng `null`.
- Có thể gây:
  - `NullPointerException`
  - Lỗi hiển thị dữ liệu
  - Sai logic nghiệp vụ

Điều này cho thấy nếu không thiết kế ràng buộc ở mức Database, lỗi có thể phát sinh ở tầng Application.

---

#### Câu 4. Cấu hình Hibernate

**Câu hỏi:**  
Tại sao mỗi lần tắt ứng dụng và chạy lại, dữ liệu trong Database bị mất hết?

**Trả lời:**  

Trong file `application.properties` có cấu hình:

```properties
spring.jpa.hibernate.ddl-auto=create
```

Giá trị `create` có nghĩa là:

- Mỗi lần ứng dụng khởi động:
  - Hibernate xóa toàn bộ bảng cũ (drop)
  - Tạo lại bảng mới dựa trên Entity

Do đó, toàn bộ dữ liệu trước đó bị xóa.

Trong môi trường thực tế (production), không sử dụng `create`.  
Thay vào đó nên dùng:

```properties
spring.jpa.hibernate.ddl-auto=update
```

hoặc:

```properties
spring.jpa.hibernate.ddl-auto=none
```

### Lab 2
#### Câu hỏi

Tại sao cần xây dựng API (Lab 2) trước khi phát triển giao diện (Lab 3/4)?

#### Trả lời

Trong quy trình phát triển phần mềm thực tế, Backend thường được thiết kế để phục vụ đa nền tảng như Web, Mobile App hoặc các hệ thống bên thứ ba (3rd Party). Việc xây dựng REST API trước khi phát triển giao diện mang lại các lợi ích quan trọng sau:

#### 1. Kiểm thử độc lập (Independent Testing)

Việc xây dựng API trước giúp kiểm tra và xác nhận rằng:
- Tầng Repository truy xuất dữ liệu chính xác.
- Tầng Service xử lý logic nghiệp vụ đúng yêu cầu.
- Tầng Controller trả về dữ liệu đúng định dạng JSON.

Nhờ đó, có thể kiểm thử backend hoàn toàn độc lập với giao diện người dùng, đảm bảo “lõi hệ thống” hoạt động ổn định trước khi tích hợp UI.

#### 2. Khả năng mở rộng và tái sử dụng (Scalability & Reusability)

Khi API đã được thiết kế và kiểm chứng:
- Có thể tái sử dụng cho nhiều nền tảng khác nhau (Web, Mobile, Desktop).
- Mobile App (React Native, Flutter) có thể dùng chung API.
- Hệ thống dễ mở rộng và tích hợp với đối tác bên ngoài.

#### 3. Phân tách trách nhiệm (Separation of Concerns)

Việc tách Backend và Frontend giúp:
- Nhóm Backend và Frontend có thể phát triển song song.
- Giảm phụ thuộc giữa các thành phần.
- Dễ bảo trì và nâng cấp hệ thống.

#### Kết luận

Lab 2 tập trung xây dựng và kiểm chứng tầng xử lý dữ liệu (Entity → Repository → Service → Controller) để đảm bảo hệ thống backend hoạt động chính xác. Sau khi nền tảng này ổn định, Lab 3 và Lab 4 mới phát triển giao diện người dùng để tương tác với các API đã có.

## Screenshots
### Màn hình chính
<img width="975" height="490" alt="image" src="https://github.com/user-attachments/assets/0d1ba396-a2bc-40d3-b185-812005a4151f" />

### Màn hình thêm sinh viên
<img width="975" height="494" alt="image" src="https://github.com/user-attachments/assets/fedfef83-a4c6-4edc-bf22-07f740f40a1c" />

### Màn hình chi tiết thông tin sinh viên
<img width="975" height="489" alt="image" src="https://github.com/user-attachments/assets/1ce8ee1c-5ae5-403d-93a6-66e86c53b7e5" />

### Màn hình chỉnh sửa thông tin sinh viên
<img width="975" height="496" alt="image" src="https://github.com/user-attachments/assets/15ae6eac-7ae0-400c-b3fe-1c6801d8ed40" />
