package dengtao.Model.dao.gasField;

import java.util.List;
import java.util.Map;

import dengtao.Model.pojo.Gasfield;

public interface GasfieldMapper {
	public List<Gasfield> getAllGasfield();
	public int addGasfield(Map<String, Object> info);
	public int modifyGasField(Map<String, Object> info);
	public int deleteGasfield(Map<String, Object> info);
	public List<Gasfield> getAllGasfieldByArea(Map<String, Object> info);
	public List<Gasfield> getAllGasfieldByType(Map<String, Object> info);
	public List<Gasfield> getAllGasfieldByDepthType(Map<String, Object> info);
}
