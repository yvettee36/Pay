<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付银行</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet/PayRequestServlet" method="post">
    <table width="60%">
        <%--这里的param.orderid跟request.getParameter("orderid")一样的--%>
        <input type="hidden" name="orderid" value="${param.orderid }">
        <input type="hidden" name="money" value="${param.money }">
        <tr>
            <td><br/></td>
        </tr>
        <tr>
            <td>请您选择在线支付银行</td>
            <%--选择哪个银行的值（比如：value="CMBCHINA-NET"），要看易宝的接口规范支付通道编码列表--%>
        </tr>
        <tr>
            <td><input type="radio" name="pd_FrpId" value="CMBCHINA-NET">招商银行</td>
            <td><input type="radio" name="pd_FrpId" value="ICBC-NET">工商银行</td>
            <td><input type="radio" name="pd_FrpId" value="ABC-NET">农业银行</td>
            <td><input type="radio" name="pd_FrpId" value="CCB-NET">建设银行</td>
        </tr>
        <tr>
            <td><input type="radio" name="pd_FrpId" value="CMBC-NET">中国民生银行总行</td>
            <td><input type="radio" name="pd_FrpId" value="CEB-NET">光大银行</td>
            <td><input type="radio" name="pd_FrpId" value="BOCO-NET">交通银行</td>
            <td><input type="radio" name="pd_FrpId" value="SDB-NET">深圳发展银行</td>
        </tr>
        <tr>
            <td><input type="radio" name="pd_FrpId" value="BCCB-NET">北京银行</td>
            <td><input type="radio" name="pd_FrpId" value="CIB-NET">兴业银行</td>
            <td><input type="radio" name="pd_FrpId" value="SPDB-NET">上海浦东发展银行</td>
            <td><input type="radio" name="pd_FrpId" value="ECITIC-NET">中信银行</td>
        </tr>
        <tr>
            <td><br/></td>
        </tr>
        <tr>
            <%--确定支付，请求交给servlet，根据易宝的规范构建出一个URL地址出来--%>
            <td><input type="submit" value="确定支付"></td>
        </tr>
    </table>
</form>
</body>
</html>
