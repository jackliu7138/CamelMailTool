package mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

public class MailPattternProcessor {

	private SpringTemplateEngine templateEngine;
	private Map<String, List<MailModel>> map = new HashMap<>();

	public void clean() {
		map.clear();
	}

	public void parser(Exchange exchange) throws Exception {
		String[] datas = exchange.getIn().getBody(String.class).split("	");
		MailModel model = new MailModel();
		model.setA(datas[0]);
		model.setB(datas[1]);
		model.setC(datas[2]);
		model.setD(datas[3]);
		model.setE(datas[4]);
		model.setF(datas[5]);
		model.setG(datas[6]);
		model.setH(datas[7]);
		model.setI(datas[8]);
		model.setJ(datas[9]);
		model.setK(datas[10]);
		if (map.get(datas[6]) != null) {
			map.get(datas[6]).add(model);
		} else {
			List<MailModel> list = new ArrayList<>();
			list.add(model);
			map.put(datas[6], list);
		}
	}

	public void change(Exchange exchange) {
		List<List<MailModel>> list = new ArrayList<>(map.values());
		exchange.getIn().setBody(list);
	}

	@SuppressWarnings("unchecked")
	public void process(Exchange exchange) throws Exception {
		List<MailModel> list = exchange.getIn().getBody(ArrayList.class);
		Context ctx = new Context();
		ctx.setVariable("list", list);

		String html = templateEngine.process("mail", ctx);

		exchange.getIn().setHeader("to", list.get(0).getI() + ";" + list.get(0).getJ());
		exchange.getIn().setHeader("cc", list.get(0).getK() + "@XXXX.com");
		exchange.getIn().setHeader("from", "XXXX@XXXX.com");
		exchange.getIn().setHeader("subject", "Yahoo! 購物中心熱銷品缺貨補貨提醒_" + list.get(0).getG() + " " + list.get(0).getH());
		exchange.getIn().setHeader(Exchange.CONTENT_TYPE,"text/html");
		exchange.getIn().setBody(html);

		System.out.println(html);
	}

	public SpringTemplateEngine getTemplateEngine() {
		return templateEngine;
	}

	public void setTemplateEngine(SpringTemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

}
