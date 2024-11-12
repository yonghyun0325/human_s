<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글등록</title>
</head>
<body>
<div id="wrap">
    <h2>글목록</h2>
    <div id="container">
        <form name="frmBoardWrite" action="writeProcess.do" method="post">
            <input type="hidden" name="memIdx" value="${member.memIdx}">
            작성자: <input type="text" name="gbWriter" value="${member.memberName}" readonly><br>
            제목: <input type="text" name="gbTitle"><br>
            내용<br>
            <textarea name="gbContent" cols="30" rows="10"></textarea><br>
            <div>
                <input type="submit" value="작성완료">
                <input type="reset" value="다시입력">
                <input type="button" value="목록보기" onclick="location.href='../index.do'">
            </div>
        </form>

    </div>

</div>
</body>
</html>