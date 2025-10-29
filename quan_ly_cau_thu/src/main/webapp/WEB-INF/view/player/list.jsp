<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Danh sách cầu thủ</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f7f7f7;
    }

    h2 {
      text-align: center;
      margin-top: 30px;
      color: #333;
    }

    .container {
      width: 90%;
      margin: 0 auto;
      text-align: center;
    }

    table {
      border-collapse: collapse;
      width: 100%;
      margin: 30px auto;
      background: white;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    }

    th, td {
      border: 1px solid #ddd;
      padding: 10px;
      text-align: center;
      vertical-align: middle;
    }

    th {
      background-color: #4CAF50;
      color: white;
    }

    img {
      width: 70px;
      height: 70px;
      border-radius: 50%;
      object-fit: cover;
    }

    .btn {
      padding: 6px 12px;
      background-color: #2196F3;
      color: white;
      text-decoration: none;
      border-radius: 5px;
      font-size: 13px;
      display: inline-block;
      margin: 3px;
    }

    .btn:hover {
      opacity: 0.9;
    }

    .btn-delete {
      background-color: #f44336;
    }

    .btn-add {
      background-color: #4CAF50;
      font-weight: bold;
      padding: 8px 14px;
      margin-top: 10px;
      display: inline-block;
    }

    .message {
      color: green;
      font-weight: bold;
      text-align: center;
    }

    .actions {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 6px;
    }

    .actions form {
      margin: 0;
    }
  </style>
</head>
<body>

<div class="container">

  <h2>Danh sách cầu thủ</h2>

  <c:if test="${not empty mess}">
    <p class="message">${mess}</p>
  </c:if>

  <div style="text-align: right; margin-bottom: 15px;">
    <a href="/players/add" class="btn btn-add"> Thêm cầu thủ mới</a>
  </div>

  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Tên cầu thủ</th>
      <th>Ngày sinh</th>
      <th>Kinh nghiệm</th>
      <th>Vị trí</th>
      <th>Ảnh đại diện</th>
      <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="player" items="${playerList}">
      <tr>
        <td>${player.id}</td>
        <td>${player.name}</td>
        <td>${player.dob}</td>
        <td>${player.experience}</td>
        <td>${player.position}</td>
        <td>
          <c:if test="${not empty player.avatar}">
            <img src="${player.avatar}" alt="avatar">
          </c:if>
        </td>
        <td class="actions">
          <a class="btn" href="/players/detail/${player.id}">Chi tiết</a>
          <form action="/players/delete/${player.id}" method="post">
            <button class="btn btn-delete" type="submit"
                    onclick="return confirm('Bạn có chắc muốn xoá cầu thủ này không?');">Xoá</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

</div>

</body>
</html>
