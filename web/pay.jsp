<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付</title>
</head>
<body>
您的订单是：3666876542
订单的金额是：0.01
<form action="${pageContext.request.contextPath}/bank.jsp" method="post">
    <%--数据通过隐藏域带过去--%>
    <input type="hidden" name="orderid" value="3666876542">
    <input type="hidden" name="money" value="0.01"><br>
    <%--下一个页面就是点支付跳转到支付银行--%>
    <input type="submit" value="支付">
</form>
</body>
</html>
