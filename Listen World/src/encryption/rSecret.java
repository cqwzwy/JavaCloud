package encryption;
import javax.script.*;
import static java.lang.System.*;
import java.security.*;
import java.util.Scanner;

import javax.crypto.*;
import org.apache.commons.codec.binary.*;
import org.apache.commons.codec.digest.Crypt;

import contro.commentsClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
public class rSecret{
	private static boolean bl=false;
	private static String params;
	private static String encSecKey;
	private static String ida;
	private static String li;
	private static int of;
	private static String u;
	private static String tot;
	private static int types=0;
	private static ScriptEngineManager manager=new ScriptEngineManager();
	public rSecret(String name,int type,int n) {
		if(type==1) {
			ida=name;
			u="https://music.163.com/weapi/v1/resource/comments/R_SO_4_"+ida+"?csrf_token=";
			of=n;
			li="20";
			types=type;
			tot="true";
		}else if(type==2) {
			u="https://music.163.com/weapi/cloudsearch/get/web?csrf_token=";
			ida=name;
			types=type;
			bl=true;
			of=n;
			li="30";
			tot="true";
		}else if(type==3) {
			u="https://music.163.com/weapi/song/enhance/player/url/v1?csrf_token=";
			ida=name;
			types=type;
		}
		Ent();
	}
	public void Ent(){
		ScriptEngine jseng=manager.getEngineByName("javascript");
		if(jseng!=null) {
			//out.println("wu");
		}
		if(!(jseng instanceof Invocable)) {
			out.println("NO SUPPORT INVOCABLE");
		}
		Bindings bind=jseng.createBindings();
		bind.put("factor", 20);
		jseng.setBindings(bind, ScriptContext.ENGINE_SCOPE);
		if(of==0) {
			tot="true";
		}else {
			tot="false";
		}
		try {
			jseng.eval(new FileReader("D:/ListenWorld/js/encode.js"));
			if((jseng instanceof Invocable)) {
				Invocable inv=(Invocable)jseng;
				try {
					
					String o=""+of;
					//out.print(ida+" "+of+" "+tot+"  "+o+"  ");
					if(types==1) {
						params=(String) inv.invokeFunction("paramssend",ida,"20",o,tot);
						encSecKey=(String)inv.invokeFunction("encSecKeysend",ida,"20",o,tot);
						new Thread(new commentsClient(u,params,encSecKey,1)).start();
					}else if(types==2) {
						params=(String) inv.invokeFunction("Song_Listparamssend",ida,"30",o,tot);
						encSecKey=(String)inv.invokeFunction("Song_ListencSecKeysend",ida,"30",o,tot);
						out.println("params : "+params);
						out.println("enc : "+encSecKey);
						new Thread(new commentsClient(u,params,encSecKey,2)).start();
					}else if(types==3) {
						params=(String) inv.invokeFunction("down_paramssend",ida);
						encSecKey=(String)inv.invokeFunction("down_encSecKeysend",ida);
						out.println("params : "+params);
						out.println("enc : "+encSecKey);
						new Thread(new commentsClient(u,params,encSecKey,3)).start();
					}
					
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		}
}
