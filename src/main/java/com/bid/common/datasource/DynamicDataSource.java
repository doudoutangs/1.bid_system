package com.bid.common.datasource;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源切换
 * @author sugar 2016-9-20 10:41:24
 * 继承{@link org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource}
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	public static String defaultDataSourceName;

	private static Map<Object, Object> dataSourcesMap;

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

    public static final ThreadLocal<String> dataSources = new ThreadLocal<String>(){
    	 @Override
         protected String initialValue() {
             return defaultDataSourceName;
         }
    };

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSouce();
    }

    /**
     * 初始化
     */
    @Override
    public void afterPropertiesSet() {
    	if (dataSourcesMap == null ) {
            throw new IllegalArgumentException("Property 'dataSourcesMap' is required");
        }
        if (defaultDataSourceName == null ) {
            throw new IllegalArgumentException("Property 'defaultDataSourceName' is required");
        }
        Object defalutDatasource = dataSourcesMap.get(defaultDataSourceName);
        if( defalutDatasource == null){
        	throw new IllegalArgumentException("Property '[dataSourcesMap[key:" + defaultDataSourceName + "]' is not found");
        }
        this.setDefaultTargetDataSource(defalutDatasource);
        this.setTargetDataSources(dataSourcesMap);
        super.afterPropertiesSet();
    }

    /**
	 * 设置数据源
	 * @param name 数据源名称
	 */
	public static void setDataSource(String name) {
		Object dataSource = dataSourcesMap.get(name);
		if (dataSource == null) {
			throw new IllegalArgumentException("Property '[dataSourcesMap[key:" + name + "]' is not found");
		}
		//LOGGER.debug("Thread:" + new Long(Thread.currentThread().getId()).toString() + "->Set DataSource:" + name);
		dataSources.set(name);
	}

	/**
	 * 获取数据源
	 * @return
	 */
	public static String getDataSouce() {
		//LOGGER.debug("==> DataSource：" +  dataSources.get());
		return dataSources.get();
	}

	/**
	 * 重置数据源
	 * @return
	 */
	public static void resetDataSource(){
		dataSources.remove();
	}

	public String getDefaultDataSourceName() {
		return defaultDataSourceName;
	}

	public void setDefaultDataSourceName(String defaultDataSourceName) {
		this.defaultDataSourceName = defaultDataSourceName;
	}

	public Map<Object, Object> getDataSourcesMap() {
		return dataSourcesMap;
	}

	public void setDataSourcesMap(Map<Object, Object> dataSourcesMap) {
		this.dataSourcesMap = dataSourcesMap;
	}

}
