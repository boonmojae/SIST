package kr.spring.member.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	//회원관리 - 일반회원
	public void insertMember(MemberVO member);
	public MemberVO selectCheckMember(String id);//id전달돼서 정보체크
	public MemberVO selectMember(Long mem_num);//마이바티스는 (long mem_num)를 객체형태로 처리했는데 최근엔 l을 대문자,소문자 상관없음
	public void updateMember(MemberVO member);
	public void updatePassword(MemberVO member);
	public void deleteMember(Long mem_num);

	// 자동 로그인
	public void updateAu_id(String au_id, Long mem_num);
	public MemberVO selectAu_id(String au_id);
	public void deleteAu_id(Long mem_num);

	// 비밀번호 찾기
	public void updateRandomPassword(MemberVO member);


	// 프로필 이미지 업데이트
	public void updateProfile(MemberVO member);

	//채팅 회원 정보 검색
	public List<MemberVO> selectSearchMember(String id);
}
