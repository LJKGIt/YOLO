
public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i=0; i<12; i++){
		String tmp="dddddddddddddddddddhttp://localhost:8282/YOLO/images/fuploadImage/0000543500000.jpgddddddddd";
		int start=0;
		int end=65;
		if(tmp.contains("http")){
			//이미지가 없는 경우는 기본 이미지 제공.
			System.out.println(tmp.contains("http://localhost:8282"));
			start=tmp.indexOf("http");
			end=tmp.indexOf("jpg")+3;
		//해당 이미지 주소의 시작 index 뽑아옴.
		//해당 이미지 주소를 뽑아옴.
		
		}else {
			//이미지가 없는 글이 경우 - 기본 이미지 제공
			tmp="http://localhost:8282/YOLO/images/fuploadImage/00000000000000.jpg";
		} 
		//마지막 인덱스 뽑아냄.
		String tmp2=tmp.substring(start, end);
		System.out.println(tmp2);
		
		String path[]=new String[12];
		path[i]=tmp2;
		
		System.out.println(path[i]);
		}
	}

}
