package cn.itcast.web;

import cn.itcast.utils.PayConfig;
import cn.itcast.utils.PaymentUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PaymentResponseServlet", urlPatterns = "/servlet/PaymentResponse")
public class PaymentResponseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("GB2312");
        response.setContentType("text/html;charset=GB2312");
        //证明这个消息是由易宝带过来的
        String p1_MerId = PayConfig.getValue("p1_MerId");
        String r0_Cmd = request.getParameter("r0_Cmd");
        String r1_Code = request.getParameter("r1_Code");
        String r2_TrxId = request.getParameter("r2_TrxId");
        String r3_Amt = request.getParameter("r3_Amt");
        String r4_Cur = request.getParameter("r4_Cur");
        String r5_Pid = request.getParameter("r5_Pid");
        String r6_Order = request.getParameter("r6_Order");
        String r7_Uid = request.getParameter("r7_Uid");
        String r8_MP = request.getParameter("r8_MP");
        String r9_BType = request.getParameter("r9_BType");
        String hmac = request.getParameter("hmac");

        String keyValue = PayConfig.getValue("keyValue");
        /*
         *两个hmac(String hmac = request.getParameter("hmac");与下面传进来的hmac)进行比对，
         * 成功表示1.传过来的数据没做修改，2.的确是易宝带给我的
         */
        boolean b = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
        if (!b) {
            response.getWriter().write("交易签名已被修改！！！");
            return;
        }

        if ("1".equals(r1_Code)) {  //处理支付成功
            //把用户页面置为true
            if ("1".equals(r9_BType)) {
                response.getWriter().write("支付成功！！");
                return;
            }
            if ("2".equals(r9_BType)) {
                response.getWriter().write("success");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
