package Info;
import java.util.*;
public class List_Song {
	private int song_id;
	private String song_name;
	private String songer_id;
	private String songer_name;
	private int song_pop;
	private int song_time;
	private String song_pic;
	private String song_cd;
	private int mv_id;
	public String toString() {
		return "������("+song_id+" | "+song_name+")"+"���֣�("+songer_id+" | "+songer_name+") �ȶ� : "
				+song_pop+" ר����"+song_cd+" ר����Ƭ��"+song_pic+" ʱ���� "+song_time+" MV: "+mv_id;
	}
}
