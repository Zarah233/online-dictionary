package src.information;

public class UserState {
	//�û���Ϊ�ձ�ʾû�е�¼
	
	private String username=null;
	
	public void setUsername(String username){
		this.username=username;
	}
	
	public String getUsername(){
		return username;
	}
	
	public boolean Logged(){
		if(username==null)return false;
		else return true;
	}
}
