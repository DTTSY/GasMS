import java.util.HashMap;
import java.util.Map;

import dengtao.Model.service.gasField.GasFiledservice;
import dengtao.Model.service.gasField.GasFiledserviceImpl;

public class test {
public static void main(String[] args) {
	GasFiledservice gasFiledservice=new GasFiledserviceImpl();
	Map<String, Object> map=new HashMap<>();
	map.put("type", "常规气");
	System.out.println(gasFiledservice.getgetAllGasfieldPipe(map));
}
}

