<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성일</th>
			<th>작성시간</th>
		</tr>
		<c:forEach var="pBoard" items="${pBoardList}">
			<tr>
				<td>${pBoard.pbNum}</td> <!-- 태그가 자동으로 .뒤에 get을 붙여서 .getPbNum()으로 바꿔준다. 메소드 명이랑 1대1로 맵핑된다. 그래서 getPbNum이라는 메소드가 있어야 가져 올 수 있다.-->
				<td><img title="${pBoard.pbRealPath}" width="30"
					src="${pBoard.pbFilePath}" alt="${pBoard.pbRealPath}">
					${pBoard.pbTitle}</td>
				<td>${pBoard.pbContent}</td>
				<td>${pBoard.pbCredat}</td>
				<td>${pBoard.pbCretim}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>