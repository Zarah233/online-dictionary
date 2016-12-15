package src.Translate;
/*
�ο�
http://blog.csdn.net/daixinmei/article/details/10210731
http://wangbaiyuan.cn/android-java-parsing-api-youdao-translation-json-data.html  //�е�api json����
*/ 

import java.io.*;
import java.net.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class YoudaoTranslate {
	private String url = "http://fanyi.youdao.com/openapi.do";
	public Translation t=new Translation();
	 
	private String sendGet(String str) {
		t.word=str;
		
	  // �����UTF-8
		try {
			str = URLEncoder.encode(str, "utf-8");
		} 
		catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		}
	  
		String param ="keyfrom=ZrqandZfy&key=1740122831&type=data&doctype=json&version=1.1&q="+str ;
		BufferedReader reply = null;
		try {
		//http://fanyi.youdao.com/openapi.do?keyfrom=ZrqandZfy&key=1740122831&type=data&doctype=<doctype>&version=1.1&q=Ҫ������ı�
		  String urlName = url + "?" + param;
		  URL realUrl = new URL(urlName);
	   // �򿪺�URL֮�������
		  URLConnection conn = realUrl.openConnection();
		  conn.setConnectTimeout(3000);
          conn.connect();
		  reply = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));//Լ���������ı���
		  
		  String result = reply.readLine();
		  reply.close();
		  
		  return result;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String JsonToString(String jstring) {  
        try {  
        	String message="";
            JSONObject obj = new JSONObject(jstring);  
            String errorcode = obj.getString("errorCode");
            
            if(errorcode.equals("20")){
            	message = "Ҫ������ı�����";
            }
            else if(errorcode.equals("30")){
            	message = "�޷�������Ч�ķ���";
            }
            else if(errorcode.equals("40")){
            	message = "��֧�ֵ���������";
            }
            else if(errorcode.equals("50")){
            	message = "��Ч��key";
            }
            else if(errorcode.equals("60")){
            	message = "�޴ʵ��������ڻ�ȡ�ʵ�����Ч";
            }
            else if(errorcode.equals("0")){
            	String query = obj.getString("query");
            	JSONArray translation = obj.has("translation") ? obj.getJSONArray("translation") : null;
            	JSONObject basic = obj.has("basic") ? obj.getJSONObject("basic") : null;
            	JSONArray web = obj.has("web") ? obj.getJSONArray("web") : null;
            	
                JSONArray explains=null;
                if(basic!=null){
                	t.phonetic=basic.has("phonetic")? basic.getString("phonetic"):null;
                	t.enPhonetic=basic.has("uk-phonetic")? basic.getString("uk-phonetic"):null;
                	t.amPhonetic=basic.has("us-phonetic")? basic.getString("us-phonetic"):null;
                    explains=basic.has("explains")? basic.getJSONArray("explains"):null;
                }
                
                if(explains!=null){
                    for(int i=0;i<explains.length();i++){
                    	Definition def=new Definition();
                    	String temp=explains.getString(i);
                    	
                    	int j=0;
                    	for(;j<temp.length();j++){
                    		if(temp.charAt(j)=='.')break;
                    	}
                    	
                    	if(j==temp.length()){
                    		def.characteristic="";
                        	def.definitions=temp;
                    	}
                    	else{
                    		def.characteristic=temp.substring(0, j+1);
                    		def.definitions=temp.substring(j+1,temp.length());
                    	}
                    	
                    	t.trans.add(def);
                    }
                }
                
                if (web!=null) {  
                	JSONArray webString = new JSONArray("[" + web+ "]");  
                    Definition def=new Definition();
                    def.characteristic= "��������";  
                    def.definitions="";
                    JSONArray webArray = webString.getJSONArray(0);  
                    int count = 0;  
                    while (!webArray.isNull(count)) {  

                        if (webArray.getJSONObject(count)  
                                .has("key")) {  
                            String key = webArray.getJSONObject(  
                                    count).getString("key");  
                            def.definitions += "\n(" + (count + 1) + ")"  
                                    + key+"\n";  
                        }  
                        if (webArray.getJSONObject(count).has(  
                                "value")) {  
                            String value = webArray.getJSONObject(  
                                    count).getString("value");  
                            def.definitions += "\t" + value;  
                        }
                        count++; 
                    } 
                    t.trans.add(def);
                }

            }
            return message;  
        } 
        catch (JSONException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return null;  
    }   
	
	public Translation Translation(String query){
		JsonToString(sendGet(query));
		return t;
	}

}
