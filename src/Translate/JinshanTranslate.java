package src.Translate;

/*
 * <script type="text/javascript" src="http://open.iciba.com/ds_open.php?id=53617&name=ZrqandZfy&auth=CA291F937780CC9AB550DC7626F14A3E" charset="utf-8"></script>
 *�ο���http://nddjava.iteye.com/blog/1849983
*/ 

import java.io.*;
import java.net.*;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class JinshanTranslate {
	private String url = "http://dict-co.iciba.com/api/dictionary.php";
	private String key = "DFAD3B39DE20B8E63E050779B91F9F0D";
	
	Translation t=new Translation();
	
	private String sendGet(String str) {
		t.word=str;
		
	  // �����UTF-8
		try {
			str = URLEncoder.encode(str, "utf-8");
		} 
		catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		}
	  
		String param = "w="+str+"&key="+key;
		BufferedReader reply = null;
		try {
		//http://fanyi.youdao.com/openapi.do?keyfrom=ZrqandZfy&key=1740122831&type=data&doctype=<doctype>&version=1.1&q=Ҫ������ı�
		  String urlName = url + "?" + param;
		  URL realUrl = new URL(urlName);
	   // �򿪺�URL֮�������
		  URLConnection conn = realUrl.openConnection();
		  conn.setConnectTimeout(10000);
		  
		  reply = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));//Լ���������ı���
		  
		  String temp ;
		  String result = "";
		  while((temp = reply.readLine()) != null){
			result = result + temp.trim();
		  }
		  
		  reply.close();
		  //System.out.println(result);
		  return result;
		}
		
		catch(IOException e){
			e.printStackTrace();
		}
		
		return null;
	}
    
	public String xmltostring(String protocolXML) {   
        SAXBuilder builder=new SAXBuilder(false);   
        try {   
        	String message="";
        	Document doc = builder.build(new InputSource(new StringReader(protocolXML)));   
            Element eles = doc.getRootElement(); // �õ���Ԫ��   
             //System.out.println("���ڵ�"+eles.getName());   
               
             @SuppressWarnings("unchecked")
			List<Element> list = eles.getChildren(); // �õ�Ԫ�صļ���   
  
            int flag=0;
            if( list!=null){
            	boolean en=true;
                for (int i = 0; i < list.size(); i++) {   
                     Element book = list.get(i);   
                     if(book.getName()=="query"||book.getName()=="key")
                    	 flag=1;
                     if(flag==1){
                    	 if(book.getName().equals("ps")){
                    		 if(en){
                    			 t.enPhonetic=book.getValue();
                    			 en=false;
                    		 }
                    		 else {
                    			 t.amPhonetic=book.getValue();
                    			 en=true;
                    		 }
                    	 }
                    	 
                    	 if(book.getName().equals("pos")){
                    		 Definition def=new Definition();
                    		 def.characteristic=book.getValue();
                    		 
                    		 i++;
                    		 book=list.get(i);
                    		 def.definitions=book.getValue();
                    		 
                    		 t.trans.add(def);
                    	 }
                    	 
                    	 if(book.getName().equals("sent")){
                    		 t.sen.add(book.getValue());
                    	 }
                     }
                    	 message += book.getName() + " : "  + book.getValue()+'\n';
                 }   
            }   
            return message;
        } 
        catch (Exception e) {   
             e.printStackTrace();   
        }   
        return null;
     }   
	
	public Translation Translate(String query){
		xmltostring(sendGet(query));
		return t;
	} 

}
