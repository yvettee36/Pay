package cn.itcast.web;

import cn.itcast.utils.PayConfig;
import cn.itcast.utils.PaymentUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PayRequestServlet", urlPatterns = "/servlet/PayRequestServlet")
public class PayRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //支付请求参数说明，看易宝接口文档
        String p0_Cmd = "Buy";
        //p1_MerId是向易宝申请的编号
        String p1_MerId = PayConfig.getValue("p1_MerId");
        String p2_Order = request.getParameter("orderid");
        String p3_Amt = request.getParameter("money");
        String p4_Cur = "CNY";
        String p5_Pid = "";
        String p6_Pcat = "";
        String p7_Pdesc = "";
        //响应地址
        String p8_Url = PayConfig.getValue("responseURL");
        String p9_SAF = "";
        String pa_MP = "";
        String pd_FrpId = request.getParameter("pd_FrpId");
        String pr_NeedResponse = "1";
        String keyValue = PayConfig.getValue("keyValue");//秘钥
        //发数据和秘钥混在一起的md5码
        /*
         *hmac是一种秘密的密钥验证算法。
         *hmac提供的数据完整性和源身份验证完全取决于密钥分配的范围。
         *如果只有发起者和接收者知道hmac 密钥，
         *那么这就对两者间发送的数据提供了源身份验证和完整性保证。
         */
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

        //接下来将数据存进域里带给jsp，由jsp构建URL
        request.setAttribute("p0_Cmd", p0_Cmd);
        request.setAttribute("p1_MerId", p1_MerId);
        request.setAttribute("p2_Order", p2_Order);
        request.setAttribute("p3_Amt", p3_Amt);
        request.setAttribute("p4_Cur", p4_Cur);
        request.setAttribute("p5_Pid", p5_Pid);
        request.setAttribute("p6_Pcat", p6_Pcat);
        request.setAttribute("p7_Pdesc", p7_Pdesc);
        request.setAttribute("p8_Url", p8_Url);
        request.setAttribute("p9_SAF", p9_SAF);
        request.setAttribute("pa_MP", pa_MP);
        request.setAttribute("pd_FrpId", pd_FrpId);
        request.setAttribute("pr_NeedResponse", pr_NeedResponse);
        request.setAttribute("hmac", hmac);

        request.getRequestDispatcher("/payconfirm.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
