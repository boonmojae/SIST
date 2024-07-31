package kr.spring.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

@Mapper//이걸 명시해야 컨테이너에 넣을수 있음
public interface MemberMapper {
	//회원관리 - 일반회원
	@Select("SELECT spmember_seq.nextval FROM dual")//자바빈에 담아서 13,14에 공급
	public Long selectMem_num();//시퀀스 만드는 부분
	@Insert("INSERT INTO spmember (mem_num,id,nick_name) VALUES (#{mem_num},#{id},#{nick_name})")//auth값은 자동으로 2
	public void insertMember(MemberVO member);
	public void insertMember_detail(MemberVO member);
	public MemberVO selectCheckMember(String id);//id전달돼서 정보체크
	@Select("SELECT * FROM spmember JOIN spmember_detail USING(mem_num) WHERE mem_num=#{mem_num}")
	public MemberVO selectMember(Long mem_num);//마이바티스는 (long mem_num)를 객체형태로 처리했는데 최근엔 l을 대문자,소문자 상관없음
	@Update("UPDATE spmember SET nick_name=#{nick_name} WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);
	public void updateMember_detail(MemberVO member);
	
	@Update("UPDATE spmember_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);
	
	public void deleteMember(Long mem_num);
	public void deleteMember_detail(Long mem_num);//long을 Long을 객체형태로 명시하는게 전통적,객체형태로 인식->처리될때는 내부적으로 기본자료형,참조자료형으로 자동 변환
	
	// 자동 로그인
	@Update("UPDATE spmember_detail SET au_id=#{au_id} WHERE mem_num=#{mem_num}")
	public void updateAu_id(String au_id, Long mem_num);
	@Select("SELECT m.mem_num,m.id,m.auth,d.au_id,d.passwd,m.nick_name,d.email FROM spmember m JOIN spmember_detail d ON m.mem_num=d.mem_num WHERE d.au_id=#{au_id}")
	public MemberVO selectAu_id(String au_id);
	@Update("UPDATE spmember_detail SET au_id='' WHERE mem_num=#{mem_num}")
	public void deleteAu_id(Long mem_num);
	
	// 비밀번호 찾기
	@Update("UPDATE spmember_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void updateRandomPassword(MemberVO member);
	
	// 프로필 이미지 업데이트
	@Update("UPDATE spmember_detail SET photo=#{photo}, photo_name=#{photo_name} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);
	
	//채팅 회원 정보 검색
	@Select("SELECT mem_num,id,nick_name FROM spmember WHERE auth >=2 AND id LIKE '%' || #{id} || '%'")
	public List<MemberVO> selectSearchMember(String id);
	
	
	
}
