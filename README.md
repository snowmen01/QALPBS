BỐI CẢNH 1: BÀI TOÁN CHI PHÍ Hệ thống giao dịch của chúng tôi đang sử dụng SMS OTP. Với lượng người dùng lớn và tần suất giao dịch cao (5-10 giao dịch/ngày), chi phí cho SMS đang tiêu tốn hàng trăm triệu VNĐ mỗi tháng, đồng thời thỉnh thoảng gặp tình trạng nghẽn mạng do nhà mạng. Công ty quyết định chuyển đổi sang SMART OTP (Soft OTP) – mã xác thực được sinh ra trực tiếp trên ứng dụng di động của khách hàng từ một Secret Key đã được mã hóa.
Câu hỏi 1: 
Giả sử người dùng đã đăng nhập thành công. Hãy định nghĩa các API cần thiết để Client (Mobile App) và Server có thể thực hiện luồng Smart OTP này.
Không cần implement, chỉ cần định nghĩa rõ (URL, HTTP Method, Request Payload, Response Payload).
Không cần định nghĩa các mã lỗi HTTP tiêu chuẩn (4xx, 5xx).
Câu hỏi 2: 
Để xác thực Smart OTP, Server cần một class để kiểm tra mã OTP client gửi lên có khớp với mã Server tự tính toán ra hay không. Đặc thù của mã này là dựa trên thời gian (Time-based). 
Hãy implement Core Logic cho tính năng này bằng Java. (Yêu cầu thực hiện code apply theo TDD và Clean Architecture).
Câu hỏi 3: 
Lúc này nếu OTP client chỉnh thời gian chạy nhanh hơn thời gian của server 1 phút. Điều gì sẽ xảy ra. Mô tả và giải thích chi tiết vấn đề ở đây. (Optional) Hãy implement core logic để handle tính năng này (Yêu cầu thực hiện code apply theo TDD và Clean Architecture), việc implement lúc này solution của bạn đã đi từ TDD ra chưa, có tuân thủ Clean Architecture hay không.


BỐI CẢNH 2: TRẢI NGHIỆM ĐA NỀN TẢNG (OMNICHANNEL) Blossom (Đào) - Product Owner của chúng ta nhận ra một vấn đề về UX: "Khách hàng của chúng ta dùng cả Web và Mobile. Nhưng chỉ Mobile mới sinh được Smart OTP. Chẳng lẽ khách đang đặt lệnh trên Web (Laptop), lại phải mò mẫm tìm điện thoại, mở app, copy 6 số rồi gõ ngược lại lên Web?"
Blossom muốn áp dụng luồng Out-of-band Authentication (Xác thực ngoài luồng): Khi khách đặt lệnh trên Web, Web không đòi nhập OTP. Thay vào đó, một thông báo Push Notification / Realtime Message sẽ được đẩy thẳng xuống Mobile App của khách. Khách chỉ cần mở điện thoại, bấm nút "Xác nhận giao dịch", Mobile App sẽ tự ngầm sinh Smart OTP và gửi lên Server. Lệnh trên Web tự động báo thành công.
Câu hỏi 4: 
Là 1 developer khi nhận được đầu bài từ PO như này bạn sẽ thiết kế hệ thống ra sao để đáp ứng được vấn đề đó. Hãy về 1 sequence diagram mô tả chi tiết về luồng này.
Câu hỏi 5:
Vào những thời điểm thị trường biến động mạnh (ví dụ phiên ATC/ATO), lượng request xác thực giao dịch sẽ tăng đột biến (Spike traffic). Bạn sẽ monitor (giám sát) những metric nào ở API "Xác thực OTP" để đảm bảo hệ thống đang hoạt động ổn định và sẵn sàng scale thêm?