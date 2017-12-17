import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			
			 int thumbnail_width = 316;
	            //썸네일 세로사이즈
	            int thumbnail_height = 130;
	            //원본이미지파일의 경로+파일명
	            File origin_file_name = new File("http://localhost:8282/YOLO/images/fuploadImage/20170804173316.jpg");
	            //생성할 썸네일파일의 경로+썸네일파일명
	            File thumb_file_name = new File("http://localhost:8282/YOLO/images/fuploadImage/test.jpg");
	 
	            BufferedImage buffer_original_image = ImageIO.read(origin_file_name);
	            BufferedImage buffer_thumbnail_image = new BufferedImage(thumbnail_width, thumbnail_height, BufferedImage.TYPE_3BYTE_BGR);
	            Graphics2D graphic = buffer_thumbnail_image.createGraphics();
	            graphic.drawImage(buffer_original_image, 0, 0, thumbnail_width, thumbnail_height, null);
	            ImageIO.write(buffer_thumbnail_image, "jpg", thumb_file_name);
			
		} catch (Exception e) {
			System.out.println("실패");
		}
		
	}

}
