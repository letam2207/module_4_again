<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Danh sách cầu thủ</title>
  <style>
    table {
      border-collapse: collapse;
      width: 80%;
      margin: 30px auto;
    }

    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: center;
    }

    th {
      background-color: #4CAF50;
      color: white;
    }

    img {
      width: 70px;
      height: 70px;
      border-radius: 50%;
    }

    .btn {
      padding: 5px 10px;
      background-color: #2196F3;
      color: white;
      text-decoration: none;
      border-radius: 5px;
    }

    .btn-delete {
      background-color: #f44336;
    }
  </style>
</head>
<body>

<h2 style="text-align: center;">Danh sách cầu thủ</h2>

<c:if test="${not empty mess}">
  <p style="color: green; text-align: center;">${mess}</p>
</c:if>

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
      <td>
        <a class="btn" href="/players/detail/${player.id}">Chi tiết</a>
        <form action="/players/delete/${player.id}" method="post" style="display:inline;">
          <button class="btn btn-delete" type="submit"
                  onclick="return confirm('Bạn có chắc muốn xoá cầu thủ này không?');">Xoá</button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
