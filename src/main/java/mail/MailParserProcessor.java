package mail;

import org.apache.camel.Exchange;

public class MailParserProcessor {

	public void process(Exchange exchange) throws Exception {
		String[] datas = exchange.getIn().getBody(String.class).split("	");

		StringBuilder content = new StringBuilder();

		content.append("<html>");
		content.append("<head>");
		content.append("</head>");
		content.append("<meta content=\"text/html; charset=utf-8\">\r\n");

		content.append("親愛的供應商合作夥伴，您好：<br>");
		content.append("感謝您對購物中心的支持！<br>");
		content.append("貴司近30天熱銷且已無庫存品項如下表，<br>");
		content.append("煩請針對下述商品確認庫存狀況並儘可能補齊貨量，以期共創銷售高峰<br>" );
		content.append("(若您於收到信件前已補量，請忽略即可，或可視狀況增加補貨量)<br><br>" );
		
		content.append("<table>\r\n" + "<tbody>\r\n" + " <tr height=\"20\" style=\"height:15pt\">\r\n"
				+ "  <td height=\"20\" width=\"116\" style=\"height:15pt;width:87pt;color:white;font-weight:700;text-align:center;vertical-align:middle;background:rgb(53,28,117);padding:0px;font-size:11pt;font-family:Calibri;border:none;white-space:nowrap\">TEAM</td>\r\n"
				+ "  <td width=\"76\" style=\"width:57pt;color:white;font-weight:700;vertical-align:middle;background:rgb(53,28,117);padding:0px;font-size:11pt;font-family:Calibri;border:none;white-space:nowrap\">子站編號</td>\r\n"
				+ "  <td width=\"69\" style=\"width:52pt;color:white;font-weight:700;vertical-align:middle;background:rgb(53,28,117);padding:0px;font-size:11pt;font-family:Calibri;border:none;white-space:nowrap\">子站</td>\r\n"
				+ "  <td width=\"69\" style=\"width:52pt;color:white;font-weight:700;text-align:center;vertical-align:middle;background:rgb(53,28,117);padding:0px;font-size:11pt;font-family:Calibri;border:none;white-space:nowrap\">商品類型</td>\r\n"
				+ "  <td width=\"100\" style=\"width:75pt;color:white;font-weight:700;vertical-align:middle;background:rgb(53,28,117);padding:0px;font-size:11pt;font-family:Calibri;border:none;white-space:nowrap\">商品編號</td>\r\n"
				+ "  <td width=\"423\" style=\"width:317pt;color:white;font-weight:700;vertical-align:middle;background:rgb(53,28,117);padding:0px;font-size:11pt;font-family:Calibri;border:none;white-space:nowrap\">商品名稱</td>\r\n"
				+ " </tr>\r\n" + " <tr height=\"20\" style=\"height:15.75pt\">\r\n"
				+ "  <td height=\"20\" style=\"height:15.75pt;padding:0px;font-size:11pt;font-family:Calibri;vertical-align:bottom;border:none;white-space:nowrap\">"+ datas[0] +"</td>\r\n"
				+ "  <td style=\"text-align:right;padding:0px;font-size:11pt;font-family:Calibri;vertical-align:bottom;border:none;white-space:nowrap\">"+ datas[1] +"</td>\r\n"
				+ "  <td style=\"padding:0px;font-size:11pt;font-family:Calibri;vertical-align:bottom;border:none;white-space:nowrap\">"+ datas[2] +"</td>\r\n"
				+ "  <td style=\"padding:0px;font-size:11pt;font-family:Calibri;vertical-align:bottom;border:none;white-space:nowrap\">"+ datas[3] +"</td>\r\n"
				+ "  <td style=\"text-align:right;padding:0px;font-size:11pt;font-family:Calibri;vertical-align:bottom;border:none;white-space:nowrap\">"+ datas[4] +"</td>\r\n"
				+ "  <td style=\"padding:0px;font-size:11pt;font-family:Calibri;vertical-align:bottom;border:none;white-space:nowrap\">"+ datas[5] +"</td>\r\n"
				+ " </tr>\r\n" + "</tbody>\r\n" + "</table>" + "<br><br>");

		content.append("補貨操作說明如下：<br>");
		content.append("	● 直出商品<br>");
		content.append("	功能路徑：商品可銷量管理＞可銷售數量調整 或 可銷售數量整批調整<br>");
		content.append("	操作說明：https://reurl.cc/44Y2ov <br>");
		content.append("	● 快倉商品<br>");
		content.append("	功能路徑：倉儲作業＞新增進倉單<br>");
		content.append("	操作說明：https://reurl.cc/Zr8Yyl <br>");
		content.append("<br>");
		content.append("感謝您的配合，敬祝業績長紅");

		content.append("</body>\r\n");
		content.append("</html>");

		exchange.getIn().setHeader("to", datas[8]+";" + datas[9]);
		exchange.getIn().setHeader("cc", datas[10] + "@XXX.com");
		exchange.getIn().setHeader("from", "XXXX@XXXX.com");
		exchange.getIn().setHeader("subject", "Yahoo! 購物中心熱銷品缺貨補貨提醒_" + datas[6] + " " + datas[7]);
		exchange.getIn().setHeader(Exchange.CONTENT_TYPE,"text/html");
		exchange.getIn().setBody(content.toString());
	}
}
