package cn.com.webxml;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.4
 * 2015-04-20T12:35:33.172+08:00
 * Generated source version: 3.0.4
 * 
 */
@WebService(targetNamespace = "http://WebXml.com.cn/", name = "WeatherWSHttpPost")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface WeatherWSHttpPost {

    /**
     * <br /><h3>获得支持的城市/地区名称和与之对应的ID</h3><p>输入参数：theRegionCode = 省市、国家ID或名称，返回数据：DataSet。</p><br />
     */
    @WebResult(name = "DataSet", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public DataSet getSupportCityDataset(
        @WebParam(partName = "theRegionCode", name = "theRegionCode", targetNamespace = "http://WebXml.com.cn/")
        java.lang.String theRegionCode
    );

    /**
     * <br /><h3>获得天气预报数据</h3><p>输入参数：城市/地区ID或名称，返回数据：一维字符串数组。</p><br />
     */
    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public ArrayOfString getWeather(
        @WebParam(partName = "theCityCode", name = "theCityCode", targetNamespace = "http://WebXml.com.cn/")
        java.lang.String theCityCode,
        @WebParam(partName = "theUserID", name = "theUserID", targetNamespace = "http://WebXml.com.cn/")
        java.lang.String theUserID
    );

    /**
     * <br /><h3>获得支持的城市/地区名称和与之对应的ID</h3><p>输入参数：theRegionCode = 省市、国家ID或名称，返回数据：一维字符串数组。</p><br />
     */
    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public ArrayOfString getSupportCityString(
        @WebParam(partName = "theRegionCode", name = "theRegionCode", targetNamespace = "http://WebXml.com.cn/")
        java.lang.String theRegionCode
    );

    /**
     * <br /><h3>获得中国省份、直辖市、地区和与之对应的ID</h3><p>输入参数：无，返回数据：一维字符串数组。</p><br />
     */
    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public ArrayOfString getRegionProvince();

    /**
     * <br /><h3>获得国外国家名称和与之对应的ID</h3><p>输入参数：无，返回数据：一维字符串数组。</p><br />
     */
    @WebResult(name = "ArrayOfString", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public ArrayOfString getRegionCountry();

    /**
     * <br /><h3>获得中国省份、直辖市、地区；国家名称（国外）和与之对应的ID</h3><p>输入参数：无，返回数据：DataSet。</p><br />
     */
    @WebResult(name = "DataSet", targetNamespace = "http://WebXml.com.cn/", partName = "Body")
    @WebMethod
    public DataSet getRegionDataset();
}
