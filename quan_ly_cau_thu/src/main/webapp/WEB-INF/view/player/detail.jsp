<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Chi tiết cầu thủ</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 40px;
      background-color: #f7f7f7;
    }
    h2 {
      color: #333;
    }
    .wrapper {
      display: flex;
      gap: 24px;
      align-items: flex-start;
    }
    table {
      border-collapse: collapse;
      width: 400px;
      background-color: white;
      box-shadow: 0 0 8px rgba(0,0,0,0.1);
    }
    td {
      padding: 10px;
      border: 1px solid #ddd;
      vertical-align: top;
    }
    tr:nth-child(even) {
      background-color: #f2f2f2;
    }
    .avatar-box {
      background: white;
      box-shadow: 0 0 8px rgba(0,0,0,0.1);
      padding: 16px;
      border-radius: 8px;
      min-width: 160px;
      text-align: center;
    }
    .avatar-box img {
      width: 140px;
      height: 140px;
      object-fit: cover;
      border-radius: 50%;
      border: 2px solid #ccc;
      background: #eee;
    }
    .actions {
      margin-top: 20px;
    }
    a, button {
      text-decoration: none;
      padding: 8px 12px;
      background-color: #4CAF50;
      color: white;
      border-radius: 4px;
      border: none;
      cursor: pointer;
      font-size: 14px;
      margin-right: 8px;
      display: inline-block;
    }
    a:hover, button:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>

<h2>Thông tin chi tiết cầu thủ</h2>

<div class="wrapper">

  <table>
    <tr>
      <td><strong>ID</strong></td>
      <td>${player.id}</td>
    </tr>
    <tr>
      <td><strong>Tên cầu thủ</strong></td>
      <td>${player.name}</td>
    </tr>
    <tr>
      <td><strong>Ngày sinh</strong></td>
      <td>${player.dob}</td>
    </tr>
    <tr>
      <td><strong>Kinh nghiệm</strong></td>
      <td>${player.experience}</td>
    </tr>
    <tr>
      <td><strong>Vị trí thi đấu</strong></td>
      <td>${player.position}</td>
    </tr>
  </table>

  <div class="avatar-box">
    <div><strong>Ảnh đại diện</strong></div>
    <br/>
    <c:if test="${not empty player.avatar}">
      <img src="${player.avatar}" alt="avatar của ${player.name}">
    </c:if>
    <c:if test="${empty player.avatar}">
      <div style="font-size: 12px; color: #777;"></div>
    </c:if>
  </div>

</div>

<div class="actions">
  <a href="/players">⬅ Quay lại danh sách</a>

</div>

</body>
</html>
