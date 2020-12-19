package dengtao.Model.service.gasField;

import java.util.Map;

public interface GasFiledservice {
	public String getAllGasfiled();
	public String addGasField(Map<String, Object> info);
	public String modifyGasField(Map<String, Object> info);
	public String deleteGasfield(Map<String, Object> info);
	public String getAllGasfieldByArea(Map<String, Object> info);
	public String getChartInitData();
	public String getgetAllGasfieldPipe(Map<String, Object> info);
}
