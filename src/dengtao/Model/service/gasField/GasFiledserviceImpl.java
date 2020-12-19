package dengtao.Model.service.gasField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import dengtao.Model.dao.gasField.GasfieldMapper;
import dengtao.Model.dao.util.MybatisUtils;
import dengtao.Model.pojo.Gasfield;

public class GasFiledserviceImpl implements GasFiledservice{
	private SqlSession sqlSession;
	private GasfieldMapper gasfieldMapper;
	
	@Override
	public String getAllGasfiled() {
		// TODO Auto-generated method stub
		List<Gasfield> gasfields = null;
		try {
			sqlSession = MybatisUtils.getSqlSession();
			gasfieldMapper = sqlSession.getMapper(GasfieldMapper.class);
			gasfields=gasfieldMapper.getAllGasfield();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			// TODO: handle finally clause
			if(sqlSession!=null)
				sqlSession.close();
		}
		return getGasFiledListToJSON(gasfields);
	}
	
	private String getGasFiledListToJSON(List<Gasfield> gasfields) {
		Map<String, String> areasMap = new HashMap<>();
		
		for (Gasfield gasfield : gasfields) {
			areasMap.putIfAbsent(gasfield.getArea(), gasfield.getArea());
		}
		JSONObject rs = new JSONObject(true);
		JSONObject col = new JSONObject(true);
		col.put("气田编号", "气田编号");
		col.put("气田名", "气田名");
		col.put("气区", "气区");
		col.put("气藏类型", " 气藏类型");
		col.put("埋深类型", " 埋深类型");
		col.put("储量", "储量");
		rs.put("col", col);
		rs.put("list", gasfields);
		rs.put("options", getOptions(areasMap));
		return JSON.toJSONString(rs, SerializerFeature.WriteMapNullValue);
	}

	@Override
	public String addGasField(Map<String, Object> info) {
		// TODO Auto-generated method stub
		JSONObject okJsonObject=new JSONObject();
		try {
			sqlSession = MybatisUtils.getSqlSession();
			gasfieldMapper = sqlSession.getMapper(GasfieldMapper.class);
			if (gasfieldMapper.addGasfield(info)>0) {
				okJsonObject.put("ok", 1);
				sqlSession.commit();
			}else {
				okJsonObject.put("ok", 0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			okJsonObject.put("ok", 0);
		}finally {
			// TODO: handle finally clause
			if(sqlSession!=null)
				sqlSession.close();
		}
		return okJsonObject.toJSONString();
	}

	@Override
	public String modifyGasField(Map<String, Object> info) {
		// TODO Auto-generated method stub
		JSONObject okJsonObject=new JSONObject();
		try {
			sqlSession = MybatisUtils.getSqlSession();
			gasfieldMapper = sqlSession.getMapper(GasfieldMapper.class);
			if (gasfieldMapper.modifyGasField(info)>0) {
				okJsonObject.put("ok", 1);
				sqlSession.commit();
			}else {
				okJsonObject.put("ok", 0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			okJsonObject.put("ok", 0);
		}finally {
			// TODO: handle finally clause
			if(sqlSession!=null)
				sqlSession.close();
		}
		return okJsonObject.toJSONString();
	}

	@Override
	public String deleteGasfield(Map<String, Object> info) {
		// TODO Auto-generated method stub
		JSONObject okJsonObject=new JSONObject();
		try {
			sqlSession = MybatisUtils.getSqlSession();
			gasfieldMapper = sqlSession.getMapper(GasfieldMapper.class);
			if (gasfieldMapper.deleteGasfield(info)>0) {
				okJsonObject.put("ok", 1);
				sqlSession.commit();
			}else {
				okJsonObject.put("ok", 0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			okJsonObject.put("ok", 0);
		}finally {
			// TODO: handle finally clause
			if(sqlSession!=null)
				sqlSession.close();
		}
		return okJsonObject.toJSONString();
	}

	@Override
	public String getAllGasfieldByArea(Map<String, Object> info) {
		// TODO Auto-generated method stub
		List<Gasfield> gasfields = null;
		try {
			sqlSession = MybatisUtils.getSqlSession();
			gasfieldMapper = sqlSession.getMapper(GasfieldMapper.class);
			gasfields=gasfieldMapper.getAllGasfieldByArea(info);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			// TODO: handle finally clause
			sqlSession.close();
		}
		return getGasFiledListToJSON(gasfields);
	}
	
	private List<JSONObject> getOptions(Map<String, String> optionsMap){
		List<JSONObject> options=new ArrayList<>();
		JSONObject optionJsonObject = null;
		Collection cl = optionsMap.values();
		Iterator itr = cl.iterator();
		while (itr.hasNext()) {
			optionJsonObject=new JSONObject();
			optionJsonObject.put("value", itr.next());
			optionJsonObject.put("label", optionJsonObject.get("value"));
			options.add(optionJsonObject);
	     }
		return options;
	}

	@Override
	public String getChartInitData() {
		// TODO Auto-generated method stub
		List<Gasfield> gasfields = null;
		JSONObject rs = new JSONObject();
		Map<String, String> typesMap = new HashMap<>();
		Map<String, String> depthTypesMap = new HashMap<>();
		try {
			sqlSession = MybatisUtils.getSqlSession();
			gasfieldMapper = sqlSession.getMapper(GasfieldMapper.class);
			gasfields = gasfieldMapper.getAllGasfield();
			
			for (Gasfield g : gasfields) {
				typesMap.putIfAbsent(g.getType(), g.getType());
				depthTypesMap.putIfAbsent(g.getDepthType(), g.getDepthType());
			}
			rs.put("typeOptions", getOptions(typesMap));
			rs.put("depthTypeOptions", getOptions(depthTypesMap));
			rs.put("ok", 1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rs.put("ok", 0);
		}finally {
			// TODO: handle finally clause
			if (sqlSession!=null) {
				sqlSession.close();
			}
		}
		return JSON.toJSONString(rs, SerializerFeature.WriteMapNullValue);
	}

	@Override
	public String getgetAllGasfieldPipe(Map<String, Object> info) {
		// TODO Auto-generated method stub
		List<Gasfield> gasfields = null;
		Map<String, Object> dataMap =new HashMap<>();
		JSONObject rs = new JSONObject();
		try {
			sqlSession = MybatisUtils.getSqlSession();
			gasfieldMapper = sqlSession.getMapper(GasfieldMapper.class);
			if(info.get("type")!=null)
				gasfields = gasfieldMapper.getAllGasfieldByType(info);
			else
				gasfields = gasfieldMapper.getAllGasfieldByDepthType(info);
			dataMap = getPipeDate(gasfields);
			rs.put("total", dataMap.get("total"));
			rs.put("pieData", dataMap.get("pipeData"));
			rs.put("ok", 1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rs.put("ok", 0);
		}finally {
			// TODO: handle finally clause
			if (sqlSession!=null) {
				sqlSession.close();
			}
		}
		return JSON.toJSONString(rs, SerializerFeature.WriteMapNullValue);
	}
	
	private Map<String, Object> getPipeDate(List<Gasfield> gasfields){
		Map<String, Object> dataMap =new HashMap<>();
		float total=0;
		JSONObject pieJsonObject=null;
		List<JSONObject> pipeDataJsonObjects = new ArrayList<>();
		 for (Gasfield gasfield : gasfields) {
			 pieJsonObject = new JSONObject();
			 pieJsonObject.put("name", gasfield.getName());
			 pieJsonObject.put("value", gasfield.getReserves());
			 pipeDataJsonObjects.add(pieJsonObject);
			 total += gasfield.getReserves();
		}
		 dataMap.put("pipeData", pipeDataJsonObjects);
		 dataMap.put("total", total);
		return dataMap;
	}
}
