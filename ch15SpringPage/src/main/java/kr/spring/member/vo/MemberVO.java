package kr.spring.member.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"photo"})
public class MemberVO {
	private long mem_num;
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String id;
	private String nick_name;
	private int auth;
	private String auto; //자동로그인 처리에 필요,컬럼 없음
	private String au_id; //자동로그인 처리에 필요
	@NotBlank
	private String name;
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String passwd;
	@NotBlank
	private String phone;
	@Email
	@NotBlank
	private String email;
	@Size(min=5,max=5)
	private String zipcode;
	@NotBlank
	private String address1;
	@NotBlank
	private String address2;
	private byte[] photo; //마이바티스에서 바이너리 사진 blob 지원해줌 - byte[]사용해야됨/처음에 들어오는건 multipart로 들어와서 byte로 변환해줘야됨
	private String photo_name;
	private Date reg_date;
	private Date modify_date;
	//비밀번호 변경시 현재 비밀번호를 저장하는 용도로 사용
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String now_passwd; //컬럼 없음
	
	// 비밀번호 변경에만 조건 체크
	@Pattern(regexp="^[A-Za-z0-9]+$")
	private String captcha_chars;
	
	
	//비밀번호 일치 여부 체크
	public boolean ischeckedPassword(String userPasswd) {
		if(auth > 1 && passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	//이미지 BOLB 처리
	//(주의)폼에서 파일업로드 파라미터네임은 반드시 upload로 지정해야 함//upload라는 이름으로 찾기때문에 
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[]
		setPhoto(upload.getBytes());//lombok이 있어서 annotation넣으면 set,get,toString 명시 안해도됨
		//파일 이름
		setPhoto_name(upload.getOriginalFilename());
	}
	
}

